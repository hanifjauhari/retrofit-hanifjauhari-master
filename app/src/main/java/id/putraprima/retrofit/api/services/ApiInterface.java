package id.putraprima.retrofit.api.services;

import id.putraprima.retrofit.api.models.AppVersion;
import id.putraprima.retrofit.api.models.DataResponseProfil;
import id.putraprima.retrofit.api.models.LoginRequest;
import id.putraprima.retrofit.api.models.LoginResponse;
import id.putraprima.retrofit.api.models.MeRequest;
import id.putraprima.retrofit.api.models.MeResponse;
import id.putraprima.retrofit.api.models.RegisterRequest;
import id.putraprima.retrofit.api.models.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface{
    @GET("/")
    Call<AppVersion> getAppVersion();

    @POST("/api/auth/login")
    Call<LoginResponse> dologin(@Body LoginRequest loginRequest);

    @POST("/api/auth/register")
    Call<RegisterResponse> doRegister(@Body RegisterRequest resgiterRequest);

    @POST("/api/auth/me/{token}")
    Call<MeResponse> doMe(@Path("token") MeRequest meRequest);

    @GET("/api/auth/me")
    Call<DataResponseProfil> doDataMe(@Query("token") String token);
}
