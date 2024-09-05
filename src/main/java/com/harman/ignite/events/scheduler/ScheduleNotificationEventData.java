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

package com.harman.ignite.events.scheduler;

import com.harman.ignite.annotations.EventMapping;
import com.harman.ignite.domain.EventID;
import com.harman.ignite.domain.Version;
import com.harman.ignite.entities.AbstractEventData;
import lombok.EqualsAndHashCode;

import java.util.Arrays;

/**
 * Event to notify the services , when their schedule is up.
 *
 * @author VKanchepalli
 */
@EventMapping(id = EventID.SCHEDULE_NOTIFICATION_EVENT, version = Version.V1_0)
@EqualsAndHashCode
public class ScheduleNotificationEventData extends AbstractEventData {

    private static final long serialVersionUID = -3736798123313255989L;

    /**
     * store scheduleid.
     */
    private String scheduleId;

    /**
     * Time at which schedule is triggerd.
     */
    private long triggerTimeMs;

    /**
     * payload which needs to be sent to the service when their schedule is up.
     */
    private byte[] payload;

    public ScheduleNotificationEventData() {
        super();
    }

    /**
     * Instantiates a new Schedule notification event data.
     *
     * @param scheduleId
     *         the schedule id
     * @param triggerTimeMs
     *         the trigger time ms
     * @param payload
     *         the payload
     */
    public ScheduleNotificationEventData(String scheduleId, long triggerTimeMs, byte[] payload) {
        super();
        this.scheduleId = scheduleId;
        this.triggerTimeMs = triggerTimeMs;
        this.payload = payload;
    }

    public String getScheduleIdId() {
        return scheduleId;
    }

    public void setScheduleIdId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public long getTriggerTimeMs() {
        return triggerTimeMs;
    }

    public void setTriggerTimeMs(long triggerTimeMs) {
        this.triggerTimeMs = triggerTimeMs;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "ScheduleNotificationEvent [scheduleId=" + scheduleId
                + ", triggerTimeMs=" + triggerTimeMs + ", payload="
                + Arrays.toString(payload) + "]";
    }
}
