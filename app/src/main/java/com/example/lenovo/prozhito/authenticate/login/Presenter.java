package com.example.lenovo.prozhito.authenticate.login;

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
    public void login(UserInformationModel user) {
        model.login(user);
    }

    @Override
    public void loginSuccessed() {
        view.loginSuccessed();
    }

    @Override
    public void loginError() {
        view.loginError();
    }

    @Override
    public void sendPassword(String phoneNumber) {
        model.sendPassword(phoneNumber);
    }

    @Override
    public void passwordSent() {
        view.passwordSent();
    }

    @Override
    public void passwordFailed() {
view.passwordFailed();
    }
}
