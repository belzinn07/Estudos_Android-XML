package com.example.controledeestoque_xml.ui.cliente;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.controledeestoque_xml.R;
import com.example.controledeestoque_xml.core.utils.InputUtils;
import com.example.controledeestoque_xml.data.local.entities.Cliente;
import com.example.controledeestoque_xml.viewmodel.ClienteViewModel;

public class AddClienteActivity extends AppCompatActivity {

    private EditText editNome,editEmail,editTelefone;
    private Button btnSalvar;
    private ClienteViewModel clienteViewModel;
    private Cliente clienteExistente = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cliente);

        inicializarViews();
        configurarViewModel();
        verificarClienteExistente();

        btnSalvar.setOnClickListener(v -> salvarCliente());
    }

    private void inicializarViews(){
        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        editTelefone = findViewById(R.id.editTelefone);
        btnSalvar = findViewById(R.id.btnSalvar);
    }

    private void configurarViewModel() {
        clienteViewModel = new ViewModelProvider(this).get(ClienteViewModel.class);

        clienteViewModel.mensagem.observe(this, msg -> {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            finish();
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
        editNome.setText(cliente.getNome());
        editEmail.setText(cliente.getEmail());
        editTelefone.setText(String.valueOf(cliente.getTelefone()));
    }

    private void salvarCliente(){
        String nome = editNome.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String telefone = editTelefone.getText().toString().trim();


        if (InputUtils.camposInvalidos(this, nome, email, telefone)) return;

        Cliente clienteNovo = new Cliente(nome,email,telefone);
        if (clienteExistente != null){
            clienteViewModel.atualizarCliente(clienteExistente,clienteNovo);
        }else {
            clienteViewModel.adicionarCliente(clienteNovo);
        }
    }
}
