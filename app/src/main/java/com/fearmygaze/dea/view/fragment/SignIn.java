package com.fearmygaze.dea.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.fearmygaze.dea.R;
import com.fearmygaze.dea.custom.MyToast.CustomToast;
import com.fearmygaze.dea.custom.RegEx;
import com.fearmygaze.dea.custom.TextHandler;
import com.fearmygaze.dea.view.activity.Main;
import com.fearmygaze.dea.view.activity.Starting;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class SignIn extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        CustomToast customToast = ((Starting) requireActivity()).customToast;

        TextInputEditText loginEmail = view.findViewById(R.id.loginEmail);
        TextInputLayout loginEmailError = view.findViewById(R.id.loginEmailError);

        TextInputEditText loginPasswd = view.findViewById(R.id.loginPasswd);
        TextInputLayout loginPasswdError = view.findViewById(R.id.loginPasswdError);

        TextView loginErrorShower = view.findViewById(R.id.loginErrorShower);

        CheckBox rememberMe = view.findViewById(R.id.login_remember_me);

        Button confirmLogIn = view.findViewById(R.id.confirmLogIn);

        TextView gotoRegister = view.findViewById(R.id.gotoRegister);

        gotoRegister.setOnClickListener(v -> ((Starting)requireActivity()).replaceFragment(((Starting)requireActivity()).registerFragment));

        /*
         * The moment the TextInputEditText is filled with a text after an error occurred th error
         *   vanishes from the text that was changed
         * */

        loginEmail.addTextChangedListener(new TextHandler(loginEmailError));
        loginPasswd.addTextChangedListener(new TextHandler(loginPasswdError));

        confirmLogIn.setOnClickListener(v -> {

            TextHandler.IsTextInputEmpty(loginEmail,loginEmailError,requireActivity());
            TextHandler.IsTextInputEmpty(loginPasswd,loginPasswdError,requireActivity());

            if (!loginEmailError.isErrorEnabled() && !loginPasswdError.isErrorEnabled()){
                String email = Objects.requireNonNull(loginEmail.getText()).toString().trim();
                String passwd = Objects.requireNonNull(loginPasswd.getText()).toString().trim();

                if (RegEx.IsEmailValid(email,loginEmailError,requireActivity()) && RegEx.IsPasswdValid(passwd,loginErrorShower,requireActivity())) {

                    if (rememberMe.isChecked()) {
                        /*
                         * TODO: Add the remember me function here
                         * */



                        /*
                         * TODO: Remove it
                         * */
                        Intent intent = new Intent(requireActivity(), Main.class);
                        startActivity(intent);

                        customToast.setOnSuccessMsg(email + " " + passwd + " " + rememberMe);
                        customToast.onSuccess();
                    }

                    /*
                     * TODO: Add the stuff to complete the login form
                     * */


                    customToast.setOnSuccessMsg(email + " " + passwd);
                    customToast.onSuccess();
                }

            }
            customToast.setOnErrorMsg("Wrong credentials");
            customToast.onError();


        });

        return view;
    }
}