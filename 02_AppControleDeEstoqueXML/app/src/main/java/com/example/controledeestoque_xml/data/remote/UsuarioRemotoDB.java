    package com.example.controledeestoque_xml.data.remote;

    import android.app.Application;
    import android.content.Context;
    import android.content.SharedPreferences;
    import android.util.Log;

    import androidx.lifecycle.LiveData;
    import androidx.lifecycle.MutableLiveData;

    import com.example.controledeestoque_xml.data.dtos.CadastroRequest;
    import com.example.controledeestoque_xml.data.dtos.AutenticarResponse;
    import com.example.controledeestoque_xml.data.dtos.LoginRequest;
    import com.example.controledeestoque_xml.data.dtos.AutenticarResponse;
    import com.example.controledeestoque_xml.data.local.entities.Usuario;
    import com.example.controledeestoque_xml.network.ApiService;

    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;

    public class UsuarioRemotoDB {
        private final ApiService apiService;
        private final Context appContext;


        public UsuarioRemotoDB(Application application  ,ApiService apiService) {
            this.apiService = apiService;
            this.appContext = application.getApplicationContext();
        }

        public LiveData<AutenticarResponse> login(String email, String senha){
            MutableLiveData<AutenticarResponse> usuarioLiveData = new MutableLiveData<>();

            apiService.login(new LoginRequest(email,senha)).enqueue(new Callback<AutenticarResponse>() {
                @Override
                public void onResponse(Call<AutenticarResponse> call, Response<AutenticarResponse> response) {
                    if (response.isSuccessful() && response.body() != null){
                        AutenticarResponse resposta = response.body();
                        usuarioLiveData.postValue(response.body());
                        salvarToken(resposta.getToken());
                        Log.i("API_LOGIN_SUCESSO", "Login bem-sucedido! -> Token Salvo: " + resposta.getToken());


                    } else {
                        usuarioLiveData.postValue(null);
                    }
                }

                @Override
                public void onFailure(Call<AutenticarResponse> call, Throwable t) {
                    Log.e("API_LOGIN_ERRO", "Erro: " + t.getMessage());
                    usuarioLiveData.postValue(null);

                }
            });

            return usuarioLiveData;
        }

        public LiveData<AutenticarResponse> cadastrar(String nome, String email, String senha) {
            MutableLiveData<AutenticarResponse> usuarioLiveData = new MutableLiveData<>();

            apiService.cadastrar(new CadastroRequest(nome,email,senha)).enqueue(new Callback<AutenticarResponse>() {

                @Override
                public void onResponse(Call<AutenticarResponse> call, Response<AutenticarResponse> response) {
                  if (response.isSuccessful() && response.body() != null){
                      AutenticarResponse resposta = response.body();
                      usuarioLiveData.postValue(response.body());
                      salvarToken(resposta.getToken());
                      Log.i("API_CADASTRO_SUCESSO", "cadastro bem-sucedido! -> Token Salvo: " + resposta.getToken());

                  }else{
                      Log.e("API_CADASTRO_FALHA", "Falha no cadastro: código " + response.code());
                      usuarioLiveData.postValue(null);
                  }
                }

                @Override
                public void onFailure(Call<AutenticarResponse> call, Throwable t) {
                    Log.e("API_CADASTRO_ERRO", "Erro: " + t.getMessage());
                    usuarioLiveData.postValue(null);

                }
            });
           return usuarioLiveData;
        }

        private void salvarToken(String token){
            SharedPreferences preferences = appContext.getSharedPreferences("APP_PREFERENCIAS", Application.MODE_PRIVATE);
            preferences.edit().putString("token_jwt", token).apply();

        }

        private String obterToken(String token){
            SharedPreferences preferences = appContext.getSharedPreferences("APP_PREFERENCIAS", Application.MODE_PRIVATE);
            return preferences.getString("token_jwt", null);
        }

        public void limparToken() {
            SharedPreferences preferences = appContext.getSharedPreferences("APP", Application.MODE_PRIVATE);
            preferences.edit().remove("token_jwt").apply();
            Log.i("TOKEN", "Token JWT removido com sucesso.");
        }



    }
