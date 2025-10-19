package com.example.controledeestoque_xml.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.controledeestoque_xml.model.Produto;
import com.example.controledeestoque_xml.model.ProdutoRepository;

import java.util.List;

public class ProdutoViewModel extends AndroidViewModel{
    private ProdutoRepository produtoRepository;
    private LiveData<List<Produto>> todosProdutos;

    public ProdutoViewModel(@NonNull Application application){
        super(application);
        produtoRepository = new ProdutoRepository(application);
        todosProdutos = produtoRepository.getTodosProdutos();
    }

    public LiveData<List<Produto>> getTodosProdutos() {
        return todosProdutos;
    }

    public void adicionarProduto(Produto produto){
        produtoRepository.adicionarProduto(produto);
    }

    public  void atualizarProduto(Produto produto){
        produtoRepository.atualizarProduto(produto);
    }

    public void deletarProduto(Produto produto){
        produtoRepository.deletarProduto(produto);
    }

}
