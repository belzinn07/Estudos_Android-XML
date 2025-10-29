package com.example.controledeestoque_xml.viewmodel.produto;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.controledeestoque_xml.data.local.entities.Produto;
import com.example.controledeestoque_xml.data.repository.ProdutoRepository;

public class AddProdutoViewModel extends AndroidViewModel {

    private final ProdutoRepository repository;
    private final MutableLiveData<String> mensagemLiveData = new MutableLiveData<>();
    public LiveData<String> mensagem = mensagemLiveData;

    public AddProdutoViewModel(@NonNull Application application){
        super(application);
        repository = new ProdutoRepository(application);

    }

    public static boolean validarProduto(Produto produto) {
        if (produto == null) return false;

        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            Log.e("ProdutoViewModel", "Nome inválido");
            return false;
        }

        if (produto.getPrecoUnitario() <= 0) {
            Log.e("ProdutoViewModel", "Preço inválido");
            return false;
        }

        if (produto.getQuantidade() <= 0) {
            Log.e("ProdutoViewModel", "Quantidade inválida");
            return false;
        }

        return true;
    }

    public void salvarouAtualiuzarProduto(Produto produtoExistente, Produto produtoNovo){
        if (produtoExistente != null){
            produtoExistente.setNome(produtoNovo.getNome());
            produtoExistente.setPrecoUnitario(produtoExistente.getPrecoUnitario());
            produtoExistente.setQuantidade(produtoNovo.getQuantidade());
            repository.atualizarProduto(produtoExistente);
            mensagemLiveData.setValue("Produto atualizado com sucesso!");
        }else {
            repository.adicionarProduto(produtoNovo);
            mensagemLiveData.setValue("Produto adicionado com sucesso!");
        }

    }
}
