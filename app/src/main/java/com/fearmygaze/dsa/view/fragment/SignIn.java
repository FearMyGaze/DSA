package com.fearmygaze.dsa.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.util.TextHandler;
import com.fearmygaze.dsa.view.activity.Main;
import com.fearmygaze.dsa.view.activity.Starting;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.OAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;


public class SignIn extends Fragment {
    View view;

    MaterialButton signInWithEmail, signInWithGithub;

    TextInputEditText signInUserEmail, signInUserPassword;
    TextInputLayout signInUserEmailError, signInUserPasswordError;

    TextView signInGotoSignUp;

    FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        signInUserEmail = view.findViewById(R.id.signInUserEmail);
        signInUserEmailError = view.findViewById(R.id.signInUserEmailError);

        signInUserPassword = view.findViewById(R.id.signInUserPassword);
        signInUserPasswordError = view.findViewById(R.id.signInUserPasswordError);

        signInGotoSignUp = view.findViewById(R.id.signInGotoSignUp);

        signInWithEmail = view.findViewById(R.id.signInWithEmail);
        signInWithGithub = view.findViewById(R.id.signInWithGithub);

        /*
         * The moment the TextInputEditText is filled with a text after an error occurred the error
         *   vanishes from the text that was changed
         * */
        signInUserEmail.addTextChangedListener(new TextHandler(signInUserEmailError));
        signInUserPassword.addTextChangedListener(new TextHandler(signInUserPasswordError));

        signInWithGithub.setOnClickListener(v -> gitLogin());
        signInWithEmail.setOnClickListener(v -> {
            TextHandler.isTextInputEmpty(signInUserEmail,signInUserEmailError,requireActivity());
            TextHandler.isTextInputEmpty(signInUserPassword,signInUserPasswordError,requireActivity());
            if (!signInUserEmailError.isErrorEnabled() || !signInUserPasswordError.isErrorEnabled())
                loginUser();
        });

        signInGotoSignUp.setOnClickListener(v -> ((Starting) requireActivity()).replaceFragment(((Starting) requireActivity()).signUp));

        return view;
    }

    private void loginUser() {
        String email = Objects.requireNonNull(signInUserEmail.getText()).toString();
        String password = Objects.requireNonNull(signInUserPassword.getText()).toString();

        firebaseAuth
                .signInWithEmailAndPassword(email,password)
                .addOnFailureListener(e -> Toast.makeText(requireActivity(), getResources().getText(R.string.loginUserError) +" "+ e.getMessage(), Toast.LENGTH_LONG).show())
                .addOnSuccessListener(authResult -> {
                    Toast.makeText(requireActivity(), getResources().getText(R.string.loginUserSuccess), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(requireActivity(), Main.class));
                    requireActivity().finish();
                });

    }

    private void gitLogin() {
        OAuthProvider provider = OAuthProvider.newBuilder("github.com").build();
        Task<AuthResult> resultTask = firebaseAuth.getPendingAuthResult();
        if (resultTask != null){
            resultTask
                    .addOnSuccessListener(authResult -> Toast.makeText(requireActivity(), getResources().getText(R.string.gitLoginUserExist), Toast.LENGTH_LONG).show())
                    .addOnFailureListener(e -> Toast.makeText(requireActivity(), getResources().getText(R.string.gitLoginError), Toast.LENGTH_LONG).show());
        }else{
            firebaseAuth
                    .startActivityForSignInWithProvider(requireActivity(),provider)
                    .addOnFailureListener(e -> Toast.makeText(requireActivity(), getResources().getText(R.string.gitLoginError)+" "+e.getMessage(), Toast.LENGTH_LONG).show())
                    .addOnSuccessListener(authResult -> {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        if (user != null){
                            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(Objects.requireNonNull(authResult.getUser()).getDisplayName())
                                    .build();
                            user.updateProfile(profileChangeRequest);

                            startActivity(new Intent(requireActivity(), Main.class));
                            requireActivity().finish();
                            Toast.makeText(requireActivity(), getResources().getText(R.string.gitLoginSuccess), Toast.LENGTH_SHORT).show();
                        }
                    });
        }


    }
}