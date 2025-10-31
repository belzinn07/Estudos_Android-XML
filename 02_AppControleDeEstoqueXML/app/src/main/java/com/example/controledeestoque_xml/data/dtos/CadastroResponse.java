package com.example.controledeestoque_xml.data.dtos;

import com.google.gson.annotations.SerializedName;

public class CadastroResponse {
    @SerializedName("token")
    private String token;
    @SerializedName("sucesso")
    private boolean sucesso;
    @SerializedName("mensagem")
    private String mensagem;

    public String getToken() {
        return token;
    }

    public boolean isSucesso() { return sucesso; }
    public String getMensagem() { return mensagem; }
}
