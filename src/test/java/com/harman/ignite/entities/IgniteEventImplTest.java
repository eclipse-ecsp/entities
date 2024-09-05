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

import com.harman.ignite.domain.Version;
import com.harman.ignite.utils.NumericConstants;
import org.junit.Assert;
import org.junit.Test;

/**
 * Ignite event impl test class.
 */
public class IgniteEventImplTest {

    @Test
    public void igniteEventHeaderCloneTest() {
        IgniteEventImpl testEvent = createIgniteEvent();
        IgniteEventImpl clonedEvent = testEvent.headerClone();
        Assert.assertNotSame(testEvent, clonedEvent);
    }

    private IgniteEventImpl createIgniteEvent() {
        IgniteEventImpl testEvent = new IgniteEventImpl();
        testEvent.setMessageId("123");
        testEvent.setCorrelationId("123");
        testEvent.setTargetDeviceId("target123");
        testEvent.setBizTransactionId("tx123");
        testEvent.setSourceDeviceId("device123");
        testEvent.setEventId("ackEvent");
        testEvent.setRequestId("123");
        testEvent.setTimestamp(NumericConstants.TIME_STAMP);
        testEvent.setVehicleId("vehicleId");
        testEvent.setVersion(Version.V1_0);
        return testEvent;
    }

}
