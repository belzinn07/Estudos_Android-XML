package com.example.controledeestoque_xml.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.controledeestoque_xml.model.Produto;
import com.example.controledeestoque_xml.model.ProdutoRepository;

import java.util.List;

public class ProdutoViewModel extends AndroidViewModel{
    private ProdutoRepository produtoRepository;
    private LiveData<List<Produto>> todosProdutos;

    private MediatorLiveData<Double> valorTotalEstoque = new MediatorLiveData<>();

    public ProdutoViewModel(@NonNull Application application){
        super(application);
        produtoRepository = new ProdutoRepository(application);
        todosProdutos = produtoRepository.getTodosProdutos();

        valorTotalEstoque.addSource(todosProdutos, new Observer<List<Produto>>(){
            @Override
            public void onChanged(List<Produto> produtos) {
                double total = 0;
                if(produtos != null){
                    for (Produto produto : produtos){
                        total += produto.getQuantidade() * produto.getPrecoUnitario();
                    }
                }
                valorTotalEstoque.setValue(total);
            }
        });
    }

    public LiveData<List<Produto>> getTodosProdutos() {
        return todosProdutos;
    }

    public LiveData<Double> getValorTotalEstoque() {
        return valorTotalEstoque;
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
/*
    public boolean validarCampos(){
        List<Produto> lista = todosProdutos.getValue();

        for (Produto produto : lista){
            if (produto.getNome().isEmpty() || produto.getPrecoUnitario() <= 0 || produto.getQuantidade() <= 0){
                Log.e("ProdutoViewModel","Preencha todos os campos corretamente");
                return false;
            }
        }
        return true;
    }
*/
}
