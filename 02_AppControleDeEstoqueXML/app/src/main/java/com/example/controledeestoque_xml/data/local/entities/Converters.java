package com.example.controledeestoque_xml.data.local.entities;

import androidx.room.TypeConverter;

public class Converters {
    @TypeConverter
    public static Categoria toCategoria(String value) {
        return value == null ? null : Categoria.valueOf(value);
    }

    @TypeConverter
    public static String fromCategoria(Categoria categoria) {
        return categoria == null ? null : categoria.name();
    }
}
