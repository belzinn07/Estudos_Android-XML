package com.example.controledeestoque_xml.ui.produto;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.controledeestoque_xml.R;


public class ListaProdutosActivity extends AppCompatActivity {
    private ProdutoDataBinder binder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder = new ProdutoDataBinder(this);
        binder.inicializar();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return binder.onItemSelecionadoMenu(item.getItemId()) || super.onOptionsItemSelected(item);
    }

    }



