package com.waterhub.waterhub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.waterhub.waterhub.SessionManager.SessionManager;

public class MainHomeActivity extends AppCompatActivity {

    private Button btnLogout;
    private Button mapButton;
    private Menu mMenu1;

    private SessionManager mSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        mSessionManager = new SessionManager(this);
        Boolean isLogin = mSessionManager.isLogin();

        if (!isLogin) {
            moveToLogin();
        }

        initComponent();
        onCreateOptionsMenu(mMenu1);
    }

    private void initComponent() {
        btnLogout = (Button) findViewById(R.id.btn_logout);
        mMenu1 = (Menu) findViewById(R.id.menu1);
        mapButton = (Button) findViewById(R.id.but_home_search);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainHomeActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mSessionManager.deleteUser();
                moveToLogin();
            }
        });
    }

    private void moveToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

}
