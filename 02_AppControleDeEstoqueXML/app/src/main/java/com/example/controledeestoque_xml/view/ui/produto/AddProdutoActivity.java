package com.example.controledeestoque_xml.view.ui.produto;

import android.annotation.SuppressLint;
import android.app.AlertDialog;

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

import androidx.lifecycle.ViewModelProvider;


import com.example.controledeestoque_xml.R;
import com.example.controledeestoque_xml.data.local.entities.Categoria;
import com.example.controledeestoque_xml.data.local.entities.Produto;
import com.example.controledeestoque_xml.utils.InputUtils;
import com.example.controledeestoque_xml.viewmodel.produto.ProdutoViewModel;


import java.util.List;

public class AddProdutoActivity extends AppCompatActivity {

    private EditText editNome,editPreco,editQuantidade;
    private TextView textCategoriaSelecionada;
    private Button btnSelecionarCategoria,btnEditar;

    private ProdutoViewModel produtoViewModel;
    private Produto produtoExistente = null;
    private Categoria categoriaSelecionada = null;
    private LiveData<List<Produto>> todosProdutos;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_produto);

        inicializarViews();
        configurarViewModel();
        verificarProdutoExistente();

        btnSelecionarCategoria.setOnClickListener(v-> mostrarCategorias());

        btnEditar.setOnClickListener(v -> salvarProduto());
    }

    private void inicializarViews(){
        editNome = findViewById(R.id.editNome);
        editPreco = findViewById(R.id.editPreco);
        editQuantidade = findViewById(R.id.editQuantidade);
        btnSelecionarCategoria = findViewById(R.id.btnSelecionarCategoria);
        textCategoriaSelecionada = findViewById(R.id.textCategoriaSelecionada);
        btnEditar = findViewById(R.id.btnAdicionar);
    }

    private void configurarViewModel() {
        produtoViewModel = new ViewModelProvider(this).get(ProdutoViewModel.class);

        produtoViewModel.mensagem.observe(this, msg -> {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        });
    }

    private void verificarProdutoExistente(){
        Intent intent = getIntent();
        if (intent.hasExtra("PRODUTO EXTRA")) {
            produtoExistente = intent.getParcelableExtra("PRODUTO EXTRA");
            if (produtoExistente != null) {
                preencherCampos(produtoExistente);
            }
        }
    }

    private void preencherCampos(Produto produto){
        editNome.setText(produtoExistente.getNome());
        editPreco.setText(String.valueOf(produtoExistente.getPrecoUnitario()));
        editQuantidade.setText(String.valueOf(produtoExistente.getQuantidade()));
        if (produto.getCategoria() != null){
            categoriaSelecionada = produto.getCategoria();
            textCategoriaSelecionada.setText("Categoria: " + categoriaSelecionada.name());

            textCategoriaSelecionada.setVisibility(View.VISIBLE);
        }

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
    private void salvarProduto(){
        String nome = editNome.getText().toString().trim();
        int quantidade = Integer.parseInt(editQuantidade.getText().toString().trim());
        double preco = Double.parseDouble(editPreco.getText().toString().trim());
        Categoria categoria = this.categoriaSelecionada;

        Produto produtoNovo = new Produto(nome, preco, quantidade,categoria);

        if (produtoExistente != null){
            produtoViewModel.atualizarProduto(produtoExistente,produtoNovo);
        }else {
            produtoViewModel.adicionarProduto(produtoNovo);
        }
    }


}
