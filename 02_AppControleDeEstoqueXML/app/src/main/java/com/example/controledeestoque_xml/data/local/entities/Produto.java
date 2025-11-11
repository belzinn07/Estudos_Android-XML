package com.example.controledeestoque_xml.data.local.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.controledeestoque_xml.abstractions.Nomeavel;

@Entity(tableName = "tabela_produto")
public class Produto implements Parcelable, Nomeavel {
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

    public Produto(String nome, Double precoUnitario, int quantidade, Categoria categoria){
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.quantidade =quantidade;
        this.categoria = categoria;

    }


    protected Produto(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        if (in.readByte() == 0) {
            precoUnitario = null;
        } else {
            precoUnitario = in.readDouble();
        }
        quantidade = in.readInt();
        // Lê a categoria a partir do nome (String)
        String categoriaStr = in.readString();
        categoria = categoriaStr == null ? null : Categoria.valueOf(categoriaStr);
    }

    public static final Creator<Produto> CREATOR = new Creator<Produto>() {
        @Override
        public Produto createFromParcel(Parcel in) {
            return new Produto(in);
        }

        @Override
        public Produto[] newArray(int size) {
            return new Produto[size];
        }
    };

    public int getId() {
        return id;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setId(int id) {
        this.id = id;
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


    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
        if (precoUnitario == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(precoUnitario);
        }
        dest.writeInt(quantidade);
        // Escreve a categoria como o nome dela (String)
        dest.writeString(categoria == null ? null : categoria.name());
    }

    @Override
    public String getDescricao() {
        return "O produto " + this.nome;
    }
}
