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

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

import static com.harman.ignite.domain.Version.V3_8;

/**
 * Vehicle id device id status test class.
 */
public class VehicleIdDeviceIdStatusTest {

    @Test
    public void testVehicleIdDeviceIdStatus() {
        VehicleIdDeviceIdStatus vehicleIdDeviceIdStatus = new VehicleIdDeviceIdStatus(V3_8, new ConcurrentHashMap<>());
        vehicleIdDeviceIdStatus.addDeviceId("d1", "ACTIVE");
        Assert.assertTrue(vehicleIdDeviceIdStatus.getDeviceIds().containsKey("d1"));
        Assert.assertTrue(vehicleIdDeviceIdStatus.deleteDeviceId("d1"));
        Assert.assertFalse(vehicleIdDeviceIdStatus.getDeviceIds().containsKey("d1"));
    }
}
