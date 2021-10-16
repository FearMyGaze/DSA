package com.fearmygaze.dea.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fearmygaze.dea.R;
import com.fearmygaze.dea.model.ViewTextHandler;
import com.fearmygaze.dea.view.activity.MainActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class Sign_in extends Fragment {

    View view;

    TextView sign_up;
    Button button_sign_in;
    TextInputLayout login_email_view_error , login_passwd_view_error;
    TextInputEditText login_email_view , login_passwd_view;

    String login_email , login_passwd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        login_email_view = view.findViewById(R.id.login_email);
        login_email_view_error = view.findViewById(R.id.login_email_error);

        login_passwd_view = view.findViewById(R.id.login_passwd);
        login_passwd_view_error = view.findViewById(R.id.register_passwd_error);

        button_sign_in = view.findViewById(R.id.sign_in);

        sign_up = view.findViewById(R.id.sign_up);

        sign_up.setOnClickListener(v -> ((MainActivity)requireActivity()).replaceFragment(((MainActivity)requireActivity()).sign_up_fragment));

        button_sign_in.setOnClickListener(v -> {

            /*
             * TODO: Add the stuff for the login
             * */
            login_email = Objects.requireNonNull(login_email_view.getText()).toString().trim();
            login_passwd = Objects.requireNonNull(login_passwd_view.getText()).toString().trim();

            /*
            * Checks if is the TextViews are empty
            * */
            ViewTextHandler.checkIfTextViewIsEmptyThenAddError(login_email_view,login_email_view_error,requireActivity());
            ViewTextHandler.checkIfTextViewIsEmptyThenAddError(login_passwd_view,login_passwd_view_error,requireActivity());

            /*
             * TODO: Create a new class file that will handle all the errors that will occur during user creation
             * */
        });


        return view;
    }
}