package com.fearmygaze.dsa.model;

import java.util.List;

public interface IFile {
    void getFileList(List<File> fileList);
    void onError(String message);

}
