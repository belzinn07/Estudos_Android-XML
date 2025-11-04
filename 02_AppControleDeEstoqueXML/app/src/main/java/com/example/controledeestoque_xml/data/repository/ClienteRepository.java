package com.example.controledeestoque_xml.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.controledeestoque_xml.data.local.dao.ClienteDao;
import com.example.controledeestoque_xml.data.local.database.AppDataBase;
import com.example.controledeestoque_xml.data.local.entities.Cliente;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClienteRepository {
    private ClienteDao clienteDao;
    private LiveData<List<Cliente>> todosClientes;
    private ExecutorService executorService;

    public ClienteRepository(Application application){
        AppDataBase db = AppDataBase.getINSTANCE(application);
        clienteDao = db.clienteDao();
        todosClientes = clienteDao.buscarTodosClientes();
        executorService = Executors.newSingleThreadExecutor();

    }

    public LiveData<List<Cliente>> getTodosClientes() {
        return todosClientes;
    }

    public void adicionarCliente(Cliente cliente){
        executorService.execute(() -> clienteDao.inserir(cliente));
    }

    public void atualizarCliente(Cliente cliente){
        executorService.execute(() -> clienteDao.atualizar(cliente));
    }

    public void excluirCliente(Cliente cliente){
        executorService.execute(() -> clienteDao.deletar(cliente));
    }


}
