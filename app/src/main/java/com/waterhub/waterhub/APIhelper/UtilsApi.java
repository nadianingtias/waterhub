package com.waterhub.waterhub.APIhelper;

import android.content.Context;

/**
 * Created by Nadian on 12/5/2017.
 */

public class UtilsApi {

    public static final String BASE_URL_API = "http://192.168.100.12:8080/api/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(Context context){
        return RetrofitClient.getClient(context, BASE_URL_API).create(BaseApiService.class);
    }
}
