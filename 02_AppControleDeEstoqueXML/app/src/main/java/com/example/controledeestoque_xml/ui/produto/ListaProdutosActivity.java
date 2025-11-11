package com.example.controledeestoque_xml.ui.produto;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controledeestoque_xml.R;
import com.example.controledeestoque_xml.abstractions.ConfirmarAcao;
import com.example.controledeestoque_xml.core.InicializadorDeDependencias;
import com.example.controledeestoque_xml.data.local.entities.Produto;
import com.example.controledeestoque_xml.utils.DialogUtils;
import com.example.controledeestoque_xml.ui.adapter.OnExcluirItemListener;
import com.example.controledeestoque_xml.ui.adapter.OnItemClickListener;
import com.example.controledeestoque_xml.ui.adapter.ProdutoAdapter;
import com.example.controledeestoque_xml.ui.autenticacao.LoginActivity;
import com.example.controledeestoque_xml.viewmodel.global.AppViewModel;
import com.example.controledeestoque_xml.viewmodel.global.AppViewModelFactory;
import com.example.controledeestoque_xml.viewmodel.produto.ProdutoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListaProdutosActivity extends AppCompatActivity {
    private ProdutoViewModel produtoViewModel;

    private AppViewModel appViewModel;
    private ProdutoAdapter adapter;
    private TextView textValorTotal;
    private Toolbar toolbar;


    private static final int ADD_PRODUTO_REQUEST_CODE = 1;
    private static final int EDIT_PRODUTO_REQUEST_CODE = 2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        FloatingActionButton fabAddProduto = findViewById(R.id.fabAddProduto);
        textValorTotal = findViewById(R.id.textValorTotal);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new ProdutoAdapter();
        recyclerView.setAdapter(adapter);

        produtoViewModel = new ViewModelProvider(this).get(ProdutoViewModel.class);


        InicializadorDeDependencias inicializador = (InicializadorDeDependencias) getApplication();
        AppViewModelFactory factory = new AppViewModelFactory(inicializador.getUsuarioRepository());
        appViewModel = new ViewModelProvider(this, factory).get(AppViewModel.class);

        produtoViewModel.getTodosProdutos().observe(this, new Observer<List<Produto>>() {
            @Override
            public void onChanged(List<Produto> produtos) {
                adapter.setProdutos(produtos);

            }
        });

        produtoViewModel.getValorTotalEstoque().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double valorTotal) {
                if (valorTotal != null) {
                    textValorTotal.setText(String.format("R$ %.2f", valorTotal));

                }

            }
        });

        fabAddProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaProdutosActivity.this, AddProdutoActivity.class);
                startActivityForResult(intent, ADD_PRODUTO_REQUEST_CODE);
            }
        });

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Produto produto) {
                Intent intent = new Intent(ListaProdutosActivity.this, AddProdutoActivity.class);
                intent.putExtra("PRODUTO EXTRA", produto);
                startActivityForResult(intent, EDIT_PRODUTO_REQUEST_CODE);
            }


        });

        adapter.setOnExcluirProdutoListener(new OnExcluirItemListener() {
            @Override
            public void onExcluir(Produto produto) {
                chamarDialogoDeExclusao(produto);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            appViewModel.logout();
            Intent intent = new Intent(ListaProdutosActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void chamarDialogoDeExclusao(Produto produto) {
        DialogUtils.msgConfirmacaoExclusao(
                produto,
                this,
                        new ConfirmarAcao() {
                            @Override
                            public void confirmar() {

                                produtoViewModel.deletarProduto(produto);
                                Toast.makeText(ListaProdutosActivity.this, "Produto excluído com sucesso", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
    }


}
