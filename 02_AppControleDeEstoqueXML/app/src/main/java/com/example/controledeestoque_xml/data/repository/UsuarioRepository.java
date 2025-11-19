package com.example.controledeestoque_xml.data.repository;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;


import com.example.controledeestoque_xml.data.remote.dtos.AutenticarResponse;
import com.example.controledeestoque_xml.core.token.GerenciadorDeToken;
import com.example.controledeestoque_xml.data.datasource.UsuarioLocalDataSource;
import com.example.controledeestoque_xml.data.local.entities.Usuario;
import com.example.controledeestoque_xml.data.datasource.UsuarioRemotoDataSource;



public class UsuarioRepository {
    private final UsuarioLocalDataSource usuarioLocalDataSource;
    private final UsuarioRemotoDataSource usuarioRemotoDataSource;

    private final GerenciadorDeToken gerenciadorDeToken;


    public  UsuarioRepository(UsuarioRemotoDataSource usuarioRemotoDataSource, UsuarioLocalDataSource usuarioLocalDataSource, GerenciadorDeToken gerenciadorDeToken){
        this.usuarioRemotoDataSource = usuarioRemotoDataSource;
        this.usuarioLocalDataSource = usuarioLocalDataSource;
        this.gerenciadorDeToken = gerenciadorDeToken;

    }

    public LiveData<Usuario> login(String email, String senha){
        return processarRespostaRemota(
                usuarioRemotoDataSource.login(email, senha),
                usuario -> {
                    usuario.setEmail(email);
                    return usuario;
                }
        );
    }

    public LiveData<Usuario> cadastrar(String nome, String email, String senha){
        return processarRespostaRemota(
                usuarioRemotoDataSource.cadastrar(nome, email, senha),
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
                usuarioLocalDataSource.salvarUsuario(usuario);
            }

            result.postValue(usuario);
        });

        return result;
    }

    public LiveData<Usuario> getUsuarioLogado(){
        return usuarioLocalDataSource.getUsuarioLogado();
    }

    public void logout(){
        usuarioLocalDataSource.deletarUsuarioLogado();
        gerenciadorDeToken.limparToken();
    }

}



