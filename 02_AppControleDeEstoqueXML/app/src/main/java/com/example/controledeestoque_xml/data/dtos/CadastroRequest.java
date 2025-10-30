package com.example.controledeestoque_xml.data.dtos;

public class CadastroRequest {
    private String nomeEmpresa;
    private String email;
    private String senha;

    public CadastroRequest(String nomeEmpresa, String email, String senha) {
        this.nomeEmpresa = nomeEmpresa;
        this.email = email;
        this.senha = senha;
    }
}
