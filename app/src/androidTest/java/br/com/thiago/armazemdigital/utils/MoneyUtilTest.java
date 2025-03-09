package br.com.thiago.armazemdigital.utils;

import static org.junit.Assert.assertEquals;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MoneyUtilTest {

    @Test
    public void getFormattedMoney() {
        assertEquals("R$ 5,00", MoneyUtil.getFormattedMoney(500L));
        assertEquals("R$ 5,00", MoneyUtil.getFormattedMoney(500L));
        assertEquals("R$ 0,00", MoneyUtil.getFormattedMoney(null));
        assertEquals("R$ 0,00", MoneyUtil.getFormattedMoney(0L));
        assertEquals("R$ 4,50", MoneyUtil.getFormattedMoney(450L));
        assertEquals("R$ 121,49", MoneyUtil.getFormattedMoney(12149L));
        assertEquals("R$ 12.459,49", MoneyUtil.getFormattedMoney(1245949L));
    }
}