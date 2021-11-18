package com.fearmygaze.dsa.view.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.custom.RegEx;
import com.fearmygaze.dsa.custom.TextHandler;
import com.fearmygaze.dsa.model.User;
import com.fearmygaze.dsa.view.activity.Main;
import com.fearmygaze.dsa.view.activity.Starting;
import com.google.android.material.button.MaterialButton;
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

        gotoRegister.setOnClickListener(v -> ((Starting)requireActivity()).replaceFragment(((Starting)requireActivity()).registerFragment));

        /*
         * The moment the TextInputEditText is filled with a text after an error occurred th error
         *   vanishes from the text that was changed
         * */

        loginEmail.addTextChangedListener(new TextHandler(loginEmailError));
        loginPasswd.addTextChangedListener(new TextHandler(loginPasswdError));

        confirmLogIn.setOnClickListener(v -> {

            TextHandler.IsTextInputEmpty(loginEmail, loginEmailError, SignIn.this.requireActivity());
            TextHandler.IsTextInputEmpty(loginPasswd, loginPasswdError, SignIn.this.requireActivity());

            if (!loginEmailError.isErrorEnabled() && !loginPasswdError.isErrorEnabled()) {
                String email = Objects.requireNonNull(loginEmail.getText()).toString().trim();
                String passwd = Objects.requireNonNull(loginPasswd.getText()).toString().trim();

                if (RegEx.IsEmailValid(email, loginEmailError, SignIn.this.requireActivity()) && RegEx.IsPasswdValid(passwd, loginPasswdError, SignIn.this.requireActivity())) {

                    SharedPreferences.Editor editor = SignIn.this.requireActivity().getPreferences(MODE_PRIVATE).edit();
                    editor.putString("userEmail", email);
                    editor.putString("userPasswd", passwd);
                    editor.apply();

                    User me = new User("asd", loginEmail.getText().toString().trim());

                    /*
                     * TODO: Add the stuff to complete the login form
                     * */

                    Intent intent = new Intent(SignIn.this.requireActivity(), Main.class);
                    intent.putExtra("User", me);
                    SignIn.this.startActivity(intent);


                }
            }
        });

        return view;
    }
}