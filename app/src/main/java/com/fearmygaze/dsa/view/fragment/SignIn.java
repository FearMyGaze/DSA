package com.fearmygaze.dsa.view.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.fearmygaze.dsa.Interface.IVolleyMessage;
import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.controller.UserController;
import com.fearmygaze.dsa.custom.SnackBar;
import com.fearmygaze.dsa.util.RegEx;
import com.fearmygaze.dsa.util.TextHandler;
import com.fearmygaze.dsa.view.activity.Main;
import com.fearmygaze.dsa.view.activity.Starting;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class SignIn extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        TextInputEditText loginEmail = view.findViewById(R.id.loginEmail);
        TextInputLayout loginEmailError = view.findViewById(R.id.loginEmailError);

        TextInputEditText loginPasswd = view.findViewById(R.id.loginPasswd);
        TextInputLayout loginPasswdError = view.findViewById(R.id.loginPasswdError);

        MaterialButton confirmLogIn = view.findViewById(R.id.confirmLogIn);

        TextView gotoRegister = view.findViewById(R.id.gotoRegister);

        SharedPreferences getSharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireActivity().getApplicationContext());

        gotoRegister.setOnClickListener(v -> ((Starting) requireActivity()).replaceFragment(((Starting) requireActivity()).signUpFragment));

        /*
         * The moment the TextInputEditText is filled with a text after an error occurred th error
         *   vanishes from the text that was changed
         * */

        loginEmail.addTextChangedListener(new TextHandler(loginEmailError));
        loginPasswd.addTextChangedListener(new TextHandler(loginPasswdError));

        String prefUserEmail = getSharedPrefs.getString("userEmail","empty");
        String prefUserPasswd = getSharedPrefs.getString("userPasswd","empty");
        String prefUsername = getSharedPrefs.getString("userName","empty");
        int prefUserID = getSharedPrefs.getInt("userID",-1);
        
        if(!prefUserEmail.equals("empty") && !prefUserPasswd.equals("empty") && !prefUsername.equals("empty") && prefUserID > -1){ //Remember me func
            UserController.UserExist(requireActivity(), prefUserEmail, new IVolleyMessage() {
                @Override
                public void onWaring(String message) {
                    SharedPreferences.Editor editor = getSharedPrefs.edit().clear();
                    editor.apply();
                    Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onError(String message) {
                    SharedPreferences.Editor editor = getSharedPrefs.edit().clear();
                    editor.apply();
                }

                @Override
                public void onSuccess(String message) {
                    Intent intent = new Intent(requireActivity(), Main.class);
                    requireActivity().startActivity(intent);
                    requireActivity().finish();
                }
            });
        }

        confirmLogIn.setOnClickListener(v -> {

            TextHandler.IsTextInputEmpty(loginEmail, loginEmailError, SignIn.this.requireActivity());
            TextHandler.IsTextInputEmpty(loginPasswd, loginPasswdError, SignIn.this.requireActivity());

            if (!loginEmailError.isErrorEnabled() && !loginPasswdError.isErrorEnabled()) {
                String email = Objects.requireNonNull(loginEmail.getText()).toString().trim();
                String passwd = Objects.requireNonNull(loginPasswd.getText()).toString().trim();

                if (RegEx.IsEmailValid(email,50, loginEmailError, SignIn.this.requireActivity())
                        && RegEx.IsPasswdValid(passwd,255, loginPasswdError, SignIn.this.requireActivity())) {
                    UserController.UserLogin(requireActivity(), email, passwd, new IVolleyMessage() {
                        @Override
                        public void onWaring(String message) {
                            SnackBar userNotification = new SnackBar(requireActivity(), v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                            userNotification.setOnWarningMsg(message);
                            userNotification.onWarning();
                        }

                        @Override
                        public void onError(String message) {
                            SnackBar userNotification = new SnackBar(requireActivity(), v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                            userNotification.setOnErrorMsg(message);
                            userNotification.onError();
                        }

                        @Override
                        public void onSuccess(String message) {
                            Intent intent = new Intent(requireActivity(), Main.class);
                            requireActivity().startActivity(intent);
                            requireActivity().finish();
                        }
                    });
                }
            }
        });

        return view;
    }
}