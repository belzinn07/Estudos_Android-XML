package com.example.controledeestoque_xml.abstractions;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseDataBinder<TActivity extends AppCompatActivity>{
    protected final TActivity view;

    protected BaseDataBinder(TActivity view) {
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
