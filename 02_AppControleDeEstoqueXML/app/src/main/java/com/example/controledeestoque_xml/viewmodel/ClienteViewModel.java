package com.example.controledeestoque_xml.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.controledeestoque_xml.data.local.entities.Cliente;
import com.example.controledeestoque_xml.data.repository.ClienteRepository;

import java.util.List;

public class ClienteViewModel extends AndroidViewModel {
    private ClienteRepository repository;
    private final MutableLiveData<String> _mensagem = new MutableLiveData<>();
    public final LiveData<String> mensagem = _mensagem;

    public ClienteViewModel(@NonNull Application application) {
        super(application);
        repository = new ClienteRepository(application);
    }

    public LiveData<List<Cliente>> getTodosClientes() {
        return repository.getTodosClientes();
    }


    public void adicionarCliente(Cliente clienteNovo){
        repository.adicionarCliente(clienteNovo);
        _mensagem.setValue("Cliente adicionado com sucesso!");

    }

    public void atualizarCliente(Cliente clienteExistente, Cliente clienteNovo){
        atualizarDadosCliente(clienteExistente,clienteNovo);
        repository.atualizarCliente(clienteExistente);
        _mensagem.setValue("Cliente atualizado com sucesso!");

    }

    private void atualizarDadosCliente(Cliente clienteExistente, Cliente clienteNovo){
        clienteExistente.setNome(clienteNovo.getNome());
        clienteExistente.setEmail(clienteNovo.getEmail());
        clienteExistente.setTelefone(clienteNovo.getTelefone());
    }

    public void excluirCliente(Cliente cliente) {
        repository.excluirCliente(cliente);
        _mensagem.setValue("Cliente excluído com sucesso!");

    }

}
