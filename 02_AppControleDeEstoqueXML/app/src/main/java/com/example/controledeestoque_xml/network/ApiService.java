package com.example.controledeestoque_xml.network;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
)
}
