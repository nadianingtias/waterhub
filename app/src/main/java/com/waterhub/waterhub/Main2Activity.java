package com.waterhub.waterhub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.waterhub.waterhub.SessionManager.SessionManager;

public class Main2Activity extends AppCompatActivity {
    SessionManager mSessionManager;
    String a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mSessionManager = new SessionManager(getApplicationContext());

        menyimpan();
        menampilkan();
    }

    private void menampilkan() {
        a = mSessionManager.getData();
        Log.e("TAG", a);
    }

    private void menyimpan() {
        mSessionManager.saveData("OKE");
        Log.e("TAG", a);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
