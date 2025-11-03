package com.example.controledeestoque_xml.data.repository;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;


import com.example.controledeestoque_xml.data.dtos.AutenticarResponse;
import com.example.controledeestoque_xml.data.local.GerenciadorDeToken;
import com.example.controledeestoque_xml.data.local.UsuarioLocalDB;
import com.example.controledeestoque_xml.data.local.entities.Usuario;
import com.example.controledeestoque_xml.data.remote.UsuarioRemotoDB;



public class UsuarioRepository {
    private final UsuarioLocalDB usuarioLocalDB;
    private final UsuarioRemotoDB usuarioRemotoDB;

    private final GerenciadorDeToken gerenciadorDeToken;


    public  UsuarioRepository(UsuarioRemotoDB usuarioRemotoDB, UsuarioLocalDB usuarioLocalDB, GerenciadorDeToken gerenciadorDeToken){
        this.usuarioRemotoDB = usuarioRemotoDB;
        this.usuarioLocalDB = usuarioLocalDB;
        this.gerenciadorDeToken = gerenciadorDeToken;

    }

    public LiveData<Usuario> login(String email, String senha){
        return processarRespostaRemota(
                usuarioRemotoDB.login(email, senha),
                usuario -> {
                    usuario.setEmail(email);
                    return usuario;
                }
        );
    }

    public LiveData<Usuario> cadastrar(String nome, String email, String senha){
        return processarRespostaRemota(
                usuarioRemotoDB.cadastrar(nome, email, senha),
                usuario -> {
                    usuario.setNome(nome);
                    usuario.setEmail(email);
                    usuario.setSenha(senha);
                    return usuario;
                }
        );

    }

    private LiveData<Usuario> processarRespostaRemota(
            LiveData<AutenticarResponse> responseLiveData,
            java.util.function.Function<Usuario, Usuario> preencherUsuario
    ) {
        MediatorLiveData<Usuario> result = new MediatorLiveData<>();

        result.addSource(responseLiveData, response -> {
            Usuario usuario = null;

            if (response != null && response.isSucesso()) {
                usuario = new Usuario();
                usuario.setToken(response.getToken());
                usuario = preencherUsuario.apply(usuario);
                usuarioLocalDB.salvarUsuario(usuario);
            }

            result.postValue(usuario);
        });

        return result;
    }

    public LiveData<Usuario> getUsuarioLogado(){
        return usuarioLocalDB.getUsuarioLogado();
    }

    public void logout(){
        usuarioLocalDB.deletarUsuarioLogado();
        gerenciadorDeToken.limparToken();
    }

}



