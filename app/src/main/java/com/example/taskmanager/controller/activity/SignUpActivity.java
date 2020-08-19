package com.example.taskmanager.controller.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taskmanager.R;

public class SignUpActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME_FROM_SIGN_UP = "extraUsernameFromSignUp";
    public static final String EXTRA_KEY_PASSWORD_FROM_SIGN_UP = "extraKeyPasswordFromSignUp";
    private Button mButtonSignUp;
    private EditText mEditTextUsername;
    private EditText mEditTextPassword;
    private String mUsername;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.setTitle("Sign Up");
        findViews();
        Intent intent = getIntent();
        mUsername = intent.getStringExtra(LoginActivity.EXTRA_KEY_USERNAME);
        mPassword = intent.getStringExtra(LoginActivity.EXTRA_KEY_PASSWORD);
        mEditTextUsername.setText(mUsername);
        mEditTextPassword.setText(mPassword);
        setListener();
    }

    private void setListener() {
        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditTextUsername.getText().toString().equals("") || mEditTextPassword.getText().toString().equals(""))
                    showToast("Please enter valid username and password");
                else {
                    String username = mEditTextUsername.getText().toString();
                    String password = mEditTextPassword.getText().toString();
                    saveResult(username, password);
                    finish();
                }
            }
        });
    }

    private void findViews() {
        mButtonSignUp = findViewById(R.id.button_sign_in);
        mEditTextUsername = findViewById(R.id.edit_text_username);
        mEditTextPassword = findViewById(R.id.edit_text_password);
    }

    private void saveResult(String username, String password) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_USERNAME_FROM_SIGN_UP, username);
        intent.putExtra(EXTRA_KEY_PASSWORD_FROM_SIGN_UP, password);
        setResult(RESULT_OK, intent);
    }

    private void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }
}