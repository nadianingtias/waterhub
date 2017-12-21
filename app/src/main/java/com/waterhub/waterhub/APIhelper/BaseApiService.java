package com.waterhub.waterhub.APIhelper;

import com.waterhub.waterhub.model.Login;
import com.waterhub.waterhub.model.MyResponse;
import com.waterhub.waterhub.model.Register;
import com.waterhub.waterhub.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Nadian on 12/5/2017.
 * Class ini berfungsi untuk mengisi perintah-perintah apa saja yang diperlukan untuk berkomunikasi dengan API. Seperti GET,POST,UPDATE, DELETE.
 */


public interface BaseApiService {

    //API login
    // Fungsi ini untuk memanggil API http://localhost/waterhub_web/login.php
    @POST("user/verify-user")
    Call<MyResponse<User>> loginRequest(@Body Login login);

    //API register
    // Fungsi ini untuk memanggil API http://localhost/waterhub_web/register.php
    @POST("user/save")
    Call<MyResponse<User>> registerRequest(@Body User user);
}
