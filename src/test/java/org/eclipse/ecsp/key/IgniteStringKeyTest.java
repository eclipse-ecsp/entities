package org.eclipse.ecsp.key;

import org.junit.Assert;
import org.junit.Test;

/**
 * IgniteStringKeyTest class.
 */
public class IgniteStringKeyTest {

    @Test
    public void testGetterSetterAndToString() {
        IgniteStringKey key = new IgniteStringKey();
        key.setKey("abc");

        Assert.assertEquals("abc", key.getKey());
        Assert.assertEquals("IgniteStringKey [key=abc]", key.toString());
    }

    @Test
    public void testEqualsWithSameReference() {
        IgniteStringKey key = new IgniteStringKey("same");

        Assert.assertEquals(key, key);
    }

    @Test
    public void testEqualsWithNullAndDifferentType() {
        IgniteStringKey key = new IgniteStringKey("value");

        Assert.assertNotEquals(null, key);
        Assert.assertNotEquals("value", key);
    }

    @Test
    public void testEqualsWhenBothKeysAreNull() {
        IgniteStringKey left = new IgniteStringKey();
        IgniteStringKey right = new IgniteStringKey();

        Assert.assertEquals(left, right);
        Assert.assertEquals(left.hashCode(), right.hashCode());
    }

    @Test
    public void testEqualsWhenLeftKeyNullAndRightNonNull() {
        IgniteStringKey left = new IgniteStringKey();
        IgniteStringKey right = new IgniteStringKey("non-null");

        Assert.assertNotEquals(left, right);
    }

    @Test
    public void testEqualsWhenKeysDiffer() {
        IgniteStringKey left = new IgniteStringKey("a");
        IgniteStringKey right = new IgniteStringKey("b");

        Assert.assertNotEquals(left, right);
    }

    @Test
    public void testEqualsWhenKeysMatch() {
        IgniteStringKey left = new IgniteStringKey("match");
        IgniteStringKey right = new IgniteStringKey("match");

        Assert.assertEquals(left, right);
        Assert.assertEquals(left.hashCode(), right.hashCode());
    }
}
