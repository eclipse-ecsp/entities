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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.harman.ignite.domain.EventAttribute;

import java.io.Serializable;
import java.util.Optional;

/**
 * It represents the Data in Ignite event specification.
 *
 * @author avadakkootko
 *
 */

public abstract class AbstractEventData implements EventData, Serializable {

    private static final long serialVersionUID = -207501751223603791L;
    // used to set custom data, (eg: specific to customer)
    @JsonProperty(value = EventAttribute.CUSTOM_EXTENSION)
    private Object customExtension;

    /**
     * JsonIgnore is required to properly deserialize the custom object.
     * If JsonIgnore annotation is not present then it will deserialize as
     * customData:{present=false | true} because we are returning optional.
     *
     * @return Object
     */
    @JsonIgnore
    public Optional<Object> getCustomExtension() {
        return Optional.ofNullable(this.customExtension);
    }

    public void setCustomExtension(Object customExtension) {
        this.customExtension = customExtension;
    }

    @Override
    public String toString() {
        return "AbstractEventData [customExtension=" + customExtension + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customExtension != null) ? customExtension.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AbstractEventData other = (AbstractEventData) obj;
        if (customExtension == null) {
            if (other.customExtension != null) {
                return false;
            }
        } else if (!customExtension.equals(other.customExtension)) {
            return false;
        }
        return true;
    }
}
