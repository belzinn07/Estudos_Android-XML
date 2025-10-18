package com.example.controledeestoque_xml.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tabela_produto")
public class Produto {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nome_produto")
    private  String nome;
    @ColumnInfo(name = "preco_produto")
    private Double precoUnitario;
    @ColumnInfo(name = "quantidade_produto")
    private int quantidade;
    @ColumnInfo(name = "categoria_produto")
    private Categoria categoria;

    public Produto(String nome, Double precoUnitario, int quantidade){
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.quantidade =quantidade;
    }


    public String getNome() {
        return nome;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
