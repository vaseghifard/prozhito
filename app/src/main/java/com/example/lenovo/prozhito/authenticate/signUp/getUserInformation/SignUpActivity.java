package com.example.lenovo.prozhito.authenticate.signUp.getUserInformation;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;


import com.example.lenovo.prozhito.CustomViews.MyButton;
import com.example.lenovo.prozhito.CustomViews.MyEditText;
import com.example.lenovo.prozhito.CustomViews.MyTextView;
import com.example.lenovo.prozhito.R;
import com.example.lenovo.prozhito.authenticate.ValidateInformationUser;
import com.example.lenovo.prozhito.authenticate.signUp.confirmCode.ConfirmCodeActivity;
import com.example.lenovo.prozhito.authenticate.UserInformationModel;
import com.example.lenovo.prozhito.utils.BaseActivity;
import com.example.lenovo.prozhito.utils.PublicMethods;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends BaseActivity implements Contract.View, View.OnClickListener {
    CircleImageView user_photo;
    MyEditText user_name_family;
    MyEditText user_phone;
    MyEditText user_password;
    MyButton sign_up;
    MyTextView showResult;
    int CAMERA_PERMISSION_CODE = 1000;
    int CAPTURE_CODE = 2000;
    Bitmap userImage;

    Presenter presenter = new Presenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        bind();


    }

    void bind() {

        user_photo = findViewById(R.id.user_photo);
        user_name_family = findViewById(R.id.user_name_family);
        user_phone = findViewById(R.id.user_phone);
        user_password = findViewById(R.id.user_password);
        showResult = findViewById(R.id.showResult);
        sign_up = findViewById(R.id.sign_up);
        presenter.attachView(this);
        sign_up.setOnClickListener(this);
        user_photo.setOnClickListener(this);
    }



    void checkCameraPermissionAndCapture() {
        if (ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mActivity,
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_CODE);
        } else {
            capture();
        }
    }

    public  void capture(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       startActivityForResult(cameraIntent, CAPTURE_CODE);

    }

    @Override
    public void savedInformation() {
        startActivity(new Intent(mContext, ConfirmCodeActivity.class));
    }

    @Override
    public void onError() {
        PublicMethods.toast(R.string.error_sign_up);
    }

    @Override
    public void savedUserImage() {
        user_photo.setImageBitmap(userImage);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.sign_up):

                if (!ValidateInformationUser.validateNameFamily(user_name_family.text())) {
                    showResult.setVisibility(View.VISIBLE);
                    showResult.setText(R.string.error_name_family_sign_up);
                } else if (!ValidateInformationUser.validatePassword(user_password.text())) {
                    showResult.setVisibility(View.VISIBLE);
                    showResult.setText(R.string.error_password_sign_up);
                } else if (!ValidateInformationUser.validatePhone(user_phone.text())) {
                    showResult.setVisibility(View.VISIBLE);
                    showResult.setText(R.string.error_phone_sign_up);
                } else {
                    showResult.setVisibility(View.GONE);
                    UserInformationModel userInformationModel = UserInformationModel
                            .newBuilder().name_family(user_name_family.text())
                            .phone(user_phone.text()).password(user_password.text()).build();
                    presenter.saveInformatioUser(userInformationModel);
                }
                break;
            case (R.id.user_photo):
                checkCameraPermissionAndCapture();
                break;
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            checkCameraPermissionAndCapture();
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode==CAPTURE_CODE){
            if( resultCode== Activity.RESULT_OK){
                userImage=(Bitmap) data.getExtras().get("data");
                presenter.captureImage(userImage);

            }
        }
    }
}
