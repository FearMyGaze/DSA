package com.fearmygaze.dea.model;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;

import com.fearmygaze.dea.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class ViewTextHandler implements TextWatcher {

    private final TextInputLayout textInputLayout;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(s.length() != 0){
            textInputLayout.setError(null);
            textInputLayout.setErrorEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public ViewTextHandler(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
    }

    /*
    * TODO: MAYBE add a version for multiple cells(3,5) so the code be more clear and the if's are smaller
    * */

    /*
    * TODO: Add regex for email and (Maybe)password
    * */

    public static boolean IsTextInputEmpty(TextInputEditText textInputEditText , TextInputLayout textInputLayout , Context context){
        if (Objects.requireNonNull(textInputEditText.getText()).toString().isEmpty()){
            textInputLayout.setError(context.getString(R.string.emptyTextInputEditText));
            textInputLayout.setErrorEnabled(true);
            return true;
        }
        return false;
    }
}
