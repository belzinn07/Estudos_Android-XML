package com.example.controledeestoque_xml.data.local.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.controledeestoque_xml.data.local.dao.UsuarioDao;
import com.example.controledeestoque_xml.data.local.entities.Usuario;

@Database(entities = {Usuario.class}, version = 1, exportSchema = false)
public abstract class UsuarioDataBase extends  RoomDatabase {
    private static volatile UsuarioDataBase instancia;
    public abstract UsuarioDao usuarioDao();

    public static  UsuarioDataBase getINSTANCE(Context context){
        if (instancia == null){
            synchronized (UsuarioDataBase.class){
                if (instancia == null){
                    instancia = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    UsuarioDataBase.class,
                                    "usuario_db"
                            )
                            .fallbackToDestructiveMigration()
                            .build();;
                }
            }
        }
        return instancia;
    }


}
