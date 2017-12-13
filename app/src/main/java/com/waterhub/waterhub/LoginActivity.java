package com.waterhub.waterhub;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.waterhub.waterhub.APIhelper.BaseApiService;
import com.waterhub.waterhub.APIhelper.UtilsApi;

import butterknife.BindView;

public class LoginActivity extends AppCompatActivity {

    //bindview dengan butterknife
    @BindView(R.id.et_signin_username) EditText etUsername;
    @BindView(R.id.et_signin_password) EditText etPassword;
    @BindView(R.id.but_signin) Button butSignin;

    ProgressDialog signinLoading;

    Context mContext;
    BaseApiService mBaseApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ver_2);

        mContext = this;
            mBaseApiService = UtilsApi.getAPIService(); //ini adlah inisialisasi yg ada di dalam package apihelper
            initComponents();
    }

    private void initComponents() {
        butSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinLoading = ProgressDialog.show(mContext, "WaterHub SIgn In","Harap menunggu ya",true,false);
                signinLoading = ProgressDialog.show(mContext, "WaterHub SIgn In","Harap menunggu ya",true,false);
                requestLogin();
            }
        });
    }

    private void requestLogin() {
    }
}
