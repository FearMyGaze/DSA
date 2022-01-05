package com.fearmygaze.dsa.view.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.controller.UserController;
import com.fearmygaze.dsa.custom.UserNotification;
import com.fearmygaze.dsa.model.IVolleyMessage;
import com.fearmygaze.dsa.model.User;
import com.fearmygaze.dsa.util.RegEx;
import com.fearmygaze.dsa.util.TextHandler;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class Profile extends Fragment {
    View view;
    private User me;

    public Profile(User user) {
        this.me = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextInputEditText profileName = view.findViewById(R.id.profileName);
        TextInputLayout profileNameError = view.findViewById(R.id.profileNameError);

        TextInputEditText profileEmail = view.findViewById(R.id.profileEmail);
        TextInputLayout profileEmailError = view.findViewById(R.id.profileEmailError);

        MaterialButton profileUpdate = view.findViewById(R.id.profileUpdate);

        TextView profileDeleteAcc = view.findViewById(R.id.profileDeleteAcc);

        SharedPreferences getSharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireActivity().getApplicationContext());

        profileName.setText(me.getName());
        profileEmail.setText(me.getEmail());

        profileName.addTextChangedListener(new TextHandler(profileNameError));
        profileEmail.addTextChangedListener(new TextHandler(profileEmailError));

        profileUpdate.setOnClickListener(v -> {
            /*
             * This reads from the TextInputEditText's when the button is pressed
             * */
            String updateName = Objects.requireNonNull(profileName.getText()).toString().trim();
            String updateEmail = Objects.requireNonNull(profileEmail.getText()).toString().trim();

            if (RegEx.IsNameValid(updateName, profileNameError, requireActivity()) && RegEx.IsEmailValid(updateEmail, profileEmailError, requireActivity())) {
                if (!updateEmail.equals(me.getEmail()) || !updateName.equals(me.getName())) {
                    UserController.UserUpdate(requireActivity(), updateName, me.getName(), updateEmail, new IVolleyMessage() {
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
                            me.setEmail(updateEmail);
                            me.setName(updateName);

                            /*
                            * TODO: MAYBE we need to add and oldEmail Because now we are referencing the oldName
                            *       but what happens if we have the same oldName in the DB more than once
                            * */

                            profileEmail.setText(me.getEmail());
                            profileName.setText(me.getName());

                            SharedPreferences.Editor editor = getSharedPrefs.edit();
                            editor.putString("userEmail", me.getEmail());
                            editor.putString("userName", me.getName());
                            editor.apply();

                            UserNotification userNotification = new UserNotification(requireActivity(), v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                            userNotification.setOnSuccessMsg(getResources().getString(R.string.successOnUpdate));
                            userNotification.onSuccess();
                        }
                    });
                }

            }
        });
        profileDeleteAcc.setOnClickListener(v -> {

            UserController.UserDelete(requireActivity(), me.getEmail(), new IVolleyMessage() {
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
                    SharedPreferences.Editor editor = getSharedPrefs.edit().clear();
                    editor.apply();
                    Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show();
                    requireActivity().finish();
                }
            });
        });
        return view;
    }
}