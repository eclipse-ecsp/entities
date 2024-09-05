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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.harman.ignite.domain.EventAttribute;

/**
 * Ignite device aware blob event class.
 */
public class IgniteDeviceAwareBlobEvent extends IgniteBlobEvent implements IgniteDeviceAware {

    private static final long serialVersionUID = -3920038506977633926L;
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = EventAttribute.ECU_TYPE)
    private String ecuType;
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = EventAttribute.MQTT_TOPIC)
    private String mqttTopic;

    public IgniteDeviceAwareBlobEvent(String ecuType, String mqttTopic) {
        this.ecuType = ecuType;
        this.mqttTopic = mqttTopic;
    }

    @Override
    public String getEcuType() {
        return ecuType;
    }

    public void setEcuType(String ecuType) {
        this.ecuType = ecuType;
    }

    @Override
    public String getMqttTopic() {
        return mqttTopic;
    }

    public void setMqttTopic(String mqttTopic) {
        this.mqttTopic = mqttTopic;
    }

    @Override
    public String toString() {
        return "IgniteDeviceAwareBlobEvent [ecuType=" + ecuType
                + ", mqttTopic=" + mqttTopic + ", eventId=" + eventId
                + ", version=" + version + ", timestamp=" + timestamp
                + ", eventData=" + eventData + ", requestId=" + requestId
                + ", sourceDeviceId=" + sourceDeviceId + ", vehicleId=" + vehicleId
                + ", targetDeviceId=" + targetDeviceId + "]";
    }

}
