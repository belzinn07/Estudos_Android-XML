package com.example.controledeestoque_xml.core;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.controledeestoque_xml.view.ui.autenticacao.LoginActivity;
import com.example.controledeestoque_xml.view.ui.produto.MainActivity;
import com.example.controledeestoque_xml.viewmodel.global.AppViewModel;
// 1. Import que faltava
import com.example.controledeestoque_xml.viewmodel.global.AppViewModelFactory;

public class DecisaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InicializadorDeDependencias inicializador = (InicializadorDeDependencias) getApplication();

        AppViewModelFactory factory = new AppViewModelFactory(inicializador.getUsuarioRepository());

        AppViewModel appViewModel = new ViewModelProvider(this, factory).get(AppViewModel.class);

        // 2. Observando o CAMPO público, não um método
        appViewModel.isUsuarioLogado().observe(this, estaLogado -> {
            // 3. Lógica simplificada para o booleano
            if (estaLogado) {
                Intent intent = new Intent(DecisaoActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(DecisaoActivity.this, LoginActivity.class);
                startActivity(intent);
            }
            finish(); // Garante que a tela de decisão sempre seja fechada
        });
    }
}
