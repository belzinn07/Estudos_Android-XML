package com.example.controledeestoque_xml.view.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.controledeestoque_xml.R;
import com.example.controledeestoque_xml.model.Categoria;
import com.example.controledeestoque_xml.model.Produto;
import com.example.controledeestoque_xml.viewmodel.AddProdutoViewModel;
import com.example.controledeestoque_xml.viewmodel.ProdutoViewModel;

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
        String nome = editNome.getText().toString();
        String precoStr = editPreco.getText().toString();
        String quantidadeStr = editQuantidade.getText().toString();

        int quantidade = Integer.parseInt(quantidadeStr);
        double preco = Double.parseDouble(precoStr);

        Produto novo = new Produto(nome, preco, quantidade);

        if (!AddProdutoViewModel.validarProduto(novo)){
            Toast.makeText(this,"Preencha todos os campos corretamente.",Toast.LENGTH_SHORT).show();
            return;
        }

        addProdutoViewModel.salvarouAtualiuzarProduto(produtoExistente, novo);
        finish();

    }



}
