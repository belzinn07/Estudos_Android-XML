package com.example.controledeestoque_xml.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.controledeestoque_xml.data.local.entities.Produto;
import com.example.controledeestoque_xml.data.repository.ProdutoRepository;

import java.util.List;

public class ProdutoViewModel extends AndroidViewModel{
    private ProdutoRepository repository;
    private LiveData<List<Produto>> todosProdutos;

    private MediatorLiveData<Double> valorTotalEstoque = new MediatorLiveData<>();

    private final MutableLiveData<String> mensagemLiveData = new MutableLiveData<>();
    public LiveData<String> mensagem = mensagemLiveData;

    public ProdutoViewModel(@NonNull Application application){
        super(application);
        repository = new ProdutoRepository(application);
        todosProdutos = repository.getTodosProdutos();

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



    public void adicionarProduto(Produto produtoNovo){

              repository.adicionarProduto(produtoNovo);
              mensagemLiveData.setValue("Produto Adicionado com sucesse! ");

    }

    public void atualizarProduto(Produto produtoExistente, Produto produtoNovo){
            atualizarDadosProduto(produtoExistente,produtoNovo);
            repository.atualizarProduto(produtoExistente);
            mensagemLiveData.setValue("Produto Atualizado com sucesso!");
    }

    public void atualizarDadosProduto(Produto produtoExistente, Produto produtoNovo){

        produtoExistente.setNome(produtoNovo.getNome());
        produtoExistente.setPrecoUnitario(produtoNovo.getPrecoUnitario());
        produtoExistente.setQuantidade(produtoNovo.getQuantidade());
        produtoExistente.setCategoria(produtoNovo.getCategoria());

    }

    public void deletarProduto(Produto produto){
        repository.deletarProduto(produto);
    }






}
