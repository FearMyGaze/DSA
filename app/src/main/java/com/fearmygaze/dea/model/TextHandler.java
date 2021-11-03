package com.fearmygaze.dea.model;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import com.fearmygaze.dea.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextHandler implements TextWatcher {

    private final TextInputLayout textInputLayout;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(s.length() != 0){
            textInputLayout.setError(null);
            textInputLayout.setErrorEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public TextHandler(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
    }

    public static boolean IsEmailValid(String email, TextInputLayout textInputLayout, Context context){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
        Matcher matcher = pattern.matcher(email);
        boolean matches = matcher.matches();
        if (!matches) {
            textInputLayout.setError(context.getString(R.string.emailRegExError));
            textInputLayout.setErrorEnabled(true);
        }
        return matches;
    }

    public static boolean IsPasswdValid(String passwd , TextView textView, Context context){
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
        Matcher matcher = pattern.matcher(passwd);
        boolean matches = matcher.matches();
        /*
        * TODO: ADD THIS TO SPECIAL CHARACTER !@#$%^&*
        * */

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
            textView.setText(context.getString(R.string.passwdRegExError));
        }
        return matches;
    }

    public static void IsTextInputEmpty(TextInputEditText textInputEditText , TextInputLayout textInputLayout , Context context){
        if (Objects.requireNonNull(textInputEditText.getText()).toString().isEmpty()){
            textInputLayout.setError(context.getString(R.string.emptyTextInputEditText));
            textInputLayout.setErrorEnabled(true);
        }
    }
    public static void IsMultipleTextInputsEmpty(TextInputEditText input1 , TextInputLayout error1 ,
                                                 TextInputEditText input2 , TextInputLayout error2 ,
                                                 TextInputEditText input3 , TextInputLayout error3 ,
                                                 TextInputEditText input4 , TextInputLayout error4 ,
                                                 TextInputEditText input5 , TextInputLayout error5 ,
                                                 Context context){
        if (Objects.requireNonNull(input1.getText()).toString().isEmpty()){
            error1.setError(context.getString(R.string.emptyTextInputEditText));
            error1.setErrorEnabled(true);
        }
        if (Objects.requireNonNull(input2.getText()).toString().isEmpty()){
            error2.setError(context.getString(R.string.emptyTextInputEditText));
            error2.setErrorEnabled(true);
        }
        if (Objects.requireNonNull(input3.getText()).toString().isEmpty()){
            error3.setError(context.getString(R.string.emptyTextInputEditText));
            error3.setErrorEnabled(true);
        }
        if (Objects.requireNonNull(input4.getText()).toString().isEmpty()){
            error4.setError(context.getString(R.string.emptyTextInputEditText));
            error4.setErrorEnabled(true);
        }
        if (Objects.requireNonNull(input5.getText()).toString().isEmpty()){
            error5.setError(context.getString(R.string.emptyTextInputEditText));
            error5.setErrorEnabled(true);
        }
    }

    public static boolean IsTextInputsEqual(TextInputEditText textInputEditText , TextInputEditText textInputEditText2 , TextInputLayout  textInputLayout , Context context){
        String input1 = Objects.requireNonNull(textInputEditText.getText()).toString().trim();
        String input2 = Objects.requireNonNull(textInputEditText2.getText()).toString().trim();

        if (!input1.equals(input2)){
            textInputLayout.setError(context.getString(R.string.passwordNotEqual));
            textInputLayout.setErrorEnabled(true);
            return true;
        }
        return false;
    }
}
