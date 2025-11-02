package com.example.controledeestoque_xml.viewmodel.global;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.controledeestoque_xml.data.repository.UsuarioRepository;

public class AppViewModelFactory implements ViewModelProvider.Factory {

    private final UsuarioRepository repository;

    // O construtor recebe o Repositório que será injetado
    public AppViewModelFactory(UsuarioRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @SuppressWarnings("unchecked") // <--- Adicionando a supressão de aviso aqui
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        // Verifica se a classe solicitada é a AppViewModel
        if (modelClass.isAssignableFrom(AppViewModel.class)) {
            // Instancia a AppViewModel passando o Repositório e faz o cast seguro
            return (T) new AppViewModel(repository);
        }

        // Lança exceção para qualquer outra ViewModel que esta fábrica não suporte
        throw new IllegalArgumentException("Classe ViewModel desconhecida: " + modelClass.getName());
    }
}
