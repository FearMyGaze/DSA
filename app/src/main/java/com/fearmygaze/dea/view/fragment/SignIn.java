package com.fearmygaze.dea.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.fearmygaze.dea.R;
import com.fearmygaze.dea.model.TextHandler;
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


        TextInputEditText loginEmail = view.findViewById(R.id.loginEmail);
        TextInputLayout loginEmailError = view.findViewById(R.id.loginEmailError);

        TextInputEditText loginPasswd = view.findViewById(R.id.loginPasswd);
        TextInputLayout loginPasswdError = view.findViewById(R.id.loginPasswdError);

        TextView gotoRegister = view.findViewById(R.id.gotoRegister);

        CheckBox rememberMe = view.findViewById(R.id.login_remember_me);

        Button confirmLogIn = view.findViewById(R.id.confirmLogIn);

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

                if (rememberMe.isChecked()){
                    /*
                    * TODO: Add the remember me function here
                    * */

                    /*
                    * TODO: Remove it
                    * */
                    Intent intent = new Intent(requireActivity(), Main.class);
                    startActivity(intent);


                    Toast.makeText(requireActivity(), email+" "+passwd+" "+"rememberMe", Toast.LENGTH_SHORT).show();
                }

                /*
                 * TODO: Add the stuff to complete the login form
                 * */



                Toast.makeText(requireActivity(), email+" "+passwd+" ", Toast.LENGTH_SHORT).show();
            }


        });


        return view;
    }
}