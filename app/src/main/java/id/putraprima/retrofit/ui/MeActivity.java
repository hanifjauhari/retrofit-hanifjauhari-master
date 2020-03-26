package id.putraprima.retrofit.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import id.putraprima.retrofit.R;
import id.putraprima.retrofit.api.helper.ServiceGenerator;
import id.putraprima.retrofit.api.models.DataResponseProfil;
import id.putraprima.retrofit.api.models.MeRequest;
import id.putraprima.retrofit.api.models.MeResponse;
import id.putraprima.retrofit.api.services.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeActivity extends AppCompatActivity {

    private TextView id, txtName, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        txtEmail = findViewById(R.id.TxtEmail);
        txtName = findViewById(R.id.TxtName);
        meData();
    }

    private void meData() {
        Bundle bundle = getIntent().getExtras();
        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<DataResponseProfil> call = apiInterface.doDataMe(bundle.getString("KEY_TOKEN"));
        call.enqueue(new Callback<DataResponseProfil>() {
            @Override
            public void onResponse(Call<DataResponseProfil> call, Response<DataResponseProfil> response) {
                if(response != null){
//                    id.setText(response.body().getData().getId());
                    txtName.setText((response.body().getData().getName()));
                    txtEmail.setText(response.body().getData().getEmail());
                }
            }

            @Override
            public void onFailure(Call<DataResponseProfil> call, Throwable t) {

            }
        });
    }

}
