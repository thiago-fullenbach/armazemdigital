package br.com.thiago.armazemdigital.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MoneyFormatterUtilsTest {

    @Test
    public void getFormattedMoney() {
        assertEquals("$ 5.00", MoneyFormatterUtils.getFormattedMoney(500L));
        assertEquals("$ 0.00", MoneyFormatterUtils.getFormattedMoney(null));
        assertEquals("$ 0.00", MoneyFormatterUtils.getFormattedMoney(0L));
        assertEquals("$ 4.50", MoneyFormatterUtils.getFormattedMoney(450L));
        assertEquals("$ 121.49", MoneyFormatterUtils.getFormattedMoney(12149L));
        assertEquals("$ 12,459.49", MoneyFormatterUtils.getFormattedMoney(1245949L));
    }

    @Test
    public void moneyLongToString() {
        assertEquals("5.00", MoneyFormatterUtils.moneyLongToString(500L));
        assertNull(MoneyFormatterUtils.moneyLongToString(null));
        assertEquals("0.00", MoneyFormatterUtils.moneyLongToString(0L));
        assertEquals("4.50", MoneyFormatterUtils.moneyLongToString(450L));
        assertEquals("121.49", MoneyFormatterUtils.moneyLongToString(12149L));
        assertEquals("12459.49", MoneyFormatterUtils.moneyLongToString(1245949L));

    }

    @Test
    public void moneyStringToLong() {
        assertEquals((Long) 500L, MoneyFormatterUtils.moneyStringToLong("5.00"));
        assertEquals((Long) 0L, MoneyFormatterUtils.moneyStringToLong("0.00"));
        assertNull(MoneyFormatterUtils.moneyStringToLong(null));
        assertEquals((Long) 450L, MoneyFormatterUtils.moneyStringToLong("4.50"));
        assertEquals((Long) 12149L, MoneyFormatterUtils.moneyStringToLong("121.49"));
        assertEquals((Long) 1245949L, MoneyFormatterUtils.moneyStringToLong("12459.49"));
    }
}