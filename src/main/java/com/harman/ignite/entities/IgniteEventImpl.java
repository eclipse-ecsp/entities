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

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.morphia.annotations.Entity;

import java.util.Collections;
import java.util.List;

/**
 * Ignite event Impl Class.
 */
@Entity
public class IgniteEventImpl extends AbstractIgniteEvent {

    @Override
    @JsonIgnore
    public List<IgniteEvent> getNestedEvents() {
        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return "IgniteEventImpl [getNestedEvents()=" + getNestedEvents()
                + ", getEventId()=" + getEventId() + ", getVersion()="
                + getVersion() + ", getTimestamp()=" + getTimestamp()
                + ", getEventData()=" + getEventData() + ", getRequestId()="
                + getRequestId() + ", getSourceDeviceId()=" + getSourceDeviceId()
                + ", getVehicleId()=" + getVehicleId()
                + ", getSchemaVersion()=" + getSchemaVersion()
                + ", getDFFQualifier()=" + getDFFQualifier() + ", getTargetDeviceId()="
                + getTargetDeviceId() + ", getMessageId()=" + getMessageId()
                + ", getCorrelationId()=" + getCorrelationId()
                + ", getBizTransactionId()=" + getBizTransactionId() + ", isDeviceRoutable()="
                + isDeviceRoutable() + ", toString()="
                + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
    }

    /**
     * Returns a clone of the event headers except for message id.
     *
     * @return IgniteEventImpl
     */
    public IgniteEventImpl headerClone() {
        IgniteEventImpl clone = new IgniteEventImpl();
        clone.setBenchMode(getBenchMode());
        clone.setBizTransactionId(getBizTransactionId());
        clone.setCorrelationId(getCorrelationId());
        clone.setDeviceDeliveryCutoff(getDeviceDeliveryCutoff());
        clone.setDeviceRoutable(isDeviceRoutable());
        clone.setDFFQualifier(getDFFQualifier());
        clone.setEventId(getEventId());
        clone.setRequestId(getRequestId());
        clone.setResponseExpected(isResponseExpected());
        clone.setSchemaVersion(getSchemaVersion());
        clone.setShoulderTapEnabled(isShoulderTapEnabled());
        clone.setSourceDeviceId(getSourceDeviceId());
        clone.setTargetDeviceId(getTargetDeviceId().orElse(null));
        clone.setDevMsgTopicPrefix(getDevMsgTopicPrefix().orElse(null));
        clone.setDevMsgTopicSuffix(getDevMsgTopicSuffix().orElse(null));
        clone.setTimestamp(getTimestamp());
        clone.setTimezone(getTimezone());
        clone.setTracingContext(getTracingContext());
        clone.setTransientData(isTransientData());
        clone.setVehicleId(getVehicleId());
        clone.setVersion(getVersion());
        return clone;
    }

}
