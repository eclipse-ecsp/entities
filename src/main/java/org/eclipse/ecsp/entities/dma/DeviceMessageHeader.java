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
import org.apache.commons.lang3.StringUtils;
import org.eclipse.ecsp.enums.QosLevel;

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
    private QosLevel qosLevel;

    /*
     * RTC 285555 DMA should store the retry attempt in offline buffer. So as
     * not to reset the retry count if device goes inactive before all the retry
     * attempts are exhausted. pendingRetries will keep a track of remaining
     * retries.
     */
    private int pendingRetries;
    // below property will indicate pendingRetries has been set.
    private boolean isPendingRetriesSet;

    /**
     * default constructor.
     */
    public DeviceMessageHeader() {
        // default constructor
    }

    /**
     * set messageid.
     *
     * @param messageId : String
     *
     * @return DeviceMessageHeader
     */
    public DeviceMessageHeader withMessageId(String messageId) {
        this.messageId = messageId;
        return this;
    }

    /**
     * set correlationId.
     *
     * @param correlationId : String
     *
     * @return DeviceMessageHeader
     */
    public DeviceMessageHeader withCorrelationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    /**
     * set requestId.
     *
     * @param requestId : String
     *
     * @return DeviceMessageHeader
     */
    public DeviceMessageHeader withRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    /**
     * set vehicleId.
     *
     * @param vehicleId : String
     *
     * @return DeviceMessageHeader
     */
    public DeviceMessageHeader withVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    /**
     * set targetDeviceId.
     *
     * @param targetDeviceId : String
     *
     * @return DeviceMessageHeader
     */
    public DeviceMessageHeader withTargetDeviceId(String targetDeviceId) {
        this.targetDeviceId = targetDeviceId;
        return this;
    }

    /**
     * set deviceRoutable flag.
     *
     * @param deviceRoutable : boolean
     *
     * @return DeviceMessageHeader
     */
    public DeviceMessageHeader withDeviceRoutable(boolean deviceRoutable) {
        this.deviceRoutable = deviceRoutable;
        return this;
    }

    /**
     * set responseExpected flag.
     *
     * @param responseExpected : boolean
     *
     * @return DeviceMessageHeader
     */
    public DeviceMessageHeader withResponseExpected(boolean responseExpected) {
        this.responseExpected = responseExpected;
        return this;
    }

    /**
     * set shoulderTapEnabled flag.
     *
     * @param shoulderTapEnabled : boolean
     *
     * @return DeviceMessageHeader
     */
    public DeviceMessageHeader withShoulderTapEnabled(boolean shoulderTapEnabled) {
        this.shoulderTapEnabled = shoulderTapEnabled;
        return this;
    }

    /**
     * set deviceDeliveryCutoff.
     *
     * @param deviceDeliveryCutoff : long
     *
     * @return DeviceMessageHeader
     */
    public DeviceMessageHeader withDeviceDeliveryCutoff(long deviceDeliveryCutoff) {
        this.deviceDeliveryCutoff = deviceDeliveryCutoff;
        return this;
    }

    /**
     * set timestamp.
     *
     * @param timestamp : long
     *
     * @return DeviceMessageHeader
     */
    public DeviceMessageHeader withTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * set devMsgTopicSuffix.
     *
     * @param devMsgTopicSuffix : String
     *
     * @return DeviceMessageHeader
     */
    public DeviceMessageHeader withDevMsgTopicSuffix(String devMsgTopicSuffix) {
        this.devMsgTopicSuffix = devMsgTopicSuffix;
        return this;
    }

    /**
     * set devMsgTopicPrefix.
     *
     * @param devMsgTopicPrefix : String
     *
     * @return DeviceMessageHeader
     */
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

    /**
     * set pendingRetries.
     *
     * @param pendingRetries : int
     *
     * @return DeviceMessageHeader
     */
    public DeviceMessageHeader withPendingRetries(int pendingRetries) {
        this.pendingRetries = pendingRetries;
        return this;
    }

    /**
     * set platformId.
     *
     * @param platformId : String
     *
     * @return DeviceMessageHeader
     */
    public DeviceMessageHeader withPlatformId(String platformId) {
        this.platformId = platformId;
        return this;
    }

    /**
     * set timezone.
     *
     * @param timezone : short
     *
     * @return DeviceMessageHeader
     */
    public DeviceMessageHeader withTimezone(short timezone) {
        this.timezone = timezone;
        return this;
    }

    /**
     * set qosLevel.
     *
     * @param qosLevel : Integer
     *
     * @return DeviceMessageHeader
     */
    public DeviceMessageHeader withQosLevel(QosLevel qosLevel) {
        this.qosLevel = qosLevel;
        return this;
    }

    /**
     * set isPendingRetriesSet flag.
     *
     * @param isPendingRetriesSet : boolean
     *
     * @return DeviceMessageHeader
     */
    public DeviceMessageHeader isPendingRetriesSet(boolean isPendingRetriesSet) {
        this.isPendingRetriesSet = isPendingRetriesSet;
        return this;
    }

    /**
     * get pendingRetries.
     *
     * @return int
     */
    public int getPendingRetries() {
        return this.pendingRetries;
    }

    /**
     * get isPendingRetriesSet.
     *
     * @return boolean
     */
    public boolean getIsPendingRetriesSet() {
        return this.isPendingRetriesSet;
    }

    /**
     * get messageId.
     *
     * @return String
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * get correlationId.
     *
     * @return String
     */
    public String getCorrelationId() {
        return correlationId;
    }

    /**
     * get requestId.
     *
     * @return String
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * get vehicleId.
     *
     * @return String
     */
    public String getVehicleId() {
        return vehicleId;
    }

    /**
     * get targetDeviceId.
     *
     * @return String
     */
    public String getTargetDeviceId() {
        return targetDeviceId;
    }

    /**
     * get deviceRoutable.
     *
     * @return boolean
     */
    public boolean isDeviceRoutable() {
        return deviceRoutable;
    }

    /**
     * get responseExpected.
     *
     * @return boolean
     */
    public boolean isResponseExpected() {
        return responseExpected;
    }

    /**
     * get shoulderTapEnabled.
     *
     * @return boolean
     */
    public boolean isShoulderTapEnabled() {
        return shoulderTapEnabled;
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
     * get timestamp.
     *
     * @return long
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * get devMsgTopicSuffix.
     *
     * @return String
     */
    public String getDevMsgTopicSuffix() {
        return devMsgTopicSuffix;
    }

    /**
     * get devMsgTopicPrefix.
     *
     * @return String
     */
    public String getDevMsgTopicPrefix() {
        return devMsgTopicPrefix;
    }

    /**
     * get devMsgGlobalTopic.
     *
     * @return String
     */
    public String getDevMsgGlobalTopic() {
        return devMsgGlobalTopic;
    }

    /**
     * get globalTopicNameProvided.
     *
     * @return boolean
     */
    public boolean isGlobalTopicNameProvided() {
        return globalTopicNameProvided;
    }

    /**
     * get platformId.
     *
     * @return String
     */
    public String getPlatformId() {
        return platformId;
    }

    /**
     * get timezone.
     *
     * @return short
     */
    public short getTimezone() {
        return timezone;
    }

    /**
     * get qosLevel.
     *
     * @return QosLevel
     */
    public QosLevel getQosLevel() {
        return qosLevel;
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
                + ", timezone=" + timezone + ", qosLevel=" + qosLevel
                + ", devMsgTopicSuffix=" + devMsgTopicSuffix
                + ", devMsgTopicPrefix=" + devMsgTopicPrefix + ", devMsgGlobalTopic="
                + devMsgGlobalTopic + "]";
    }

}
