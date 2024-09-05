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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.harman.ignite.key.IgniteKey;
import com.harman.ignite.key.IgniteStringKey;

import java.io.IOException;

/**
 * RTC-156940 - Class to de-serialize the IgniteKey data.
 * Currently it is used in ADA/DMF component to de-serialize the data obtained from
 * the Redis Cache.
 *
 * @author mmenon
 */
public class IgniteKeySerDe extends StdDeserializer<IgniteKey<?>> {

    private static final long serialVersionUID = -1675962590432629661L;

    public IgniteKeySerDe() {
        super(IgniteKey.class);
    }

    @Override
    public IgniteKey deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {

        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        ObjectNode actualEventDataNode = mapper.readTree(jp);

        return mapper.readValue(actualEventDataNode.toString(), IgniteStringKey.class);
    }

}
