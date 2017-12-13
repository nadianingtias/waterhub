package com.waterhub.waterhub.APIhelper;

/**
 * Created by Nadian on 12/5/2017.
 */

public class UtilsApi {
    public static final String BASE_URL_API = "http://localhost/waterhub_web/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }

}
