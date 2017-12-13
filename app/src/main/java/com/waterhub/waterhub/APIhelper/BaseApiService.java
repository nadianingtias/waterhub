package com.waterhub.waterhub.APIhelper;

import okhttp3.ResponseBody;
import retrofit2.Call;
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
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                             @Field("password") String password);

    //API register
    // Fungsi ini untuk memanggil API http://localhost/waterhub_web/register.php
    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseBody> registerRequest(
                                    @Field("nama") String nama,
                                    @Field("email") String email,
                                    @Field("phone") String phone,
                                    @Field("password") String password,
                                    @Field("status") String status);
}
