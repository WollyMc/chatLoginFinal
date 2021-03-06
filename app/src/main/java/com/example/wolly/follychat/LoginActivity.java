package com.example.wolly.follychat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar mToolbar;

    private Button mLogin_btn;

    private TextInputLayout mLoginEmail;
    private TextInputLayout mLoginPassword;

    private ProgressDialog mLoginProgress;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Login");

        mLoginProgress = new ProgressDialog(this);

        mLoginEmail=(TextInputLayout) findViewById(R.id.login_email);
        mLoginPassword=(TextInputLayout) findViewById(R.id.login_password);
        mLogin_btn=(Button) findViewById(R.id.login_btn);

        mLogin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mLoginEmail.getEditText().getText().toString();
                String password = mLoginPassword.getEditText().getText().toString();

                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)) {
                    mLoginProgress.setTitle("Logging In");
                    mLoginProgress.setMessage("Please Wait!");
                    mLoginProgress.setCanceledOnTouchOutside(false);
                    mLoginProgress.show();
                    loginUser(email, password);
                }


            }
            });
    }

            private void loginUser(String email, String password) {
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            mLoginProgress.dismiss();
                            Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(mainIntent);
                            finish();
                        } else {
                            mLoginProgress.hide();

                            Toast.makeText(LoginActivity.this, "Unsuccessful!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }


