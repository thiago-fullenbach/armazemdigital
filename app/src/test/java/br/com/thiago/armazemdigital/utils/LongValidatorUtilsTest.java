package br.com.thiago.armazemdigital.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LongValidatorUtilsTest {
    @Test
    public void testUnbox() {
        Long longObj = 10L;
        long result = LongValidatorUtils.unbox(longObj);
        assertEquals(10L, result);
        assertEquals(0L, LongValidatorUtils.unbox(null));
    }
}