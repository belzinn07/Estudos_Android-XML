package com.example.controledeestoque_xml.core.token;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class GerenciadorDeToken {

    private static final String PREF_NAME = "APP_PREFERENCIAS";
    private static final String KEY_TOKEN = "token_jwt";
    private final Context appContext;

    public GerenciadorDeToken(Application application) {
        this.appContext = application.getApplicationContext();
    }


    public void salvarToken(String token){
        SharedPreferences preferences = appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_TOKEN, token).apply();
        Log.i("TOKEN", "Token JWT salvo com sucesso.");
    }

    public void limparToken() {

        SharedPreferences preferences = appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().remove(KEY_TOKEN).apply();
        Log.i("TOKEN", "Token JWT removido com sucesso.");
    }


}
