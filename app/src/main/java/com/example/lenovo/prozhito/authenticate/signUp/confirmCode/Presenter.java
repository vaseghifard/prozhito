package com.example.lenovo.prozhito.authenticate.signUp.confirmCode;

public class Presenter implements Contract.Presenter {
    Model model = new Model();
    Contract.View view;

    @Override
    public void attachView(Contract.View view) {
        this.view = view;
        model.attachPresenter(this);
    }

    @Override
    public void finalSignUp(String confirmCode) {
        model.finalSignUp(confirmCode);
    }

    @Override
    public void signedUp() {
        view.signedUp();
    }

    @Override
    public void onErrorSignedUp() {
        view.onErrorSignedUp();
    }

    @Override
    public void sendCodeAgain() {
        model.sendCodeAgain();
    }

    @Override
    public void sent() {
        view.sent();
    }


    @Override
    public void onErrorSent() {
        view.onErrorSent();
    }
}
