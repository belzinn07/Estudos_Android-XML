package com.example.controledeestoque_xml.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.controledeestoque_xml.data.local.entities.Usuario;

@Dao
public interface UsuarioDao {

    @Query("SELECT * FROM tabela_usuario LIMIT 1")
    LiveData<Usuario> getUsuarioLogado();

    @Query("DELETE FROM tabela_usuario")
     void deletarUsuarioLogado();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void inserir(Usuario usuario);

}
