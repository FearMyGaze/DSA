package com.fearmygaze.dsa.util;

import android.content.Context;

import com.fearmygaze.dsa.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {

    /**
     * @param email We need it to get the email and check if it is following the regEx we set.
     * @param maxLength We need it set the max character length of our string.
     * @param textInputLayout We need it to set the error if exists in the corresponding "cell".
     * @param context We need it to get the String from resource file strings.xml.
     * @return a bool statement based on if the the email is correct based on regEx.
     */
    public static boolean IsEmailValid(String email, int maxLength, TextInputLayout textInputLayout, Context context) {
        Pattern pattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,3})$");
        Matcher matcher = pattern.matcher(email);
        boolean matches = matcher.matches();

        if (email.length() > maxLength){
            textInputLayout.setError(context.getString(R.string.regExTooManyCharacters));
            textInputLayout.setErrorEnabled(true);
        }else {
            if (!matches) {
                textInputLayout.setError(context.getString(R.string.emailRegExError));
                textInputLayout.setErrorEnabled(true);
            }
        }
        return matches;
    }

    /**
     * @param passwd We need it to get the passwd and check if it is following the regEx we set.
     * @param maxLength We need it set the max character length of our string.
     * @param textInputLayout We need it to set the error if exists in the corresponding "cell".
     * @param context We need it to get the String from resource file strings.xml.
     * @return a bool statement based on if the the passwd is correct based on regEx.
     */
    public static boolean IsPasswdValid(String passwd, int maxLength, TextInputLayout textInputLayout, Context context) {
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

        if (passwd.length() > maxLength){
            textInputLayout.setError(context.getString(R.string.regExTooManyCharacters));
            textInputLayout.setErrorEnabled(true);
        }else{
            if (!matches) {
                textInputLayout.setError(context.getString(R.string.passwdRegExError));
                textInputLayout.setErrorEnabled(true);
            }
        }
        return matches;
    }

    /**
     * @param name We need it to get the name and check if it is following the regEx we set.
     * @param textInputLayout We need it to set the error if exists in the corresponding "cell".
     * @param context We need it to get the String from resource file strings.xml.
     * @return a bool statement based on if the the name is correct based on regEx.
     */
    public static boolean IsNameValid(String name, int maxLength, TextInputLayout textInputLayout, Context context) {
        Pattern pattern = Pattern.compile("[a-zA-Z_]+");
        Matcher matcher = pattern.matcher(name);
        boolean matches = matcher.matches();

        if (name.length() > maxLength){
            textInputLayout.setError(context.getString(R.string.regExTooManyCharacters));
            textInputLayout.setErrorEnabled(true);
        }else {
            if (!matches) {
                textInputLayout.setError(context.getString(R.string.nameRegExError));
                textInputLayout.setErrorEnabled(true);
            }
        }
        return matches;
    }

}
