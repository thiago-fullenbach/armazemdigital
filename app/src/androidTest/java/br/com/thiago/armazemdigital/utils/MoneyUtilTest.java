package br.com.thiago.armazemdigital.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MoneyUtilTest {

    @Test
    public void getFormattedMoney() {
        assertEquals("$ 5.00", MoneyUtil.getFormattedMoney(500L));
        assertEquals("$ 0.00", MoneyUtil.getFormattedMoney(null));
        assertEquals("$ 0.00", MoneyUtil.getFormattedMoney(0L));
        assertEquals("$ 4.50", MoneyUtil.getFormattedMoney(450L));
        assertEquals("$ 121.49", MoneyUtil.getFormattedMoney(12149L));
        assertEquals("$ 12,459.49", MoneyUtil.getFormattedMoney(1245949L));
    }

    @Test
    public void moneyLongToString() {
        assertEquals("5.00", MoneyUtil.moneyLongToString(500L));
        assertNull(MoneyUtil.moneyLongToString(null));
        assertEquals("0.00", MoneyUtil.moneyLongToString(0L));
        assertEquals("4.50", MoneyUtil.moneyLongToString(450L));
        assertEquals("121.49", MoneyUtil.moneyLongToString(12149L));
        assertEquals("12459.49", MoneyUtil.moneyLongToString(1245949L));

    }

    @Test
    public void moneyStringToLong() {
        assertEquals((Long) 500L, MoneyUtil.moneyStringToLong("5.00"));
        assertEquals((Long) 0L, MoneyUtil.moneyStringToLong("0.00"));
        assertNull(MoneyUtil.moneyStringToLong(null));
        assertEquals((Long) 450L, MoneyUtil.moneyStringToLong("4.50"));
        assertEquals((Long) 12149L, MoneyUtil.moneyStringToLong("121.49"));
        assertEquals((Long) 1245949L, MoneyUtil.moneyStringToLong("12459.49"));
    }
}