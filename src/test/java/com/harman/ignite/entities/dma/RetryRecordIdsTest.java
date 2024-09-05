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

package com.harman.ignite.entities.dma;

import com.harman.ignite.domain.Version;
import com.harman.ignite.utils.ConcurrentHashSet;
import org.junit.Assert;
import org.junit.Test;

/**
 * Retry record ids test class.
 */
public class RetryRecordIdsTest {
    @Test
    public void testDeleteRetryRecordIDdMapping() {
        RetryRecordIds retryRecordIds = new RetryRecordIds();
        retryRecordIds.addRecordId("record1");
        Assert.assertTrue(retryRecordIds.deleteRecordId("record1"));
        Assert.assertFalse(retryRecordIds.deleteRecordId("record2"));
    }

    @Test
    public void testDeleteRetryRecordIDdMappingwithParams() {
        RetryRecordIds retryRecordIds = new RetryRecordIds(Version.V1_0);
        retryRecordIds.addRecordId("record1");
        Assert.assertTrue(retryRecordIds.deleteRecordId("record1"));
        Assert.assertFalse(retryRecordIds.deleteRecordId("record2"));
    }

    @Test
    public void testDeleteRetryRecordIDdMappingwithtwoParams() {
        ConcurrentHashSet<String> recordIds = new ConcurrentHashSet<String>();
        recordIds.add("id1");
        RetryRecordIds retryRecordIds = new RetryRecordIds(Version.V1_0, recordIds);
        retryRecordIds.addRecordId("record1");
        Assert.assertTrue(retryRecordIds.deleteRecordId("record1"));
        Assert.assertFalse(retryRecordIds.deleteRecordId("record2"));
    }

}
