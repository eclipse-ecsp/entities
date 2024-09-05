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

import com.harman.ignite.domain.BlobDataV1_0;

/**
 * Default ignite event class.
 */
public class DefaultIgniteEvent extends IgniteEventImpl {

    private static final long serialVersionUID = -8453647847172837817L;

    private static final String DEFAULT = "DEFAULT";

    public DefaultIgniteEvent(byte[] value) {
        init(value);
    }

    private void init(byte[] value) {
        this.setEventId(DEFAULT);
        BlobDataV1_0 blob = new BlobDataV1_0();
        blob.setPayload(value);
        this.setEventData(blob);
    }

}
