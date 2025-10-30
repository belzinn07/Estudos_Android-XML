package com.example.controledeestoque_xml.view.ui.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.controledeestoque_xml.R;
import com.example.controledeestoque_xml.network.ApiService;

public class LoginActivity extends AppCompatActivity {
    private EditText editEmail,editSenha;
    private Button btnLogin, btnCadastrar;

    private ApiService apiService;






    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);





    }
}
