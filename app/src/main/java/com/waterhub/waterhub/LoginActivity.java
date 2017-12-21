package com.waterhub.waterhub;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.waterhub.waterhub.APIhelper.BaseApiService;
import com.waterhub.waterhub.APIhelper.UtilsApi;
import com.waterhub.waterhub.SessionManager.SessionManager;
import com.waterhub.waterhub.model.Login;
import com.waterhub.waterhub.model.MyResponse;
import com.waterhub.waterhub.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public TextInputEditText etUsername;
    public TextInputEditText etPassword;
    private Button butSignin;
    public TextView linkToRegister;

    ProgressDialog signinLoading;

    Context mContext;
    BaseApiService mBaseApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ver_2);

        mContext = this;
            mBaseApiService = UtilsApi.getAPIService(mContext); //ini adlah inisialisasi yg ada di dalam package apihelper
            initComponents();
    }

    private void initComponents() {
        etUsername = (TextInputEditText) findViewById(R.id.et_signin_username);
        etPassword = (TextInputEditText) findViewById(R.id.et_signin_password);
        linkToRegister = (TextView) findViewById(R.id.tv_link_register);

        butSignin = (Button) findViewById(R.id.but_signin);

        butSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinLoading = ProgressDialog.show(mContext, "WaterHub Sign In","Harap menunggu ya",true,false);
                requestLogin();
            }
        });

        linkToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void requestLogin() {
        String email = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        Login login = new Login(email, password);

        Call<MyResponse<User>> call = mBaseApiService.loginRequest(login);

        call.enqueue(new Callback<MyResponse<User>>() {

            @Override
            public void onResponse(Call<MyResponse<User>> call, Response<MyResponse<User>> response) {
                MyResponse<User> myResponse = response.body(); //retrofit response

                if (myResponse != null) {

                    String message = myResponse.getMessage();
                    User user = myResponse.getData();

                    if (user != null) {
                        SessionManager sessionManager = new SessionManager(mContext);
                        sessionManager.saveUser(user);

                        Intent intent = new Intent(mContext, MainHomeActivity.class);
                        startActivity(intent);

                        finish();
                    }

                    Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
                }

                signinLoading.dismiss();
            }

            @Override
            public void onFailure(Call<MyResponse<User>> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                signinLoading.dismiss();
            }
        });
    }
}
