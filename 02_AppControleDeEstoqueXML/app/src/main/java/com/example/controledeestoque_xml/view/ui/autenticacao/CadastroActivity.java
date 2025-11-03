package com.example.controledeestoque_xml.view.ui.autenticacao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.controledeestoque_xml.R;
import com.example.controledeestoque_xml.Utils.InputUtils;
import com.example.controledeestoque_xml.core.InicializadorDeDependencias;
import com.example.controledeestoque_xml.viewmodel.global.AppViewModel;
import com.example.controledeestoque_xml.viewmodel.global.AppViewModelFactory;
import com.example.controledeestoque_xml.data.local.entities.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private AppViewModel appViewModel;
    private EditText editNome, editEmail, editSenha;
    private Button btnCadastrar;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        inicializarViews();
        inicializarViewModel();

        btnCadastrar.setOnClickListener(v -> realizarCadastro());
    }

    private void inicializarViews() {
        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        progressBar = findViewById(R.id.progressBarCadastro); // adicione um ProgressBar no XML
    }

    private void inicializarViewModel() {
        InicializadorDeDependencias inicializador = (InicializadorDeDependencias) getApplication();
        AppViewModelFactory factory = new AppViewModelFactory(inicializador.getUsuarioRepository());
        appViewModel = new ViewModelProvider(this, factory).get(AppViewModel.class);
    }

    private void realizarCadastro() {
        String nome = editNome.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String senha = editSenha.getText().toString().trim();

        if (InputUtils.camposInvalidos(this, nome, email, senha)) return;

        setLoading(true);

        appViewModel.cadastrar(nome, email, senha).observe(this, usuario -> {
            setLoading(false);

            if (usuario != null && usuario.getEmail() != null) {
                Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                navegarParaLogin();
            } else {
                Toast.makeText(this, "Erro ao realizar cadastro. Verifique suas credenciais.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setLoading(boolean carregando) {
        if (carregando) {
            progressBar.setVisibility(View.VISIBLE);
            btnCadastrar.setEnabled(false);
        } else {
            progressBar.setVisibility(View.GONE);
            btnCadastrar.setEnabled(true);
        }
    }

    private void navegarParaLogin() {
        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
