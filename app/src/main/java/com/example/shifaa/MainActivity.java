package com.example.shifaa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity<idToken> extends AppCompatActivity {

    TextView signup;
    Button login;
    EditText user;
    EditText passw;
    EditText email_login;
    ImageView google;
    ImageView facebook;

    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;

    BeginSignInRequest signInRequest ;
    GoogleSignInClient mGoogleSignInClient ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.edtUsername);
        passw = findViewById(R.id.edtPassword);
        email_login = findViewById(R.id.Email_LogIn);
        signup = findViewById(R.id.infoTxtCredits);
        login = findViewById(R.id.btnLogIn);
        google = findViewById(R.id.google);
        facebook = findViewById(R.id.facebook);

        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(MainActivity.this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkForm();
            }
        });

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomePage.class);
                intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }


    private void checkForm() {
        String username_signIn = user.getText().toString();
        String mail_signIn = email_login.getText().toString();
        String pass_singIn = passw.getText().toString();

        if (username_signIn.isEmpty() || username_signIn.length() < 7) {
            Toast.makeText(MainActivity.this, "Your username is not valid!", Toast.LENGTH_SHORT).show();
        } else if (mail_signIn.isEmpty() || !mail_signIn.contains("@")) {
            Toast.makeText(MainActivity.this, "Email is not valid!", Toast.LENGTH_SHORT).show();
        } else if (pass_singIn.isEmpty() || pass_singIn.length() < 7) {
            Toast.makeText(MainActivity.this, "Password must be 7 charcter", Toast.LENGTH_SHORT).show();
        } else {
            mLoadingBar.setTitle("Login");
            mLoadingBar.setMessage("Please wait when chick your Information");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.signInWithEmailAndPassword(mail_signIn, pass_singIn).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        mLoadingBar.dismiss();
                        Intent intent = new Intent(MainActivity.this, HomePage.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}