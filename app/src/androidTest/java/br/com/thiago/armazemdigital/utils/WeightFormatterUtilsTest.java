package br.com.thiago.armazemdigital.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class WeightFormatterUtilsTest {

    @Test
    public void testGetFormattedWeight() {
        assertEquals("0.000", WeightFormatterUtils.getFormattedWeight(null));
        assertEquals("0.000", WeightFormatterUtils.getFormattedWeight(0L));
        assertEquals("1.000", WeightFormatterUtils.getFormattedWeight(1000L));
    }

    @Test
    public void testWeightLongToString() {
        assertNull(WeightFormatterUtils.weightLongToString(null));
        assertEquals("0.000", WeightFormatterUtils.weightLongToString(0L));
        assertEquals("1.000", WeightFormatterUtils.weightLongToString(1000L));
    }

    @Test
    public void testWeightStringToLong() {
        assertNull(WeightFormatterUtils.weightStringToLong(null));
        assertEquals(Long.valueOf(0), WeightFormatterUtils.weightStringToLong("0"));
        assertEquals(Long.valueOf(1000), WeightFormatterUtils.weightStringToLong("1"));
    }
}