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

package com.harman.ignite.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *  Concurrent hash set test class.
 */
public class ConcurrentHashSetTest {

    @Test
    public void testConcurrentHashSetFunctionalities() {
        ConcurrentHashSet<String> concurrentHashSet = new ConcurrentHashSet<>();
        Assert.assertTrue(concurrentHashSet.add("data1"));
        Assert.assertFalse(concurrentHashSet.isEmpty());
        Assert.assertTrue(concurrentHashSet.contains("data1"));
        Assert.assertFalse(concurrentHashSet.contains("data2"));
        Assert.assertNotNull(concurrentHashSet.iterator());
        List<String> dataSet = new ArrayList<>();
        dataSet.add("data2");
        dataSet.add("data3");
        concurrentHashSet.addAll(dataSet);
        Assert.assertEquals(NumericConstants.THREE, concurrentHashSet.toArray().length);
        Assert.assertTrue(concurrentHashSet.containsAll(dataSet));
        Assert.assertEquals(NumericConstants.THREE, concurrentHashSet.size());
        Assert.assertTrue(concurrentHashSet.remove("data1"));
        Assert.assertTrue(concurrentHashSet.removeAll(dataSet));
        Assert.assertEquals(concurrentHashSet, (new ConcurrentHashSet<>()));
        Assert.assertNotEquals(concurrentHashSet.hashCode(), 0);

    }

}
