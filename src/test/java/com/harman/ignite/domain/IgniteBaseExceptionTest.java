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

import com.harman.ignite.entities.IgniteEventImpl;
import com.harman.ignite.utils.NumericConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * This class tests the IgniteBaseException's getter and setters methods.
 *
 * @author MaKumari
 */
public class IgniteBaseExceptionTest {

    private IgniteBaseException igniteBaseException;
    private String exceptionMessage;
    private String deatiledExceptionMessage;
    private boolean isRetryable;
    private Exception exception;
    private Map<String, Object> context;
    private String key;
    private String value;
    private String updatedValue;
    private IgniteEventImpl igniteEventImpl;
    private String eventId;
    private Version version;
    private String requestId;
    private String bizTransactionId;
    private String messageId;
    private String vehicleId;
    private String newVehicleId;
    private long timestamp;
    private short timeZone;
    private SpeedV1_0 speed;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        exceptionMessage = "Stream closed (through reference chain ...)";
        deatiledExceptionMessage = "Json mapping exception error";
        isRetryable = true;
        exception = new RuntimeException(deatiledExceptionMessage);
        igniteBaseException = new IgniteBaseException(exceptionMessage, isRetryable, exception);
        key = "Stage";
        value = "Stage-Task-1";
        updatedValue = "Stage-Task-2";
        context = new HashMap<String, Object>();
        context.put(key, value);
        igniteEventImpl = new IgniteEventImpl();
        eventId = EventID.SPEED;
        version = Version.V1_0;
        requestId = "Request-1";
        bizTransactionId = "bizTransactionId-1";
        messageId = "messageId-1";
        vehicleId = "vehicleId-1";
        newVehicleId = "vehicleId-2";
        timestamp = System.currentTimeMillis();
        timeZone = NumericConstants.TEN;
        speed = new SpeedV1_0();
        speed.setValue(NumericConstants.TEN);
        igniteEventImpl.setEventId(eventId);
        igniteEventImpl.setVersion(Version.V1_0);
        igniteEventImpl.setRequestId(requestId);
        igniteEventImpl.setBizTransactionId(bizTransactionId);
        igniteEventImpl.setMessageId(messageId);
        igniteEventImpl.setVehicleId(vehicleId);
        igniteEventImpl.setTimestamp(timestamp);
        igniteEventImpl.setTimezone(timeZone);
        igniteEventImpl.setEventData(speed);
    }

    @Test
    public void testIsRetryable() {
        Assert.assertEquals(true, igniteBaseException.isRetryable());
        Assert.assertNotEquals(false, igniteBaseException.isRetryable());
    }

    @Test
    public void testgetIgniteEvent() {
        Assert.assertNull(igniteBaseException.getIgniteEvent());
    }

    @Test
    public void testWithServiceContext() {
        igniteBaseException.withServiceContext(context);
        Assert.assertEquals(value, igniteBaseException.getServiceContext().get(key));
        Assert.assertNotEquals(updatedValue, igniteBaseException.getServiceContext().get(key));
    }

    //This is a test case to check weather the fields of type IgnitEvent,
    // Hashmap in the IgniteBaseExceptionClass is serializable or not
    @Test
    public void testSerializationAndDesereialization() throws InstantiationException, IllegalAccessException {
        IgniteBaseException igniteBaseException = new IgniteBaseException("string",
                true, NullPointerException.class.newInstance());
        context.put(key, updatedValue);
        igniteBaseException.withServiceContext(context);
        igniteBaseException.withIgniteEvent(igniteEventImpl);

        String filename = "/resources/test-serialization.ser";

        // Serialization
        try {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(igniteBaseException);

            out.close();
            file.close();

            System.out.println("Object has been serialized");
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }


        IgniteBaseException object1 = null;

        // Deserialization
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            object1 = (IgniteBaseException) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
            Assert.assertEquals(requestId, object1.getIgniteEvent().getRequestId());
            Assert.assertEquals("Stage-Task-2", object1.getServiceContext().get("Stage"));
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

    }

    @Test
    public void testWithIgniteEvent() {
        igniteBaseException.withIgniteEvent(igniteEventImpl);
        Assert.assertEquals(eventId, igniteEventImpl.getEventId());
        Assert.assertEquals(version, igniteEventImpl.getVersion());
        Assert.assertEquals(requestId, igniteEventImpl.getRequestId());
        Assert.assertEquals(bizTransactionId, igniteEventImpl.getBizTransactionId());
        Assert.assertEquals(messageId, igniteEventImpl.getMessageId());
        Assert.assertEquals(vehicleId, igniteEventImpl.getVehicleId());
        Assert.assertEquals(timestamp, igniteEventImpl.getTimestamp());
        Assert.assertEquals(timeZone, igniteEventImpl.getTimezone());
        Assert.assertEquals(speed, igniteEventImpl.getEventData());
        Assert.assertNotEquals(newVehicleId, igniteEventImpl.getVehicleId());
    }

    @Test
    public void testGetIgniteEvent() {
        igniteEventImpl.setVehicleId(newVehicleId);
        igniteBaseException.withIgniteEvent(igniteEventImpl);
        Assert.assertEquals(eventId, igniteEventImpl.getEventId());
        Assert.assertEquals(version, igniteEventImpl.getVersion());
        Assert.assertEquals(requestId, igniteEventImpl.getRequestId());
        Assert.assertEquals(bizTransactionId, igniteEventImpl.getBizTransactionId());
        Assert.assertEquals(messageId, igniteEventImpl.getMessageId());
        Assert.assertEquals(newVehicleId, igniteEventImpl.getVehicleId());
        Assert.assertEquals(timestamp, igniteEventImpl.getTimestamp());
        Assert.assertEquals(timeZone, igniteEventImpl.getTimezone());
        Assert.assertEquals(speed, igniteEventImpl.getEventData());
        Assert.assertNotEquals(vehicleId, igniteEventImpl.getVehicleId());
    }

    @Test
    public void testGetServiceContext() {
        context.put(key, updatedValue);
        igniteBaseException.withServiceContext(context);
        Assert.assertEquals(updatedValue, igniteBaseException.getServiceContext().get(key));
        Assert.assertNotEquals(value, igniteBaseException.getServiceContext().get(key));
    }

}
