package com.fearmygaze.dea.view.fragment;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.fearmygaze.dea.R;
import com.fearmygaze.dea.model.ViewTextHandler;
import com.fearmygaze.dea.view.activity.MainActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.UUID;


public class Sign_up extends Fragment {

    View view;
    TextInputEditText register_name_view , register_lastname_view , register_email_view , register_passwd_view;
    TextInputLayout register_name_view_error , register_lastname_view_error , register_email_view_error , register_passwd_view_error;
    Button sign_up;
    String uuid , register_name , register_lastname ,register_email , register_passwd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        register_name_view = view.findViewById(R.id.register_name);
        register_name_view_error = view.findViewById(R.id.register_name_error);

        register_email_view = view.findViewById(R.id.register_email);
        register_email_view_error = view.findViewById(R.id.register_email_error);

        register_passwd_view = view.findViewById(R.id.register_passwd);
        register_passwd_view_error = view.findViewById(R.id.register_passwd_error);

        register_lastname_view = view.findViewById(R.id.register_lastname);
        register_lastname_view_error = view.findViewById(R.id.register_lastname_error);


        sign_up = view.findViewById(R.id.sign_up);



        /*
        * The moment the TextInputEditText is filled with a text after an error occurred th error
        *   vanishes from the text that was changed
        * */
        register_name_view.addTextChangedListener(new ViewTextHandler(register_name_view_error));
        register_lastname_view.addTextChangedListener(new ViewTextHandler(register_lastname_view_error));
        register_email_view.addTextChangedListener(new ViewTextHandler(register_email_view_error));
        register_passwd_view.addTextChangedListener(new ViewTextHandler(register_passwd_view_error));

        sign_up.setOnClickListener(v -> {

            /*
             * TODO: Add all the stuff we need for the register form
             * */

            /*
            * Getting the text
            * */

            register_name = Objects.requireNonNull(register_name_view.getText()).toString().trim();
            register_lastname = Objects.requireNonNull(register_lastname_view.getText()).toString().trim();
            register_email = Objects.requireNonNull(register_email_view.getText()).toString().trim();
            register_passwd = Objects.requireNonNull(register_passwd_view.getText()).toString().trim();

            /*
             * Checks if is the TextViews are empty
             * */
            ViewTextHandler.checkIfTextViewIsEmptyThenAddError(register_name_view,register_name_view_error,requireActivity());
            ViewTextHandler.checkIfTextViewIsEmptyThenAddError(register_lastname_view,register_lastname_view_error,requireActivity());
            ViewTextHandler.checkIfTextViewIsEmptyThenAddError(register_email_view,register_email_view_error,requireActivity());
            ViewTextHandler.checkIfTextViewIsEmptyThenAddError(register_passwd_view,register_passwd_view_error,requireActivity());

            /*
            * TODO: Create a new class file that will handle all the errors that will occur during user creation
            * */
            uuid = UUID.randomUUID().toString();

            Toast.makeText(requireActivity(), register_name+register_lastname+register_email+register_passwd+uuid, Toast.LENGTH_SHORT).show();
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true ) {
            @Override
            public void handleOnBackPressed() {
                ((MainActivity)requireActivity()).replaceFragment(((MainActivity)requireActivity()).sign_in_fragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
        return view;
    }
}