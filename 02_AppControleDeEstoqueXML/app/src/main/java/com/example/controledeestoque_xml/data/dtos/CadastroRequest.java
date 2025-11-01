package com.example.controledeestoque_xml.data.dtos;

import com.google.gson.annotations.SerializedName;

public class CadastroRequest {


    private String nomeEmpresa;
    private String email;
    private String senha;

    public CadastroRequest(String nomeEmpresa, String email, String senha) {
        this.nomeEmpresa = nomeEmpresa;
        this.email = email;
        this.senha = senha;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
