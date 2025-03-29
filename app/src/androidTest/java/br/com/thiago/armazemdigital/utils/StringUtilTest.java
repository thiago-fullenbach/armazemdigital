package br.com.thiago.armazemdigital.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringUtilTest {

    @Test
    public void isNullOrEmpty() {
        assertTrue(StringUtil.isNullOrEmpty(null));
        assertTrue(StringUtil.isNullOrEmpty(""));
        assertFalse(StringUtil.isNullOrEmpty("teste"));
    }

    @Test
    public void getSafeStringFromCharSequence() {
        assertEquals("", StringUtil.getSafeStringFromCharSequence(null));
        assertEquals("", StringUtil.getSafeStringFromCharSequence(""));
        assertEquals("teste", StringUtil.getSafeStringFromCharSequence("teste"));
    }
}