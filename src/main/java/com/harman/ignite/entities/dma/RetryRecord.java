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

import com.harman.ignite.entities.AbstractIgniteEntity;
import com.harman.ignite.key.IgniteKey;

import java.util.Map;

/**
 * This data structure keeps track of the meta info of the event
 * that needs to be retried, including how many times the retry has been
 * attempted.
 *
 * @author avadakkootko
 */
public class RetryRecord extends AbstractIgniteEntity {
    private IgniteKey<?> igniteKey;

    private DeviceMessage deviceMessage;

    private int attempts;

    private long lastRetryTimestamp;

    private Map<String, Object> extraParameters;

    public RetryRecord() {
    }

    /**
     * Instantiates a new Retry record.
     *
     * @param igniteKey
     *         the ignite key
     * @param deviceMessage
     *         the device message
     * @param lastRetryTimestamp
     *         the last retry timestamp
     */
    public RetryRecord(IgniteKey<?> igniteKey, DeviceMessage deviceMessage, long lastRetryTimestamp) {
        super();
        this.igniteKey = igniteKey;
        this.deviceMessage = deviceMessage;
        this.lastRetryTimestamp = lastRetryTimestamp;
        attempts = 0;
    }

    public IgniteKey<?> getIgniteKey() {
        return igniteKey;
    }

    public void setIgniteKey(IgniteKey<?> igniteKey) {
        this.igniteKey = igniteKey;
    }

    public DeviceMessage getDeviceMessage() {
        return deviceMessage;
    }

    public void setDeviceMessage(DeviceMessage deviceMessage) {
        this.deviceMessage = deviceMessage;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public long getLastRetryTimestamp() {
        return lastRetryTimestamp;
    }

    public void setLastRetryTimestamp(long lastRetryTimestamp) {
        this.lastRetryTimestamp = lastRetryTimestamp;
    }

    public void addAttempt(long lastRetryTimestamp) {
        attempts++;
        this.lastRetryTimestamp = lastRetryTimestamp;
    }

    public Map<String, Object> getExtraParameters() {
        return extraParameters;
    }

    public void setExtraParameters(Map<String, Object> extraParameters) {
        this.extraParameters = extraParameters;
    }

    @Override
    public String toString() {
        return "RetryRecord [igniteKey=" + igniteKey + ", deviceMessage="
                + deviceMessage + ", attempts=" + attempts
                + ", lastRetryTimestamp=" + lastRetryTimestamp + ", version="
                +  getSchemaVersion() + ", extraParameters=" + extraParameters + "]";
    }

}
