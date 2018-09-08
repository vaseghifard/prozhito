package com.example.lenovo.prozhito.authenticate;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UsersEndPoints {

    @FormUrlEncoded
    @POST("register.php")
    Call<Void> register(
            @Field("name_family") String user_name_family,
            @Field("phone") String user_phone,
            @Field("password") String user_password
    );
    @FormUrlEncoded
    @POST("confirmCode.php")
    Call<Void> confirmCode(
            @Field("confirm_code") String confirm_code,
            @Field("phone") String phone
    );

    @FormUrlEncoded
    @POST("sendCodeAgain.php")
    Call<Void> sendCodeAgain(
            @Field("phone") String phone
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<UserInformationModel> login(
            @Field("phone") String phone,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("sendPassword.php")
    Call<Void> sendPassword(
            @Field("phone") String phone
    );
}
