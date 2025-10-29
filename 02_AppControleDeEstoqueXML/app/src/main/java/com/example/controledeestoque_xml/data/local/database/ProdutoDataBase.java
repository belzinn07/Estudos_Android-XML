package com.example.controledeestoque_xml.data.local.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.controledeestoque_xml.data.local.dao.ProdutoDao;
import com.example.controledeestoque_xml.data.local.entities.Produto;

@Database(entities = {Produto.class}, version = 1, exportSchema = false)
public abstract class ProdutoDataBase extends  RoomDatabase {
    private static volatile ProdutoDataBase INSTANCE;
    public abstract ProdutoDao produtoDao();

    public static  ProdutoDataBase getINSTANCE(Context context){
        if (INSTANCE == null){
            synchronized (ProdutoDataBase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ProdutoDataBase.class,
                            "produto_db"
                    )
                            .fallbackToDestructiveMigration()
                            .build();;
                }
            }
        }
        return INSTANCE;
    }


}
