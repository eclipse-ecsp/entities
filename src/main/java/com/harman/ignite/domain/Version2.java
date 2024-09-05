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

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * enum consisting of version.
 */
public enum Version2 {

    V1_0("1.0"),
    V1_1("1.1"),
    V1_2("1.2"),
    V1_3("1.3"),
    V1_4("1.4"),
    V1_5("1.5"),
    V1_6("1.6"),
    V1_7("1.7"),
    V1_8("1.8"),
    V1_9("1.9"),
    V2_0("2.0"),
    V2_2("2.2"),
    V2_3("2.3"),
    V2_4("2.4"),
    V2_5("2.5"),
    V2_6("2.6"),
    V2_7("2.7"),
    V2_8("2.8"),
    V2_9("2.9"),
    V3_0("3.0"),
    V3_1("3.1"),
    V3_2("3.2"),
    V3_3("3.3"),
    V3_4("3.4"),
    V3_5("3.5"),
    V3_6("3.6"),
    V3_7("3.7"),
    V3_8("3.8"),
    V3_9("3.9"),
    V4_0("4.0"),
    V4_1("4.1"),
    V4_2("4.2"),
    V4_3("4.3"),
    V4_4("4.4"),
    V4_5("4.5"),
    V4_6("4.6"),
    V4_7("4.7"),
    V4_8("4.8"),
    V4_9("4.9"),
    V5_0("5.0"),
    V5_1("5.1"),
    V5_2("5.2"),
    V5_3("5.3"),
    V5_4("5.4"),
    V5_5("5.5"),
    V5_6("5.6"),
    V5_7("5.7"),
    V5_8("5.8"),
    V5_9("5.9"),
    V6_0("6.0"),
    V6_1("6.1"),
    V6_2("6.2"),
    V6_3("6.3"),
    V6_4("6.4"),
    V6_5("6.5"),
    V6_6("6.6"),
    V6_7("6.7"),
    V6_8("6.8"),
    V6_9("6.9"),
    V7_0("7.0"),
    V7_1("7.1"),
    V7_2("7.2"),
    V7_3("7.3"),
    V7_4("7.4"),
    V7_5("7.5"),
    V7_6("7.6"),
    V7_7("7.7"),
    V7_8("7.8"),
    V7_9("7.9"),
    V8_0("8.0"),
    V8_1("8.1"),
    V8_2("8.2"),
    V8_3("8.3"),
    V8_4("8.4"),
    V8_5("8.5"),
    V8_6("8.6"),
    V8_7("8.7"),
    V8_8("8.8"),
    V8_9("8.9"),
    V9_0("9.0"),
    V9_1("9.1"),
    V9_2("9.2"),
    V9_3("9.3"),
    V9_4("9.4"),
    V9_5("9.5"),
    V9_6("9.6"),
    V9_7("9.7"),
    V9_8("9.8"),
    V9_9("9.9"),
    ;

    private String value;

    private Version2(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}