package com.fearmygaze.dsa.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.util.TextHandler;
import com.fearmygaze.dsa.view.activity.Main;
import com.fearmygaze.dsa.view.activity.Starting;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;


public class SignUp extends Fragment {
    View view;


    MaterialButton signUpWithEmail;

    TextInputEditText signUpUserName, signUpUserEmail, signUpUserPassword;
    TextInputLayout signUpUserNameError, signUpUserEmailError, signUpUserPasswordError;

    TextView signUpGotoSignIn;

    CheckBox signUpCheckTOS;

    FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        signUpUserName = view.findViewById(R.id.signUpUserName);
        signUpUserNameError = view.findViewById(R.id.signUpUserNameError);

        signUpUserEmail = view.findViewById(R.id.signUpUserEmail);
        signUpUserEmailError = view.findViewById(R.id.signUpUserEmailError);

        signUpUserPassword = view.findViewById(R.id.signUpUserPassword);
        signUpUserPasswordError = view.findViewById(R.id.signUpUserPasswordError);

        signUpWithEmail = view.findViewById(R.id.signUpContinueWithEmail);

        signUpGotoSignIn = view.findViewById(R.id.signUpGotoSignIn);

        signUpCheckTOS = view.findViewById(R.id.signUpCheck);


        /*
         * The moment the TextInputEditText is filled with a text after an error occurred the error
         *   vanishes from the text that was changed
         * */
        signUpUserName.addTextChangedListener(new TextHandler(signUpUserNameError));
        signUpUserEmail.addTextChangedListener(new TextHandler(signUpUserEmailError));
        signUpUserPassword.addTextChangedListener(new TextHandler(signUpUserPasswordError));

        signUpCheckTOS.setOnCheckedChangeListener((buttonView, isChecked) -> {
           if (isChecked){
               signUpWithEmail.setEnabled(true);
               signUpWithEmail.setOnClickListener(v -> {
                   TextHandler.isTextInputEmpty(signUpUserName,signUpUserNameError,requireActivity());
                   TextHandler.isTextInputEmpty(signUpUserEmail,signUpUserEmailError,requireActivity());
                   TextHandler.isTextInputEmpty(signUpUserPassword,signUpUserPasswordError,requireActivity());

                   if (!signUpUserNameError.isErrorEnabled() || !signUpUserEmailError.isErrorEnabled() || !signUpUserPasswordError.isErrorEnabled())
                       userRegister();
               });
           }else {
               signUpWithEmail.setEnabled(false);
           }

        });

        signUpGotoSignIn.setOnClickListener(v -> ((Starting) requireActivity()).replaceFragment(((Starting) requireActivity()).signIn));

        return view;
    }

    private void userRegister() {
        String name = Objects.requireNonNull(signUpUserName.getText()).toString();
        String email = Objects.requireNonNull(signUpUserEmail.getText()).toString();
        String password = Objects.requireNonNull(signUpUserPassword.getText()).toString();

        /*
        * TODO: Add regEx Password and check the name of the user for inappropriate name
        * */

        firebaseAuth
                .createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(authResult -> {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if (user != null){
                        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .build();
                        user.updateProfile(profileChangeRequest);

                        startActivity(new Intent(requireActivity(), Main.class));
                        requireActivity().finish();
                        Toast.makeText(requireActivity(), getResources().getText(R.string.createUserSuccess), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(requireActivity(), getResources().getText(R.string.createUserError), Toast.LENGTH_LONG).show());

    }
}