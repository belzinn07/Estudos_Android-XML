package com.example.controledeestoque_xml.core;

import android.app.Application;

import com.example.controledeestoque_xml.data.local.UsuarioLocalDB;
import com.example.controledeestoque_xml.data.local.dao.UsuarioDao;
import com.example.controledeestoque_xml.data.remote.UsuarioRemotoDB;
import com.example.controledeestoque_xml.data.repository.UsuarioRepository;
import com.example.controledeestoque_xml.network.RetrofitClient;

import java.util.concurrent.Executors;

public class InicializadorDeDependencias extends Application {
    private UsuarioRepository usuarioRepository;

    @Override
    public void onCreate(){
        super.onCreate();
        inicializarDependencias();

    }

    private void inicializarDependencias(){
        UsuarioDao usuarioDao = null;
        UsuarioRemotoDB usuarioRemotoDB = null;

        UsuarioLocalDB usuarioLocalDB = new UsuarioLocalDB(usuarioDao, Executors.newSingleThreadExecutor());
        usuarioRemotoDB = new UsuarioRemotoDB(this, RetrofitClient.getApiService());

        usuarioRepository = new UsuarioRepository(usuarioRemotoDB,usuarioLocalDB);

    }

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }
}
