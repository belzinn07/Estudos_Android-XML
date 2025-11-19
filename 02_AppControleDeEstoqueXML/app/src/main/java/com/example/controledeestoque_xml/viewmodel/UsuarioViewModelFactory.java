package com.example.controledeestoque_xml.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.controledeestoque_xml.data.repository.UsuarioRepository;

public class UsuarioViewModelFactory implements ViewModelProvider.Factory {

    private final UsuarioRepository repository;

    public UsuarioViewModelFactory(UsuarioRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(UsuarioViewModel.class)) {

            return (T) new UsuarioViewModel(repository);
        }


        throw new IllegalArgumentException("Classe ViewModel desconhecida: " + modelClass.getName());
    }
}
