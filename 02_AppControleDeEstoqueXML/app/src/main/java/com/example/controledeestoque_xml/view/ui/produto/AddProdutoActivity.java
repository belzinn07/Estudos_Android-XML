package com.example.controledeestoque_xml.view.ui.produto;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controledeestoque_xml.R;
import com.example.controledeestoque_xml.data.local.entities.Categoria;
import com.example.controledeestoque_xml.data.local.entities.Produto;
import com.example.controledeestoque_xml.view.adapter.OnExcluirProdutoListener;
import com.example.controledeestoque_xml.view.adapter.OnItemClickListener;
import com.example.controledeestoque_xml.view.adapter.ProdutoAdapter;
import com.example.controledeestoque_xml.viewmodel.produto.AddProdutoViewModel;
import com.example.controledeestoque_xml.viewmodel.produto.ProdutoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AddProdutoActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editPreco;
    private EditText editQuantidade;
    private TextView textCategoriaSelecionada;
    private Button btnSelecionarCategoria;
    private Button btnAdicionar;

    private AddProdutoViewModel addProdutoViewModel;
    private Produto produtoExistente = null;
    private Categoria categoriaSelecionada = null;
    private LiveData<List<Produto>> todosProdutos;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_produto);

        editNome = findViewById(R.id.editNome);
        editPreco = findViewById(R.id.editPreco);
        editQuantidade = findViewById(R.id.editQuantidade);
        btnSelecionarCategoria = findViewById(R.id.btnSelecionarCategoria);
        textCategoriaSelecionada = findViewById(R.id.textCategoriaSelecionada);
        btnAdicionar = findViewById(R.id.btnAdiconar);

        addProdutoViewModel = new ViewModelProvider(this).get(AddProdutoViewModel.class);

        addProdutoViewModel.mensagem.observe(this, msg -> {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        });

        Intent intent = getIntent();
        if (intent.hasExtra("PRODUTO EXTRA")) {
            produtoExistente = intent.getParcelableExtra("PRODUTO EXTRA");
            if (produtoExistente != null) {
                editNome.setText(produtoExistente.getNome());
                editPreco.setText(String.valueOf(produtoExistente.getPrecoUnitario()));
                editQuantidade.setText(String.valueOf(produtoExistente.getQuantidade()));

            }
        }

        btnSelecionarCategoria.setOnClickListener(v-> mostrarCategorias());

        btnAdicionar.setOnClickListener(v -> salvarProduto());
    }


    private void mostrarCategorias() {
        Categoria[] categorias = Categoria.values();
        String[] nomes = new String[categorias.length];
        for (int i = 0; i < categorias.length; i++) {
            nomes[i] = categorias[i].name();
        }

        new AlertDialog.Builder(this)
                .setTitle("Selecione uma categoria")
                .setSingleChoiceItems(nomes, -1, (dialog, which) -> {
                    categoriaSelecionada = categorias[which];
                    textCategoriaSelecionada.setText("Categoria: " + categoriaSelecionada.name());
                    dialog.dismiss();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }


    private void salvarProduto() {
        String nome = editNome.getText().toString().trim();
        double preco;
        int quantidade;

        try {
            quantidade = Integer.parseInt(editQuantidade.getText().toString().trim());
            preco = Double.parseDouble(editPreco.getText().toString().trim());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Preço ou quantidade inválidos.", Toast.LENGTH_SHORT).show();
            return;
        }


        Produto novo = new Produto(nome, preco, quantidade);

        if (!AddProdutoViewModel.validarProduto(novo)) {
            Toast.makeText(this, "Preencha todos os campos corretamente.", Toast.LENGTH_SHORT).show();
            return;
        }

        addProdutoViewModel.salvarouAtualiuzarProduto(produtoExistente, novo);
        finish();

    }


    public static class MainActivity extends AppCompatActivity {
        private ProdutoViewModel produtoViewModel;
        private ProdutoAdapter adapter;
        private TextView textValorTotal;

        private  static  final int ADD_PRODUTO_REQUEST_CODE = 1;
        private static final int EDIT_PRODUTO_REQUEST_CODE = 2;
        private static  final int DELETE_PRODUTO_REQUEST_CODE = 3;

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
}
