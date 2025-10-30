package com.example.controledeestoque_xml.data.dtos;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("sucesso")
    private boolean sucess;
    @SerializedName("token")
    private  String token;
    @SerializedName("mensagem")
    private String mensagem;

    public LoginResponse(boolean sucess, String token, String mensagem){
        this.sucess = sucess;
        this.token = token;
        this.mensagem = mensagem;
    }

    public boolean isSucess(){
        return sucess;
    }

    public String getToken(){
        return token;
    }

    public String getMensagem(){
        return mensagem;
    }


}
