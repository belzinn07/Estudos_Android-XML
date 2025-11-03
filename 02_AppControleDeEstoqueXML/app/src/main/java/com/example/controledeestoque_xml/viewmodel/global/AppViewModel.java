package com.example.controledeestoque_xml.viewmodel.global;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.controledeestoque_xml.data.local.entities.Usuario;
import com.example.controledeestoque_xml.data.repository.UsuarioRepository;

public class AppViewModel extends ViewModel {

    private final UsuarioRepository usuarioRepository;
    private final LiveData<Boolean> usuarioLogado;


    public AppViewModel(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioLogado = Transformations.map(usuarioRepository.getUsuarioLogado(), usuario -> usuario != null);
    }


    public LiveData<Boolean> isUsuarioLogado() {
        return usuarioLogado;
    }




    public  LiveData<Usuario> login(String email, String senha) {
        return usuarioRepository.login(email, senha);
    }

    public LiveData<Usuario> cadastrar(String nome, String email, String senha) {
        return usuarioRepository.cadastrar(nome, email, senha);
    }

    public void logout() {
        usuarioRepository.logout();
    }

}
