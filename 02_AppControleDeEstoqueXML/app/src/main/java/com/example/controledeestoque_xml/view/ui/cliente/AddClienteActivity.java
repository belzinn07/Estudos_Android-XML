package com.example.controledeestoque_xml.view.ui.cliente;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.controledeestoque_xml.R;
import com.example.controledeestoque_xml.data.local.entities.Cliente;
import com.example.controledeestoque_xml.viewmodel.cliente.ClienteViewModel;

public class AddClienteActivity extends AppCompatActivity {

    private EditText editNome,editEmail,editTelefone;
    private ClienteViewModel clienteViewModel;
    private Cliente clienteExistente = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cliente);

        inicializarViews();
        configurarViewModel();
        verificarClienteExistente();
    }

    private void inicializarViews(){
        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        editTelefone = findViewById(R.id.editTelefone);
    }

    private void configurarViewModel() {
        clienteViewModel = new ViewModelProvider(this).get(ClienteViewModel.class);

        clienteViewModel.mensagem.observe(this, msg -> {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        });

    }

    private void verificarClienteExistente(){
        Intent intent = getIntent();

        if (intent.hasExtra("CLIENTE EXTRA")){
            clienteExistente = intent.getParcelableExtra("CLIENTE EXTRA");

            if (clienteExistente != null){
                preencherCampos(clienteExistente);
            }
        }

    }

    private void preencherCampos(Cliente cliente){
        editNome.setText(clienteExistente.getNome());
        editEmail.setText(clienteExistente.getEmail());
        editTelefone.setText(String.valueOf(clienteExistente.getTelefone()));
    }


}
