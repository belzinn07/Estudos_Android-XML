package com.example.controledeestoque_xml.data.repository;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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

    public  UsuarioRepository(ApiService apiService, UsuarioDao usuarioDao){
        this.apiService = apiService;
        this.usuarioDao = usuarioDao;
    }

    public static synchronized UsuarioRepository getInstance(ApiService apiService, UsuarioDao usuarioDao){
        if (instancia == null){
            instancia = new UsuarioRepository(apiService,usuarioDao);
        }
        return instancia;
    }

    public LiveData<Usuario> loginUsuario(String email, String senha){
        MutableLiveData<Usuario> usuarioLiveData = new MutableLiveData<>();

        apiService.login(new LoginRequest(email,senha)).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    Usuario usuario = response.body().getUsuario();
                })


            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }


}
