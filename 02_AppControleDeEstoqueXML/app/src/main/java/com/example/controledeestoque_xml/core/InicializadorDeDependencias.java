package com.example.controledeestoque_xml.core;

import android.app.Application;

import com.example.controledeestoque_xml.core.token.GerenciadorDeToken;
import com.example.controledeestoque_xml.data.datasource.UsuarioLocalDataSource;
import com.example.controledeestoque_xml.data.local.dao.UsuarioDao;
import com.example.controledeestoque_xml.data.local.database.AppDataBase;
import com.example.controledeestoque_xml.data.datasource.UsuarioRemotoDataSource;
import com.example.controledeestoque_xml.data.repository.UsuarioRepository;
import com.example.controledeestoque_xml.data.remote.RetrofitClient;

import java.util.concurrent.Executors;

public class InicializadorDeDependencias extends Application {
    private UsuarioRepository usuarioRepository;

    @Override
    public void onCreate(){
        super.onCreate();
        inicializarDependencias();

    }

    private void inicializarDependencias(){

        AppDataBase database = AppDataBase.getINSTANCE(this);

        UsuarioDao usuarioDao = database.usuarioDao();

        UsuarioLocalDataSource usuarioLocalDataSource = new UsuarioLocalDataSource(usuarioDao, Executors.newSingleThreadExecutor());
        UsuarioRemotoDataSource usuarioRemotoDataSource = new UsuarioRemotoDataSource(this, RetrofitClient.getApiService());
        GerenciadorDeToken gerenciadorDeToken = new GerenciadorDeToken(this);


        usuarioRepository = new UsuarioRepository(usuarioRemotoDataSource, usuarioLocalDataSource, gerenciadorDeToken);
    }

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }
}
