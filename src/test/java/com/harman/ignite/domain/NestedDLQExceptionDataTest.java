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

/**
 * This class tests the NestedDLQExceptionData's getter and setter methods.
 *
 * @author MaKumari
 */
public class NestedDLQExceptionDataTest {

    private NestedDLQExceptionData nestedDLQExceptionData;
    private String processorName;
    private String msg;
    private Map<String, Object> context;
    private String key;
    private String value;
    private String updatedValue;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        nestedDLQExceptionData = new NestedDLQExceptionData();
        processorName = "ProtocolTranslatorPreProcessor";
        msg = "Connection failure!";
        context = new HashMap<String, Object>();
        key = "Stage";
        value = "Stage1-Task1";
        updatedValue = "Stage1-Task2";
        context.put(key, value);
    }

    @Test
    public void testGetIgniteEventBlob() {
        nestedDLQExceptionData = new NestedDLQExceptionData();
        byte[] igniteEventByteArray = new byte[] { NumericConstants.TEN,
            NumericConstants.TWENTY, NumericConstants.THIRTY, NumericConstants.FORTY,
            NumericConstants.FIFTY };
        nestedDLQExceptionData.setIgniteEventBlob(igniteEventByteArray);
        Assert.assertArrayEquals(igniteEventByteArray, nestedDLQExceptionData.getIgniteEventBlob());
    }

    @Test
    public void testSetIgniteEventBlob() {
        nestedDLQExceptionData = new NestedDLQExceptionData();
        byte[] igniteEventByteArray = new byte[] { NumericConstants.ELEVEN,
            NumericConstants.TWENTY_TWO, NumericConstants.THIRTY_THREE,
            NumericConstants.FORTY_FOUR, NumericConstants.FIFTY_FIVE };
        nestedDLQExceptionData.setIgniteEventBlob(igniteEventByteArray);
        Assert.assertArrayEquals(igniteEventByteArray, nestedDLQExceptionData.getIgniteEventBlob());
    }

    @Test
    public void testGetIteration() {
        nestedDLQExceptionData.setIteration(1);
        Assert.assertEquals(1, nestedDLQExceptionData.getIteration());
    }

    @Test
    public void testSetIteration() {
        nestedDLQExceptionData.setIteration(NumericConstants.TWO);
        Assert.assertEquals(NumericConstants.TWO, nestedDLQExceptionData.getIteration());
    }

    @Test
    public void testGetProcessorName() {
        nestedDLQExceptionData.setProcessorName(processorName);
        Assert.assertEquals(processorName, nestedDLQExceptionData.getProcessorName());
    }

    @Test
    public void testSetProcessorName() {
        nestedDLQExceptionData.setProcessorName(processorName);
        Assert.assertEquals(processorName, nestedDLQExceptionData.getProcessorName());
    }

    @Test
    public void testGetException() {
        nestedDLQExceptionData.setException(new RuntimeException(msg));
        Assert.assertEquals(msg, nestedDLQExceptionData.getException().getMessage());
    }

    @Test
    public void testSetException() {
        nestedDLQExceptionData.setException(new RuntimeException(msg));
        Assert.assertEquals(msg, nestedDLQExceptionData.getException().getMessage());
    }

    @Test
    public void testGetContext() {
        nestedDLQExceptionData.setContext(context);
        Assert.assertEquals(value, nestedDLQExceptionData.getContext().get(key));
    }

    @Test
    public void testSetContext() {
        context.put(key, updatedValue);
        nestedDLQExceptionData.setContext(context);
        Assert.assertEquals(updatedValue, nestedDLQExceptionData.getContext().get(key));
    }
}
