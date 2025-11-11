package com.example.controledeestoque_xml.core.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.controledeestoque_xml.data.local.entities.Produto;

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

    public static boolean validarProduto(Produto produto) {
        if (produto == null) return false;

        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            Log.e("ProdutoViewModel", "Nome inválido");
            return false;
        }

        if (produto.getPrecoUnitario() <= 0) {
            Log.e("ProdutoViewModel", "Preço inválido");
            return false;
        }

        if (produto.getQuantidade() <= 0) {
            Log.e("ProdutoViewModel", "Quantidade inválida");
            return false;
        }

        return true;
    }


}
