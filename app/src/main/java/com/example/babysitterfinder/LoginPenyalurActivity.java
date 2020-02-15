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

public class LoginPenyalurActivity extends AppCompatActivity {

    EditText emailPenyalur, passwordPenyalur;
    Button signIn;
    TextView signUpFirstPenyalur;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_penyalur);
        emailPenyalur = findViewById(R.id.loginEmailPenyalur);
        passwordPenyalur = findViewById(R.id.loginPasswordPenyalur);
        signUpFirstPenyalur = findViewById(R.id.signUpPenyalurDulu);
        mAuth = FirebaseAuth.getInstance();
        signIn = findViewById(R.id.signInPenyalur);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            FirebaseUser mFirebaseUser = mAuth.getCurrentUser();

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(mFirebaseUser != null){
                    Toast.makeText(LoginPenyalurActivity.this, "You're Logged In",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginPenyalurActivity.this,HomeMajikanActivity.class);
                    startActivity(i);
                } else{
                    Toast.makeText(LoginPenyalurActivity.this, "Please Login",Toast.LENGTH_SHORT).show();
                }
            }
        };
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailIdPylr = emailPenyalur.getText().toString();
                String pwdPylr = passwordPenyalur.getText().toString();

                if (emailIdPylr.isEmpty() || pwdPylr.isEmpty() || pwdPylr.length() < 8 || !(emailIdPylr.isEmpty()&&pwdPylr.isEmpty())){
                    if (emailIdPylr.isEmpty()) {
                        emailPenyalur.setError("Mohon isi email anda");
                        emailPenyalur.requestFocus();
                    }
                    if (pwdPylr.isEmpty()) {
                        passwordPenyalur.setError("Mohon isi password anda");
                        passwordPenyalur.requestFocus();
                    }
                    if (pwdPylr.length() < 8) {
                        passwordPenyalur.setError("Password minimal harus 8 karakter");
                        passwordPenyalur.requestFocus();
                    }
                    if (!(emailIdPylr.isEmpty() && pwdPylr.isEmpty())){
                        mAuth.signInWithEmailAndPassword(emailIdPylr,pwdPylr).addOnCompleteListener(LoginPenyalurActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(LoginPenyalurActivity.this, "Login error, please login again", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Intent intToHome = new Intent(LoginPenyalurActivity.this, HomePenyalurActivity.class);
                                    startActivity(intToHome);
                                }
                            }
                        });
                    } else{
                        Toast.makeText(LoginPenyalurActivity.this,"Please login",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        signUpFirstPenyalur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToSignUpPenyalur = new Intent(LoginPenyalurActivity.this, DaftarPenyalurActivity.class);
                startActivity(intToSignUpPenyalur);
            }
        });
    }
}


