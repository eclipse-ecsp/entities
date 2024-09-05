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

import com.harman.ignite.entities.CompositeIgniteEvent;
import com.harman.ignite.entities.EventData;
import com.harman.ignite.entities.IgniteEventImpl;

import java.io.Serializable;

/**
 * Abstract ignite exception data.
 */
public class AbstractIgniteExceptionData implements EventData, Serializable {

    private static final long serialVersionUID = 1L;
    protected long errorTimeInMilis;
    // It holds the serialized byte[] value
    protected  Object rawData;
    protected IgniteEventImpl igniteEvent;
    protected CompositeIgniteEvent compositeIgniteEvent;
    protected Exception exception;

    public long getErrorTimeInMilis() {
        return errorTimeInMilis;
    }

    public void setErrorTimeInMilis(long errorTimeInMilis) {
        this.errorTimeInMilis = errorTimeInMilis;
    }

    public Object getRawData() {
        return rawData;
    }

    public void setRawData(Object rawData) {
        this.rawData = rawData;
    }

    public IgniteEventImpl getIgniteEvent() {
        return igniteEvent;
    }

    public void setIgniteEvent(IgniteEventImpl igniteEvent) {
        this.igniteEvent = igniteEvent;
    }

    public CompositeIgniteEvent getCompositeIgniteEvent() {
        return compositeIgniteEvent;
    }

    public void setCompositeIgniteEvent(CompositeIgniteEvent compositeIgniteEvent) {
        this.compositeIgniteEvent = compositeIgniteEvent;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "AbstractIgniteExceptionData [errorTimeInMilis=" + errorTimeInMilis
                + ", rawData=" + rawData + ", igniteEvent=" + igniteEvent
                + ", compositeIgniteEvent=" + compositeIgniteEvent
                + ", exception=" + exception + "]";
    }

}
