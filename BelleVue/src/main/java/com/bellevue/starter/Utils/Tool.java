package com.bellevue.starter.Utils;

import android.os.Environment;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bellevue.starter.R;

import java.io.File;

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

    public static boolean check_content_edittext(String name, String description, View focusView) {
        /*
        boolean cancel = false;
        // USER
        if (TextUtils.isEmpty(user)) {
            signup_user.setError("PUTAIN C VIDE");
            focusView = signup_user;
            cancel = true;
        } else if (isUsernameInvalid(user)) {
            signup_user.setError("RETIRE LES ESPACES PTIN");
            focusView = signup_user;
            cancel = true;
        }

        // PASSWORD
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            signup_password.setError(getResources().getQuantityString(R.plurals.com_parse_ui_password_too_short_toast, 4, 4));
            if (focusView == null)
                focusView = signup_password;
            cancel = true;
        } else if (password.compareTo(password1) != 0) {
            signup_password.setError("Password not equal please try again");
            if (focusView == null)
                focusView = signup_password;
            cancel = true;
            signup_password.setText(null);
            signup_confirm_password.setText(null);
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
            return false;
        } else {
            return true;
        }*/ return false;
    }
}
