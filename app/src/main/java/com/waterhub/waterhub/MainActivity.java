package com.waterhub.waterhub;

import android.content.Intent;
import android.service.textservice.SpellCheckerService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.waterhub.waterhub.SessionManager.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView tvLogResponse, tvData;

    SessionManager mSessionManager;
    String URL_GET_REQ_HEADER ="http://khoiron.000webhostapp.com/codeigniter/Siswa/getwithheader";
    String URL_GET_REQ = "https://gist.githubusercontent.com/filippella/a728a34822a3bc7add98e477a4057b69/raw/310d712e87941f569074a63fedb675d2b611342a/cakes";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvLogResponse =(TextView) findViewById(R.id.log_response);
        tvData =(TextView) findViewById(R.id.tv_response_data);

        mSessionManager =  new SessionManager(getApplicationContext());

            //menyimpan Session
        menyimpanSession();
            //menampilkan session
        menampilkanSession();

            //use the FAN networking
        AndroidNetworking.initialize(getApplicationContext());

            //menjalankan get request API
        //getRequestApi();
        postWithHeaderApi();             //get request with header

        //Intent i = new Intent(MainActivity.this, Main2Activity.class);
        //startActivity(i);
    }

    private void postWithHeaderApi() {
        AndroidNetworking.post("URL_GET_REQ_HEADER")
//                .addPathParameter("pageNumber", "0")
//                .addQueryParameter("limit", "3")
                .addHeaders("keterangan", "kesehatan")              //with HEADER
                .setTag("test API")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {                  //mencoba mengambil array ob JSONObject
                        tvLogResponse.setText(response.toString());
                        try {
                            JSONObject responsObject = response.getJSONObject(0); //mengambil JSONObject pada array index ke berapa

                            String jenisBarang = responsObject.getString("jenisbarang");
                            tvData.setText(jenisBarang);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // do anything with response
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }


    private void getRequestApi(){
        AndroidNetworking.get(URL_GET_REQ)
               // .addPathParameter("", "")                   //ini adalah jika butuh parameter (coba di postman dulu)
               // .addQueryParameter("limit", "3")              //ini juga
               // .addHeaders("token", "1234")                  //ini juga
                .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        tvLogResponse.setText(response.toString());
                        Log.e("Response ==>", response.toString());
//                        JSONObject response1 = response.getString(response);
                        try {
                            String data = response.getString("product");        //response diambil datanya yang dari API
                            //TODO: melog data response
                            tvData.setText(data);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        //param makeText(context, string pesan, duration).method show()
                        Toast.makeText(MainActivity.this, anError.getErrorBody(), Toast.LENGTH_SHORT).show();
                        Log.e("Response ==>" , anError.getErrorBody());         //menampilkan log error jika tidak berhasil mengambil response
                    }
                });
//                .getAsJSONArray(new JSONArrayRequestListener() {  //ini jika response dari API berupa JSON ARRAY
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        // do anything with response
//                    }
//                    @Override
//                    public void onError(ANError error) {
//                        // handle error
//                    }
//                });
    }

    private void postResponseApi(){

    }

    private void menyimpanSession() {
        String a = "simpanan session";
        mSessionManager.saveData(a);
        Log.e("session", "save data");
    }

    private void menampilkanSession() {
        mSessionManager.getData();
        Log.e("session", "show data");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Sedang Berjalan","onPause Method");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.e("Sedang Berjalan","onPostResume Method");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Sedang Berjalan","onResume Method");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Sedang Berjalan","onStop Method");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Sedang Berjalan","onDestroy Method");
    }

    void showString(){
        Log.e("Saya", "Siapp");
    }
}
