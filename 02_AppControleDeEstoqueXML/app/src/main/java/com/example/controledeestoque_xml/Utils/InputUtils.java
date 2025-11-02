package com.example.controledeestoque_xml.Utils;

import android.content.Context;
import android.widget.Toast;

public class InputUtils {

    public static boolean camposInvalidos(Context context, String... campos) {
        for (String campo : campos) {
            if (campo == null || campo.trim().isEmpty()) {
                Toast.makeText(context, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return false;
    }


}
