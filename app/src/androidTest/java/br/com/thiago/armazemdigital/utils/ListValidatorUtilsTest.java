package br.com.thiago.armazemdigital.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.List;

public class ListValidatorUtilsTest {

    @Test
    public void isNullOrEmpty() {
        assertTrue(ListValidatorUtils.isNullOrEmpty(null));
        assertTrue(ListValidatorUtils.isNullOrEmpty(List.of()));
        assertFalse(ListValidatorUtils.isNullOrEmpty(List.of("teste")));
    }
}