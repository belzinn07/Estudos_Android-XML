package com.example.controledeestoque_xml.data.local.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.controledeestoque_xml.data.local.dao.ProdutoDao;
import com.example.controledeestoque_xml.data.local.dao.UsuarioDao;
import com.example.controledeestoque_xml.data.local.entities.Produto;
import com.example.controledeestoque_xml.data.local.entities.Usuario;


@Database(entities = {Produto.class, Usuario.class}, version = 2, exportSchema = false)
public abstract class AppDataBase extends  RoomDatabase {
    private static volatile AppDataBase instancia;
    public abstract ProdutoDao produtoDao();
    public abstract UsuarioDao usuarioDao();


    public static AppDataBase getINSTANCE(Context context){
        if (instancia == null){
            synchronized (AppDataBase.class){
                if (instancia == null){
                    instancia = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDataBase.class,
                            "app_database"
                    )

                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instancia;
    }
}
