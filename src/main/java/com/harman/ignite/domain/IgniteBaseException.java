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

import com.harman.ignite.entities.IgniteEvent;

import java.util.Map;

/**
 * This exception class is contract between stream processor and service based processors.
 * In order to proceed with DLQ re-processing , this
 * exception should be thrown with retryable flag set to true else exception
 * will be sent directly to the DLQ topic without re-processing.
 * At the time DLQ re-processing , if the exception is not required to be processed
 * further due to whatsoever reason (business logic etc)
 * its mandatory to throw the exception either with IgniteBaseException with
 * retryable set to false or any other exception in order to
 * ensure that this event failure is rightfully stored into DLQ topic for future analysis.
 *
 * @author MaKumari
 */
public class IgniteBaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    /**
     * Exception occurred should re-processed or not.
     */
    private boolean isRetryable;
    /**
     * IgniteEvent , which can be raw or processed one as returned by processor
     * in the event of failure for re-processing.
     */
    private IgniteEvent igniteEvent;

    /**
     * Service context is used to store the necessary stage info where
     * failure occurred so that in the next re-run, processing should be
     * resumed from the same stage.
     */
    private Map<String, Object> serviceContext;

    public IgniteBaseException() {
        super();
    }

    public IgniteBaseException(String msg, boolean isRetryable, Exception cause) {
        super(msg, cause);
        this.isRetryable = isRetryable;
    }

    public boolean isRetryable() {
        return isRetryable;

    }

    public IgniteBaseException withServiceContext(Map<String, Object> serviceContext) {
        this.serviceContext = serviceContext;
        return this;
    }

    public IgniteBaseException withIgniteEvent(IgniteEvent igniteEvent) {
        this.igniteEvent = igniteEvent;
        return this;
    }

    public IgniteEvent getIgniteEvent() {
        return igniteEvent;
    }

    public Map<String, Object> getServiceContext() {
        return serviceContext;
    }

}
