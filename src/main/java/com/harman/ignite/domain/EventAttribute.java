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

/**
 * Defining common event attributes.
 */
public class EventAttribute {

    private EventAttribute() {
        //private constructor
    }

    /*
     * Instead of enum these are string constants because @JsonProperty(value =
     * ) require string constants.
     */
    public static final String EVENTID = "EventID";
    public static final String VERSION = "Version";
    public static final String TIMESTAMP = "Timestamp";
    public static final String DATA = "Data";
    public static final String REQUEST_ID = "RequestId";
    public static final String SOURCE_DEVICE_ID = "SourceDeviceId";
    public static final String VEHICLE_ID = "VehicleId";
    public static final String CUSTOM_EXTENSION = "customExtension";
    public static final String DFF_QUALIFIER = "DFFQualifier";
    public static final String BIZTRANSACTION_ID = "BizTransactionId";
    public static final String MESSAGE_ID = "MessageId";
    public static final String CORRELATION_ID = "CorrelationId";
    public static final String TARGET_DEVICE_ID = "TargetDeviceId";
    public static final String TIMEZONE = "Timezone";
    public static final String EVENT_FILTER = "eventFilter";
    public static final String DEVICE_DELIVERY_CUTOFF = "DeviceDeliveryCutoff";
    public static final String RESPONSE_EXPECTED = "ResponseExpected";
    public static final String BENCH_MODE = "BenchMode";
    public static final String TRC_CTX = "trcCtx";
    public static final String USER_ID = "userId";
    public static final String USER_ROLE = "role";
    public static final String USER_CONTEXT = "UserContext";
    public static final String DUPLICATE_MESSAGE = "DuplicateMessage";
    public static final String ECU_TYPE = "ecuType";
    public static final String MQTT_TOPIC = "mqttTopic";
    public static final String PLATFORM_ID = "PlatformId";

}