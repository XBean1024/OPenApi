package com.binny.openapi.constant;

import android.os.Environment;

import java.io.File;

/**
 * Created by binny on 2018/6/8.
 */

public class ConstantParams {
    public static final String PICTURE_FILE_NAME = "美女";
    public static final String PATH_PICTURE = Environment.getExternalStorageDirectory()
            .getAbsoluteFile() + File.separator + PICTURE_FILE_NAME + File.separator;

}
