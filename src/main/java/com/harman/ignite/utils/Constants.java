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

package com.harman.ignite.utils;

import com.harman.ignite.domain.Version;

/**
 * Constants.
 */
public class Constants {
    private Constants() {
    }

    public static final String HYPHEN = "-";

    public static final String SUCCESS = "SUCCESS";
    public static final String FAILURE = "FAILURE";
    public static final String FAIL = "FAIL";
    public static final String CUSTOM_EXTENSION = "CUSTOM_EXTENSION";
    public static final String VALIDATION_MESSAGE = "received either null or empty";
    public static final String GENERIC_EVT_DATA_VALUE = "com.harman.ignite.entities.GenericEventData";

    public static final long DEFAULT_DELIVERY_CUTOFF = -1L;

    public static final String DEFAULT_VERSION = Version.V1_0.getValue();

    // PIIConfig Types
    public static final String VIN = "vin";

    public static final String DESERIALIZATION_LARGE_EVENT_ERROR = "Unexpected error during "
            + "deserialization of event's json string having length larger than 32kb";

    public static final String DESERIALIZATION_EVENT_ERROR = "Unexpected error during"
            + " deserialization of event's json string ";

    public static final String STRING_READER_FIELD_STR = "str";

    public static final String CLIENT_CREDENTIALS = "client_credentials";
    public static final String RESOURCE_OWNER_PSWRD_CREDENTIALS = "password";

    public static final String COMMA = ",";
    public static final String ASTRIX = "*";

}
