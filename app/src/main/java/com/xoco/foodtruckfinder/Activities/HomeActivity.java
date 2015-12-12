package com.xoco.foodtruckfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xoco.foodtruckfinder.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener
{

    private Button mToMapBtn;
    private Button mLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mLoginBtn = (Button)findViewById(R.id.home_login_btn);
        mLoginBtn.setOnClickListener(this);

        mToMapBtn = (Button)findViewById(R.id.home_to_map_btn);
        mToMapBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.home_to_map_btn:
                Intent toMapActivityIntent = new Intent(this, MainActivity.class);
                startActivity(toMapActivityIntent);
                break;
            case R.id.home_login_btn:
                Intent toLoginActivityIntent = new Intent(this, LoginActivity.class);
                startActivity(toLoginActivityIntent);
                break;

        }   

    }
}
