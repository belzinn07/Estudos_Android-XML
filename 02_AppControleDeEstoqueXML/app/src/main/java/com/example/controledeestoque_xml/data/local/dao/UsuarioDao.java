package com.example.controledeestoque_xml.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.controledeestoque_xml.data.local.entities.Usuario;

@Dao
public interface UsuarioDao {

    @Insert
    void inserir(Usuario usuario);

    @Update
    void atualizar(Usuario usuario);

    @Delete
    void deletar(Usuario usuario);

}
