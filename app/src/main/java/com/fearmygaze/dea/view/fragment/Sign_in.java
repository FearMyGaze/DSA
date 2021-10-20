package com.fearmygaze.dea.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.fearmygaze.dea.R;
import com.fearmygaze.dea.model.ViewTextHandler;
import com.fearmygaze.dea.view.activity.StartingActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class Sign_in extends Fragment {

    /*
    * TODO: After finishing the code part of the class start refactoring the names
    * */
    View view;

    TextView sign_up;
    Button button_sign_in;
    CheckBox remember_me;
    TextInputLayout login_email_view_error , login_passwd_view_error;
    TextInputEditText login_email_view , login_passwd_view;

    String login_email , login_passwd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        login_email_view = view.findViewById(R.id.login_email);
        login_email_view_error = view.findViewById(R.id.login_email_error);

        login_passwd_view = view.findViewById(R.id.login_passwd);
        login_passwd_view_error = view.findViewById(R.id.login_passwd_error);

        remember_me = view.findViewById(R.id.login_remember_me);

        button_sign_in = view.findViewById(R.id.sign_in);

        sign_up = view.findViewById(R.id.sign_up);

        sign_up.setOnClickListener(v -> ((StartingActivity)requireActivity()).replaceFragment(((StartingActivity)requireActivity()).sign_up_fragment));

        /*
         * The moment the TextInputEditText is filled with a text after an error occurred th error
         *   vanishes from the text that was changed
         * */

        login_email_view.addTextChangedListener(new ViewTextHandler(login_email_view_error));
        login_passwd_view.addTextChangedListener(new ViewTextHandler(login_passwd_view_error));

        button_sign_in.setOnClickListener(v -> {

            ViewTextHandler.IsTextInputEmpty(login_email_view,login_email_view_error,requireActivity());
            ViewTextHandler.IsTextInputEmpty(login_passwd_view,login_passwd_view_error,requireActivity());

            if (!login_email_view_error.isErrorEnabled() && !login_passwd_view_error.isErrorEnabled()){
                login_email = Objects.requireNonNull(login_email_view.getText()).toString().trim();
                login_passwd = Objects.requireNonNull(login_passwd_view.getText()).toString().trim();

                if (remember_me.isChecked()){
                    /*
                    * TODO: Add the remember me function here
                    * */
                    Toast.makeText(requireActivity(), login_email+login_passwd+"remember_me", Toast.LENGTH_SHORT).show();
                }

                /*
                 * TODO: Add the stuff to complete the login form
                 * */



                Toast.makeText(requireActivity(), login_email+login_passwd, Toast.LENGTH_SHORT).show();
            }


        });


        return view;
    }
}