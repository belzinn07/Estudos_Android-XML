package com.example.controledeestoque_xml.service;

import com.example.controledeestoque_xml.model.Produto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdutoService {
    private Map<Long, Produto> banco = new HashMap<>();
    private  Long geradorId;

    public List<Produto> listarProdutos() {
        return new ArrayList<>(banco.values());
    }

    public Produto buscarPorId(Long id){
        return  banco.get(id);
    }

    public boolean salvar(Produto produto){
        if (produto.getId() == null ){
            produto.setId(geradorId++);
        }
        banco.put(produto.getId(),produto);
        return  true;
    }

    public boolean atualizar(Produto produto){
        if (produto.getId() == null && !banco.containsKey(produto.getId())){
            return false;
        }
        banco.put(produto.getId(),produto);
        return true;

    }

    public boolean excluir(Long id){
        return banco.remove(id) != null;
    }

}
