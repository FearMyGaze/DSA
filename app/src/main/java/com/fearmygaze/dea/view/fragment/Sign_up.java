package com.fearmygaze.dea.view.fragment;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fearmygaze.dea.R;
import com.fearmygaze.dea.view.activity.MainActivity;


public class Sign_up extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.fragment_sign_up, container, false);

        /*
         * TODO: Add all the stuff we need for the register form
         * */


        OnBackPressedCallback callback = new OnBackPressedCallback(true ) {
            @Override
            public void handleOnBackPressed() {
                remove();
                ((MainActivity)requireActivity()).replaceFragment(((MainActivity)requireActivity()).sign_in_fragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);


        return view;
    }
}