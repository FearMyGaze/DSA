package com.fearmygaze.dsa.view.fragment;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.fearmygaze.dsa.Interface.IVolleyMessage;
import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.controller.UserController;
import com.fearmygaze.dsa.custom.UserNotification;
import com.fearmygaze.dsa.util.RegEx;
import com.fearmygaze.dsa.util.TextHandler;
import com.fearmygaze.dsa.view.activity.Main;
import com.fearmygaze.dsa.view.activity.Starting;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class SignUp extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        TextInputEditText registerName = view.findViewById(R.id.registerName);
        TextInputLayout registerNameError = view.findViewById(R.id.registerNameError);

        TextInputEditText registerEmail = view.findViewById(R.id.registerEmail);
        TextInputLayout registerEmailError = view.findViewById(R.id.registerEmailError);

        TextInputEditText registerPasswd = view.findViewById(R.id.registerPasswd);
        TextInputLayout registerPasswdError = view.findViewById(R.id.registerPasswdError);

        TextInputEditText registerConfirmPasswd = view.findViewById(R.id.registerConfirmPasswd);
        TextInputLayout registerConfirmPasswdError = view.findViewById(R.id.registerConfirmPasswdError);

        TextView gotoLogIn = view.findViewById(R.id.gotoLogIn);

        CheckBox registerTOS = view.findViewById(R.id.registerTOS);

        MaterialButton confirmRegistration = view.findViewById(R.id.confirmRegistration);

        ContentResolver resolver = requireActivity().getContentResolver();


        /*
         * The moment the TextInputEditText is filled with a text after an error occurred the error
         *   vanishes from the text that was changed
         * */
        registerName.addTextChangedListener(new TextHandler(registerNameError));
        registerEmail.addTextChangedListener(new TextHandler(registerEmailError));
        registerPasswd.addTextChangedListener(new TextHandler(registerPasswdError));
        registerConfirmPasswd.addTextChangedListener(new TextHandler(registerConfirmPasswdError));

        gotoLogIn.setOnClickListener(v -> ((Starting) requireActivity()).replaceFragment(((Starting) requireActivity()).singInFragment));

        registerTOS.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                confirmRegistration.setEnabled(true);
                confirmRegistration.setOnClickListener(v -> {

                    TextHandler.IsMultipleTextInputsEmpty(
                            registerName, registerNameError,
                            registerEmail, registerEmailError,
                            registerPasswd, registerPasswdError,
                            registerConfirmPasswd, registerConfirmPasswdError,
                            requireActivity());

                    if (!registerNameError.isErrorEnabled() && !registerEmailError.isErrorEnabled() &&
                            !registerPasswdError.isErrorEnabled() && !registerConfirmPasswdError.isErrorEnabled() &&
                            !TextHandler.IsTextInputsEqual(registerPasswd, registerConfirmPasswd, registerConfirmPasswdError, requireActivity())) {

                        String name = Objects.requireNonNull(registerName.getText()).toString().trim();
                        String email = Objects.requireNonNull(registerEmail.getText()).toString().trim();
                        String passwd = Objects.requireNonNull(registerPasswd.getText()).toString().trim();
                        @SuppressLint("HardwareIds") String deviceID = Settings.Secure.getString(resolver, Settings.Secure.ANDROID_ID);

                        if (RegEx.IsEmailValid(email,50, registerEmailError, requireActivity()) && RegEx.IsPasswdValid(passwd,255, registerPasswdError, requireActivity()) &&
                                RegEx.IsNameValid(name,30, registerNameError, requireActivity())) {

                            UserController.UserRegister(requireActivity(), name, email, passwd, deviceID, new IVolleyMessage() {
                                @Override
                                public void onWaring(String message) {
                                    UserNotification userNotification = new UserNotification(requireActivity(), v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                                    userNotification.setOnWarningMsg(message);
                                    userNotification.onWarning();
                                }

                                @Override
                                public void onError(String message) {
                                    UserNotification userNotification = new UserNotification(requireActivity(), v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
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

            } else confirmRegistration.setEnabled(false);
        });
        return view;
    }
}