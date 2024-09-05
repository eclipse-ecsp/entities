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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harman.ignite.domain.EventAttribute;
import dev.morphia.annotations.Entity;

import java.io.Serializable;

/**
 * RTC-177051 - Class to create user context on Ignite Event.
 * IgniteEvent will have the added flexibility of adding the user context
 * information such as user id and the role to the ignite event.
 *
 * @author vkanchepalli
 */
@Entity
public class UserContext implements Serializable {

    protected static final long serialVersionUID = -3920038506977633922L;

    @JsonProperty(value = EventAttribute.USER_ID)
    protected String userId;

    @JsonProperty(value = EventAttribute.USER_ROLE)
    protected String role;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}