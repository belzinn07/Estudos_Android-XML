package com.example.controledeestoque_xml.data.local;

import androidx.lifecycle.LiveData;

import com.example.controledeestoque_xml.data.local.dao.UsuarioDao;
import com.example.controledeestoque_xml.data.local.entities.Usuario;

import java.util.concurrent.ExecutorService;

public class UsuarioLocalDB {
    private final UsuarioDao usuarioDao;
    private  final ExecutorService executorService;

    public UsuarioLocalDB(UsuarioDao usuarioDao, ExecutorService executorService){
        this.usuarioDao = usuarioDao;
        this.executorService = executorService;
    }

    public void salvarUsuario(Usuario usuario){
        executorService.execute(() ->{
            usuarioDao.inserir(usuario);
        });
    }
    public LiveData<Usuario> getUsuarioLogado(){
        return usuarioDao.getUsuarioLogado();
    }

    public void deletarUsuarioLogado(){
        executorService.execute(usuarioDao::deletarUsuarioLogado);
    }

}
