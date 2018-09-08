package com.example.lenovo.prozhito.authenticate.signUp.getUserInformation;

import android.graphics.Bitmap;

import com.example.lenovo.prozhito.authenticate.UserInformationModel;

public class Presenter implements Contract.Presenter {
    Model model = new Model();
    private Contract.View view;

    @Override
    public void attachView(Contract.View view) {
        this.view = view;
        model.attachPresenter(this);

    }

    @Override
    public void saveInformatioUser(UserInformationModel userInformationModel) {
        model.saveInformationUser(userInformationModel);
    }

    @Override
    public void savedInformation() {
        view.savedInformation();
    }

    @Override
    public void onError() {
        view.onError();
    }

    @Override
    public void captureImage(Bitmap bitmap) {
        model.captureImage(bitmap);
    }

    @Override
    public void savedUserImage() {
        view.savedUserImage();
    }
}
