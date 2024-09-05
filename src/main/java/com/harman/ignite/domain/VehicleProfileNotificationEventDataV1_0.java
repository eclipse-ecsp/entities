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
import lombok.EqualsAndHashCode;
import java.util.ArrayList;
import java.util.List;

/**
 * Vehicle profile notification event data v 1.1 class.
 *
 * @author schunchu
 */
@SuppressWarnings("checkstyle:TypeName")
@EventMapping(id = EventID.VEHICLE_PROFILE_CHANGED_NOTIFICATION_EVENT, version = Version.V1_1)
@EqualsAndHashCode
public class VehicleProfileNotificationEventDataV1_0 extends AbstractEventData {

    private static final long serialVersionUID = -339997586322190202L;

    private List<ChangeDescription> changeDescriptions;

    /**
     * Add change descriptions.
     *
     * @param descriptions
     *         the change descriptions
     */
    public void addChangeDescriptions(List<ChangeDescription> descriptions) {
        if (!descriptions.isEmpty()) {
            if (changeDescriptions == null) {
                this.changeDescriptions = new ArrayList<>();
            }
            changeDescriptions.addAll(descriptions);
        }
    }

    /**
     * Add change description.
     *
     * @param changeDescription
     *         the change description
     */
    public void addChangeDescription(ChangeDescription changeDescription) {
        if (this.changeDescriptions == null) {
            this.changeDescriptions = new ArrayList<>();
        }
        this.changeDescriptions.add(changeDescription);
    }

    /**
     * Gets change descriptions.
     *
     * @return the changeDescription
     */
    public List<ChangeDescription> getChangeDescriptions() {
        return changeDescriptions;
    }

    /**
     * Sets change descriptions.
     *
     * @param changeDescriptions
     *         the changeDescription to set
     */
    public void setChangeDescriptions(List<ChangeDescription> changeDescriptions) {
        this.changeDescriptions = changeDescriptions;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "VehicleProfileNotificationEventDataV1 [changeDescriptions=" + changeDescriptions + "]";
    }

    /**
     * The type Change description.
     */
    public static class ChangeDescription {

        private String key;
        private String path;
        private Object old;
        private Object changed;

        /**
         * Gets key.
         *
         * @return the key
         */
        public String getKey() {
            return key;
        }

        /**
         * Sets key.
         *
         * @param key
         *         the key to set
         */
        public void setKey(String key) {
            this.key = key;
        }

        /**
         * Gets path.
         *
         * @return the hierarchyKey
         */
        public String getPath() {
            return path;
        }

        /**
         * Sets path.
         *
         * @param path
         *         the hierarchyKey to set
         */
        public void setPath(String path) {
            this.path = path;
        }


        /**
         * Gets old.
         *
         * @return the old
         */
        public Object getOld() {
            return old;
        }

        /**
         * Sets old.
         *
         * @param old
         *         the old to set
         */
        public void setOld(Object old) {
            this.old = old;
        }

        /**
         * Gets changed.
         *
         * @return the changed
         */
        public Object getChanged() {
            return changed;
        }

        /**
         * Sets changed.
         *
         * @param changed
         *         the changed to set
         */
        public void setChanged(Object changed) {
            this.changed = changed;
        }

        /*
         * (non-Javadoc)
         *
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "ChangeDescription [key=" + key + ", hierarchyKey=" + path + ", old=" + old + ", changed=" + changed
                    + "]";
        }

    }
}
