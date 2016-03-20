package com.bellevue.starter.Utils;

import android.os.Environment;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bellevue.starter.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by aguidis on 09/12/2015.
 */
public class Tool {

    public static String root_path = Environment.getExternalStorageDirectory() + "/BelleVue";

    private Tool() {
    }

    public static void reset_bellevue_dir(File bellevue_dir) {
        File dir = bellevue_dir;
        if (dir.isDirectory())
        {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++)
            {
                new File(dir, children[i]).delete();
            }
        }
    }

    /****************************/
    /*  ADD VIEW VERIFICATION   */
    /****************************/

    public static boolean check_content_radio_button(RadioButton m_one, RadioButton m_two,
                                                RadioButton m_three, RadioButton m_four) {
        if (m_one.isChecked() || m_two.isChecked() || m_three.isChecked() || m_four.isChecked()) {
            return true;
        } else {
            return false;
        }
    }

    public static int get_categorie(RadioButton m_one, RadioButton m_two,
                                     RadioButton m_three, RadioButton m_four) {
        if (m_one.isChecked())
            return 1;
        else if (m_two.isChecked())
            return 2;
        else if (m_three.isChecked())
            return 3;
        else if (m_four.isChecked())
            return 4;
        else
            return 0;
    }

    public static boolean check_content_edittext(String name, String description,
                                                 EditText name_input, EditText description_input) {
        View focusView = null;
        boolean cancel = false;

        // NAME
        if (TextUtils.isEmpty(name)) {
            name_input.setError("Ce champ est vide");
            focusView = name_input;
            cancel = true;
        }

        // DESCRIPTION
        if (TextUtils.isEmpty(description)) {
            description_input.setError("Ce champ est vide");
            if (focusView == null)
                focusView = description_input;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}
