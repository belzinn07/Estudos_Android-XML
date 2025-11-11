package com.example.controledeestoque_xml.core.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.example.controledeestoque_xml.abstractions.ConfirmarAcao;
import com.example.controledeestoque_xml.abstractions.Nomeavel;
import com.example.controledeestoque_xml.data.local.entities.Produto;

public class DialogUtils {

    public static void msgConfirmacaoExclusao(
            Nomeavel descricao,
            Context context,
            ConfirmarAcao confirma


    ) {

        new AlertDialog.Builder(context)
                .setTitle("Confirmação")
                .setMessage("Tem certeza que deseja excluir  '" + descricao.getDescricao() + "' ? ")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        confirma.confirmar();

                    }
                })

                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Ação cancelada", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })

                .setCancelable(false)
                .show();

    }

}
