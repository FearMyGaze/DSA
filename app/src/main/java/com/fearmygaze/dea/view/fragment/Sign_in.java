package com.fearmygaze.dea.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fearmygaze.dea.R;
import com.fearmygaze.dea.view.activity.MainActivity;

public class Sign_in extends Fragment {

    View view;
    TextView forgot_passwd , sign_up;
    Button button_sign_in;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        button_sign_in = view.findViewById(R.id.sign_in);

        forgot_passwd = view.findViewById(R.id.forget_passwd);
        sign_up = view.findViewById(R.id.sign_up);

        sign_up.setOnClickListener(v -> ((MainActivity)requireActivity()).replaceFragment(((MainActivity)requireActivity()).sign_up_fragment));

        button_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                 * TODO: Add the stuff for the login
                 * */

            }
        });


        return view;
    }
}