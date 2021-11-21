package com.fearmygaze.dsa.model;

public interface IVolleyMessage {
    void onWaring(String message);
    void onError(String message);
    void onSuccess(String message);
}
