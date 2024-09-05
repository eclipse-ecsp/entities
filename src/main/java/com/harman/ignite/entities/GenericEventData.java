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

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * It holds the payload data in the form of map.
 *
 * @author vpuvvada
 */
@EqualsAndHashCode
public class GenericEventData extends AbstractEventData {

    /**
     * Serialize version.
     */
    private static final long serialVersionUID = -2149490530226401089L;

    private Map<Object, Object> data = new HashMap<>();

    @JsonAnyGetter
    public Map<Object, Object> getData() {
        return data;
    }

    public Optional<Object> getData(Object key) {
        return Optional.ofNullable(data.get(key));
    }

    @JsonAnySetter
    public void set(Object key, Object value) {
        data.put(key, value);
    }

    @Override
    public String toString() {
        return "GenericEventData [data=" + data + ", getData()=" + getData()
                + ", getCustomExtension()=" + getCustomExtension()
                + ", toString()=" + super.toString() + ", hashCode()=" + hashCode()
                + ", getClass()=" + getClass() + "]";
    }

}
