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

import com.harman.ignite.annotations.EventMapping;
import com.harman.ignite.entities.AbstractEventData;
import com.harman.ignite.utils.Constants;
import lombok.EqualsAndHashCode;

/**
 * Acknowledgement v 1 0.
 */
@SuppressWarnings("checkstyle:TypeName")
@EqualsAndHashCode
@EventMapping(id = EventID.ACKNOWLEDGEMENT, version = Version.V1_0)
public class AcknowledgementV1_0 extends AbstractEventData {

    private static final long serialVersionUID = -6745785116371883005L;
    private Status status;

    public AcknowledgementV1_0() {
    }

    public AcknowledgementV1_0(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AcknowledgementV1_0 [status=" + status + "]";
    }

    /**
     * The enum Status.
     */
    public enum Status {
        SUCCESS(Constants.SUCCESS),
        FAILURE(Constants.FAILURE),
        CUSTOM_EXTENSION(Constants.CUSTOM_EXTENSION);
        private String value;

        Status(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

}
