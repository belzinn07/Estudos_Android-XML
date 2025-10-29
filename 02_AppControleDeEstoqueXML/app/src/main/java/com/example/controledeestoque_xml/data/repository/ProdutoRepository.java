package com.example.controledeestoque_xml.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.controledeestoque_xml.data.local.dao.ProdutoDao;
import com.example.controledeestoque_xml.data.local.database.ProdutoDataBase;
import com.example.controledeestoque_xml.data.local.entities.Produto;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProdutoRepository {
    private ProdutoDao produtoDao;
    private LiveData<List<Produto>> todosProdutos;

    private final ExecutorService executorService;

    public ProdutoRepository(Application application){
        ProdutoDataBase db = ProdutoDataBase.getINSTANCE(application);
        produtoDao = db.produtoDao();
        todosProdutos= produtoDao.buscarTodosProdutos();
        executorService = Executors.newSingleThreadExecutor();

    }

    public LiveData<List<Produto>> getTodosProdutos() {
        return todosProdutos;
    }

    public void adicionarProduto(Produto produto){
        executorService.execute(() -> produtoDao.inserir(produto));

    }

    public  void atualizarProduto(Produto produto){
        executorService.execute(() -> produtoDao.atualizar(produto));
    }

    public void deletarProduto(Produto produto){
        executorService.execute(() -> produtoDao.deletar(produto));
    }


}
