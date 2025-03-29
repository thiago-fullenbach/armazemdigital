package br.com.thiago.armazemdigital.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.List;

public class ListUtilTest {

    @Test
    public void isNullOrEmpty() {
        assertTrue(ListUtil.isNullOrEmpty(null));
        assertTrue(ListUtil.isNullOrEmpty(List.of()));
        assertFalse(ListUtil.isNullOrEmpty(List.of("teste")));
    }
}