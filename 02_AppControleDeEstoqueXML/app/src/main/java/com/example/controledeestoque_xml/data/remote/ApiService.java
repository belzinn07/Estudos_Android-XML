package com.example.controledeestoque_xml.data.remote;

import com.example.controledeestoque_xml.data.remote.dtos.CadastroRequest;

import com.example.controledeestoque_xml.data.remote.dtos.LoginRequest;
import com.example.controledeestoque_xml.data.remote.dtos.AutenticarResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/api/login")
    Call<AutenticarResponse> login(@Body LoginRequest loginRequest);

    @POST("/api/cadastrar")
    Call<AutenticarResponse> cadastrar(@Body CadastroRequest cadastroRequest);

}
