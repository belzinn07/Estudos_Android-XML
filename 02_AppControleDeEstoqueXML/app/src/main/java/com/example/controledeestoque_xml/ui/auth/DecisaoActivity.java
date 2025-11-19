package com.example.controledeestoque_xml.ui.auth;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.controledeestoque_xml.core.InicializadorDeDependencias;
import com.example.controledeestoque_xml.ui.principal.MainActivity;
import com.example.controledeestoque_xml.viewmodel.UsuarioViewModel;
// 1. Import que faltava
import com.example.controledeestoque_xml.viewmodel.UsuarioViewModelFactory;

public class DecisaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InicializadorDeDependencias inicializador = (InicializadorDeDependencias) getApplication();

        UsuarioViewModelFactory factory = new UsuarioViewModelFactory(inicializador.getUsuarioRepository());

        UsuarioViewModel usuarioViewModel = new ViewModelProvider(this, factory).get(UsuarioViewModel.class);


        usuarioViewModel.isUsuarioLogado().observe(this, estaLogado -> {

            if (estaLogado) {
                Intent intent = new Intent(DecisaoActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(DecisaoActivity.this, LoginActivity.class);
                startActivity(intent);
            }
            finish();
        });
    }
}
