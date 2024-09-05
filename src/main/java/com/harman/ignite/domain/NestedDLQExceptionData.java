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

import java.io.Serializable;
import java.util.Map;

/**
 * This class stores the previous history of event exception
 * information which occurred during DLQ re-processing to enable proper analysis.
 *
 * @author MaKumari
 */
public class NestedDLQExceptionData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Byte array of the igniteEvent which was being processed during DLQ re-processing.
     */
    private byte[] igniteEventBlob;
    /**
     * Retry Attempt made during DLQ re-processing.
     */
    private int iteration;
    /**
     * Processor name ,to store the stage where failure occurred
     * so that re-processing can be started from the same stage.
     */
    private String processorName;
    /**
     * Exception occurred during DLQ re-processing.
     */
    private Exception exception;
    /**
     * Context which stores the state info. of processor so that re-processing can be resumed from the same state.
     */
    private Map<String, Object> context;

    public NestedDLQExceptionData() {
    }

    /**
     * Instantiates a new Nested dlq exception data.
     *
     * @param igniteEventBlob
     *         the ignite event blob
     * @param iteration
     *         the iteration
     * @param processorName
     *         the processor name
     * @param exception
     *         the exception
     * @param context
     *         the context
     */
    public NestedDLQExceptionData(byte[] igniteEventBlob, int iteration, String processorName,
            Exception exception,
            Map<String, Object> context) {
        super();
        this.igniteEventBlob = igniteEventBlob;
        this.iteration = iteration;
        this.processorName = processorName;
        this.exception = exception;
        this.context = context;
    }

    public byte[] getIgniteEventBlob() {
        return igniteEventBlob;
    }

    public void setIgniteEventBlob(byte[] igniteEventBlob) {
        this.igniteEventBlob = igniteEventBlob;
    }

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public String getProcessorName() {
        return processorName;
    }

    public void setProcessorName(String processorName) {
        this.processorName = processorName;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "NestedDLQExceptionData [iteration=" + iteration
                + ", processorName=" + processorName + ", exception="
                + exception.getMessage() + ", context=" + context + "]";
    }

}
