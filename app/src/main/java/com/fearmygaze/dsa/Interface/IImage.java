package com.fearmygaze.dsa.Interface;

import com.fearmygaze.dsa.model.Exam;

import java.util.List;

public interface IImage {
    void getFileList(List<Exam> fileList);
    void onError(String message);

}
