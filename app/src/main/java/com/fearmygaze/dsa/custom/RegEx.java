package com.fearmygaze.dsa.custom;

import android.content.Context;
import android.widget.TextView;

import com.fearmygaze.dsa.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {

    public static boolean IsEmailValid(String email, TextInputLayout textInputLayout, Context context){
        Pattern pattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,3})$");
        Matcher matcher = pattern.matcher(email);
        boolean matches = matcher.matches();
        if (!matches) {
            textInputLayout.setError(context.getString(R.string.emailRegExError));
            textInputLayout.setErrorEnabled(true);
        }
        return matches;
    }

    public static boolean IsPasswdValid(String passwd , TextInputLayout textInputLayout, Context context){
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$");
        Matcher matcher = pattern.matcher(passwd);
        boolean matches = matcher.matches();

        /*
         * ^                 # start of the string
         * (?=.*[0-9])       # a digit must occur at least once
         * (?=.*[a-z])       # a lower case letter must occur at least once
         * (?=.*[A-Z])       # an upper case letter must occur at least once
         * (?=.*[@#$%^&+=])  # a special character must occur at least once
         * (?=\S+$)          # no whitespace allowed in the entire string
         * .{8,}             # anything, at least eight places though
         * $                 # end of the string
         * */

        if (!matches) {
            textInputLayout.setError(context.getString(R.string.passwdRegExError));
            textInputLayout.setErrorEnabled(true);
        }
        return matches;
    }
}
