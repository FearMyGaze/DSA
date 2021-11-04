package com.fearmygaze.dea.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.fearmygaze.dea.R;
import com.fearmygaze.dea.custom.MyToast.CustomToast;
import com.fearmygaze.dea.custom.UpdateProfile.CustomProfileUpdate;
import com.fearmygaze.dea.view.activity.Main;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;
import java.util.Objects;

public class Profile extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_profile, container, false);
        CustomToast customToast = ((Main) requireActivity()).customToast;

        TextInputEditText profileName = view.findViewById(R.id.profileName);
        TextInputLayout profileNameError = view.findViewById(R.id.profileNameError);

        TextInputEditText profileLastname = view.findViewById(R.id.profileLastname);
        TextInputLayout profileLastnameError = view.findViewById(R.id.profileLastnameError);

        TextInputEditText profileBirthday = view.findViewById(R.id.profileBirthday);
        TextInputLayout profileBirthdayError = view.findViewById(R.id.profileBirthdayError);

        TextInputEditText profileEmail = view.findViewById(R.id.profileEmail);
        TextInputLayout profileEmailError = view.findViewById(R.id.profileEmailError);

        TextInputEditText profileSSN = view.findViewById(R.id.profileSSN);
        TextInputLayout profileSSNError = view.findViewById(R.id.profileSSNError);

        TextInputEditText profileLocation = view.findViewById(R.id.profileLocation);
        TextInputLayout profileLocationError = view.findViewById(R.id.profileLocationError);

        Button profileUpdate = view.findViewById(R.id.profileUpdate);


        /*
        * TODO: The button will be enabled when the cell's are filled and added with new value
        * */


        /*
         * The moment the TextInputEditText is filled with a text after an error occurred the error
         *   vanishes from the text that was changed
         * */
        profileName.addTextChangedListener(new CustomProfileUpdate(profileNameError));
        profileLastname.addTextChangedListener(new CustomProfileUpdate(profileLastnameError));
        profileBirthday.addTextChangedListener(new CustomProfileUpdate(profileBirthdayError));
        profileEmail.addTextChangedListener(new CustomProfileUpdate(profileEmailError));
        profileSSN.addTextChangedListener(new CustomProfileUpdate(profileSSNError));
        profileLocation.addTextChangedListener(new CustomProfileUpdate(profileLocationError));

        profileUpdate.setEnabled(true); //TODO: Remove
        profileUpdate.setOnClickListener(v -> {

            CustomProfileUpdate.IsMultipleTextInputsEmpty(
                    profileName , profileNameError,
                    profileLastname , profileLastnameError,
                    profileBirthday , profileBirthdayError,
                    profileEmail , profileEmailError,
                    profileSSN , profileSSNError,
                    profileLocation , profileLocationError,
                    requireActivity());

            if(!profileNameError.isErrorEnabled() && !profileLastnameError.isErrorEnabled() &&
                    !profileBirthdayError.isErrorEnabled() && !profileEmailError.isErrorEnabled() &&
                    !profileSSNError.isErrorEnabled() && !profileLocationError.isErrorEnabled()){


                String updatedName = Objects.requireNonNull(profileName.getText()).toString().trim();
                String updatedLastname = Objects.requireNonNull(profileLastname.getText()).toString().trim();
                Date updatedBirthday = (Date) profileBirthday.getText();
                String updatedEmail = Objects.requireNonNull(profileEmail.getText()).toString().trim();
                String updatedSSN = Objects.requireNonNull(profileSSN.getText()).toString().trim();
                String updatedLocation = Objects.requireNonNull(profileLocation.getText()).toString().trim();

                customToast.setOnSuccessMsg(updatedName+" "+updatedLastname+" "+updatedBirthday+" "+updatedEmail+" "+updatedSSN+" "+updatedLocation);
                customToast.onSuccess();

            }
        });

        return view;
    }
}