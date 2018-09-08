package com.example.lenovo.prozhito.authenticate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateInformationUser {
    private static Boolean result_name_family;
    private static Boolean result_phone;
    private static Boolean result_password;
    private static Pattern pattern;
    private static Matcher matcher;
    private static final String password_pattern = "^[0-9a-zA-Z!@#$*%^&{}':;.,?`|+-_%<>/=~\\[\\]\"\\\\ ]{6,32}$";
    private static final String phone_pattern = "^(\\+98|0)?9\\d{9}$";

    public static Boolean validateNameFamily(String userNameFamily) {
        if (userNameFamily.equals(""))
            result_name_family = false;
        else {
            String[] firstNameList = userNameFamily.split("\\s+");
            StringBuilder listString = new StringBuilder();
            for (String s : firstNameList)
                listString.append(s + " ");
            for (int i = 0; i < Character.codePointCount(userNameFamily, 0, userNameFamily.length()); i++) {
                int c = userNameFamily.codePointAt(i);
                if (c >= 0x0600 && c <= 0x06FF || c == 0xFB8A || c == 0x067E || c == 0x0686 || c == 0x06AF || c == 0x0020)
                    result_name_family = true;
                else {
                    result_name_family = false;
                    return result_name_family;
                }
            }
        }

        return result_name_family;
    }


    public static Boolean validatePassword(String userPassword) {
        if (userPassword.equals(""))
            result_password = false;
        else {
            pattern = Pattern.compile(password_pattern);
            matcher = pattern.matcher(userPassword);
            if (matcher.matches()) {
                result_password = true;
            } else {
                result_password = false;
                return result_password;

            }
        }

        return result_password;
    }

    public static Boolean validatePhone(String userPhone) {
        if (userPhone.equals(""))
            result_phone = false;
        else {
            pattern = Pattern.compile(phone_pattern);
            matcher = pattern.matcher(userPhone);
            if (matcher.matches()) {
                result_phone = true;
            } else {
                result_phone = false;
                return result_phone;
            }
        }


        return result_phone;

    }

}
