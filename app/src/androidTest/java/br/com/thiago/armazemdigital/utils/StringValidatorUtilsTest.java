package br.com.thiago.armazemdigital.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.text.SpannableStringBuilder;

import org.junit.Test;

public class StringValidatorUtilsTest {

    @Test
    public void testIsNullOrEmpty() {
        assertTrue(StringValidatorUtils.isNullOrEmpty(null));
        assertTrue(StringValidatorUtils.isNullOrEmpty(""));
        assertFalse(StringValidatorUtils.isNullOrEmpty("teste"));
    }

    @Test
    public void testGetSafeStringFromCharSequence() {
        assertEquals("", StringValidatorUtils.getSafeStringFromCharSequence(null));
        assertEquals("", StringValidatorUtils.getSafeStringFromCharSequence(""));
        assertEquals("teste", StringValidatorUtils.getSafeStringFromCharSequence("teste"));
    }

    @Test
    public void testGetSafeStringFromEditable() {
        assertEquals("", StringValidatorUtils.getSafeStringFromEditable(null));
        assertEquals("", StringValidatorUtils.getSafeStringFromEditable(new SpannableStringBuilder("")));
        assertEquals("teste", StringValidatorUtils.getSafeStringFromEditable(new SpannableStringBuilder("teste")));
    }
}