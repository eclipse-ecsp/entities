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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.harman.ignite.domain.EventAttribute;
import com.harman.ignite.domain.Version;

import java.io.Serializable;

/**
 * Abstract ignite event base class.
 */
public class AbstractIgniteEventBase implements IgniteEventBase, Serializable {

    protected static final long serialVersionUID = -3920038506977633926L;

    @JsonIgnore
    @Override
    public Version getSchemaVersion() {
        return version;
    }

    @Override
    public void setSchemaVersion(Version v) {
        setVersion(v);
    }

    @JsonProperty(value = EventAttribute.EVENTID, required = true)
    protected String eventId;

    @JsonProperty(value = EventAttribute.VERSION, required = true)
    protected Version version;

    @JsonProperty(value = EventAttribute.TIMESTAMP, required = true)
    protected long timestamp;

    @JsonProperty(value = "Data", required = true)
    protected EventData eventData;

    @JsonProperty(value = EventAttribute.REQUEST_ID)
    protected String requestId;

    @JsonProperty(value = EventAttribute.SOURCE_DEVICE_ID)
    protected String sourceDeviceId;

    @JsonProperty(value = EventAttribute.VEHICLE_ID)
    protected String vehicleId;

    @JsonIgnore
    @JsonProperty(value = EventAttribute.TARGET_DEVICE_ID)
    protected String targetDeviceId;

    @JsonProperty(value = EventAttribute.TRC_CTX)
    private String tracingContext;

    @Override
    public String getEventId() {
        return this.eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Override
    public Version getVersion() {
        return this.version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    @Override
    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public EventData getEventData() {
        return eventData;
    }

    public void setEventData(EventData eventData) {
        this.eventData = eventData;
    }

    @Override
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * One vehicle (denoted by vehicleId) can have multiple devices that are talking to the cloud (denoted by deviceId).
     *
     * @return deviceId
     */
    @Override
    public String getSourceDeviceId() {
        return sourceDeviceId;
    }

    public void setSourceDeviceId(String sourceDeviceId) {
        this.sourceDeviceId = sourceDeviceId;
    }

    /**
     * vehicleId (VIN) - unique identification of a vehicle.
     *
     * @retrun vehicleId
     */
    @Override
    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public String getTracingContext() {
        return tracingContext;
    }

    @Override
    public void setTracingContext(String tracingContext) {
        this.tracingContext = tracingContext;
    }

}