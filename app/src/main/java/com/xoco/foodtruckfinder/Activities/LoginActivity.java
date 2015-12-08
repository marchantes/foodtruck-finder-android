package com.xoco.foodtruckfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xoco.foodtruckfinder.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mToRegisterBtn;
    private Button mLoginSubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginSubmitBtn = (Button) findViewById(R.id.login_submit_btn);
        mLoginSubmitBtn.setOnClickListener(this);

        mToRegisterBtn = (Button)findViewById(R.id.login_register_btn);
        mToRegisterBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.login_submit_btn:
                //TODO Implement login submit logic
                Toast.makeText(this, "Not yet implemented", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_register_btn:
                Intent toLoginActivityIntent = new Intent(this, RegisterActivity.class);
                startActivity(toLoginActivityIntent);
                break;

        }

    }
}
