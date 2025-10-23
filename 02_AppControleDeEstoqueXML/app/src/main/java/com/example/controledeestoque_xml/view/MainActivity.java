package com.example.controledeestoque_xml.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controledeestoque_xml.R;
import com.example.controledeestoque_xml.view.adapter.ProdutoAdapter;
import com.example.controledeestoque_xml.viewmodel.ProdutoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private ProdutoViewModel viewModel;
    private ProdutoAdapter adapter;
    private TextView textValorTotal;

    private static int ADD_PRODUTO_REQUISICAO_CODIGO = 1;
    private static int EDIT_PRODUTO_REQUISICAO_CODIGO = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        FloatingActionButton fabAddProduto = findViewById(R.id.fabAddProduto);
        textValorTotal = findViewById(R.id.textValorTotal);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new ProdutoAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(ProdutoViewModel.class);

        



    }
}
