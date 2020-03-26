package id.putraprima.retrofit.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import id.putraprima.retrofit.R;
import id.putraprima.retrofit.api.APIClient;
import id.putraprima.retrofit.api.helper.ServiceGenerator;
import id.putraprima.retrofit.api.models.AuthenticationRequest;
import id.putraprima.retrofit.api.models.LoginRequest;
import id.putraprima.retrofit.api.models.LoginResponse;
import id.putraprima.retrofit.api.models.MeRequest;
import id.putraprima.retrofit.api.models.MeResponse;
import id.putraprima.retrofit.api.services.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_TOKEN = "token";
    private Bundle token;
    public LoginRequest loginRequest;
    public MeRequest meRequest;
    EditText edtEmail;
    EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
    }

    public class Token{
        @SerializedName("token")
        @Expose
        public String token;
    }

    public void me(){
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<MeResponse> call = service.doMe(meRequest);
        call.enqueue(new Callback<MeResponse>() {
            @Override
            public void onResponse(Call<MeResponse> call, Response<MeResponse> response) {
                if (response != null){
                    Intent intent = new Intent(MainActivity.this, MeActivity.class);
                    intent.putExtra("KEY_TOKEN", meRequest.getToken());
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<MeResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal koneksi ke server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void login() {
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<LoginResponse> call = service.dologin(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Toast.makeText(MainActivity.this, response.body().getToken(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, response.body().getToken_type(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, response.body().getExpires_in(), Toast.LENGTH_SHORT).show();
                if(response != null){
                    meRequest = new MeRequest(response.body().getToken());
                    me();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal Koneksi Ke Server", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void handleLogin(View view) {
        String user = edtEmail.getText().toString();
        String pass = edtPassword.getText().toString();
        loginRequest = new LoginRequest(user, pass);
        boolean cek1, cek2;
        if(user.equals("")){
            Toast.makeText(this, "Masukkan Email", Toast.LENGTH_SHORT).show();
            cek1 = false;
        }
        else{
            cek1 = true;
        }
        if(pass.equals("")){
            Toast.makeText(this, "Masukkan password", Toast.LENGTH_SHORT).show();
            cek2 = false;
        }
        else{
            cek2 = true;
        }
        if(cek1 == true && cek2 == true){
            login();
        }
    }

    public void handleRegister(View view) {
        Intent intent = new Intent(MainActivity.this, MainRegis.class);
        startActivity(intent);
    }
}
