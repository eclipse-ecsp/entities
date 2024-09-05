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

import java.util.List;


/**
 * This class is used when we have a composite list of IgniteEvents.
 *
 * @author avadakkootko
 */
public class CompositeIgniteEvent extends AbstractIgniteEvent {

    private List<IgniteEvent> nestedEvents;

    public List<IgniteEvent> getNestedEvents() {
        return this.nestedEvents;
    }

    public void setNestedEvents(List<IgniteEvent> nestedEvents) {
        this.nestedEvents = nestedEvents;
    }

}
