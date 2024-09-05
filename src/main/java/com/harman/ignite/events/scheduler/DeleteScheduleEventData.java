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

/**
 * Class for removing schedules for a service.
 *
 * @author VKanchepalli
 */
@EventMapping(id = EventID.DELETE_SCHEDULE_EVENT, version = Version.V1_0)
@EqualsAndHashCode
public class DeleteScheduleEventData extends AbstractEventData {

    private static final long serialVersionUID = -3736798123313255987L;

    /**
     * id for deleting schedule event.
     */
    private String scheduleId;

    public DeleteScheduleEventData() {
        super();
    }

    public DeleteScheduleEventData(String id) {
        super();
        this.scheduleId = id;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Override
    public String toString() {
        return "DeleteScheduleEvent [scheduleId=" + scheduleId + "]";
    }
}
