package com.example.controledeestoque_xml.core;

import android.app.Application;

import com.example.controledeestoque_xml.data.local.UsuarioLocalDB;
import com.example.controledeestoque_xml.data.local.dao.UsuarioDao;
import com.example.controledeestoque_xml.data.local.database.ProdutoDataBase;
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
        // 1. Obter a instância do banco de dados Room
        ProdutoDataBase database = ProdutoDataBase.getINSTANCE(this);

        // 2. Usar o banco de dados para obter uma instância real do DAO
        UsuarioDao usuarioDao = database.usuarioDao();

        // 3. Criar os data sources com o DAO real
        UsuarioLocalDB usuarioLocalDB = new UsuarioLocalDB(usuarioDao, Executors.newSingleThreadExecutor());
        UsuarioRemotoDB usuarioRemotoDB = new UsuarioRemotoDB(this, RetrofitClient.getApiService());

        // 4. Criar o repositório
        usuarioRepository = new UsuarioRepository(usuarioRemotoDB, usuarioLocalDB);
    }

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }
}
