package com.fearmygaze.dea.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import com.fearmygaze.dea.R;
import com.fearmygaze.dea.custom.MyToast.CustomToast;
import com.fearmygaze.dea.model.TextHandler;
import com.fearmygaze.dea.view.activity.Starting;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class SignUp extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        //CustomToast customToast = ((Starting) requireActivity()).customToast;

        TextInputEditText registerName = view.findViewById(R.id.registerName);
        TextInputLayout registerNameError = view.findViewById(R.id.registerNameError);

        TextInputEditText registerLastName = view.findViewById(R.id.registerLastName);
        TextInputLayout registerLastNameError = view.findViewById(R.id.registerLastNameError);

        TextInputEditText registerEmail = view.findViewById(R.id.registerEmail);
        TextInputLayout registerEmailError = view.findViewById(R.id.registerEmailError);

        TextInputEditText registerPasswd = view.findViewById(R.id.registerPasswd);
        TextInputLayout registerPasswdError = view.findViewById(R.id.registerPasswdError);

        TextInputEditText registerConfirmPasswd = view.findViewById(R.id.registerConfirmPasswd);
        TextInputLayout registerConfirmPasswdError = view.findViewById(R.id.registerConfirmPasswdError);

        TextView gotoLogIn = view.findViewById(R.id.gotoLogIn);
        TextView errorShower = view.findViewById(R.id.registerErrorShower);

        CheckBox registerTOS = view.findViewById(R.id.registerTOS);

        Button confirmRegistration = view.findViewById(R.id.confirmRegistration);


        /*
        * The moment the TextInputEditText is filled with a text after an error occurred the error
        *   vanishes from the text that was changed
        * */
        registerName.addTextChangedListener(new TextHandler(registerNameError));
        registerLastName.addTextChangedListener(new TextHandler(registerLastNameError));
        registerEmail.addTextChangedListener(new TextHandler(registerEmailError));
        registerPasswd.addTextChangedListener(new TextHandler(registerPasswdError));
        registerConfirmPasswd.addTextChangedListener(new TextHandler(registerConfirmPasswdError));

        gotoLogIn.setOnClickListener(v -> ((Starting)requireActivity()).replaceFragment(((Starting)requireActivity()).logInFragment));

        registerTOS.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                confirmRegistration.setEnabled(true);
                confirmRegistration.setOnClickListener(v -> {

                    TextHandler.IsMultipleTextInputsEmpty(registerName , registerNameError,
                            registerLastName ,registerLastNameError ,
                            registerEmail ,registerEmailError,
                            registerPasswd,registerPasswdError,
                            registerConfirmPasswd,registerConfirmPasswdError ,
                            requireActivity());

                    if(!registerNameError.isErrorEnabled() && !registerLastNameError.isErrorEnabled() &&
                        !registerEmailError.isErrorEnabled() && !registerPasswdError.isErrorEnabled() &&
                        !registerConfirmPasswdError.isErrorEnabled() &&
                            !TextHandler.IsTextInputsEqual(registerPasswd,registerConfirmPasswd , registerConfirmPasswdError ,requireActivity())){

                        String name = Objects.requireNonNull(registerName.getText()).toString().trim();
                        String lastName = Objects.requireNonNull(registerLastName.getText()).toString().trim();
                        String email = Objects.requireNonNull(registerEmail.getText()).toString().trim();
                        String passwd = Objects.requireNonNull(registerPasswd.getText()).toString().trim();

                        if (TextHandler.IsEmailValid(email,registerEmailError,requireActivity()) && TextHandler.IsPasswdValid(passwd,errorShower,requireActivity())){

                            /*
                             * TODO: Add all the stuff we need for the register form
                             * */
                            System.out.println("Account created"+name+lastName+email+passwd);

//                            customToast.setOnSuccessMsg("This is the end");
//                            customToast.onSuccess();

                        }
                    }
                });

            }
            else confirmRegistration.setEnabled(false);
        });




        /*
        * TODO: MAKE THE FORM CLEAR WHEN THE BACK BUTTON IS PRESSED
        *       see what we did in FarmWeather old
        * */
        OnBackPressedCallback callback = new OnBackPressedCallback(true ) {
            @Override
            public void handleOnBackPressed() {
                remove();
                ((Starting)requireActivity()).replaceFragment(((Starting)requireActivity()).logInFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
        return view;
    }
}