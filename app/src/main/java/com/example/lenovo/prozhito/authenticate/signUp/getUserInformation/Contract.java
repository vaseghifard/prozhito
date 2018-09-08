package com.example.lenovo.prozhito.authenticate.signUp.getUserInformation;

import android.app.Activity;
import android.graphics.Bitmap;

import com.example.lenovo.prozhito.authenticate.UserInformationModel;

public interface Contract {

    interface View {
        void savedInformation();
        void onError();
        void savedUserImage();
    }

    interface Presenter {
        void attachView(View view);
        void saveInformatioUser(UserInformationModel userInformationModel);
        void savedInformation();
        void onError();
        void captureImage(Bitmap bitmap);
        void savedUserImage();
    }

    interface Model {
        void attachPresenter(Presenter presenter);
        void saveInformationUser(UserInformationModel userInformationModel);
        void captureImage(Bitmap bitmap);
    }
}
