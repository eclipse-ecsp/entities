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

import com.harman.ignite.utils.NumericConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;

import static com.harman.ignite.utils.NumericConstants.FIVE;
import static com.harman.ignite.utils.NumericConstants.FOUR;

/**
 * This class tests the IgniteExceptionDataV1_1's getter and setters method.
 *
 * @author MaKumari
 */
@SuppressWarnings("checkstyle:TypeName")
public class IgniteExceptionDataV1_1Test {

    private IgniteExceptionDataV1_1 igniteExceptionDataV2;
    private String processorName;
    private String serviceProcessorName;
    private String exceptionMsg;
    private String ioExceptionMsg;
    private String key;
    private String value;
    private String updatedValue;
    private Map<String, Object> context;
    private Exception exception;
    private NestedDLQExceptionData nestedDLQExceptionData;
    private byte[] igniteEventBlob;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        igniteExceptionDataV2 = new IgniteExceptionDataV1_1();
        processorName = "Processor-Test";
        serviceProcessorName = "Service-Processor-Test";
        exceptionMsg = "Stream closed (through reference chain ...)";
        ioExceptionMsg = "File cannot be open.";
        key = "Processor-Stage";
        value = "Processor-Stage-Task1";
        updatedValue = "Processor-Stage-Task2";
        context = new HashMap<String, Object>();
        context.put(key, value);
        exception = new RuntimeException(exceptionMsg);
        igniteEventBlob = new byte[] { NumericConstants.TEN, NumericConstants.TWENTY,
            NumericConstants.THIRTY, NumericConstants.FORTY, NumericConstants.FIFTY };
        nestedDLQExceptionData = new NestedDLQExceptionData(igniteEventBlob, FIVE,
                processorName, exception, context);
    }

    @Test
    public void testGetProcessorName() {
        igniteExceptionDataV2.setProcessorName(processorName);
        Assert.assertEquals(processorName, igniteExceptionDataV2.getProcessorName());
        Assert.assertNotEquals(serviceProcessorName, igniteExceptionDataV2.getProcessorName());
    }

    @Test
    public void testSetProcessorName() {
        igniteExceptionDataV2.setProcessorName(serviceProcessorName);
        Assert.assertEquals(serviceProcessorName, igniteExceptionDataV2.getProcessorName());
        Assert.assertNotEquals(processorName, igniteExceptionDataV2.getProcessorName());
    }

    @Test
    public void testGetRetryCount() {
        igniteExceptionDataV2.setRetryCount(FIVE);
        Assert.assertEquals(FIVE, igniteExceptionDataV2.getRetryCount());
        Assert.assertNotEquals(NumericConstants.FOUR, igniteExceptionDataV2.getRetryCount());
        igniteExceptionDataV2.setRetryCount(FIVE);
        Assert.assertEquals(FIVE, igniteExceptionDataV2.getRetryCount());
        Assert.assertNotEquals(FOUR, igniteExceptionDataV2.getRetryCount());
    }

    @Test
    public void testSetRetryCount() {
        igniteExceptionDataV2.setRetryCount(NumericConstants.TWO);
        Assert.assertEquals(NumericConstants.TWO, igniteExceptionDataV2.getRetryCount());
        Assert.assertNotEquals(NumericConstants.FOUR, igniteExceptionDataV2.getRetryCount());
    }

    @Test
    public void testGetNestedIgniteException() {
        igniteExceptionDataV2.setException(exception);
        Assert.assertEquals(exceptionMsg, igniteExceptionDataV2.getException().getMessage());
        Assert.assertNotEquals(ioExceptionMsg, igniteExceptionDataV2.getException().getMessage());
    }

    @Test
    public void testSetNestedIgniteException() {
        exception = new RuntimeException(ioExceptionMsg);
        igniteExceptionDataV2.setException(exception);
        Assert.assertEquals(ioExceptionMsg, igniteExceptionDataV2.getException().getMessage());
        Assert.assertNotEquals(exceptionMsg, igniteExceptionDataV2.getException().getMessage());
    }

    @Test
    public void testGetServiceContext() {
        igniteExceptionDataV2.setContext(context);
        Assert.assertEquals(value, igniteExceptionDataV2.getContext().get(key));
        Assert.assertNotEquals(updatedValue, igniteExceptionDataV2.getContext().get(key));
    }

    @Test
    public void testSetServiceContext() {
        context.put(key, updatedValue);
        igniteExceptionDataV2.setContext(context);
        Assert.assertEquals(updatedValue, igniteExceptionDataV2.getContext().get(key));
        Assert.assertNotEquals(value, igniteExceptionDataV2.getContext().get(key));
    }

    @Test
    public void testGetNestedDLQExceptionData() {
        exception = new RuntimeException(exceptionMsg);
        context.put(key, updatedValue);
        igniteExceptionDataV2.setContext(context);
        igniteExceptionDataV2.setNestedDLQExceptionData(nestedDLQExceptionData);
        Assert.assertEquals(processorName, nestedDLQExceptionData.getProcessorName());
        Assert.assertArrayEquals(igniteEventBlob, nestedDLQExceptionData.getIgniteEventBlob());
        Assert.assertEquals(updatedValue, nestedDLQExceptionData.getContext().get(key));
        Assert.assertEquals(exceptionMsg, nestedDLQExceptionData.getException().getMessage());
    }

    @Test
    public void testSetNestedDLQExceptionData() {
        context.put(key, value);
        igniteExceptionDataV2.setContext(context);
        igniteExceptionDataV2.setNestedDLQExceptionData(nestedDLQExceptionData);
        Assert.assertEquals(processorName, nestedDLQExceptionData.getProcessorName());
        Assert.assertArrayEquals(igniteEventBlob, nestedDLQExceptionData.getIgniteEventBlob());
        Assert.assertEquals(value, nestedDLQExceptionData.getContext().get(key));
        Assert.assertEquals(exceptionMsg, nestedDLQExceptionData.getException().getMessage());
    }

}
