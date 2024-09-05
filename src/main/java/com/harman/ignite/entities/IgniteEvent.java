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

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Contract for all events in Ignite.
 *
 * @author avadakkootko
 */
public interface IgniteEvent extends IgniteEventBase {

    public short getTimezone();

    /**
     * Gets nested events.
     *
     * @return a list of IgniteEvents
     */
    public List<IgniteEvent> getNestedEvents();

    /**
     * Get the qualifier which will be used by DFFAgent to choose the right
     * feed in case of overlapping feed definitions for this same
     * event.
     */
    public String getDFFQualifier();

    /**
     * One vehicle can have multiple target devices that are talking to the cloud.
     * This identifies the device to which the event will be
     * routed to
     *
     * @return clientId
     */
    public Optional<String> getTargetDeviceId();

    /**
     * Unique identifier of this event (per service).
     *
     * @return messageId
     */
    public String getMessageId();

    /**
     * Correlation id helps identify the request for which this event is the response.
     *
     * @return correlationId
     */
    public String getCorrelationId();

    /**
     * Gets biz transaction id.
     *
     * @return - business transaction id.
     */
    public String getBizTransactionId();

    /**
     * If true, this event will be sent to a device.
     *
     * @return - boolean to decide whether this event needs to be send to device or not
     */
    public boolean isDeviceRoutable();

    /**
     * If true, device messaging will wake up the device if it is not currently active.
     *
     * @return - boolean
     */
    public boolean isShoulderTapEnabled();

    /**
     * To decide whether or not to retry this event by Device Messaging Agent.
     *
     * @return - boolean
     */
    public boolean isTransientData();

    /**
     * Use this to signal device messaging that you are expecting a response
     * from the device for this event. If this returns true, then
     * device messaging will keep resending this event (at configured intervals
     * and attempts) till a response is received from the device.
     *
     * @return - boolean
     */
    public boolean isResponseExpected();

    /**
     * The max threshold time beyond which an event will not be processed by device messaging.
     *
     * @return DeviceDeliveryCutoff
     */
    public long getDeviceDeliveryCutoff();

    /**
     * Optional. Typically this wouldn't be set. But when provided,
     * this overrides the configured device messaging topic suffix. This gives
     * flexibility to services that need to dynamically change outbound mqtt topics.
     *
     * @return mqttTopicSuffix
     */
    public Optional<String> getDevMsgTopicSuffix();

    /**
     * #177051. RTN: Create user context on Ignite Event.
     * <p>
     * Optional. This is used to get user details associated to event.
     * </p>
     *
     * @return {@Link List<UserContext> }
     */
    public List<UserContext> getUserContextInfo();

    public Boolean isDuplicateMessage();

    public String getEcuType();

    public String getMqttTopic();

    public Map<String, String> getKafkaHeaders();

    public String getPlatformId();

}