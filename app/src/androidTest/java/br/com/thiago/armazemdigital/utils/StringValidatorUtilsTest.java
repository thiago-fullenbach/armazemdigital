package br.com.thiago.armazemdigital.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringValidatorUtilsTest {

    @Test
    public void isNullOrEmpty() {
        assertTrue(StringValidatorUtils.isNullOrEmpty(null));
        assertTrue(StringValidatorUtils.isNullOrEmpty(""));
        assertFalse(StringValidatorUtils.isNullOrEmpty("teste"));
    }

    @Test
    public void getSafeStringFromCharSequence() {
        assertEquals("", StringValidatorUtils.getSafeStringFromCharSequence(null));
        assertEquals("", StringValidatorUtils.getSafeStringFromCharSequence(""));
        assertEquals("teste", StringValidatorUtils.getSafeStringFromCharSequence("teste"));
    }
}