package com.github.ruediste.attachedProperties4J;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AttachedPropertyTest {

    private class TestBearer extends AttachedPropertyBearerBase {

    }

    private final static AttachedProperty<TestBearer, String> test = new AttachedProperty<>(
            "test");
    private TestBearer bearer;

    @Before
    public void before() {
        bearer = new TestBearer();
    }

    @Test
    public void canSet() {
        assertFalse(test.isSet(bearer));
        test.set(bearer, "Hello");
        assertTrue(test.isSet(bearer));
        assertEquals("Hello", test.get(bearer));
    }
}
