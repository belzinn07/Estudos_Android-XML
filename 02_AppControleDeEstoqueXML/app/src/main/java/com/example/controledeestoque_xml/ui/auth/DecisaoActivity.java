package com.example.controledeestoque_xml.ui.auth;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.controledeestoque_xml.core.InicializadorDeDependencias;
import com.example.controledeestoque_xml.ui.produto.ListaProdutosActivity;
import com.example.controledeestoque_xml.viewmodel.AppViewModel;
// 1. Import que faltava
import com.example.controledeestoque_xml.viewmodel.AppViewModelFactory;

public class DecisaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InicializadorDeDependencias inicializador = (InicializadorDeDependencias) getApplication();

        AppViewModelFactory factory = new AppViewModelFactory(inicializador.getUsuarioRepository());

        AppViewModel appViewModel = new ViewModelProvider(this, factory).get(AppViewModel.class);


        appViewModel.isUsuarioLogado().observe(this, estaLogado -> {

            if (estaLogado) {
                Intent intent = new Intent(DecisaoActivity.this, ListaProdutosActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(DecisaoActivity.this, LoginActivity.class);
                startActivity(intent);
            }
            finish();
        });
    }
}
