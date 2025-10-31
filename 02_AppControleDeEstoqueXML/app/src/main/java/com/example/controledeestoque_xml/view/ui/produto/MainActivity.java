
package com.example.controledeestoque_xml.view.ui.produto;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controledeestoque_xml.R;
import com.example.controledeestoque_xml.data.local.entities.Produto;
import com.example.controledeestoque_xml.view.adapter.OnExcluirProdutoListener;
import com.example.controledeestoque_xml.view.adapter.OnItemClickListener;
import com.example.controledeestoque_xml.view.adapter.ProdutoAdapter;
import com.example.controledeestoque_xml.viewmodel.produto.ProdutoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ProdutoViewModel produtoViewModel;
    private ProdutoAdapter adapter;
    private TextView textValorTotal;

    private  static  final int ADD_PRODUTO_REQUEST_CODE = 1;
    private static final int EDIT_PRODUTO_REQUEST_CODE = 2;


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
                    textValorTotal.setText(String.format("R$ %.2f", valorTotal));

                }

            }
        });

        fabAddProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddProdutoActivity.class);
                startActivityForResult(intent, ADD_PRODUTO_REQUEST_CODE);
            }
        });

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Produto produto) {
                Intent intent = new Intent(MainActivity.this, AddProdutoActivity.class);
                intent.putExtra("PRODUTO EXTRA",  produto);
                startActivityForResult(intent, EDIT_PRODUTO_REQUEST_CODE);
            }


        });

        adapter.setOnExcluirProdutoListener(new OnExcluirProdutoListener() {
            @Override
            public void onExcluir(Produto produto) {
                msgConfirmacaoExclusao(produto);
            }
        });






    }

    private void msgConfirmacaoExclusao(Produto produto){
        Context applicationContext = getApplicationContext();

        new AlertDialog.Builder(this)
                .setTitle("Confirmação")
                .setMessage("Tem certeza que deseja excluir este produto: ("  + produto.getNome()  + ")? ")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        produtoViewModel.deletarProduto(produto);
                        Toast.makeText(applicationContext, "Produto excluído com sucesso", Toast.LENGTH_SHORT).show();

                    }
                })

                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Exclusão cancelada", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })

                .setCancelable(false)
                .show();

    }

}