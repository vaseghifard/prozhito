package com.example.lenovo.prozhito.authenticate.signUp.confirmCode;

import com.example.lenovo.prozhito.authenticate.RestUserService;
import com.example.lenovo.prozhito.authenticate.UsersEndPoints;
import com.example.lenovo.prozhito.utils.PublicMethods;

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
    public void finalSignUp(String confirmCode) {

        RestUserService.create(UsersEndPoints.class)
                .confirmCode(confirmCode,
                        PublicMethods.getValue("phone",null))
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            presenter.signedUp();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        presenter.onErrorSignedUp();
                    }
                });
    }

    @Override
    public void sendCodeAgain() {

        RestUserService.create(UsersEndPoints.class)
                .sendCodeAgain(PublicMethods.getValue("phone",null))
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200){
                            presenter.sent();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        presenter.onErrorSent();
                    }
                });

    }
}
