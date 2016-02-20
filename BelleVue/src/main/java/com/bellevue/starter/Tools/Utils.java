package com.bellevue.starter.Tools;

import android.os.Environment;

import java.io.File;


/**
 * Created by aguidis on 19/02/2016.
 */
public class Utils {

    private static File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/BelleVue");

    private Utils() {
    }
    
}
