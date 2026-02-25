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

package org.eclipse.ecsp.entities.dma;

import dev.morphia.annotations.Entity;
import org.eclipse.ecsp.domain.Version;
import org.eclipse.ecsp.entities.AbstractIgniteEntity;
import org.eclipse.ecsp.entities.IgniteEventImpl;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * Device Routable entity is a wrapper around IgniteKey and IgniteEvent.
 * It also encapsulates attributes like deviceRoutable and igniteKey.
 *
 * @author avadakkootko
 */
@Entity
public class DeviceMessage extends AbstractIgniteEntity {

    private byte[] message;
    private DeviceMessageHeader deviceMessageHeader;

    // This is the actual IgniteEvent, using which the byteArray message was
    // generated. We need this event in scenarios where we have to send a
    // feedback on DM failures.
    private IgniteEventImpl event;

    private String feedBackTopic;

    //RTC 401954, provisioning service with functionality of setting DMA retryInterval
    //at event level.
    private long eventLevelRetryInterval;

    private boolean isOtherBrokerConfigured = false;

    /**
     * default constructor.
     */
    public DeviceMessage() {
    }

    /**
     * Instantiates a new Device message.
     *
     * @param message
     *         the message
     * @param schemaVersion
     *         the schema version
     * @param event
     *         the event
     * @param feedBackTopic
     *         the feed back topic
     * @param eventLevelRetryInterval
     *         the event level retry interval
     */
    public DeviceMessage(byte[] message, Version schemaVersion, IgniteEventImpl event,
            String feedBackTopic, long eventLevelRetryInterval) {
        super();
        this.message = message;
        this.event = event;
        setSchemaVersion(schemaVersion);
        this.feedBackTopic = feedBackTopic;
        this.eventLevelRetryInterval = eventLevelRetryInterval;
        populateDeviceMessageHeader();
    }

    /**
     * get message.
     *
     * @return byte[]
     */
    public byte[] getMessage() {
        return message;
    }

    /**
     * set message.
     *
     * @param message : byte[]
     */
    public void setMessage(byte[] message) {
        this.message = message;
    }

    /**
     * get headers.
     *
     * @return DeviceMessageHeader
     */
    public DeviceMessageHeader getDeviceMessageHeader() {
        return deviceMessageHeader;
    }

    /**
     * set headers.
     *
     * @param deviceMessageHeader : DeviceMessageHeader
     */
    public void setDeviceMessageHeader(DeviceMessageHeader deviceMessageHeader) {
        this.deviceMessageHeader = deviceMessageHeader;
    }

    /**
     * get if other broker configured.
     *
     * @param isOtherBrokerConfigured : boolean
     */
    public void isOtherBrokerConfigured(boolean isOtherBrokerConfigured) {
        this.isOtherBrokerConfigured = isOtherBrokerConfigured;
    }

    /**
     * set if other broker configured.
     *
     * @return boolean
     */
    public boolean isOtherBrokerConfigured() {
        return this.isOtherBrokerConfigured;
    }

    private void populateDeviceMessageHeader() {
        this.deviceMessageHeader = new DeviceMessageHeader().withMessageId(event.getMessageId())
                .withCorrelationId(event.getCorrelationId()).withVehicleId(event.getVehicleId())
                .withRequestId(event.getRequestId())
                .withDeviceRoutable(event.isDeviceRoutable()).withResponseExpected(
                        event.isResponseExpected())
                .withShoulderTapEnabled(event.isShoulderTapEnabled())
                .withDeviceDeliveryCutoff(event.getDeviceDeliveryCutoff())
                .withTimestamp(event.getTimestamp()).withPlatformId(event.getPlatformId())
                .withTimezone(event.getTimezone());
        Optional<String> globalTopicOpt = event.getDevMsgGlobalTopic();
        if (!globalTopicOpt.isEmpty()) {
            deviceMessageHeader.withDevMsgGlobalTopic(globalTopicOpt.get());
        }
        Optional<String> devMsgTopicSuffix = event.getDevMsgTopicSuffix();
        if (!devMsgTopicSuffix.isEmpty()) {
            deviceMessageHeader.withDevMsgTopicSuffix(devMsgTopicSuffix.get());
        }
        Optional<String> devMsgTopicPrefix = event.getDevMsgTopicPrefix();
        if (!devMsgTopicPrefix.isEmpty()) {
            deviceMessageHeader.withDevMsgTopicPrefix(devMsgTopicPrefix.get());
        }
        deviceMessageHeader.withTargetDeviceId(event.getTargetDeviceId().orElse(event.getSourceDeviceId()));
        deviceMessageHeader.withQosLevel(event.getQosLevel());
    }

    /**
     * get feedback topic.
     *
     * @return String
     */
    public String getFeedBackTopic() {
        return feedBackTopic;
    }

    /**
     * set feedback topic.
     *
     * @param feedBackTopic : String
     */
    public void setFeedBackTopic(String feedBackTopic) {
        this.feedBackTopic = feedBackTopic;
    }

    /**
     * get eventLevelRetryInterval.
     *
     * @return long
     */
    public long getEventLevelRetryInterval() {
        return eventLevelRetryInterval;
    }

    /**
     * set eventLevelRetryInterval.
     *
     * @param eventLevelRetryInterval : long
     */
    public void setEventLevelRetryInterval(long eventLevelRetryInterval) {
        this.eventLevelRetryInterval = eventLevelRetryInterval;
    }

    /**
     * get event.
     *
     * @return IgniteEventImpl
     */
    public IgniteEventImpl getEvent() {
        return event;
    }

    /**
     * set event.
     *
     * @param event : IgniteEventImpl
     */
    public void setEvent(IgniteEventImpl event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "DeviceMessage [message=" + Arrays.toString(message)
                + ", version=" + getSchemaVersion() + ", deviceMessageHeader="
                + deviceMessageHeader + ", retryInterval= " + this.eventLevelRetryInterval + " ]";
    }

}
