/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.bellevue.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by guidis on ?.
 */

public class SignInUp extends AppCompatActivity {

    private ViewGroup mContainerView;
    private ViewGroup newView;

    private Button parseLoginButton;
    private Button parseSignupButton;
    private Button parseSignupConfirmButton;
    private Button parseForgotButton;

    private EditText login_username;                // login_username_input
    private EditText login_password;                // login_password_input

    private EditText signup_user;                   // signup_user_input
    private EditText signup_password;               // signup_password_input
    private EditText signup_confirm_password;       // signup_confirm_password_input
    private EditText signup_email;                  // signup_email_input
    private EditText signup_name;                   // signup_email_input

    private View     coordinatorLayoutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        mContainerView = (ViewGroup) findViewById(R.id.scene_container);

        parseLoginButton = (Button) findViewById(R.id.parse_login_button);
        parseSignupButton = (Button) findViewById(R.id.parse_signup_button);
        parseForgotButton = (Button) findViewById(R.id.parse_login_help);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        signin_user();

        signup_user();

        parseForgotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Bravo poisson rouge bien ouej", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void signin_user() {
        parseLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_username = (EditText) findViewById(R.id.login_username_input);
                login_password = (EditText) findViewById(R.id.login_password_input);

                ParseUser.logInInBackground(login_username.getText().toString(), login_password.getText().toString(), new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            Toast.makeText(getBaseContext(), "Signin successful", Toast.LENGTH_LONG).show();
                            Intent bellevue = new Intent(getApplicationContext(), BelleVue.class);
                            bellevue.putExtra("user_name", user.get("name").toString());
                            startActivity(bellevue);
                            finish();
                        } else {
                            // Signup failed. Look at the ParseException to see what happened.
                            Toast.makeText(getBaseContext(), "Error : " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    private void signup_user() {
        parseSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show views from signin to signup
                changeComponent(false);

                signup_user             = ((EditText) mContainerView.getChildAt(2));
                signup_password         = ((EditText) mContainerView.getChildAt(3));
                signup_confirm_password = (EditText) findViewById(R.id.signup_confirm_password_input);
                signup_email            = (EditText) findViewById(R.id.signup_email_input);
                signup_name             = (EditText) findViewById(R.id.signup_name_input);

                signup_user.setError(null);
                signup_password.setError(null);
                signup_email.setError(null);

                parseSignupConfirmButton = (Button) findViewById(R.id.create_account);
                parseSignupConfirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isSignUp;
                        boolean cancel = false;
                        View focusView = null;

                        String user      = signup_user.getText().toString();
                        String password  = signup_password.getText().toString();
                        String password1 = signup_confirm_password.getText().toString();
                        String email     = signup_email.getText().toString();
                        String name      = signup_name.getText().toString();

                        isSignUp = formValidation(user, password, password1,
                                                    email, name, cancel, focusView);

                        if (isSignUp) signUpProcess(user, password, email, name);

                        /*Toast.makeText(getBaseContext(), "Sign IN with " + user
                                + " and " + password
                                + " and " + email
                                + " and " + name, Toast.LENGTH_LONG).show();*/
                    }
                });
            }
        });
    }

    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    @Override
    public void onBackPressed() {
        // Hide views from signup to signin
        changeComponent(true);
    }

    private void signUpProcess(final String user, final String password, final String email, final String name) {

        /* SIGN UP PARSE */
        ParseUser user_account = new ParseUser();
        user_account.setUsername(user);
        user_account.setPassword(password);
        user_account.setEmail(email);
        user_account.put("name", name);

        user_account.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    Toast.makeText(getBaseContext(), "Bienvenue " + user, Toast.LENGTH_LONG).show();
                    Intent bellevue = new Intent(getApplicationContext(), BelleVue.class);
                    bellevue.putExtra("user_name", user);
                    startActivity(bellevue);
                    finish();

                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Toast.makeText(getBaseContext(), "Error : " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean formValidation(String user, String password,
                                   String password1, String email, String name,
                                   boolean cancel, View focusView) {
        /****************
         * VERIFICATION *
         ****************/
        // http://stackoverflow.com/questions/11776829/android-form-validation-ui-library
        // android-edittext-validator

        // USER
        if (TextUtils.isEmpty(user)) {
            signup_user.setError("Ce champ est vide");
            focusView = signup_user;
            cancel = true;
        } else if (isUsernameInvalid(user)) {
            signup_user.setError("Ce champ comporte des espaces");
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

        // EMAIL
        if (TextUtils.isEmpty(email)) {
            signup_email.setError("Ce champ est vide");
            if (focusView == null)
                focusView = signup_email;
            cancel = true;
        } else if (!isEmailValid(email)) {
            signup_email.setError("L'email n'est pas valide");
            if (focusView == null)
                focusView = signup_email;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean isUsernameInvalid(String username) {
        //TODO: Replace this with your own logic
        return username.contains(" ");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private void changeComponent(boolean action) {
        // #TRUE HIDE #FALSE SHOW
        // http://developer.android.com/training/animation/layout.html CODE SAMPLE

        if(action) {
            ((ViewGroup) newView.getParent()).removeView(newView);

            parseLoginButton.setVisibility(View.VISIBLE);
            parseSignupButton.setVisibility(View.VISIBLE);
            parseForgotButton.setVisibility(View.VISIBLE);
        } else {
            parseLoginButton.setVisibility(View.GONE);
            parseSignupButton.setVisibility(View.GONE);
            parseForgotButton.setVisibility(View.GONE);

            newView = (ViewGroup) LayoutInflater.from(SignInUp.this).inflate(
                    R.layout.signup_content_form, mContainerView, false);

            // Because mContainerView has android:animateLayoutChanges set to true,
            // adding this view is automatically animated.
            mContainerView.addView(newView, 0);
        }
    }
}
