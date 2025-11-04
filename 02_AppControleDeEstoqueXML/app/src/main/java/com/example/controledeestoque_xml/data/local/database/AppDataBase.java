package com.example.controledeestoque_xml.data.local.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.controledeestoque_xml.data.local.dao.ClienteDao;
import com.example.controledeestoque_xml.data.local.entities.Cliente;
import com.example.controledeestoque_xml.data.local.entities.Converters;
import com.example.controledeestoque_xml.data.local.dao.ProdutoDao;
import com.example.controledeestoque_xml.data.local.dao.UsuarioDao;
import com.example.controledeestoque_xml.data.local.entities.Produto;
import com.example.controledeestoque_xml.data.local.entities.Usuario;


@Database(entities = {Produto.class, Usuario.class, Cliente.class}, version = 3, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class AppDataBase extends  RoomDatabase {
    private static volatile AppDataBase instancia;
    public abstract ProdutoDao produtoDao();
    public abstract UsuarioDao usuarioDao();
    public abstract ClienteDao clienteDao();



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
