package com.waterhub.waterhub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import butterknife.BindView;

public class MainHomeActivity extends AppCompatActivity {

    @BindView(R.id.menu1) Menu mMenu1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        onCreateOptionsMenu(mMenu1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


}
