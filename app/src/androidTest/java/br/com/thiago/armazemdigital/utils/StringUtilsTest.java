package br.com.thiago.armazemdigital.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void isNullOrEmpty() {
        assertTrue(StringUtils.isNullOrEmpty(null));
        assertTrue(StringUtils.isNullOrEmpty(""));
        assertFalse(StringUtils.isNullOrEmpty("teste"));
    }

    @Test
    public void getSafeStringFromCharSequence() {
        assertEquals("", StringUtils.getSafeStringFromCharSequence(null));
        assertEquals("", StringUtils.getSafeStringFromCharSequence(""));
        assertEquals("teste", StringUtils.getSafeStringFromCharSequence("teste"));
    }
}