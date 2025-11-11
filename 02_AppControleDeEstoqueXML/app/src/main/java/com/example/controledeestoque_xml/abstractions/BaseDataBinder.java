package com.example.controledeestoque_xml.abstractions;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseDataBinder<T extends AppCompatActivity>{
    protected final T view;

    protected BaseDataBinder(T view) {
        this.view = view;
    }

    public final void inicializar(){
        inicializarViews();
        configurarRecyclerView();
        configurarViewModel();
        observarViewModel();
        configurarEventos();
    }

    protected void inicializarViews(){}
    protected void configurarRecyclerView(){}

    protected void configurarViewModel(){}
    protected void observarViewModel(){}
    protected void configurarEventos(){}
}
