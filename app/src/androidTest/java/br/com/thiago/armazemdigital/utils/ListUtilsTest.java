package br.com.thiago.armazemdigital.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.List;

public class ListUtilsTest {

    @Test
    public void isNullOrEmpty() {
        assertTrue(ListUtils.isNullOrEmpty(null));
        assertTrue(ListUtils.isNullOrEmpty(List.of()));
        assertFalse(ListUtils.isNullOrEmpty(List.of("teste")));
    }
}