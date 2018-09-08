package com.example.lenovo.prozhito.authenticate.login;

import com.example.lenovo.prozhito.authenticate.RestUserService;
import com.example.lenovo.prozhito.authenticate.UserInformationModel;
import com.example.lenovo.prozhito.authenticate.UsersEndPoints;
import com.example.lenovo.prozhito.utils.PublicMethods;

import java.io.IOException;

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
    public void login(UserInformationModel user) {
        try {
            Response<UserInformationModel> result = RestUserService.create(UsersEndPoints.class).login(user.getPhone()
                    , user.getPassword()).execute();
            if (result.code() == 200) {
                UserInformationModel userInfo = result.body();
                PublicMethods.saveValue("token", userInfo.getToken());
                PublicMethods.saveValue("name_family", userInfo.getName_family());
                PublicMethods.saveValue("phone", userInfo.getPhone());
                presenter.loginSuccessed();

            }
        } catch (IOException e) {
            e.printStackTrace();
            presenter.loginError();
        }
    }

    @Override
    public void sendPassword(String phoneNumber) {
        RestUserService.create(UsersEndPoints.class).sendPassword(phoneNumber)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200)
                            presenter.passwordSent();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        presenter.passwordFailed();
                    }
                });
    }
}
