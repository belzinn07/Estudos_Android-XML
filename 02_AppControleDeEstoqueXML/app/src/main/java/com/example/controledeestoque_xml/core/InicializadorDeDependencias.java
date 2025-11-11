package com.example.controledeestoque_xml.core;

import android.app.Application;

import com.example.controledeestoque_xml.data.local.GerenciadorDeToken;
import com.example.controledeestoque_xml.data.local.UsuarioLocalDB;
import com.example.controledeestoque_xml.data.local.dao.UsuarioDao;
import com.example.controledeestoque_xml.data.local.database.AppDataBase;
import com.example.controledeestoque_xml.data.remote.UsuarioRemotoDB;
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

        UsuarioLocalDB usuarioLocalDB = new UsuarioLocalDB(usuarioDao, Executors.newSingleThreadExecutor());
        UsuarioRemotoDB usuarioRemotoDB = new UsuarioRemotoDB(this, RetrofitClient.getApiService());
        GerenciadorDeToken gerenciadorDeToken = new GerenciadorDeToken(this);


        usuarioRepository = new UsuarioRepository(usuarioRemotoDB, usuarioLocalDB, gerenciadorDeToken);
    }

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }
}
