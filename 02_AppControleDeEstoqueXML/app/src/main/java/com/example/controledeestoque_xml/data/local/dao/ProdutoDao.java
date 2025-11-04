package com.example.controledeestoque_xml.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.controledeestoque_xml.data.local.entities.Produto;

import java.util.List;

@Dao
public interface ProdutoDao {

    @Insert
    void inserir(Produto produto);
    @Update
    void  atualizar(Produto produto);
    @Delete
    void deletar(Produto produto);

    @Query( "SELECT * FROM tabela_produto ORDER BY id DESC")
    LiveData<List<Produto>> buscarTodosProdutos();


}


