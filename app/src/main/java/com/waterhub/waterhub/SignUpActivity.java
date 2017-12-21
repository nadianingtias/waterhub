package com.waterhub.waterhub;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import com.waterhub.waterhub.APIhelper.BaseApiService;
import com.waterhub.waterhub.APIhelper.UtilsApi;
import com.waterhub.waterhub.model.MyResponse;
import com.waterhub.waterhub.model.Register;
import com.waterhub.waterhub.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    public TextInputEditText etEmail;
    public TextInputEditText etFirstname;
    public TextInputEditText etLastname;
    public TextInputEditText etPhone;
    public TextInputEditText etPassword;
    public AppCompatButton butRegisterUser;
    public AppCompatButton butRegisterSeller;

    ProgressDialog registerLoading;
    Integer option;

    Context mContext;
    BaseApiService mBaseApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mContext=this;
            mBaseApiService = UtilsApi.getAPIService(mContext);
            initComponent();
    }

    private void initComponent() {
        etEmail = (TextInputEditText) findViewById(R.id.et_signup_email);
        etFirstname = (TextInputEditText) findViewById(R.id.et_signup_firstname);
        etLastname = (TextInputEditText) findViewById(R.id.et_signup_lastname);
        etPhone = (TextInputEditText) findViewById(R.id.et_signup_phonenumber);
        etPassword = (TextInputEditText) findViewById(R.id.et_signup_password);

        butRegisterUser = (AppCompatButton)findViewById(R.id.but_signup_user);
        butRegisterSeller = (AppCompatButton)findViewById(R.id.but_signup_seller);

        butRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerLoading = ProgressDialog.show(mContext, "WaterHub Sign Up", "Satu langkah lagi anda bergabung bersama kami", true, false);
                requestSignup(0);
            }
        });

        butRegisterSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerLoading = ProgressDialog.show(mContext, "WaterHub Sign Up", "Satu langkah lagi anda bergabung menjadi mitra kami", true, false);
                requestSignup(1);
            }
        });
    }

    private void requestSignup(Integer option) {
        String email = etEmail.getText().toString();
        String firstname = etFirstname.getText().toString();
        String lastname = etLastname.getText().toString();
        String phonenumber = etPhone.getText().toString();
        String password = etPassword.getText().toString();


        Register userRegister = new Register(email,firstname, lastname, phonenumber,password);

        Call<MyResponse<User>> call = mBaseApiService.registerRequest(null);

        call.enqueue(new Callback<MyResponse<User>>() {
            @Override
            public void onResponse(Call<MyResponse<User>> call, Response<MyResponse<User>> response) {
                MyResponse<User> myResponse = response.body();

                if(myResponse != null){
                    String message = myResponse.getMessage();
                    User UserRegister= myResponse.getData();

                    Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();

                }
                registerLoading.dismiss();
                registerLoading.cancel();
            }

            @Override
            public void onFailure(Call<MyResponse<User>> call, Throwable t) {
                t.printStackTrace();
                registerLoading.dismiss();
                registerLoading.cancel();
            }
        });
    }
}
