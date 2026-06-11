package org.eclipse.ecsp.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * DataDeserializationExceptionTest class.
 */
public class DataDeserializationExceptionTest {

    @Test
    public void testConstructorWithMessage() {
        DataDeserializationException exception = new DataDeserializationException("deserialization error");

        Assert.assertEquals("deserialization error", exception.getMessage());
        Assert.assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithMessageAndCause() {
        Throwable cause = new IllegalStateException("root cause");
        DataDeserializationException exception = new DataDeserializationException("wrapped", cause);

        Assert.assertEquals("wrapped", exception.getMessage());
        Assert.assertEquals(cause, exception.getCause());
    }
}
