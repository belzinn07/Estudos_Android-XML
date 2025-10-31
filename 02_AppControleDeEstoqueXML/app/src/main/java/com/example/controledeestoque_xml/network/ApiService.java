package com.example.controledeestoque_xml.network;

import com.example.controledeestoque_xml.data.dtos.CadastroRequest;
import com.example.controledeestoque_xml.data.dtos.CadastroResponse;
import com.example.controledeestoque_xml.data.dtos.LoginRequest;
import com.example.controledeestoque_xml.data.dtos.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("cadastro")
    Call<CadastroResponse> cadastrar(@Body CadastroRequest cadastroRequest);



}
