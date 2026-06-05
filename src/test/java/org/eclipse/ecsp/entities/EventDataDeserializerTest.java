package org.eclipse.ecsp.entities;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.io.ContentReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.ecsp.domain.DataDeserializationException;
import org.eclipse.ecsp.domain.SpeedV1_0;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

import static org.eclipse.ecsp.utils.NumericConstants.TEN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * EventDataDeserializerTest Class.
 */
public class EventDataDeserializerTest {

    private ObjectMapper mapper;

    private EventDataDeSerializer eventDataDeSerializer;

    @Before
    public void setup() {
        mapper = new ObjectMapper();
        eventDataDeSerializer = new EventDataDeSerializer();
    }


    @Test
    public void testDeSerialization() throws IOException {
        String speedEvent = "{\"EventID\": \"Speed\",\"Version\": \"1.0\",\"Data\": "
               + "{\"value\":20.0},\"RequestId\":\"d575f05c-23db-4b4e-81d6-b69102bec61b\","
               + "\"MessageId\": \"123456\",\"CorrelationId\": \"1234\",\"BizTransactionId\": \"Biz1234\"}";
        JsonParser parser = mapper.getFactory().createParser(speedEvent);
        DeserializationContext ctxt = mapper.getDeserializationContext();
        EventData event = eventDataDeSerializer.deserialize(parser, ctxt);
        Assert.assertNotNull(event);
        assertEquals(event.getClass(), SpeedV1_0.class);
    }




    @Test
    public void testDeSerializationFailure() throws IOException {
        String speedEvent = "{\"EventID\": \"Speed\",\"Version\": \"1.0\","
                + "\"Data\": {\"value\":20.0},\"RequestId\":\"d575f05c-23db-4b4e-81d6-b69102bec61b\""
                + ",\"MessageId\": \"123456\",\"CorrelationId\": \"1234\",\"BizTransactionId\": \"Biz1234\"}";
        InputStream stream = new ByteArrayInputStream(speedEvent.getBytes(StandardCharsets.UTF_8));
        JsonParser parser = mapper.getFactory().createParser(stream);
        DeserializationContext ctxt = mapper.getDeserializationContext();
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> eventDataDeSerializer.deserialize(parser, ctxt));
        assertEquals(IllegalArgumentException.class, e.getClass());
    }

    @Test
    public void testDeSerializationWithUnexpectedSourceRef() throws IOException {
        /*
         * Java 25 Test: Verifies error handling when sourceRef is an unexpected type.
         * With --add-opens=java.base/java.io=ALL-UNNAMED, reflection always succeeds,
         * so this tests the case where sourceRef is neither StringReader nor String.
         * The implementation catches the ClassCastException and returns null,
         * which causes mapper.readTree(null) to throw IllegalArgumentException.
         */
        String speedEvent = "{\"EventID\": \"Speed\",\"Version\": \"1.0\",\"Data\": {\"value\":20.0},"
               + "\"RequestId\":\"d575f05c-23db-4b4e-81d6-b69102bec61b\",\"MessageId\": \"123456\","
               + "\"CorrelationId\": \"1234\",\"BizTransactionId\": \"Biz1234\"}";
        InputStream stream = new ByteArrayInputStream(speedEvent.getBytes(StandardCharsets.UTF_8));

        // Create a ContentReference with an unexpected type (Boolean instead of StringReader/String)
        ContentReference contentReference = ContentReference.construct(Boolean.TRUE, Boolean.TRUE);
        JsonParser parser = Mockito.mock(JsonParser.class);
        Mockito.when(parser.getParsingContext()).thenReturn(mapper.getFactory()
                .createParser(stream).getParsingContext());
        Mockito.when((ObjectMapper) parser.getCodec()).thenReturn(mapper);
        Mockito.when(parser.getCurrentLocation()).thenReturn(new JsonLocation(contentReference, TEN, TEN, TEN));
        
        DeserializationContext ctxt = mapper.getDeserializationContext();
        
        // When sourceRef is unexpected type (Boolean), casting to String fails
        // getOriginalStringFromSource catches the exception and returns null
        // mapper.readTree(null) then throws IllegalArgumentException
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> eventDataDeSerializer.deserialize(parser, ctxt));
        assertEquals(IllegalArgumentException.class, e.getClass());
    }


}
