package com.example.babysitterfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginMajikanActivity extends AppCompatActivity {
    EditText emailMajikan, passwordMajikan;
    Button signIn;
    TextView signUpFirstMajikan;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseFirestore mFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_majikan);
        emailMajikan = findViewById(R.id.loginEmailMajikan);
        passwordMajikan = findViewById(R.id.loginPasswordMajikan);
        signUpFirstMajikan = findViewById(R.id.signUpMajikanDulu);
        mAuth = FirebaseAuth.getInstance();
        signIn = findViewById(R.id.signInMajikan);
        mFirestore = FirebaseFirestore.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            FirebaseUser mFirebaseUser = mAuth.getCurrentUser();

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(mFirebaseUser != null){
                    Toast.makeText(LoginMajikanActivity.this, "You're Logged In",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginMajikanActivity.this,HomeMajikanActivity.class);
                    startActivity(i);
                } else{
                    Toast.makeText(LoginMajikanActivity.this, "Please Login",Toast.LENGTH_SHORT).show();
                }
            }
        };
            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String emailIdMjkn = emailMajikan.getText().toString();
                    String pwdMjkn = passwordMajikan.getText().toString();

                    if (emailIdMjkn.isEmpty() || pwdMjkn.isEmpty() || pwdMjkn.length() < 8 || !(emailIdMjkn.isEmpty()&&pwdMjkn.isEmpty())){
                        if (emailIdMjkn.isEmpty()) {
                            emailMajikan.setError("Mohon isi email anda");
                            emailMajikan.requestFocus();
                        }
                        if (pwdMjkn.isEmpty()) {
                            passwordMajikan.setError("Mohon isi password anda");
                            passwordMajikan.requestFocus();
                        }
                        if (pwdMjkn.length() < 8) {
                            passwordMajikan.setError("Password minimal harus 8 karakter");
                            passwordMajikan.requestFocus();
                        }
                        if (!(emailIdMjkn.isEmpty() && pwdMjkn.isEmpty())){
                            mAuth.signInWithEmailAndPassword(emailIdMjkn,pwdMjkn).addOnCompleteListener(LoginMajikanActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){
                                        Toast.makeText(LoginMajikanActivity.this, "Login error, please login again", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Intent intToHome = new Intent(LoginMajikanActivity.this, HomeMajikanActivity.class);
                                        startActivity(intToHome);
                                    }
                                }
                            });
                        } else{
                            Toast.makeText(LoginMajikanActivity.this,"Please login",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            signUpFirstMajikan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intToSignUpMajikan = new Intent(LoginMajikanActivity.this, DaftarMajikanActivity.class);
                    startActivity(intToSignUpMajikan);
                }
            });
    }
}
