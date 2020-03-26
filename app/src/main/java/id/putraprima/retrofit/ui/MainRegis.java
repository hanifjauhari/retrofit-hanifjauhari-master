package id.putraprima.retrofit.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import id.putraprima.retrofit.R;
import id.putraprima.retrofit.api.helper.ServiceGenerator;
import id.putraprima.retrofit.api.models.RegisterRequest;
import id.putraprima.retrofit.api.models.RegisterResponse;
import id.putraprima.retrofit.api.services.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRegis extends AppCompatActivity {

    public EditText inpName, inpEmail, inpPass, inpConfPass;
    public RegisterRequest registerRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);
        inpName = findViewById(R.id.inputName);
        inpEmail = findViewById(R.id.inputEmail);
        inpPass = findViewById(R.id.inputPassword);
        inpConfPass = findViewById(R.id.inputConfirmPassword);
    }
    public void register(){
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<RegisterResponse> call = service.doRegister(registerRequest);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response != null){
                    Toast.makeText(MainRegis.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(MainRegis.this, "Register Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void handleRegisLosss(View view) {
        String name = inpName.getText().toString();
        String email = inpEmail.getText().toString();
        String password = inpPass.getText().toString();
        String confirm_password = inpConfPass.getText().toString();
        registerRequest = new RegisterRequest(name, email, password, confirm_password);
        boolean cek1, cek2, cek3, cek4, cek5;
        if(name.equals("")){
            Toast.makeText(this, "Masukkan nama", Toast.LENGTH_SHORT).show();
            cek1 = false;
        }
        else{
            cek1 = true;
        }
        if(email.equals("")){
            Toast.makeText(this, "Masukkan email", Toast.LENGTH_SHORT).show();
            cek2 = false;
        }
        else{
            cek2 = true;
        }
        if(password.equals("")){
            Toast.makeText(this, "Masukkan password", Toast.LENGTH_SHORT).show();
            cek3 = false;
        }
        else{
            cek3 = true;
        }
        if(confirm_password.equals("")){
            Toast.makeText(this, "Masukkan Konfirmasi Password", Toast.LENGTH_SHORT).show();
            cek4 = false;
        }
        else{
            cek4 = true;
        }
        if(!password.equals(confirm_password)){
            Toast.makeText(this, "Password tidak sama", Toast.LENGTH_SHORT).show();
            cek5 = false;
        }
        else{
            cek5 = true;
        }
        if(cek1==true && cek2==true && cek3==true && cek4==true && cek5==true){
            register();
        }
    }
}
