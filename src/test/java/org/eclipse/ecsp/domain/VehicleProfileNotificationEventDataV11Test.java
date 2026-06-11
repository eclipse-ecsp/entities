package org.eclipse.ecsp.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * VehicleProfileNotificationEventDataV11Test class.
 */
public class VehicleProfileNotificationEventDataV11Test {

    private static final int TWO = 2;
    private static final int OLD_VALUE = 30;
    private static final int NEW_VALUE = 40;

    @Test
    public void testAddChangeDescriptionsWithEmptyListDoesNotInitialize() {
        VehicleProfileNotificationEventDataV1_1 eventData = new VehicleProfileNotificationEventDataV1_1();

        eventData.addChangeDescriptions(Collections.emptyList());

        Assert.assertNull(eventData.getChangeDescriptions());
    }

    @Test
    public void testAddChangeDescriptionsInitializesAndAddsValues() {
        VehicleProfileNotificationEventDataV1_1 eventData = new VehicleProfileNotificationEventDataV1_1();
        VehicleProfileNotificationEventDataV1_1.ChangeDescription description =
                new VehicleProfileNotificationEventDataV1_1.ChangeDescription();
        description.setKey("k1");

        eventData.addChangeDescriptions(Collections.singletonList(description));

        Assert.assertNotNull(eventData.getChangeDescriptions());
        Assert.assertEquals(1, eventData.getChangeDescriptions().size());
        Assert.assertEquals("k1", eventData.getChangeDescriptions().get(0).getKey());
    }

    @Test
    public void testAddChangeDescriptionInitializesAndAppends() {
        VehicleProfileNotificationEventDataV1_1 eventData = new VehicleProfileNotificationEventDataV1_1();

        VehicleProfileNotificationEventDataV1_1.ChangeDescription first =
                new VehicleProfileNotificationEventDataV1_1.ChangeDescription();
        first.setKey("first");
        VehicleProfileNotificationEventDataV1_1.ChangeDescription second =
                new VehicleProfileNotificationEventDataV1_1.ChangeDescription();
        second.setKey("second");

        eventData.addChangeDescription(first);
        eventData.addChangeDescription(second);

        Assert.assertEquals(TWO, eventData.getChangeDescriptions().size());
        Assert.assertEquals("first", eventData.getChangeDescriptions().get(0).getKey());
        Assert.assertEquals("second", eventData.getChangeDescriptions().get(1).getKey());
    }

    @Test
    public void testSetGetAndToString() {
        VehicleProfileNotificationEventDataV1_1 eventData = new VehicleProfileNotificationEventDataV1_1();
        List<VehicleProfileNotificationEventDataV1_1.ChangeDescription> descriptions = new ArrayList<>();
        VehicleProfileNotificationEventDataV1_1.ChangeDescription description =
                new VehicleProfileNotificationEventDataV1_1.ChangeDescription();
        description.setPath("/path");
        descriptions.add(description);

        eventData.setChangeDescriptions(descriptions);

        Assert.assertEquals(descriptions, eventData.getChangeDescriptions());
        Assert.assertTrue(eventData.toString().contains("changeDescriptions"));
    }

    @Test
    public void testChangeDescriptionGettersSettersAndToString() {
        VehicleProfileNotificationEventDataV1_1.ChangeDescription description =
                new VehicleProfileNotificationEventDataV1_1.ChangeDescription();

        description.setKey("speed");
        description.setPath("/vehicle/speed");
        description.setOld(OLD_VALUE);
        description.setChanged(NEW_VALUE);

        Assert.assertEquals("speed", description.getKey());
        Assert.assertEquals("/vehicle/speed", description.getPath());
        Assert.assertEquals(OLD_VALUE, description.getOld());
        Assert.assertEquals(NEW_VALUE, description.getChanged());
        Assert.assertTrue(description.toString().contains("hierarchyKey"));
    }
}
