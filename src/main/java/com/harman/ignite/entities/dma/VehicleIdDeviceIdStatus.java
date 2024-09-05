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

import com.harman.ignite.domain.DeviceConnStatusV1_0.ConnectionStatus;
import com.harman.ignite.domain.Version;
import com.harman.ignite.entities.AbstractIgniteEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class is almost a replica of VehicleIdDeviceIdMapping
 *          class with the change in the data structure of deviceIds. As per the
 *          RDNGs: 170506 & 170507, DMA should store the connection status
 *          of inactive devices too in its in-memory. Hence, in this class
 *          there is a map of deviceIds to connection status.
 *
 * @author hbadshah
 */
public class VehicleIdDeviceIdStatus extends AbstractIgniteEntity {

    /**
     *  Eg. If VIN = vin123 and devices associated with it are: d1,d2 and d1 is active and d2 is inactive.
     * Then, in in-memory the data would be: vin123:[d1-active,d2-inactive]
     */
    private Map<String, ConnectionStatus> deviceIdsToStatusMap;

    public VehicleIdDeviceIdStatus() {
        this.deviceIdsToStatusMap = new ConcurrentHashMap<>();
        setSchemaVersion(Version.V1_0);
    }

    public VehicleIdDeviceIdStatus(Version schemaVersion) {
        this.deviceIdsToStatusMap = new ConcurrentHashMap<>();
        setSchemaVersion(schemaVersion);
    }

    public VehicleIdDeviceIdStatus(Version schemaVersion, Map<String, ConnectionStatus> deviceIdsToStatusMap) {
        setSchemaVersion(schemaVersion);
        this.deviceIdsToStatusMap = deviceIdsToStatusMap;
    }

    public Map<String, ConnectionStatus> getDeviceIds() {
        return deviceIdsToStatusMap;
    }

    public void setDeviceIds(Map<String, ConnectionStatus> deviceIdsToStatusMap) {
        this.deviceIdsToStatusMap = deviceIdsToStatusMap;
    }

    /**
     * Add deviceId to the map of deviceIds to connectionStatus.
     *
     * @param deviceId : deviceId
     */
    public void addDeviceId(String deviceId, String connectionStatus) {
        deviceIdsToStatusMap.put(deviceId, ConnectionStatus.valueOf(connectionStatus));
    }

    /**
     * Remove deviceId from the map of deviceIds to connectionStatus.
     *
     * @param deviceId : deviceId
     * @return : true/false
     */
    public boolean deleteDeviceId(String deviceId) {
        boolean deleted = false;
        for (Map.Entry<String, ConnectionStatus> entry : deviceIdsToStatusMap.entrySet()) {
            if (StringUtils.equals(entry.getKey(), deviceId)) {
                deviceIdsToStatusMap.remove(deviceId);
                deleted = true;
            }
        }
        return deleted;
    }

    @Override
    public String toString() {
        return "VehicleIdDeviceIdToStatus [schemaVersion=" + getSchemaVersion()
                + ", deviceIdsToStatusMap=" + deviceIdsToStatusMap + "]";
    }

}
