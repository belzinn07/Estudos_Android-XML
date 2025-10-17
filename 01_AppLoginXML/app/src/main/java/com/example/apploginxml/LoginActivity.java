package com.example.apploginxml;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    TextView textLogin;
    EditText editEmail, editSenha;
    Button btnEntrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textLogin = findViewById(R.id.textLogin);
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(v -> {
            String email = editEmail.getText().toString();
            String senha = editSenha.getText().toString();


            if (autenticarUsuario(email,senha)) {
                salvarLogin(email);


                startActivity(new Intent(this, InicioActivity.class));
            } else {
                Toast.makeText(this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private boolean autenticarUsuario(String email, String senha){

        if (email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }


        if (email.equals("admin") && senha.equals("admin")) {
            Toast.makeText(this, "Login feito com sucesso", Toast.LENGTH_SHORT).show();

        }
        return false;
    }


     private void salvarLogin(String email){

         SharedPreferences preferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE);
         SharedPreferences.Editor   editor = preferences.edit();
         editor.putString("email", email);
         editor.putBoolean("estaLogado",true);
         editor.apply();

    }


}