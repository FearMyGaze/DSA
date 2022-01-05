package com.fearmygaze.dsa.controller;

import android.content.Context;

import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.model.IVolleyMessage;

public class FileController {

    /**
     * @param context We need it to get the String from resource file strings.xml
     * @param user We need the user email so the db knows in what user will be assigned
     * @param title We need the title so the user or the doctor will know what after some time what was for
     * @param desc We need the description so the user or the doctor will know the description of the upload was for
     * @param file We need the file in String format (after is encoded) so we can upload a file
     * @param iVolleyMessage a quick interface to handle the Success/Warning/Error
     */
    public static void fileUpload (Context context, String user, String title, String desc, String file, IVolleyMessage iVolleyMessage){
        String[] url = context.getResources().getStringArray(R.array.url);
        String jsonError = context.getResources().getString(R.string.jsonErrorDuring);
        String errorOnUpload = context.getResources().getString(R.string.errorOnRegister);
        String volleyError = context.getResources().getString(R.string.volleyError);





    }

    public static void fileDownload (Context context ){
        String[] url = context.getResources().getStringArray(R.array.url);
        String jsonError = context.getResources().getString(R.string.jsonErrorDuring);
        String errorOnUpload = context.getResources().getString(R.string.errorOnRegister);
        String volleyError = context.getResources().getString(R.string.volleyError);

    }

    public static void fileDelete (Context context){
        String[] url = context.getResources().getStringArray(R.array.url);
        String jsonError = context.getResources().getString(R.string.jsonErrorDuring);
        String errorOnUpload = context.getResources().getString(R.string.errorOnRegister);
        String volleyError = context.getResources().getString(R.string.volleyError);

    }

}
