package com.example.controledeestoque_xml.ui.principal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.controledeestoque_xml.R;
import com.example.controledeestoque_xml.core.InicializadorDeDependencias;
import com.example.controledeestoque_xml.ui.auth.LoginActivity;
import com.example.controledeestoque_xml.ui.cliente.ListaDeClientesActivity;
import com.example.controledeestoque_xml.viewmodel.AppViewModel;
import com.example.controledeestoque_xml.ui.produto.ListaProdutosActivity;
import com.example.controledeestoque_xml.viewmodel.AppViewModelFactory;


public class MainActivity extends AppCompatActivity {
    private AppViewModel appViewModel;

    private Toolbar toolbar;
    private TextView tvBoasVindas, tvEstoque, tvClientes;
    private Button btnVerClientes, VerProdutos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarViews();
        configurarViewModel();

        btnVerClientes.setOnClickListener(v -> {
         Intent intent = new Intent(this, ListaDeClientesActivity.class);
         startActivity(intent);

        });

        VerProdutos.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListaProdutosActivity.class);
            startActivity(intent);
        });

    }

    private void inicializarViews(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvBoasVindas = findViewById(R.id.tvBoasVindas);
        tvEstoque = findViewById(R.id.tvEstoque);
        tvClientes = findViewById(R.id.tvClientes);
        btnVerClientes = findViewById(R.id.btnVerClientes);
        VerProdutos = findViewById(R.id.VerProdutos);

    }

    private void configurarViewModel() {
        InicializadorDeDependencias inicializador = (InicializadorDeDependencias) getApplication();
        AppViewModelFactory factory = new AppViewModelFactory(inicializador.getUsuarioRepository());
        appViewModel = new ViewModelProvider(this, factory).get(AppViewModel.class);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return onItemSelecionadoMenu(item.getItemId()) || super.onOptionsItemSelected(item);

    }

    public boolean onItemSelecionadoMenu(int itemId) {
        if (itemId == R.id.action_logout) {
            appViewModel.logout();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return false;
    }


}
