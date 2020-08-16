package com.example.taskmanager.controller.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taskmanager.R;

public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_KEY_USERNAME = "com.example.myloginproject.controller.extraKeyUsername";
    public static final int REQUEST_CODE_SIGN_UP_ACTIVITY = 100;
    public static final String EXTRA_KEY_PASSWORD = "com.example.myloginproject.controller.extraKeyPassword";
    private EditText mEditTextUsername;
    private EditText mEditTextPassword;
    private Button mButtonLogIn;
    private Button mButtonSignIn;
    private String mUserName;
    private String mPassWord;
    private String mUserNameClient="";
    private String mPasswordClient="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("Login");
        findAllView();
        setListeners();
    }

    private void findAllView() {
        mEditTextUsername = findViewById(R.id.edit_text_username);
        mEditTextPassword = findViewById(R.id.edit_text_password);
        mButtonLogIn = findViewById(R.id.button_login);
        mButtonSignIn = findViewById(R.id.button_sign_in);
    }

    private void setListeners() {
        mButtonSignIn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                mUserName  = mEditTextUsername.getText().toString();
                mPassWord = mEditTextPassword.getText().toString();
startSignUpActivity();

            }
        }));
        mButtonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEditTextUsername.getText().toString().equals("") || mEditTextPassword.getText().toString().equals(""))
                    showToast("Your Username or Password is in correct!");

                else if(mUserNameClient.equals(mEditTextUsername.getText().toString())&&
                        mPasswordClient.equals(mEditTextPassword.getText().toString()))
                    showToast("Welcome!");
                else
                    showToast("Your Username or Password is in correct!");
            }
        });
    }


    private void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null)
            return;
        if (requestCode == REQUEST_CODE_SIGN_UP_ACTIVITY) {
            mUserNameClient = data.getStringExtra(SignUpActivity.EXTRA_USERNAME_FROM_SIGN_UP);
            mPasswordClient = data.getStringExtra(SignUpActivity.EXTRA_KEY_PASSWORD_FROM_SIGN_UP);
            mEditTextUsername.setText(mUserNameClient);
            mEditTextPassword.setText(mPasswordClient);
//        }
        }
    }
    private void startSignUpActivity(){
        Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
        intent.putExtra(EXTRA_KEY_USERNAME,mUserName);
        intent.putExtra(EXTRA_KEY_PASSWORD,mPassWord);
        startActivityForResult(intent, REQUEST_CODE_SIGN_UP_ACTIVITY);
    }
}