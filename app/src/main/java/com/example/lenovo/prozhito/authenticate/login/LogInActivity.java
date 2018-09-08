package com.example.lenovo.prozhito.authenticate.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.example.lenovo.prozhito.CustomViews.MyButton;
import com.example.lenovo.prozhito.CustomViews.MyEditText;
import com.example.lenovo.prozhito.CustomViews.MyTextView;
import com.example.lenovo.prozhito.MainDashboard;
import com.example.lenovo.prozhito.R;
import com.example.lenovo.prozhito.authenticate.UserInformationModel;
import com.example.lenovo.prozhito.authenticate.ValidateInformationUser;
import com.example.lenovo.prozhito.utils.BaseActivity;
import com.example.lenovo.prozhito.utils.PublicMethods;

public class LogInActivity extends BaseActivity implements View.OnClickListener, Contract.View {
    MyEditText user_phone;
    MyEditText user_password;
    MyTextView forget_password;
    MyTextView showResult;
    MyButton login;
    MyEditText phone;
    Presenter presenter = new Presenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bind();
    }

    void bind() {
        user_phone = findViewById(R.id.user_phone);
        user_password = findViewById(R.id.user_password);
        forget_password = findViewById(R.id.forget_pass);
        showResult = findViewById(R.id.showResult);
        login = findViewById(R.id.login);
        presenter.attachView(this);
        login.setOnClickListener(this);
        forget_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                if (!ValidateInformationUser.validateNameFamily(user_phone.text())) {
                    showResult.setVisibility(View.VISIBLE);
                    showResult.setText(R.string.error_phone_sign_up);
                } else if (!ValidateInformationUser.validatePassword(user_password.text())) {
                    showResult.setVisibility(View.VISIBLE);
                    showResult.setText(R.string.error_password_sign_up);
                } else {
                    showResult.setVisibility(View.GONE);
                    UserInformationModel userInformationModel = UserInformationModel
                            .newBuilder()
                            .phone(user_phone.text()).password(user_password.text()).build();
                    presenter.login(userInformationModel);
                }
                break;
            case R.id.forget_pass:
                openDialog();
                break;
        }
    }

    void openDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        View  view=this.getLayoutInflater().inflate(R.layout.dialog_forgot_password,null);
        dialog.setView(view);
        phone=view.findViewById(R.id.phone);
        MyButton send=view.findViewById(R.id.send_password);
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ValidateInformationUser.validatePhone(phone.text())) {
                    showResult.setVisibility(View.VISIBLE);
                    showResult.setText(R.string.error_phone_sign_up);
                } else {
                    showResult.setVisibility(View.GONE);
                    presenter.sendPassword(phone.text());
                }
            }
        });

    }

    @Override
    public void loginSuccessed() {
      startActivity(new Intent(mContext, MainDashboard.class));

    }

    @Override
    public void loginError() {
        PublicMethods.toast(R.string.error_login);
    }

    @Override
    public void passwordSent() {
        PublicMethods.toast(R.string.send_password_with_sms);
    }

    @Override
    public void passwordFailed() {
        PublicMethods.toast(R.string.error_login);
    }


}
