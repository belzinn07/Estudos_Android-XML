package com.example.controledeestoque_xml.ui.principal;

import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.controledeestoque_xml.R;
import com.example.controledeestoque_xml.abstractions.BaseDataBinder;
import com.example.controledeestoque_xml.viewmodel.UsuarioViewModel;

public class MainDataBinder extends BaseDataBinder <MainActivity> {

    private UsuarioViewModel usuarioViewModel;

    private Toolbar toolbar;
    private TextView tvBoasVindas, tvEstoque, tvClientes;
    private Button btnVerClientes, VerProdutos;
    protected MainDataBinder(MainActivity view) {
        super(view);
    }

    @Override
    protected void inicializarViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvBoasVindas = findViewById(R.id.tvBoasVindas);
        tvEstoque = findViewById(R.id.tvEstoque);
        tvClientes = findViewById(R.id.tvClientes);
        btnVerClientes = findViewById(R.id.btnVerClientes);
        VerProdutos = findViewById(R.id.VerProdutos);
    }
}
