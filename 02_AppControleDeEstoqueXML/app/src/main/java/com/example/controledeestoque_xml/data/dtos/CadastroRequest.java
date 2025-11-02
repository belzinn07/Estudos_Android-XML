package com.example.controledeestoque_xml.data.dtos;

import com.google.gson.annotations.SerializedName;

public class CadastroRequest {


    private String nome;
    private String email;
    private String senha;

    public CadastroRequest(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNomeEmpresa() {
        return nome;
    }

    public void setNomeEmpresa(String nome) {
        this.nome = nome;
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
