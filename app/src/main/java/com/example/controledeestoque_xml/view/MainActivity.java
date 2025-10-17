package com.example.controledeestoque_xml.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.controledeestoque_xml.R;

public class MainActivity extends AppCompatActivity {
    //private ProdutoViewModel viewModel;
    private TextView textNomeApp;
    private EditText editNome;
    private EditText editpreco;
    private EditText editQuantidade;
    private Button btnAdicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textNomeApp = findViewById(R.id.textNomeApp);
        editNome = findViewById(R.id.editNome);
        editpreco = findViewById(R.id.editPreco);
        editQuantidade = findViewById(R.id.editQuantidade);
        btnAdicionar = findViewById(R.id.btnAdiconar);

      btnAdicionar.setOnClickListener(v ->{

      });




    }
}