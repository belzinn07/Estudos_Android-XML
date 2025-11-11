package com.example.controledeestoque_xml.ui.cliente;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.controledeestoque_xml.R;

public class ListaDeClientesActivity extends AppCompatActivity {

    private ClienteDataBinder binder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder = new ClienteDataBinder(this);
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

