package com.example.controledeestoque_xml.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.controledeestoque_xml.data.local.entities.Cliente;

import java.util.List;

@Dao
public interface ClienteDao {

    @Insert
    void inserir(Cliente cliente);

    @Update
    void atualizar(Cliente cliente);

    @Delete
    void deletar(Cliente cliente);

    @Query("SELECT * FROM tabela_cliente ORDER BY id DESC")
    LiveData<List<Cliente>> buscarTodosClientes();

}
