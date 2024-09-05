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

package com.harman.ignite.entities.dma;

import dev.morphia.annotations.Entity;
import org.apache.commons.lang3.StringUtils;

/**
 * Device message header.
 */
@Entity
public class DeviceMessageHeader {

    private String messageId;
    private String correlationId;
    private String requestId;
    private String vehicleId;
    private String targetDeviceId;
    private String devMsgTopicPrefix;
    private String devMsgTopicSuffix;
    private String devMsgGlobalTopic;
    private boolean deviceRoutable;
    private boolean responseExpected;
    private boolean shoulderTapEnabled;
    private boolean globalTopicNameProvided;
    private long deviceDeliveryCutoff;
    private long timestamp;
    private String platformId;
    private short timezone;

    /*
     * RTC 285555 DMA should store the retry attempt in offline buffer. So as
     * not to reset the retry count if device goes inactive before all the retry
     * attempts are exhausted. pendingRetries will keep a track of remaining
     * retries.
     */
    private int pendingRetries;
    // below property will indicate pendingRetries has been set.
    private boolean isPendingRetriesSet;

    public DeviceMessageHeader() {

    }

    public DeviceMessageHeader withMessageId(String messageId) {
        this.messageId = messageId;
        return this;
    }

    public DeviceMessageHeader withCorrelationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    public DeviceMessageHeader withRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public DeviceMessageHeader withVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public DeviceMessageHeader withTargetDeviceId(String targetDeviceId) {
        this.targetDeviceId = targetDeviceId;
        return this;
    }

    public DeviceMessageHeader withDeviceRoutable(boolean deviceRoutable) {
        this.deviceRoutable = deviceRoutable;
        return this;
    }

    public DeviceMessageHeader withResponseExpected(boolean responseExpected) {
        this.responseExpected = responseExpected;
        return this;
    }

    public DeviceMessageHeader withShoulderTapEnabled(boolean shoulderTapEnabled) {
        this.shoulderTapEnabled = shoulderTapEnabled;
        return this;
    }

    public DeviceMessageHeader withDeviceDeliveryCutoff(long deviceDeliveryCutoff) {
        this.deviceDeliveryCutoff = deviceDeliveryCutoff;
        return this;
    }

    public DeviceMessageHeader withTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public DeviceMessageHeader withDevMsgTopicSuffix(String devMsgTopicSuffix) {
        this.devMsgTopicSuffix = devMsgTopicSuffix;
        return this;
    }

    public DeviceMessageHeader withDevMsgTopicPrefix(String devMsgTopicPrefix) {
        this.devMsgTopicPrefix = devMsgTopicPrefix;
        return this;
    }

    /**
     * With dev msg global topic device message header.
     *
     * @param devMsgGlobalTopic
     *         the dev msg global topic
     * @return the device message header
     */
    public DeviceMessageHeader withDevMsgGlobalTopic(String devMsgGlobalTopic) {
        this.devMsgGlobalTopic = devMsgGlobalTopic;
        if (StringUtils.isNotEmpty(devMsgGlobalTopic)) {
            globalTopicNameProvided = true;
        }
        return this;
    }

    public DeviceMessageHeader withPendingRetries(int pendingRetries) {
        this.pendingRetries = pendingRetries;
        return this;
    }

    public DeviceMessageHeader withPlatformId(String platformId) {
        this.platformId = platformId;
        return this;
    }

    public DeviceMessageHeader withTimezone(short timezone) {
        this.timezone = timezone;
        return this;
    }

    public DeviceMessageHeader isPendingRetriesSet(boolean isPendingRetriesSet) {
        this.isPendingRetriesSet = isPendingRetriesSet;
        return this;
    }

    public int getPendingRetries() {
        return this.pendingRetries;
    }

    public boolean getIsPendingRetriesSet() {
        return this.isPendingRetriesSet;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getTargetDeviceId() {
        return targetDeviceId;
    }

    public boolean isDeviceRoutable() {
        return deviceRoutable;
    }

    public boolean isResponseExpected() {
        return responseExpected;
    }

    public boolean isShoulderTapEnabled() {
        return shoulderTapEnabled;
    }

    public long getDeviceDeliveryCutoff() {
        return deviceDeliveryCutoff;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getDevMsgTopicSuffix() {
        return devMsgTopicSuffix;
    }

    public String getDevMsgTopicPrefix() {
        return devMsgTopicPrefix;
    }

    public String getDevMsgGlobalTopic() {
        return devMsgGlobalTopic;
    }

    public boolean isGlobalTopicNameProvided() {
        return globalTopicNameProvided;
    }

    public String getPlatformId() {
        return platformId;
    }

    public short getTimezone() {
        return timezone;
    }

    @Override
    public String toString() {
        return "DeviceMessageHeader [messageId=" + messageId
                + ", correlationId=" + correlationId + ", requestId=" + requestId
                + ", vehicleId=" + vehicleId + ", targetDeviceId=" + targetDeviceId
                + ", deviceRoutable=" + deviceRoutable + ", platformId=" + platformId
                + ", responseExpected=" + responseExpected + ", shoulderTapEnabled="
                + shoulderTapEnabled + ", deviceDeliveryCutoff=" + deviceDeliveryCutoff
                + ", timestamp=" + timestamp
                + ", timezone=" + timezone
                + ", devMsgTopicSuffix=" + devMsgTopicSuffix
                + ", devMsgTopicPrefix=" + devMsgTopicPrefix + ", devMsgGlobalTopic="
                + devMsgGlobalTopic + "]";
    }

}
