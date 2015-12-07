package com.xoco.foodtruckfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xoco.foodtruckfinder.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener
{

    private Button mGoToMap;
    private Button mSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mSignUp = (Button)findViewById(R.id.home_button_sign_up);
        mSignUp.setOnClickListener(this);

        mGoToMap = (Button)findViewById(R.id.home_button_go_to_map);
        mGoToMap.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.home_button_go_to_map:
                Intent toMapIntent = new Intent(this, MainActivity.class);
                startActivity(toMapIntent);
                break;
            //TODO: Implement logic to sign up
            case R.id.home_button_sign_up:
                Toast.makeText(getApplicationContext(), "Not yet implemented", Toast.LENGTH_SHORT).show();
                break;

        }

    }
}
