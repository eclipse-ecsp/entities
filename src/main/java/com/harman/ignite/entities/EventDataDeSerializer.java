/*
 * *******************************************************************************
 *
 *  Copyright (c) 2023-24 Harman International
 *
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *
 *  you may not use this file except in compliance with the License.
 *
 *  You may obtain a copy of the License at
 *
 *
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *       
 *
 *  Unless required by applicable law or agreed to in writing, software
 *
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 *  See the License for the specific language governing permissions and
 *
 *  limitations under the License.
 *
 *
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  *******************************************************************************
 */

package com.harman.ignite.entities;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.harman.ignite.annotations.EventMapping;
import com.harman.ignite.domain.DataDeserializationException;
import com.harman.ignite.domain.EventAttribute;
import com.harman.ignite.utils.Constants;
import com.harman.ignite.utils.NumericConstants;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Custom JacksonDeserializer class for EventData class.
 */
public class EventDataDeSerializer extends StdDeserializer<EventData> {
    private static final long serialVersionUID = -7734568057530911008L;
    private static final Logger LOGGER = LoggerFactory.getLogger(EventDataDeSerializer.class);
    private static volatile Set<Class<?>> eventMappingClasses;
    private Map<String, Class<?>> eventDataMapping = new HashMap<>();

    public EventDataDeSerializer() {
        super(EventData.class);
        init();
    }

    private void init() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Scanning base package com.harman.ignite.domain."
                    + "Fetching all the classes which are annotated with @EventId.");
        }

        if (eventMappingClasses == null) {
            // Loading once only because Reflections seems to have issues when
            // used across threads.
            // https://github.com/ronmamo/reflections/issues/81
            // Also we don't want to scan the same classes again and again and
            // again 24*n times when running in a stream processor
            synchronized (EventDataDeSerializer.class) {
                Reflections reflections = new Reflections("com.harman.ignite.domain", "com.harman.ignite.events");
                eventMappingClasses = reflections.getTypesAnnotatedWith(EventMapping.class);
            }
        }
        for (Class<?> cl : eventMappingClasses) {
            EventMapping eventId = cl.getAnnotation(EventMapping.class);
            String id = eventId.id();
            String version = eventId.version().toString();

            if (StringUtils.isBlank(id) || StringUtils.isBlank(version)) {
                throw new InvalidDataFormatException(String.format("%s has empty id or version.", cl));
            }
            String key = id + Constants.HYPHEN + version;
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Key={}, value={}", key, cl);
            }
            this.eventDataMapping.put(key, cl);
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EventID to class mapping is completed.");
        }
    }

    /**
     * Method to deserialize EventData.
     *
     * @param jp : JsonParser
     * @param ctxt : DeserializationContext
     * @return EventData
     */
    public EventData deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {

        // Get the original json string so that we can get eventid and
        // version
        JsonLocation startLocation = jp.getCurrentLocation();

        // This helps in identifying the hierarchy
        // For normal events it would be /Data for nested events it would be
        // /failedEvent/Data.
        JsonPointer jsonPointer = jp.getParsingContext().pathAsPointer();
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode node = null;
        Object sourceRef = startLocation.contentReference().getRawContent();
        String originalString = null;

        if (sourceRef instanceof StringReader) {
            originalString = getOriginalStringFromSource(sourceRef);
        } else if (sourceRef instanceof String s)  {
            originalString = s;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Deserializing the json string:{}", originalString);
        }
        node = mapper.readTree(originalString);

        Class<EventData> clazz = null;

        /*
         * When we receive single json then we we need the original string to
         * retrieve the eventid and version.
         *
         * Incase if its array of json then we dont want the eventid and
         * version, we are directly mapping the Data to RawEventData
         */
        if (node instanceof ObjectNode) {
            clazz = processObjectNode(originalString, mapper, jsonPointer);
        }
        if (null == clazz) {
            // Add Generic ignite EventData class to convert the list of json
            clazz = addGenericEventDataClass();
        }
        ObjectNode actualEventDataNode = mapper.readTree(jp);
        EventData returnVal = mapper.readValue(actualEventDataNode.toString(), clazz);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Returning the eventData:{}", returnVal);
        }
        return returnVal;
    }

    @SuppressWarnings("checkstyle:MatchXpath")
    private String getOriginalStringFromSource(Object sourceRef) {
        /*
         * RTC: 166963 Introduced reflection here as of now to fetch the str
         * field value (actual event json string) from the StringReader.
         * RCA: the previous approach of reading from sourceRef and closing
         * the reader was resetting and closing the reader present inside
         * the jp parser. Hence stream closed exception was observed .
         *
         * Note: This flow is invoked only in the case where event json
         * string is larger than 32 kb chars.
         *
         * TBD: Alternative of reflection approach to be determined and
         * implemented.
         */

        String originalString;
        try {
            var fieldStr = sourceRef.getClass().getDeclaredField(Constants.STRING_READER_FIELD_STR);
            fieldStr.setAccessible(true);
            originalString = (String) fieldStr.get(sourceRef);
        } catch (Exception e) {
            throw new DataDeserializationException(Constants.DESERIALIZATION_EVENT_ERROR, e);
        }
        return originalString;
    }

    private Class<EventData> addGenericEventDataClass() {
        Class<EventData> clazz = null;
        try {
            clazz = (Class<EventData>) Class.forName(Constants.GENERIC_EVT_DATA_VALUE);
        } catch (ClassNotFoundException e) {
            throw new DataDeserializationException("Generic EventData class not "
                   + "found in the class path, message is", e);
        }
        return clazz;
    }
    
    private Class<EventData> processObjectNode(String originalString, ObjectMapper mapper, JsonPointer jsonPointer)
            throws  JsonProcessingException {
        ObjectNode origianlJsonNode = (ObjectNode) mapper.readTree(originalString);

        JsonNode idNode = null;
        JsonNode versionNode = null;
        boolean nestedIgnitEevent = false;
        try {
            String[] strSplit = jsonPointer.toString().split("/");
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("String values: {} received in event data deserialize.", Arrays.toString(strSplit));
            }
            // Please note support has been added for only one level of
            // nested events
            if (strSplit.length == NumericConstants.THREE) {
                JsonNode failedJsonNode = origianlJsonNode.get(strSplit[1]);
                idNode = failedJsonNode.get(EventAttribute.EVENTID);
                versionNode = failedJsonNode.get(EventAttribute.VERSION);
                nestedIgnitEevent = true;
            } else {
                // support for two level of nested events
                // RTC-156940 - Redis issue when the component is not able
                // to send to device and we restart the component
                /*
                 * In order to fix the above issue, we have to incorporate a
                 * new custom jackson codec
                 * com.harman.ignite.platform.dmf.control.utils.IgniteJsonJacksonCodec
                 * in the Redission client. This jackson codec uses the
                 * ObjectMapper coming from
                 * com.harman.ignite.transform.config.JacksonMapperConfig
                 * class and doesn't put @class annotation while putting the
                 * data to redis.
                 *
                 *
                 * While deserializing the data from the redis,
                 * IgniteJsonJacksonCodec use this class to identify the
                 * EventData class. In this case JsonPointer comes as
                 * /deviceMessage/event/data and when it is splitted via /,
                 * it gives 4 values one of which is empty.
                 *
                 * Hence we are removing the empty and then proceeding
                 * inside each of the json node to get the eventId and
                 * version
                 *
                 *
                 *
                 *
                 * TBD: This could be made generic to support for nested
                 * ignite events.
                 *
                 *
                 *
                 *
                 *
                 */
                // The below logic is very specific to ADA / DMF flow, where
                // the RetryRecord received contains more than three
                // elements in the array including empty / blank data. We
                // need to remove the blank entries and then check for the
                // length in order to proceed further. The entries received
                strSplit = removeEmptyData(strSplit);
                if (strSplit.length == NumericConstants.THREE) {
                    JsonNode node1 = origianlJsonNode.get(strSplit[0]);
                    LOGGER.debug("JsonNode1 value: {}", node1);
                    JsonNode node2 = node1.get(strSplit[1]);
                    LOGGER.debug("JsonNode2 value: {}", node2);
                    idNode = node2.get(EventAttribute.EVENTID);
                    versionNode = node2.get(EventAttribute.VERSION);
                    nestedIgnitEevent = true;
                }
            }

        } catch (Exception e) {
            LOGGER.error("Exception occured when processing object node during deserialization", e);
        }
        if (!nestedIgnitEevent) {
            idNode = origianlJsonNode.get(EventAttribute.EVENTID);
            versionNode = origianlJsonNode.get(EventAttribute.VERSION);
        }

        String id = idNode == null ? null : idNode.asText();
        String version = versionNode == null ? null : versionNode.asText();

        if (StringUtils.isBlank(id)) {
            throw new DataDeserializationException(String.format("Received Null or Empty EventId for the data: %s",
                    originalString));
        }
        if (StringUtils.isBlank(version)) {
            LOGGER.warn("Received null or empty version for data:{}. Setting to default version:{}",
                    originalString, Constants.DEFAULT_VERSION);
            version = Constants.DEFAULT_VERSION;
            // the original event will still have the null version. We are
            // just making sure we are able to match the event data to
            // either actual class or generic event data.

        }
        String key = id + Constants.HYPHEN + version;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fetching Class information for key:{}", key);
        }
        Class<EventData> clazz = (Class<EventData>) this.eventDataMapping.get(key);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Received class:{} for the key:{}", clazz, key);
        }
        return clazz;
    }

    /**
     * Method to remove the blank / empty entries from the array.
     *
     * @param strSplit : strSplit
     * @return : String Array
     */
    public String[] removeEmptyData(String[] strSplit) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Checking empty or null values in string array: {}", Arrays.toString(strSplit));
        }
        if (strSplit.length > 0) {
            List<String> tempList = new ArrayList<>();
            for (String s : strSplit) {
                if (!StringUtils.isEmpty(s)) {
                    tempList.add(s);
                }
            }
            strSplit = tempList.toArray(new String[0]);
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Modified string array after removal of empty "
                        + "or null values: {}", Arrays.toString(strSplit));
            }
        }
        return strSplit;
    }

}
