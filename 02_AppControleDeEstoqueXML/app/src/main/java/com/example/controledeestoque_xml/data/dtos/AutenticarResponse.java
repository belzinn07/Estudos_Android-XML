package com.example.controledeestoque_xml.data.dtos;

import com.google.gson.annotations.SerializedName;

public class AutenticarResponse {
    private boolean sucess;
    private  String token;
    private String mensagem;

    public AutenticarResponse(boolean sucess, String token, String mensagem){
        this.sucess = sucess;
        this.token = token;
        this.mensagem = mensagem;
    }

    public boolean isSucesso(){
        return sucess;
    }

    public String getToken(){
        return token;
    }

    public String getMensagem(){
        return mensagem;
    }


}

