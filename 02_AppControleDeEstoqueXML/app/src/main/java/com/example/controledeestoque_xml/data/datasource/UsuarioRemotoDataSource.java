package com.example.controledeestoque_xml.data.datasource;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.controledeestoque_xml.data.remote.ApiService;
import com.example.controledeestoque_xml.data.remote.dtos.CadastroRequest;
import com.example.controledeestoque_xml.data.remote.dtos.AutenticarResponse;
import com.example.controledeestoque_xml.data.remote.dtos.LoginRequest;
import com.example.controledeestoque_xml.core.token.GerenciadorDeToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioRemotoDataSource {
    private final ApiService apiService;
    private final GerenciadorDeToken gerenciadorDeToken;

    public UsuarioRemotoDataSource(Application application, ApiService apiService) {
        this.apiService = apiService;

        this.gerenciadorDeToken = new GerenciadorDeToken(application);
    }

    public LiveData<AutenticarResponse> login(String email, String senha){
        MutableLiveData<AutenticarResponse> usuarioLiveData = new MutableLiveData<>();

        apiService.login(new LoginRequest(email,senha)).enqueue(new Callback<AutenticarResponse>() {
            @Override
            public void onResponse(Call<AutenticarResponse> call, Response<AutenticarResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    AutenticarResponse resposta = response.body();
                    usuarioLiveData.postValue(response.body());


                    gerenciadorDeToken.salvarToken(resposta.getToken());

                    Log.i("API_LOGIN_SUCESSO", "Login bem-sucedido! -> Token Salvo: " + resposta.getToken());
                } else {
                    usuarioLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<AutenticarResponse> call, Throwable t) {
                Log.e("API_LOGIN_ERRO", "Erro: " + t.getMessage());
                usuarioLiveData.postValue(null);
            }
        });

        return usuarioLiveData;
    }

    public LiveData<AutenticarResponse> cadastrar(String nome, String email, String senha) {
        MutableLiveData<AutenticarResponse> usuarioLiveData = new MutableLiveData<>();

        apiService.cadastrar(new CadastroRequest(nome,email,senha)).enqueue(new Callback<AutenticarResponse>() {

            @Override
            public void onResponse(Call<AutenticarResponse> call, Response<AutenticarResponse> response) {
                if (response.isSuccessful() && response.body() != null){

                    AutenticarResponse resposta = response.body();
                    usuarioLiveData.postValue(response.body());
                    gerenciadorDeToken.salvarToken(resposta.getToken());

                    Log.i("API_CADASTRO_SUCESSO", "cadastro bem-sucedido! -> Token Salvo: " + resposta.getToken());
                }else{
                    Log.e("API_CADASTRO_FALHA", "Falha no cadastro: código " + response.code());
                    usuarioLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<AutenticarResponse> call, Throwable t) {
                Log.e("API_CADASTRO_ERRO", "Erro: " + t.getMessage());
                usuarioLiveData.postValue(null);
            }
        });
        return usuarioLiveData;
    }
}
