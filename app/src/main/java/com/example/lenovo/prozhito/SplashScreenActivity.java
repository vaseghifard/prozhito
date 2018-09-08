package com.example.lenovo.prozhito;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.prozhito.authenticate.login.LogInActivity;
import com.example.lenovo.prozhito.authenticate.signUp.getUserInformation.SignUpActivity;
import com.example.lenovo.prozhito.utils.BaseActivity;

public class SplashScreenActivity extends BaseActivity implements View.OnClickListener {

    TextView login_text;
    Button signUp_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        bind();


    }

    void bind() {
        login_text = findViewById(R.id.login_text);
        signUp_text = findViewById(R.id.sign_up_text);
        login_text.setOnClickListener(this);
        signUp_text.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.login_text:
                Intent loginIntent = new Intent(this, LogInActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.sign_up_text:
                Intent signUpIntent = new Intent(this, SignUpActivity.class);
                startActivity(signUpIntent);
                break;
        }

    }
}
