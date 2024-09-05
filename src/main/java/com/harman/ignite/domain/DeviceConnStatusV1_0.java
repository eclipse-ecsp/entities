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
import lombok.EqualsAndHashCode;

/**
 * Device conn status v 1.0 class.
 */
@SuppressWarnings("checkstyle:TypeName")
@EventMapping(id = EventID.DEVICE_CONN_STATUS, version = Version.V1_0)
@EqualsAndHashCode
public class DeviceConnStatusV1_0 extends AbstractEventData {

    private static final long serialVersionUID = -8185364560878375079L;
    private String serviceName;
    private ConnectionStatus connStatus;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public ConnectionStatus getConnStatus() {
        return connStatus;
    }

    public void setConnStatus(ConnectionStatus connStatus) {
        this.connStatus = connStatus;
    }

    @Override
    public String toString() {
        return "DeviceConnStatusV1_0 [serviceName=" + serviceName + ", connStatus=" + connStatus + "]";
    }

    /**
     * The enum Connection status.
     */
    public enum ConnectionStatus {
        ACTIVE("ACTIVE"),
        INACTIVE("INACTIVE");

        private String connStatus;

        ConnectionStatus(String connStatus) {
            this.connStatus = connStatus;
        }

        public String getConnectionStatus() {
            return connStatus;
        }
    }

}
