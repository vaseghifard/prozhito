package com.example.lenovo.prozhito.authenticate;

public class UserInformationModel {
    private String name_family, phone, password,token;

    public UserInformationModel() {
    }

    public UserInformationModel(String name_family, String phone, String password,String token) {
        this.name_family = name_family;
        this.phone = phone;
        this.password = password;
        this.token = token;
    }

    private UserInformationModel(Builder builder) {
        setName_family(builder.name_family);
        setPhone(builder.phone);
        setPassword(builder.password);
        setPassword(builder.token);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getName_family() {
        return name_family;
    }

    public void setName_family(String name_family) {
        this.name_family = name_family;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static final class Builder {
        private String name_family;
        private String phone;
        private String password;
        private String token;

        private Builder() {
        }

        public Builder name_family(String val) {
            name_family = val;
            return this;
        }

        public Builder phone(String val) {
            phone = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder token(String val) {
            token = val;
            return this;
        }

        public UserInformationModel build() {
            return new UserInformationModel(this);
        }
    }
}