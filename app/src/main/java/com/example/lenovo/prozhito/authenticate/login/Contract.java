package com.example.lenovo.prozhito.authenticate.login;

import com.example.lenovo.prozhito.authenticate.UserInformationModel;

public interface Contract {
    interface View {
        void loginSuccessed();
        void loginError();
        void passwordSent();
        void passwordFailed();
    }

    interface Presenter {
        void attachView(View view);
        void login(UserInformationModel user);
        void loginSuccessed();
        void loginError();
        void sendPassword(String phoneNumber);
        void passwordSent();
        void passwordFailed();
    }

    interface Model {
        void attachPresenter(Presenter presenter);
        void login(UserInformationModel user);
        void sendPassword(String phoneNumber);
    }
}
