package com.erkuai.commonarchitecture.constants;

import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2019/8/9.
 */

public class StringConstants {

    public static final String HOST = "https://api.apiopen.top/";

    //请求成功
    public static final int STATUS_SUCCESS = 200;

    //其他错误
    public static final int STATUS_XXX = -1;

    //网络超时时间
    public static final long NETWORK_TIMEOUT = 20;

    public static final String JIANGJIANGJIANG = "JIANGJIANGJIANG";

    public static final String LOTTERY = "lottery.txt";

    public static final String PERSON_EXCEL = "person.xls";

    public static final String PERSON_EXCEL_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() +
            File.separator + JIANGJIANGJIANG;

}
