    package com.example.controledeestoque_xml.data.remote;

    import android.app.Application;
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

        public UsuarioRemotoDB(Application application,ApiService apiService) {
            this.apiService = apiService;
        }

        public LiveData<AutenticarResponse> login(String email, String senha){
            MutableLiveData<AutenticarResponse> usuarioLiveData = new MutableLiveData<>();

            apiService.login(new LoginRequest(email,senha)).enqueue(new Callback<AutenticarResponse>() {
                @Override
                public void onResponse(Call<AutenticarResponse> call, Response<AutenticarResponse> response) {
                    if (response.isSuccessful() && response.body() != null){
                        usuarioLiveData.postValue(response.body());
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
                      usuarioLiveData.postValue(response.body());

                  }else{
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




    }
