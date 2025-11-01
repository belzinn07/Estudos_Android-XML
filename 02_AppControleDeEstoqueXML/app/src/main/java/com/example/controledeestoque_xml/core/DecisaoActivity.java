package com.example.controledeestoque_xml.core;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.controledeestoque_xml.data.repository.UsuarioRepository;
import com.example.controledeestoque_xml.view.ui.login.LoginActivity;
import com.example.controledeestoque_xml.view.ui.produto.MainActivity;
import com.example.controledeestoque_xml.viewmodel.global.AppViewModel;

public class DecisaoActivity extends AppCompatActivity {

        private AppViewModel appViewModel;

        public DecisaoActivity(AppViewModel appViewModel) {
        this.appViewModel = appViewModel;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InicializadorDeDependencias inicializador= (InicializadorDeDependencias) getApplication();
        AppViewModelFactory factory = new AppViewModelFactory(inicializador.getUsuarioRepository());

        appViewModel = new ViewModelProvider(this, factory).get(AppViewModel.class);

        appViewModel.getUsuarioLogadoLiveData().observe(this, estaLogado -> {
            if (estaLogado != null) {
                Intent intent = new Intent(DecisaoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(DecisaoActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

        });
    }
}
