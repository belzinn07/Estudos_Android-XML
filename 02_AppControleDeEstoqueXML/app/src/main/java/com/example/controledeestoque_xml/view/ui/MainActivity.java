package com.example.controledeestoque_xml.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controledeestoque_xml.R;
import com.example.controledeestoque_xml.model.Produto;
import com.example.controledeestoque_xml.view.adapter.ProdutoAdapter;
import com.example.controledeestoque_xml.viewmodel.ProdutoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ProdutoViewModel produtoViewModel;
    private ProdutoAdapter adapter;
    private TextView textvalorTotal;

    private  static final int ADD_PRODUTO_REQUISICAO = 1;
    private static  final int EDITAR_PRODUTOS_REQUISICAO = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        FloatingActionButton fabAddProduto = findViewById(R.id.fabAddProduto);
        textvalorTotal = findViewById(R.id.textValorTotal);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new ProdutoAdapter();
        recyclerView.setAdapter(adapter);

        produtoViewModel = new ViewModelProvider(this).get(ProdutoViewModel.class);

        produtoViewModel.getTodosProdutos().observe(this, new Observer<List<Produto>>() {
            @Override
            public void onChanged(List<Produto> produtos) {
                adapter.setProdutos(produtos);

            }
        });

        produtoViewModel.getValorTotalEstoque().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double valorTotal) {
              if (valorTotal != null){
                  textvalorTotal.setText(String.format("R$ %.2f", valorTotal));
              }
            }
        });

        fabAddProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddProdutoActivity.class);
                startActivityForResult(intent, ADD_PRODUTO_REQUISICAO);

            }

        });

        adapter.setOnItemClickListener(new ProdutoAdapter.OnItemClickListener listener);



        

    }
}
