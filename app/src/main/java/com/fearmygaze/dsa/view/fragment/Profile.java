package com.fearmygaze.dsa.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.custom.SnackBar.UserNotification;
import com.fearmygaze.dsa.model.User;
import com.fearmygaze.dsa.view.activity.Main;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class Profile extends Fragment {
    View view;
    private User me;

    public Profile(User user) {
        this.me = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_profile, container, false);

        ConstraintLayout profileRootLayout = view.findViewById(R.id.profileRootLayout);

        TextInputEditText profileName = view.findViewById(R.id.profileName);

        TextInputEditText profileEmail = view.findViewById(R.id.profileEmail);

        MaterialButton profileUpdate = view.findViewById(R.id.profileUpdate);

        TextView profileDeleteAcc = view.findViewById(R.id.profileDeleteAcc);


        profileName.setText(me.getName());
        profileEmail.setText(me.getEmail());

        profileUpdate.setOnClickListener(v -> {

            String updateName = Objects.requireNonNull(profileName.getText()).toString().trim();
            String updateEmail = Objects.requireNonNull(profileEmail.getText()).toString().trim();

            if (!updateName.equals(me.getName())){
                me.setName(updateName);
                profileName.setText(me.getName());
                /*
                 * TODO: ADD Notification for updating the cell
                 * */
                return;
            }

            if (!updateEmail.equals(me.getEmail())){

                me.setEmail(updateEmail);
                profileEmail.setText(me.getEmail());
                /*
                 * TODO: ADD Notification for updating the cell
                 * */
            }
            UserNotification snackbar = new UserNotification(requireActivity(), v, Snackbar.LENGTH_LONG , Snackbar.ANIMATION_MODE_FADE);
            snackbar.setOnWarningMsg("Information updated");
            snackbar.onWarning();
        });


        profileDeleteAcc.setOnClickListener(v -> {
            /*
             * TODO: Delete account
             * */
            ((Main) requireActivity()).snackbarMainNotifications.removeAllViews(); // TODO: CHECK IF WE NEED IT
            UserNotification snackbar = new UserNotification(requireActivity(),v, Snackbar.LENGTH_LONG , Snackbar.ANIMATION_MODE_FADE);
            snackbar.setOnWarningMsg("Welcome  " + me.getName());
            snackbar.onWarning();

        });

        return view;
    }

}