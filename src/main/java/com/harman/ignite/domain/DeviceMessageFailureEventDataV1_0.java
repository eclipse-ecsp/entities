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

package com.harman.ignite.domain;

import com.harman.ignite.annotations.EventMapping;
import com.harman.ignite.entities.AbstractEventData;
import com.harman.ignite.entities.IgniteEvent;
import com.harman.ignite.entities.IgniteEventImpl;
import com.harman.ignite.entities.dma.DeviceMessageErrorCode;
import lombok.EqualsAndHashCode;

/**
 * This class captures the key parameters that can be cause for DeviceMessage failure such as
 * exceeding shoulder tap attempts or retry
 * attempts. It can also be used to send intermediate status such as retrying a message or retrying shoulder tap.
 *
 * @author avadakkootko
 */
@SuppressWarnings("checkstyle:TypeName")
@EventMapping(id = EventID.DEVICEMESSAGEFAILURE, version = Version.V1_0)
@EqualsAndHashCode
public class DeviceMessageFailureEventDataV1_0 extends AbstractEventData {
    private static final long serialVersionUID = -1147106467326210246L;
    private IgniteEventImpl failedIgniteEvent;
    private int retryAttempts;
    private int shoudlerTapRetryAttempts;
    private boolean deviceDeliveryCutoffExceeded;
    private boolean deviceStatusInactive;
    private DeviceMessageErrorCode errorCode;

    public IgniteEvent getFailedIgniteEvent() {
        return failedIgniteEvent;
    }

    public void setFailedIgniteEvent(IgniteEventImpl failedIgniteEvent) {
        this.failedIgniteEvent = failedIgniteEvent;
    }

    public int getRetryAttempts() {
        return retryAttempts;
    }

    public void setRetryAttempts(int retryAttempts) {
        this.retryAttempts = retryAttempts;
    }

    public int getShoudlerTapRetryAttempts() {
        return shoudlerTapRetryAttempts;
    }

    public void setShoudlerTapRetryAttempts(int shoudlerTapRetryAttempts) {
        this.shoudlerTapRetryAttempts = shoudlerTapRetryAttempts;
    }

    public boolean isDeviceDeliveryCutoffExceeded() {
        return deviceDeliveryCutoffExceeded;
    }

    public void setDeviceDeliveryCutoffExceeded(boolean deviceDeliveryCutoffExceeded) {
        this.deviceDeliveryCutoffExceeded = deviceDeliveryCutoffExceeded;
    }

    public boolean isDeviceStatusInactive() {
        return deviceStatusInactive;
    }

    public void setDeviceStatusInactive(boolean deviceStatusInactive) {
        this.deviceStatusInactive = deviceStatusInactive;
    }

    public DeviceMessageErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(DeviceMessageErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "DeviceMessageFailureEventDataV1_0 [failedIgniteEvent=" + failedIgniteEvent
                + ", retryAttempts=" + retryAttempts
                + ", shoudlerTapRetryAttempts=" + shoudlerTapRetryAttempts
                + ", deviceDeliveryCutoffExceeded=" + deviceDeliveryCutoffExceeded
                + ", deviceStatusInactive=" + deviceStatusInactive + ", errorCode=" + errorCode + "]";
    }

}
