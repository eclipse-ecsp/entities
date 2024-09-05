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

package com.harman.ignite.entity.scheduler;

import com.harman.ignite.entities.AbstractIgniteEntity;
import com.harman.ignite.entities.IgniteEvent;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * ScheduleEntry class for CreateScheduleEvent Class.
 * This Class stores the schedule information to DB.
 *
 * @author VKanchepalli
 */
@Entity("scheduleEntries")
public class ScheduleEntry extends AbstractIgniteEntity {

    /**
     * set Random UUID as scheduleId.
     */
    @Id
    private String scheduleId = UUID.randomUUID().toString();

    /*
     * vehicleId of the create schedule event
     */
    private String vehicleId;

    /*
     * set schedule event
     */
    private IgniteEvent event;

    /*
     * no of times to fire event
     */
    private int firedCount;

    /*
     * created date in milliseconds
     */
    private long createDts;

    /*
     * last updated date in milliseconds
     */
    private long updateDts;

    /*
     * deleted date in milliseconds
     */
    private long deleteDts;

    /*
     * list of firing entries
     */
    private List<Firing> firings = new ArrayList<>();

    /*
     * flag to indicate if schedule is deleted or not
     */
    private boolean deleteStatus = false;

    // RTC 162924
    /**
     * taskId represents the 'taskId' of the Kafka Stream thread that
     * processed the CreateScheduleEvent for this ScheduleEntry. On Scheduler
     * Stream Processor start up or restart or on rebalance of Kafka Streams
     * (results from upscale/downscale of K8 POD replica), this taskId
     * is used to bootstrap schedules per task basis. This enables uniform
     * distribution of schedules across all the available POD replicas
     * at any point in time, thus supporting high availability.
     */
    private String taskId;

    // RTC 165474
    /**
     * Represents the last time the caller service is notified about a missed
     * firing of this schedule. This is used to track that status
     * notification is sent only once in case of multiple re-balance of Kafka
     * Streams (results from upscale/downscale of K8 POD replica).
     */
    private long lastMissedFiringNotificationDts;

    public ScheduleEntry() {
        super();
    }

    /**
     * Instantiates a new Schedule entry.
     *
     * @param vehicleId
     *         the vehicle id
     * @param event
     *         the event
     * @param firedCount
     *         the fired count
     * @param createDts
     *         the create dts
     * @param updateDts
     *         the update dts
     * @param taskId
     *         the task id
     */
    public ScheduleEntry(String vehicleId, IgniteEvent event,
            int firedCount, long createDts, long updateDts, String taskId) {
        this.vehicleId = vehicleId;
        this.event = event;
        this.firedCount = firedCount;
        this.createDts = createDts;
        this.updateDts = updateDts;
        this.taskId = taskId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    /**
     * Sets schedule id .
     *
     * @param id
     *         the id
     * @return the schedule id
     */
    public ScheduleEntry setScheduleIdId(String id) {
        this.scheduleId = id;
        return this;

    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public IgniteEvent getEvent() {
        return event;
    }

    public void setEvent(IgniteEvent event) {
        this.event = event;
    }

    public int getFiredCount() {
        return firedCount;
    }

    public ScheduleEntry setFiredCount(int firedCount) {
        this.firedCount = firedCount;
        return this;
    }

    public long getCreateDts() {
        return createDts;
    }

    public ScheduleEntry setCreateDts(long createDts) {
        this.createDts = createDts;
        return this;
    }

    public long getUpdateDts() {
        return updateDts;
    }

    public ScheduleEntry setUpdateDts(long updateDts) {
        this.updateDts = updateDts;
        return this;
    }

    public long getDeleteDts() {
        return deleteDts;
    }

    public void setDeleteDts(long deleteDts) {
        this.deleteDts = deleteDts;
    }

    public List<Firing> getFirings() {
        return firings;
    }

    public void setFirings(List<Firing> firings) {
        this.firings = firings;
    }

    public boolean isDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public long getLastMissedFiringNotificationDts() {
        return lastMissedFiringNotificationDts;
    }

    public void setLastMissedFiringNotificationDts(long lastMissedFiringNotificationDts) {
        this.lastMissedFiringNotificationDts = lastMissedFiringNotificationDts;
    }

    @Override
    public String toString() {
        return "ScheduleEntry [scheduleId=" + scheduleId + ", vehicleId=" + vehicleId
                + ", event=" + event + ", firedCount=" + firedCount
                + ", createDts=" + createDts + ", updateDts=" + updateDts
                + ", deleteDts=" + deleteDts + ", firings=" + firings
                + ", deleteStatus=" + deleteStatus + ", taskId=" + taskId
                + ", lastMissedFiringNotificationDts=" + lastMissedFiringNotificationDts + "]";
    }

}
