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

import com.harman.ignite.entities.AbstractEventData;
import lombok.EqualsAndHashCode;
import java.util.Arrays;

/**
 * Abstract blob event data Class.
 */
@EqualsAndHashCode
public abstract class AbstractBlobEventData extends AbstractEventData {


    private static final long serialVersionUID = -3736798123313255985L;
    private Encoding encoding;
    private String eventSource;
    private byte[] payload;

    public Encoding getEncoding() {
        return encoding;
    }

    public void setEncoding(Encoding encoding) {
        this.encoding = encoding;
    }

    public String getEventSource() {
        return eventSource;
    }

    public void setEventSource(String eventSource) {
        this.eventSource = eventSource;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "AbstractBlobEventData [encoding=" + encoding + ", eventSource="
                + eventSource + ", payload=" + Arrays.toString(payload)
                + "]";
    }

    /**
     * The enum Encoding.
     */
    public enum Encoding {
        GPB("gpb"),
        JSON("json");

        private String encode;

        public String getEncode() {
            return encode;
        }

        Encoding(String blobSource) {
            this.encode = blobSource;
        }

        /**
         * Validate.
         *
         * @param encoding
         *         the encoding
         */
        public static void validate(String encoding) {
            boolean flag = true;
            for (Encoding value : Encoding.values()) {
                if (value.getEncode().equals(encoding)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                throw new InvalidBlobSourceException("Invalid blob source : " + encoding + ", provided");
            }
        }
    }
}
