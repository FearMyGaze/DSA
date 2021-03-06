package com.fearmygaze.dsa.view.fragment;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.fearmygaze.dsa.Interface.IVolleyMessage;
import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.controller.BugController;
import com.fearmygaze.dsa.controller.UserController;
import com.fearmygaze.dsa.custom.SnackBar;
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
    private final User me;

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
        MaterialButton profileLogOut = view.findViewById(R.id.profileLogOut);

        TextView profileDeleteAcc = view.findViewById(R.id.profileDeleteAcc);
        TextView profileBugList = view.findViewById(R.id.profileBugList);

        SharedPreferences getSharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireActivity().getApplicationContext());

        profileName.setText(me.getName());
        profileEmail.setText(me.getEmail());

        /*
         * The moment the TextInputEditText is filled with a text after an error occurred th error
         *   vanishes from the text that was changed
         * */
        profileName.addTextChangedListener(new TextHandler(profileNameError));
        profileEmail.addTextChangedListener(new TextHandler(profileEmailError));

        profileLogOut.setOnClickListener(v -> {
            SharedPreferences.Editor editor = getSharedPrefs.edit().clear();
            editor.apply();
            Toast.makeText(requireActivity(), requireContext().getResources().getString(R.string.profileLogOutToast), Toast.LENGTH_LONG).show();
            requireActivity().finish();
        });

        profileUpdate.setOnClickListener(v -> {
            /*
             * This reads from the TextInputEditText's when the button is pressed
             * */
            String updateName = Objects.requireNonNull(profileName.getText()).toString().trim();
            String updateEmail = Objects.requireNonNull(profileEmail.getText()).toString().trim();

            if (RegEx.IsNameValid(updateName,30, profileNameError, requireActivity()) && RegEx.IsEmailValid(updateEmail,50, profileEmailError, requireActivity())) {
                if (!updateEmail.equals(me.getEmail()) || !updateName.equals(me.getName())) {
                    UserController.UserUpdate(requireActivity(), updateName, me.getName(), updateEmail, me.getEmail(), new IVolleyMessage() {
                        @Override
                        public void onWaring(String message) {
                            SnackBar userNotification = new SnackBar(requireActivity(), v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                            userNotification.setOnWarningMsg(message);
                            userNotification.onWarning();
                        }
                        @Override
                        public void onError(String message) {
                            SnackBar userNotification = new SnackBar(requireActivity(), v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                            userNotification.setOnErrorMsg(message);
                            userNotification.onError();
                        }

                        @Override
                        public void onSuccess(String message) {
                            me.setEmail(updateEmail);
                            me.setName(updateName);

                            profileEmail.setText(me.getEmail());
                            profileName.setText(me.getName());

                            SharedPreferences.Editor editor = getSharedPrefs.edit();
                            editor.putString("userEmail", me.getEmail());
                            editor.putString("userName", me.getName());
                            editor.apply();

                            SnackBar userNotification = new SnackBar(requireActivity(), v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                            userNotification.setOnSuccessMsg(getResources().getString(R.string.successOnUpdate));
                            userNotification.onSuccess();
                        }
                    });
                }else{
                    SnackBar userNotification = new SnackBar(requireActivity(), v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                    userNotification.setOnWarningMsg(requireContext().getResources().getString(R.string.profileNoChanges));
                    userNotification.onWarning();
                }
            }
        });
        profileDeleteAcc.setOnClickListener(v -> UserController.UserDelete(requireActivity(), me.getEmail(), new IVolleyMessage() {
            @Override
            public void onWaring(String message) {
                SnackBar userNotification = new SnackBar(requireActivity(), v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                userNotification.setOnWarningMsg(message);
                userNotification.onWarning();
            }

            @Override
            public void onError(String message) {
                SnackBar userNotification = new SnackBar(requireActivity(), v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
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
        }));

        profileBugList.setOnClickListener(v -> {
            Dialog dialog = new Dialog(v.getContext());
            dialog.setContentView(R.layout.custom_bug_dialog);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            TextInputLayout dialogBugDescError = dialog.findViewById(R.id.dialogBugDescConfirmError);
            TextInputEditText dialogBugDesc = dialog.findViewById(R.id.dialogBugDescConfirm);

            MaterialButton dialogBugConfirm = dialog.findViewById(R.id.dialogBugConfirm);

            /*
             * The moment the TextInputEditText is filled with a text after an error occurred th error
             *   vanishes from the text that was changed
             * */
            dialogBugDesc.addTextChangedListener(new TextHandler(dialogBugDescError));

            dialogBugConfirm.setOnClickListener(v2 -> {
                String desc = Objects.requireNonNull(dialogBugDesc.getText()).toString();
                String userEmail = getSharedPrefs.getString("userEmail","empty");

                if (TextHandler.isSmallerThanSetLength(desc,100,dialogBugDescError, v2.getContext()) && !userEmail.equals("empty")){
                    BugController.BugReport(v.getContext(), userEmail, desc, new IVolleyMessage() {
                        @Override
                        public void onWaring(String message) {
                            SnackBar userNotification = new SnackBar(requireActivity(), v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                            userNotification.setOnWarningMsg(message);
                            userNotification.onWarning();
                        }

                        @Override
                        public void onError(String message) {
                            SnackBar userNotification = new SnackBar(requireActivity(), v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                            userNotification.setOnErrorMsg(message);
                            userNotification.onError();
                        }

                        @Override
                        public void onSuccess(String message) {
                            SnackBar userNotification = new SnackBar(requireActivity(), v, Snackbar.LENGTH_LONG, Snackbar.ANIMATION_MODE_FADE);
                            userNotification.setOnSuccessMsg(message);
                            userNotification.onSuccess();
                            dialog.dismiss();
                        }
                    });
                }
            });
            dialog.show();
        });

        return view;
    }
}