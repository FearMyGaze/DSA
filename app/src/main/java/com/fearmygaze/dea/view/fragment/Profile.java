package com.fearmygaze.dea.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.fearmygaze.dea.R;
import com.fearmygaze.dea.custom.MyToast.CustomToast;
import com.fearmygaze.dea.custom.TextHandler;
import com.fearmygaze.dea.model.User;
import com.fearmygaze.dea.view.activity.Main;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class Profile extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_profile, container, false);
        CustomToast customToast = ((Main) requireActivity()).customToast;

        TextInputEditText profileName = view.findViewById(R.id.profileName);

        TextInputEditText profileLastname = view.findViewById(R.id.profileLastname);

        TextInputEditText profileEmail = view.findViewById(R.id.profileEmail);

        TextInputEditText profileSSN = view.findViewById(R.id.profileSSN);
        TextInputLayout profileSSNError = view.findViewById(R.id.profileSSNError);

        TextInputEditText profileLocation = view.findViewById(R.id.profileLocation);
        TextInputLayout profileLocationError = view.findViewById(R.id.profileLocationError);

        Button profileUpdate = view.findViewById(R.id.profileUpdate);

        TextView profileDeleteAcc = view.findViewById(R.id.profileDeleteAcc);


        User user = new User("asd","asd","asd@gmail.com");

        profileName.setText(user.getName());
        profileLastname.setText(user.getLastname());
        profileEmail.setText(user.getEmail());
        profileSSN.setText(user.getSSN());
        profileLocation.setText(user.getLocation());

        /*
         * The moment the TextInputEditText is filled with a text after an error occurred the error
         *   vanishes from the text that was changed
         * */
        profileSSN.addTextChangedListener(new TextHandler(profileSSNError));
        profileLocation.addTextChangedListener(new TextHandler(profileLocationError));

        profileUpdate.setOnClickListener(v -> {

            TextHandler.IsTextInputEmpty(profileSSN , profileSSNError , requireActivity());
            TextHandler.IsTextInputEmpty(profileLocation , profileLocationError , requireActivity());

            if(!profileSSNError.isErrorEnabled() && !profileLocationError.isErrorEnabled()){
                String updatedSSN = Objects.requireNonNull(profileSSN.getText()).toString().trim();
                String updatedLocation = Objects.requireNonNull(profileLocation.getText()).toString().trim();

                /*
                * TODO: Control the update on CustomProfileUpdate
                * */

                user.setLocation(updatedLocation);
                user.setSSN(updatedSSN);
                user.setDevice_id("1");
                user.setUuid("1");

                customToast.setOnSuccessMsg(user.toString());
                customToast.onSuccess();

            }
        });

        profileDeleteAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                * TODO: Delete account
                * */
            }
        });

        return view;
    }
}