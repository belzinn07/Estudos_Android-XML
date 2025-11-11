package com.example.controledeestoque_xml.ui.autenticacao;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.controledeestoque_xml.R;
import com.example.controledeestoque_xml.utils.InputUtils;
import com.example.controledeestoque_xml.viewmodel.global.AppViewModelFactory;
import com.example.controledeestoque_xml.core.InicializadorDeDependencias;
import com.example.controledeestoque_xml.ui.produto.ListaProdutosActivity;
import com.example.controledeestoque_xml.viewmodel.global.AppViewModel;

public class LoginActivity extends AppCompatActivity {
    private EditText editEmail,editSenha;
    private Button btnLogin, btnCadastrar;

    private AppViewModel appViewModel;

   // CONSTRUTOR REMOVIDO - Activities não devem ter construtores customizados.

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        btnLogin = findViewById(R.id.btnEntrar);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        // A inicialização do ViewModel já é feita corretamente aqui.
        InicializadorDeDependencias inicializador = (InicializadorDeDependencias) getApplication();
        AppViewModelFactory factory = new AppViewModelFactory(inicializador.getUsuarioRepository());

        appViewModel = new ViewModelProvider(this, factory).get(AppViewModel.class);

        btnLogin.setOnClickListener(v -> realizarLogin() );
        btnCadastrar.setOnClickListener(v -> redirecionarParaCadastro());

}

private void realizarLogin(){
   String email = editEmail.getText().toString();
   String senha = editSenha.getText().toString();

   if (InputUtils.camposInvalidos(this, email, senha)) return;

   appViewModel.login(email,senha).observe(this, usuario -> {
       if (usuario != null){
           Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
           redirecionarParaInicio();
       } else {
           Toast.makeText(this, "Erro ao realizar login. Verifique suas credenciais.", Toast.LENGTH_SHORT).show();
       }

   });
   }

   private void redirecionarParaInicio(){
       Intent intent = new Intent(LoginActivity.this, ListaProdutosActivity.class);
       startActivity(intent);
       finish();
   }

   private void redirecionarParaCadastro(){
       Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
       startActivity(intent);
       finish();
   }

}
