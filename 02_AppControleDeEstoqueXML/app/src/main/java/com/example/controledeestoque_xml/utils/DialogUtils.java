package com.example.controledeestoque_xml.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.example.controledeestoque_xml.abstractions.ConfirmarAcao;
import com.example.controledeestoque_xml.data.local.entities.Produto;

public class DialogUtils {

    public static void msgConfirmacaoExclusao(
            Produto produto,
            Context context,
            ConfirmarAcao confirma


    ) {

        new AlertDialog.Builder(context)
                .setTitle("Confirmação")
                .setMessage("Tem certeza que deseja excluir este produto: (" + produto.getNome() + ")? ")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        confirma.confirmar();

                    }
                })

                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Exclusão cancelada", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })

                .setCancelable(false)
                .show();

    }

}
