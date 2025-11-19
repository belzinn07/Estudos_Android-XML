package com.example.controledeestoque_xml.core.utils;

import androidx.room.TypeConverter;

import com.example.controledeestoque_xml.data.local.entities.Categoria;

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
