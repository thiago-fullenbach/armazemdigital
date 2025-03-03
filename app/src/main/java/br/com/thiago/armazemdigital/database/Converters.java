package br.com.thiago.armazemdigital.database;

import androidx.room.TypeConverter;

import java.util.Date;

import br.com.thiago.armazemdigital.model.enums.TipoMovimentacao;

public class Converters {
    @TypeConverter
    public static int fromTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        return tipoMovimentacao.ordinal();
    }

    @TypeConverter
    public static TipoMovimentacao toTipoMovimentacao(int tipoMovimentacaoOrdinal) {
        return TipoMovimentacao.values()[tipoMovimentacaoOrdinal];
    }

    @TypeConverter
    public static Long fromDate(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }
}
