package com.example.lenovo.prozhito.authenticate.signUp.getUserInformation;


import android.graphics.Bitmap;
import android.util.Base64;

import com.example.lenovo.prozhito.authenticate.RestUserService;
import com.example.lenovo.prozhito.authenticate.UsersEndPoints;
import com.example.lenovo.prozhito.authenticate.UserInformationModel;
import com.example.lenovo.prozhito.utils.PublicMethods;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model implements Contract.Model {

    Contract.Presenter presenter;


    @Override
    public void attachPresenter(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void saveInformationUser(final UserInformationModel userInformationModel) {

        RestUserService.create(UsersEndPoints.class)
                .register(
                        userInformationModel.getName_family(),
                        userInformationModel.getPhone(),
                        userInformationModel.getPassword()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200) {
                    PublicMethods.saveValue("phone", userInformationModel.getPhone());
                    presenter.savedInformation();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                presenter.onError();
            }
        });


    }

    @Override
    public void captureImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArray);
        byte[] bytes = byteArray.toByteArray();
        String encodedImage = Base64.encodeToString(bytes, Base64.DEFAULT);
        PublicMethods.saveValue("user_image", encodedImage);
        presenter.savedUserImage();

    }
}
