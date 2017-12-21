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
import com.waterhub.waterhub.model.Role;
import com.waterhub.waterhub.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    public TextInputEditText etEmail;
    public TextInputEditText etFirstname;
    public TextInputEditText etLastname;
    public TextInputEditText etPhone;
    public TextInputEditText etCountry;
    public TextInputEditText etCity;
    public TextInputEditText etAddress;
    public TextInputEditText etPassword;
    public AppCompatButton butRegisterUser;
    public AppCompatButton butRegisterSeller;

    ProgressDialog registerLoading;

    Context mContext;
    BaseApiService mBaseApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mContext = this;
        mBaseApiService = UtilsApi.getAPIService(mContext);
        initComponent();
    }

    private void initComponent() {
        Role role = new Role();
        role.setId(2L);

        final List<Role> roles = new ArrayList<>();
        roles.add(role);

        etEmail = (TextInputEditText) findViewById(R.id.et_signup_email);
        etFirstname = (TextInputEditText) findViewById(R.id.et_signup_firstname);
        etLastname = (TextInputEditText) findViewById(R.id.et_signup_lastname);
        etPhone = (TextInputEditText) findViewById(R.id.et_signup_phonenumber);
        etCountry = (TextInputEditText) findViewById(R.id.et_signup_country);
        etCity = (TextInputEditText) findViewById(R.id.et_signup_city);
        etAddress = (TextInputEditText) findViewById(R.id.et_signup_address);
        etPassword = (TextInputEditText) findViewById(R.id.et_signup_password);

        butRegisterUser = (AppCompatButton)findViewById(R.id.but_signup_user);
        butRegisterSeller = (AppCompatButton)findViewById(R.id.but_signup_seller);

        butRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerLoading = ProgressDialog.show(mContext, "WaterHub Sign Up", "Satu langkah lagi anda bergabung bersama kami", true, false);
                requestSignup(roles);
            }
        });

        butRegisterSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Role role = new Role();
                role.setId(3L);

                roles.add(role);

                registerLoading = ProgressDialog.show(mContext, "WaterHub Sign Up", "Satu langkah lagi anda bergabung menjadi mitra kami", true, false);
                requestSignup(roles);
            }
        });
    }

    private void requestSignup(List<Role> roles) {
        String email = etEmail.getText().toString();
        String firstname = etFirstname.getText().toString();
        String lastname = etLastname.getText().toString();
        String phonenumber = etPhone.getText().toString();
        String country = etCountry.getText().toString();
        String city = etCity.getText().toString();
        String address = etAddress.getText().toString();
        String password = etPassword.getText().toString();

        User user = new User();
        Date date = new Date();
        String parseDate = "";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault());
        parseDate = dateFormat.format(date);

        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setBirthDate(parseDate);
        user.setPhone(phonenumber);
        user.setCountry(country);
        user.setCity(city);
        user.setAddress(address);
        user.setLatitude(0D);
        user.setLongitude(0D);
        user.setPicture(null);
        user.setCreatedAt(parseDate);
        user.setRoles(roles);

        Call<MyResponse<Integer>> call = mBaseApiService.registerRequest(user);

        call.enqueue(new Callback<MyResponse<Integer>>() {
            @Override
            public void onResponse(Call<MyResponse<Integer>> call, Response<MyResponse<Integer>> response) {
                MyResponse<Integer> myResponse = response.body();
                Integer data = 0;

                if(myResponse != null){
                    String message = myResponse.getMessage();
                    Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();

                    data = myResponse.getData();
                }

                registerLoading.dismiss();

                if (data == 1)
                    finish();
            }

            @Override
            public void onFailure(Call<MyResponse<Integer>> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                registerLoading.dismiss();
            }
        });
    }
}
