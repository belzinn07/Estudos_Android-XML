package com.example.controledeestoque_xml.viewmodel.global;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.controledeestoque_xml.data.local.entities.Empresa;

public class AppViewModel extends AndroidViewModel {

    private MutableLiveData<Empresa> empresaLogada = new MutableLiveData<>();
    private SharedPreferences preferences;

    public AppViewModel(android.app.Application application) {
        super(application);
        preferences = application.getSharedPreferences("app_preferences", Context.MODE_PRIVATE);

    }

    private void carregarEmpresaLogada(){
        String nome = preferences.getString("empresa_nome", null);
        String email = preferences.getString("empresa_email", null );
        String senha = preferences.getString("empresa_senha", null);

    }





}
