package com.example.controledeestoque_xml.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tabela_usuario")
public class Usuario {


    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nome_usuario")
    private String nome;
    @ColumnInfo(name = "email_usuario")
    private String email;
    @ColumnInfo(name = "senha_usuario")
    private String senha;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
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