package com.example.lenovo.prozhito.authenticate.signUp.confirmCode;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.example.lenovo.prozhito.CustomViews.MyButton;
import com.example.lenovo.prozhito.CustomViews.MyEditText;
import com.example.lenovo.prozhito.R;
import com.example.lenovo.prozhito.authenticate.login.LogInActivity;
import com.example.lenovo.prozhito.utils.BaseActivity;
import com.example.lenovo.prozhito.utils.PublicMethods;

public class ConfirmCodeActivity extends BaseActivity implements View.OnClickListener, Contract.View {

    MyEditText confirmCodeEditText;
    MyButton sendCodeAgain;


    private static final int PERMISSION_REQUEST_CODE = 1;

    Presenter presenter = new Presenter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_code);

        bind();
        receivedSms();
        requestPermission();

    }

    void bind() {
        confirmCodeEditText = findViewById(R.id.confirm_code_editText);
        sendCodeAgain = findViewById(R.id.send_code_confirm_again);
        presenter.attachView(this);
        sendCodeAgain.setOnClickListener(this);
    }

    void receivedSms() {
        SmsReceiver.bindListener(new SmsListener() {
            @Override
            public void onMessageReceived(String messageText) {
                confirmCodeEditText.setText(messageText);
                presenter.finalSignUp(messageText);
            }
        });
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_CODE);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.send_code_confirm_again:
                presenter.sendCodeAgain();
                break;
        }
    }

    @Override
    public void signedUp() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(mContext, LogInActivity.class));
            }
        }, 3000);

    }

    @Override
    public void onErrorSignedUp() {
        PublicMethods.toast(R.string.error_confirm_code);
    }

    @Override
    public void sent() {
        receivedSms();
    }

    @Override
    public void onErrorSent() {
        PublicMethods.toast(R.string.error_confirm_code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        requestPermission();

    }

}
