package com.example.controledeestoque_xml.network;

import com.example.controledeestoque_xml.data.dtos.CadastroRequest;

import com.example.controledeestoque_xml.data.dtos.LoginRequest;
import com.example.controledeestoque_xml.data.dtos.AutenticarResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("login")
    Call<AutenticarResponse> login(@Body LoginRequest loginRequest);

    @POST("cadastro")
    Call<AutenticarResponse> cadastrar(@Body CadastroRequest cadastroRequest);

}
