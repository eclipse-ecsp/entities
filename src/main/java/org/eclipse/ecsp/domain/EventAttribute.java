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

package org.eclipse.ecsp.domain;

/**
 * Defining common event attributes.
 */
public class EventAttribute {

    private EventAttribute() {
        //private constructor
    }
    
    /**
     * EventID.
     */
    public static final String EVENTID = "EventID";
    
    /**
     * Version.
     */
    public static final String VERSION = "Version";
    
    /**
     * Timestamp.
     */
    public static final String TIMESTAMP = "Timestamp";
    
    /**
     * Data.
     */
    public static final String DATA = "Data";
    
    /**
     * RequestId.
     */
    public static final String REQUEST_ID = "RequestId";
    
    /**
     * SourceDeviceId.
     */
    public static final String SOURCE_DEVICE_ID = "SourceDeviceId";
    
    /**
     * VehicleId.
     */
    public static final String VEHICLE_ID = "VehicleId";
    
    /**
     * customExtension.
     */
    public static final String CUSTOM_EXTENSION = "customExtension";
    
    /**
     * DFFQualifier.
     */
    public static final String DFF_QUALIFIER = "DFFQualifier";
    
    /**
     * BizTransactionId.
     */
    public static final String BIZTRANSACTION_ID = "BizTransactionId";
    
    /**
     * MessageId.
     */
    public static final String MESSAGE_ID = "MessageId";
    
    /**
     * CorrelationId.
     */
    public static final String CORRELATION_ID = "CorrelationId";
    
    /**
     * TargetDeviceId.
     */
    public static final String TARGET_DEVICE_ID = "TargetDeviceId";
    
    /**
     * Timezone.
     */
    public static final String TIMEZONE = "Timezone";
    
    /**
     * eventFilter.
     */
    public static final String EVENT_FILTER = "eventFilter";
    
    /**
     * DeviceDeliveryCutoff.
     */
    public static final String DEVICE_DELIVERY_CUTOFF = "DeviceDeliveryCutoff";
    
    /**
     * ResponseExpected.
     */
    public static final String RESPONSE_EXPECTED = "ResponseExpected";
    
    /**
     * BenchMode.
     */
    public static final String BENCH_MODE = "BenchMode";
    
    /**
     * trcCtx.
     */
    public static final String TRC_CTX = "trcCtx";
    
    /**
     * userId.
     */
    public static final String USER_ID = "userId";
    
    /**
     * role.
     */
    public static final String USER_ROLE = "role";
    
    /**
     * UserContext.
     */
    public static final String USER_CONTEXT = "UserContext";
    
    /**
     * DuplicateMessage.
     */
    public static final String DUPLICATE_MESSAGE = "DuplicateMessage";
    
    /**
     * ecuType.
     */
    public static final String ECU_TYPE = "ecuType";
    
    /**
     * mqttTopic.
     */
    public static final String MQTT_TOPIC = "mqttTopic";
    
    /**
     * PlatformId.
     */
    public static final String PLATFORM_ID = "PlatformId";
    
    /**
     * KafkaHeaders.
     */
    public static final String KAFKA_HEADERS = "KafkaHeaders";
    
    /**
     * QosLevel.
     */
    public static final String QOS_LEVEL = "QosLevel";

}