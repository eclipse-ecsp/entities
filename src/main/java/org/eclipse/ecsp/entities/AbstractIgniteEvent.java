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

package org.eclipse.ecsp.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.eclipse.ecsp.domain.EventAttribute;
import org.eclipse.ecsp.enums.QosLevel;
import org.eclipse.ecsp.utils.Constants;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation of IgniteEvent interface provides the meta data needed by service w.r.t to the event.
 * <p>
 * For example eventId = "speed", version = "v1", timestamp = "123445667".
 *
 * It represents the standard Ignite event specified by the event specification
 * </p>
 *
 * @author avadakkootko
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFilter(EventAttribute.EVENT_FILTER)
public abstract class AbstractIgniteEvent extends AbstractIgniteEventBase implements IgniteEvent, Serializable {

    /**
     * uid.
     */
    private static final long serialVersionUID = -470373577484784015L;

    /**
     * timezone.
     */
    @JsonProperty(value = EventAttribute.TIMEZONE, required = true)
    private short timezone;

    /**
     * dffQualifier.
     */
    @JsonProperty(value = EventAttribute.DFF_QUALIFIER)
    private String dffQualifier;

    /**
     * deviceRoutable.
     */
    @JsonIgnore
    private transient boolean deviceRoutable;

    /**
     * messageId.
     */
    @JsonProperty(value = EventAttribute.MESSAGE_ID)
    private String messageId;

    /**
     * correlationId.
     */
    @JsonProperty(value = EventAttribute.CORRELATION_ID)
    private String correlationId;

    /**
     * bizTransactionId.
     */
    @JsonProperty(value = EventAttribute.BIZTRANSACTION_ID)
    private String bizTransactionId;
    
    /**
     * benchMode.
     */
    @JsonProperty(value = EventAttribute.BENCH_MODE)
    private String benchMode;
    
    /**
     * shoulderTapEnabled.
     */
    @JsonIgnore
    private transient boolean shoulderTapEnabled;

    /**
     * transientData.
     */
    @JsonIgnore
    private transient boolean transientData;

    /**
     * responseExpected.
     */
    @JsonIgnore
    private transient boolean responseExpected;

    /**
     * userContextInfo.
     */
    @JsonProperty(value = EventAttribute.USER_CONTEXT)
    private List<UserContext> userContextInfo;

    /**
     * ecuType.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = EventAttribute.ECU_TYPE)
    private String ecuType;

    /**
     * mqttTopic.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = EventAttribute.MQTT_TOPIC)
    private String mqttTopic;

    /**
     * This field, will override the mqttTopicSuffix set in application.properties.
     */
    @JsonIgnore
    private transient String devMsgTopicSuffix;

    /**
     * This field, will override the mqtt.service.topic.name.prefix set in application.properties.
     */
    @JsonIgnore
    private transient String devMsgTopicPrefix;

    /**
     * This field, is used when you want to send data to a global topic.
     */
    @JsonIgnore
    private transient String devMsgGlobalTopic;

    /**
     * deviceDeliveryCutoff.
     */
    @JsonProperty(value = EventAttribute.DEVICE_DELIVERY_CUTOFF)
    private long deviceDeliveryCutoff = Constants.DEFAULT_DELIVERY_CUTOFF;

    /**
     * duplicateMessage.
     */
    @JsonProperty(value = EventAttribute.DUPLICATE_MESSAGE)
    private Boolean duplicateMessage;

    /**
     * This field, will store the kafka headers for current input record.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = EventAttribute.KAFKA_HEADERS)
    private Map<String, String> kafkaHeaders;

    /**
     * platformId.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = EventAttribute.PLATFORM_ID)
    private String platformId;

    /**
     * qosLevel.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = EventAttribute.QOS_LEVEL)
    private QosLevel qosLevel;

    /**
     * get value for timezone.
     *
     * @return short
     */
    @Override
    public short getTimezone() {
        return timezone;
    }

    /**
     * set value for timezone.
     *
     * @param timezone : short
     */
    public void setTimezone(short timezone) {
        this.timezone = timezone;
    }

    /**
     * This method is a getter for dffqualifier.
     *
     * @return String
     */
    @JsonIgnore
    @Override
    public String getDFFQualifier() {
        return this.dffQualifier;
    }

    /**
     * Set the qualifier which will be used by DFFAgent to choose the right feed in case of
     * overlapping feed definitions for this same
     * event.
     *
     * @param qualifier : String
     */
    public void setDFFQualifier(String qualifier) {
        this.dffQualifier = qualifier;
    }

    /**
     * get deviceRoutable flag.
     *
     * @return boolean
     */
    @Override
    @JsonIgnore
    public boolean isDeviceRoutable() {
        return this.deviceRoutable;
    }

    /**
     * set deviceRoutable flag.
     *
     * @param deviceRoutable : boolean
     */
    public void setDeviceRoutable(boolean deviceRoutable) {
        this.deviceRoutable = deviceRoutable;
    }

    /**
     * get targetDeviceId.
     *
     * @return Optional of type String
     */
    @Override
    @JsonIgnore
    public Optional<String> getTargetDeviceId() {
        return Optional.ofNullable(this.targetDeviceId);
    }

    /**
     * set targetDeviceId.
     *
     * @param targetDeviceId : String
     */
    public void setTargetDeviceId(String targetDeviceId) {
        this.targetDeviceId = targetDeviceId;
    }

    /**
     * get value for messageId.
     *
     * @return String
     */
    @Override
    public String getMessageId() {
        return this.messageId;
    }

    /**
     * set value for messageId.
     *
     * @param messageId : String
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * get value for correlationId.
     *
     * @return String
     */
    @Override
    public String getCorrelationId() {
        return this.correlationId;
    }

    /**
     * set value for correlationId.
     *
     * @param correlationId : String
     */
    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    /**
     * get value for bizTransactionId.
     *
     * @return String
     */
    @Override
    public String getBizTransactionId() {
        return this.bizTransactionId;
    }

    /**
     * set value for bizTransactionId.
     *
     * @param bizTransactionId : String
     */
    public void setBizTransactionId(String bizTransactionId) {
        this.bizTransactionId = bizTransactionId;
    }

    /**
     * get if shoulder tap is enabled.
     *
     * @return boolean
     */
    @JsonIgnore
    public boolean isShoulderTapEnabled() {
        return shoulderTapEnabled;
    }

    /**
     * set shoulder tap enabled or not.
     *
     * @param shoulderTapEnabled : boolean
     */
    public void setShoulderTapEnabled(boolean shoulderTapEnabled) {
        this.shoulderTapEnabled = shoulderTapEnabled;
    }

    /**
     * get if transient data is present or not.
     *
     * @return boolean
     */
    @JsonIgnore
    public boolean isTransientData() {
        return transientData;
    }

    /**
     * set transient data flag.
     *
     * @param transientData : boolean
     */
    public void setTransientData(boolean transientData) {
        this.transientData = transientData;
    }

    /**
     * get if response expected is set to true or false.
     *
     * @return boolean
     */
    @JsonIgnore
    public boolean isResponseExpected() {
        return responseExpected;
    }

    /**
     * set if response expected or not.
     *
     * @param responseExpected : boolean
     */
    public void setResponseExpected(boolean responseExpected) {
        this.responseExpected = responseExpected;
    }

    /**
     * get deviceDeliveryCutoff.
     *
     * @return long
     */
    public long getDeviceDeliveryCutoff() {
        return deviceDeliveryCutoff;
    }

    /**
     * set deviceDeliveryCutoff.
     *
     * @param deviceDeliveryCutoff : long
     */
    public void setDeviceDeliveryCutoff(long deviceDeliveryCutoff) {
        this.deviceDeliveryCutoff = deviceDeliveryCutoff;
    }

    /**
     * Get value for device message topic suffix.
     *
     * @return Optional of type String
     */
    @JsonIgnore
    public Optional<String> getDevMsgTopicSuffix() {
        return Optional.ofNullable(devMsgTopicSuffix);
    }

    /**
     * Set value for topic suffix.
     *
     * @param devMsgTopicSuffix : String
     */
    public void setDevMsgTopicSuffix(String devMsgTopicSuffix) {
        this.devMsgTopicSuffix = devMsgTopicSuffix;
    }

    /**
     * Get value for device message topic prefix.
     *
     * @return Optional of type String
     */
    @JsonIgnore
    public Optional<String> getDevMsgTopicPrefix() {
        return Optional.ofNullable(devMsgTopicPrefix);
    }

    /**
     * Set value for device message topic prefix.
     *
     * @param devMsgTopicPrefix : String
     */
    public void setDevMsgTopicPrefix(String devMsgTopicPrefix) {
        this.devMsgTopicPrefix = devMsgTopicPrefix;
    }

    /**
     * get value for global topic name.
     *
     * @return Optional of type String
     */
    @JsonIgnore
    public Optional<String> getDevMsgGlobalTopic() {
        return Optional.ofNullable(devMsgGlobalTopic);
    }

    /**
     * set global topic.
     *
     * @param devMsgGlobalTopic : String
     */
    public void setDevMsgGlobalTopic(String devMsgGlobalTopic) {
        this.devMsgGlobalTopic = devMsgGlobalTopic;
    }

    /**
     * Gets bench mode.
     *
     * @return the benchMode
     */
    public String getBenchMode() {
        return benchMode;
    }

    /**
     * Sets bench mode.
     *
     * @param benchMode
     *         the benchMode to set
     */
    public void setBenchMode(String benchMode) {
        this.benchMode = benchMode;
    }

    /**
     * Gets UserContext Info.
     *
     * @return List{@code <}UserContext{@code >}.
     */
    @Override
    public List<UserContext> getUserContextInfo() {
        return userContextInfo;
    }

    /**
     * set userContextInfo.
     *
     * @param userContextInfo : List{@code <}UserContext{@code >}.
     */
    public void setUserContextInfo(List<UserContext> userContextInfo) {
        this.userContextInfo = userContextInfo;
    }

    /**
     * get duplicateMessage flag.
     *
     * @return Boolean
     */
    public Boolean isDuplicateMessage() {
        return duplicateMessage;
    }

    /**
     * set duplicateMessage flag.
     *
     * @param duplicateMessage : Boolean
     */
    public void setDuplicateMessage(Boolean duplicateMessage) {
        this.duplicateMessage = duplicateMessage;
    }

    /**
     * get ecuType.
     *
     * @return String
     */
    @Override
    public String getEcuType() {
        return this.ecuType;
    }

    /**
     * set ecuType.
     *
     * @param ecuType : String
     */
    public void setEcuType(String ecuType) {
        this.ecuType = ecuType;
    }

    /**
     * get mqtt topic name.
     *
     * @return String
     */
    @Override
    public String getMqttTopic() {
        return this.mqttTopic;
    }

    /**
     * set Mqtt topic name.
     *
     * @param mqttTopic : String
     */
    public void setMqttTopic(String mqttTopic) {
        this.mqttTopic = mqttTopic;
    }

    /**
     * get platformId.
     *
     * @return String
     */
    @Override
    public String getPlatformId() {
        return this.platformId;
    }

    /**
     * set platformId.
     *
     * @param platformId : String
     */
    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    /**
     * get kafka headers.
     *
     * @return Map{@code <}String{@code >}{@code <}String{@code >}
     */
    @Override
    public Map<String, String> getKafkaHeaders() {
        return kafkaHeaders;
    }

    /**
     * set kafka headers.
     *
     * @param kafkaHeaders : Map{@code <}String{@code >}{@code <}String{@code >}
     */
    public void setKafkaHeaders(Map<String, String> kafkaHeaders) {
        this.kafkaHeaders = kafkaHeaders;
    }

    /**
     * get QoS level.
     *
     * @return Integer
     */
    @Override
    public QosLevel getQosLevel() {
        return this.qosLevel;
    }

    /**
     * set QoS level.
     *
     * @param qosLevel : Integer
     */
    public void setQosLevel(QosLevel qosLevel) {
        this.qosLevel = qosLevel;
    }

    @Override
    public String toString() {
        return "AbstractIgniteEvent [timezone=" + timezone + ", dffQualifier=" + dffQualifier
                + ", targetDeviceId=" + targetDeviceId
                + ", messageId=" + messageId + ", correlationId=" + correlationId
                + ", bizTransactionId=" + bizTransactionId
                + ", benchMode=" + benchMode + ", userContextInfo=" + userContextInfo
                + ", deviceDeliveryCutoff=" + deviceDeliveryCutoff
                + ", duplicateMessage=" + duplicateMessage + ", ecuType=" + ecuType
                + ", mqttTopic=" + mqttTopic + ", platformId=" + platformId + ", eventId=" + eventId
                + ", version=" + version + ", timestamp=" + timestamp + ", eventData=" + eventData
                + ", requestId=" + requestId
                + ", sourceDeviceId=" + sourceDeviceId + ", vehicleId=" + vehicleId
                + ", kafkaHeaders=" + kafkaHeaders + ", qosLevel=" + qosLevel + "]";
    }

}
