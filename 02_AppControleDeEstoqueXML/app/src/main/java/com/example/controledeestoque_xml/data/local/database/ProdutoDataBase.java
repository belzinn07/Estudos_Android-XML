package com.example.controledeestoque_xml.data.local.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.controledeestoque_xml.data.local.dao.ProdutoDao;
import com.example.controledeestoque_xml.data.local.dao.UsuarioDao;
import com.example.controledeestoque_xml.data.local.entities.Produto;
import com.example.controledeestoque_xml.data.local.entities.Usuario;

// 1. Versão incrementada para 2
@Database(entities = {Produto.class, Usuario.class}, version = 2, exportSchema = false)
public abstract class ProdutoDataBase extends  RoomDatabase {
    private static volatile ProdutoDataBase instancia;
    public abstract ProdutoDao produtoDao();
    public abstract UsuarioDao usuarioDao();


    public static  ProdutoDataBase getINSTANCE(Context context){
        if (instancia == null){
            synchronized (ProdutoDataBase.class){
                if (instancia == null){
                    instancia = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ProdutoDataBase.class,
                            "produto_db"
                    )
                            // 2. Este comando irá apagar o banco antigo e criar um novo com a versão 2
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instancia;
    }
}
