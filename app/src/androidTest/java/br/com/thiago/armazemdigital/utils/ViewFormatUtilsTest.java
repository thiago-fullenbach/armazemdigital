package br.com.thiago.armazemdigital.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.model.enums.TipoMovimentacao;

public class ViewFormatUtilsTest {

    @Test
    public void testGetWeightColorResIdForTypeMovement() {
        assertEquals(R.color.green, ViewFormatUtils.getWeightColorResIdForTypeMovement(TipoMovimentacao.ENTRADA));
        assertEquals(R.color.red, ViewFormatUtils.getWeightColorResIdForTypeMovement(TipoMovimentacao.SAIDA));
        assertEquals(R.color.white, ViewFormatUtils.getWeightColorResIdForTypeMovement(TipoMovimentacao.AJUSTE));
    }

    @Test
    public void testGetWeightFormattedStringResIdForTypeMovement() {
        assertEquals(R.string.placeholder_stock_in, ViewFormatUtils.getWeightFormattedStringResIdForTypeMovement(TipoMovimentacao.ENTRADA));
        assertEquals(R.string.placeholder_stock_out, ViewFormatUtils.getWeightFormattedStringResIdForTypeMovement(TipoMovimentacao.SAIDA));
        assertEquals(R.string.placeholder_stock_default, ViewFormatUtils.getWeightFormattedStringResIdForTypeMovement(TipoMovimentacao.AJUSTE));
    }
}