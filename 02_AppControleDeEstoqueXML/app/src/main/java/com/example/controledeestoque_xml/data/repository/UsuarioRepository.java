package com.example.controledeestoque_xml.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.controledeestoque_xml.data.dtos.CadastroRequest;
import com.example.controledeestoque_xml.data.dtos.CadastroResponse;
import com.example.controledeestoque_xml.data.dtos.LoginRequest;
import com.example.controledeestoque_xml.data.dtos.LoginResponse;
import com.example.controledeestoque_xml.data.local.dao.UsuarioDao;
import com.example.controledeestoque_xml.data.local.entities.Usuario;
import com.example.controledeestoque_xml.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioRepository {
    private static UsuarioRepository instancia;
    private ApiService apiService;
    private UsuarioDao usuarioDao;

    public UsuarioRepository(ApiService apiService, UsuarioDao usuarioDao) {
        this.apiService = apiService;
        this.usuarioDao = usuarioDao;
    }

    public static synchronized UsuarioRepository getInstance(ApiService apiService, UsuarioDao usuarioDao) {
        if (instancia == null) {
            instancia = new UsuarioRepository(apiService, usuarioDao);
        }
        return instancia;
    }

    public LiveData<Usuario> login(String email, String senha) {
        MutableLiveData<Usuario> usuarioLiveData = new MutableLiveData<>();

        apiService.login(new LoginRequest(email, senha)).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSucesso()) {
                    LoginResponse loginResponse = response.body();
                    Usuario usuario = new Usuario();
                    usuario.setEmail(email);
                    usuario.setToken(loginResponse.getToken());

                    new Thread(() -> {
                        usuarioDao.inserir(usuario);
                        usuarioLiveData.setValue(usuario);
                    }).start();
                } else {
                    usuarioLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                usuarioLiveData.setValue(null);
            }
        });

        return usuarioLiveData;
    }


    public LiveData<Usuario> cadastrar(String nome, String email, String senha){
        MutableLiveData<Usuario> usuarioLiveData = new MutableLiveData<>();

        apiService.cadastrar(new CadastroRequest(nome,email,senha)).enqueue(new Callback<CadastroResponse>() {
            @Override
            public void onResponse(Call<CadastroResponse> call, Response<CadastroResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSucesso()){
                    CadastroResponse cadastroResponse = response.body();
                    Usuario usuario = new Usuario();
                    usuario.setNome(nome);
                    usuario.setEmail(email);
                    usuario.setToken(cadastroResponse.getToken());

                    new Thread(() -> {
                        usuarioDao.inserir(usuario);
                        usuarioLiveData.postValue(usuario);
                    }).start();


                } else {
                    usuarioLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<CadastroResponse> call, Throwable t) {
              usuarioLiveData.setValue(null);
            }
        });

        return usuarioLiveData;

    }

    public


}
