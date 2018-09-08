package com.example.lenovo.prozhito.authenticate.signUp.confirmCode;

public interface Contract {

    interface View {
        void signedUp();

        void onErrorSignedUp();

        void sent();

        void onErrorSent();
    }

    interface Presenter {
        void attachView(View view);

        void finalSignUp(String confirmCode);

        void signedUp();

        void onErrorSignedUp();

        void sendCodeAgain();

        void sent();

        void onErrorSent();
    }

    interface Model {
        void attachPresenter(Presenter presenter);

        void finalSignUp(String confirmCode);

        void sendCodeAgain();
    }
}
