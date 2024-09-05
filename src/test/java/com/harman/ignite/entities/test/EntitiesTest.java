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

package com.harman.ignite.entities.test;

import com.harman.ignite.domain.Version;
import com.harman.ignite.entities.IgniteEventImpl;
import com.harman.ignite.utils.NumericConstants;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entities test class.
 */
public class EntitiesTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntitiesTest.class);

    @Test
    public void testIgniteEvent() {
        AckEvent ackEvent = createIgniteEvent();
        Assert.assertEquals(NumericConstants.TIME_STAMP, ackEvent.getTimestamp());
        Assert.assertEquals("device123", ackEvent.getSourceDeviceId());
        Assert.assertEquals("ackEvent", ackEvent.getEventId());
        Assert.assertEquals("123", ackEvent.getRequestId());
        Assert.assertEquals("vehicleId", ackEvent.getVehicleId());
        Assert.assertEquals(Version.V1_0, ackEvent.getVersion());
        Assert.assertEquals("123", ackEvent.getMessageId());
        Assert.assertEquals("123", ackEvent.getCorrelationId());
        Assert.assertEquals(true, ackEvent.getTargetDeviceId().isPresent());
        Assert.assertEquals("target123", ackEvent.getTargetDeviceId().get());
        Assert.assertEquals("tx123", ackEvent.getBizTransactionId());
        Assert.assertEquals(false, ackEvent.isDeviceRoutable());
    }

    @Test
    public void testDeviceRoutable() {
        AckEvent ackEvent = createDeviceRoutingEvent();
        Assert.assertEquals(NumericConstants.TIME_STAMP, ackEvent.getTimestamp());
        Assert.assertEquals("device123", ackEvent.getSourceDeviceId());
        Assert.assertEquals("ackEvent", ackEvent.getEventId());
        Assert.assertEquals("123", ackEvent.getRequestId());
        Assert.assertEquals("vehicleId", ackEvent.getVehicleId());
        Assert.assertEquals(Version.V1_0, ackEvent.getVersion());
        Assert.assertEquals("123", ackEvent.getMessageId());
        Assert.assertEquals("123", ackEvent.getCorrelationId());
        Assert.assertEquals(true, ackEvent.getTargetDeviceId().isPresent());
        Assert.assertEquals("target123", ackEvent.getTargetDeviceId().get());
        Assert.assertEquals("tx123", ackEvent.getBizTransactionId());
        Assert.assertEquals(true, ackEvent.isDeviceRoutable());
    }

    private AckEvent createDeviceRoutingEvent() {
        AckEvent ackEvent = new AckEvent();
        ackEvent.setMessageId("123");
        ackEvent.setCorrelationId("123");
        ackEvent.setTargetDeviceId("target123");
        ackEvent.setBizTransactionId("tx123");
        ackEvent.setSourceDeviceId("device123");
        ackEvent.setEventId("ackEvent");
        ackEvent.setRequestId("123");
        ackEvent.setTimestamp(NumericConstants.TIME_STAMP);
        ackEvent.setVehicleId("vehicleId");
        ackEvent.setVersion(Version.V1_0);
        ackEvent.setDeviceRoutable(true);
        return ackEvent;
    }

    private AckEvent createIgniteEvent() {
        AckEvent ackEvent = new AckEvent();
        ackEvent.setMessageId("123");
        ackEvent.setCorrelationId("123");
        ackEvent.setTargetDeviceId("target123");
        ackEvent.setBizTransactionId("tx123");
        ackEvent.setSourceDeviceId("device123");
        ackEvent.setEventId("ackEvent");
        ackEvent.setRequestId("123");
        ackEvent.setTimestamp(NumericConstants.TIME_STAMP);
        ackEvent.setVehicleId("vehicleId");
        ackEvent.setVersion(Version.V1_0);
        return ackEvent;
    }

    /**
     * Ack event.
     */
    public class AckEvent extends IgniteEventImpl {

    }

}
