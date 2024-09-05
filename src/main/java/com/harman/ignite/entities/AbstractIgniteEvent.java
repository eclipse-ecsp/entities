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

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.harman.ignite.domain.EventAttribute;
import com.harman.ignite.utils.Constants;

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

    private static final long serialVersionUID = -470373577484784015L;

    @JsonProperty(value = EventAttribute.TIMEZONE, required = true)
    private short timezone;

    @JsonProperty(value = EventAttribute.DFF_QUALIFIER)
    private String dffQualifier;

    @JsonIgnore
    private transient boolean deviceRoutable;

    @JsonProperty(value = EventAttribute.MESSAGE_ID)
    private String messageId;

    @JsonProperty(value = EventAttribute.CORRELATION_ID)
    private String correlationId;

    @JsonProperty(value = EventAttribute.BIZTRANSACTION_ID)
    private String bizTransactionId;
    @JsonProperty(value = EventAttribute.BENCH_MODE)
    private String benchMode;
    @JsonIgnore
    private transient boolean shoulderTapEnabled;

    @JsonIgnore
    private transient boolean transientData;

    @JsonIgnore
    private transient boolean responseExpected;

    @JsonProperty(value = EventAttribute.USER_CONTEXT)
    private List<UserContext> userContextInfo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = EventAttribute.ECU_TYPE)
    private String ecuType;

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

    @JsonProperty(value = EventAttribute.DEVICE_DELIVERY_CUTOFF)
    private long deviceDeliveryCutoff = Constants.DEFAULT_DELIVERY_CUTOFF;

    @JsonProperty(value = EventAttribute.DUPLICATE_MESSAGE)
    private Boolean duplicateMessage;

    /**
     * This field, will store the kafka headers for current input record.
     */
    @JsonIgnore
    private transient Map<String, String> kafkaHeaders;

    @JsonProperty(value = EventAttribute.PLATFORM_ID)
    private String platformId;

    @Override
    public short getTimezone() {
        return timezone;
    }

    public void setTimezone(short timezone) {
        this.timezone = timezone;
    }

    @JsonIgnore
    @Override
    public String getDFFQualifier() {
        return this.dffQualifier;
    }

    /**
     * Set the qualifier which will be used by DFFAgent to choose the right feed in case of
     * overlapping feed definitions for this same
     * event.
     */
    public void setDFFQualifier(String qualifier) {
        this.dffQualifier = qualifier;
    }

    @Override
    @JsonIgnore
    public boolean isDeviceRoutable() {
        return this.deviceRoutable;
    }

    public void setDeviceRoutable(boolean deviceRoutable) {
        this.deviceRoutable = deviceRoutable;
    }

    @Override
    @JsonIgnore
    public Optional<String> getTargetDeviceId() {
        return Optional.ofNullable(this.targetDeviceId);
    }

    public void setTargetDeviceId(String targetDeviceId) {
        this.targetDeviceId = targetDeviceId;
    }

    @Override
    public String getMessageId() {
        return this.messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Override
    public String getCorrelationId() {
        return this.correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    @Override
    public String getBizTransactionId() {
        return this.bizTransactionId;
    }

    public void setBizTransactionId(String bizTransactionId) {
        this.bizTransactionId = bizTransactionId;
    }

    @JsonIgnore
    public boolean isShoulderTapEnabled() {
        return shoulderTapEnabled;
    }

    public void setShoulderTapEnabled(boolean shoulderTapEnabled) {
        this.shoulderTapEnabled = shoulderTapEnabled;
    }

    @JsonIgnore
    public boolean isTransientData() {
        return transientData;
    }

    public void setTransientData(boolean transientData) {
        this.transientData = transientData;
    }

    @JsonIgnore
    public boolean isResponseExpected() {
        return responseExpected;
    }

    public void setResponseExpected(boolean responseExpected) {
        this.responseExpected = responseExpected;
    }

    public long getDeviceDeliveryCutoff() {
        return deviceDeliveryCutoff;
    }

    public void setDeviceDeliveryCutoff(long deviceDeliveryCutoff) {
        this.deviceDeliveryCutoff = deviceDeliveryCutoff;
    }

    @JsonIgnore
    public Optional<String> getDevMsgTopicSuffix() {
        return Optional.ofNullable(devMsgTopicSuffix);
    }

    public void setDevMsgTopicSuffix(String devMsgTopicSuffix) {
        this.devMsgTopicSuffix = devMsgTopicSuffix;
    }

    @JsonIgnore
    public Optional<String> getDevMsgTopicPrefix() {
        return Optional.ofNullable(devMsgTopicPrefix);
    }

    public void setDevMsgTopicPrefix(String devMsgTopicPrefix) {
        this.devMsgTopicPrefix = devMsgTopicPrefix;
    }

    @JsonIgnore
    public Optional<String> getDevMsgGlobalTopic() {
        return Optional.ofNullable(devMsgGlobalTopic);
    }

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

    public void setUserContextInfo(List<UserContext> userContextInfo) {
        this.userContextInfo = userContextInfo;
    }

    public Boolean isDuplicateMessage() {
        return duplicateMessage;
    }

    public void setDuplicateMessage(Boolean duplicateMessage) {
        this.duplicateMessage = duplicateMessage;
    }

    @Override
    public String getEcuType() {
        return this.ecuType;
    }

    public void setEcuType(String ecuType) {
        this.ecuType = ecuType;
    }

    @Override
    public String getMqttTopic() {
        return this.mqttTopic;
    }

    public void setMqttTopic(String mqttTopic) {
        this.mqttTopic = mqttTopic;
    }

    @Override
    public String getPlatformId() {
        return this.platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    @Override
    public Map<String, String> getKafkaHeaders() {
        return kafkaHeaders;
    }

    public void setKafkaHeaders(Map<String, String> kafkaHeaders) {
        this.kafkaHeaders = kafkaHeaders;
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
                + ", kafkaHeaders=" + kafkaHeaders + "]";
    }

}
