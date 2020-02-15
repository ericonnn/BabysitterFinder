package com.example.babysitterfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DaftarPenyalurActivity extends AppCompatActivity {
    private EditText namaPenyalur, emailPenyalur, pwdPenyalur, kPwdPenyalur, alamatPenyalur, kotaPenyalur, provinsiPenyalur, kodePosPenyalur, noTelpPenyalur, noWAPenyalur;
    Button buttonDftrPenyalur;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;
    String user_id;
    String statusPenyalur = "penyalur";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_penyalur);

        namaPenyalur = findViewById(R.id.daftarNamaPenyalur);
        emailPenyalur = findViewById(R.id.daftarEmailPenyalur);
        pwdPenyalur = findViewById(R.id.daftarPasswordPenyalur);
        kPwdPenyalur = findViewById(R.id.daftarKPasswordPenyalur);
        alamatPenyalur = findViewById(R.id.daftarAlamatPenyalur);
        kotaPenyalur = findViewById(R.id.daftarKotaPenyalur);
        provinsiPenyalur = findViewById(R.id.daftarProvinsiPenyalur);
        kodePosPenyalur = findViewById(R.id.daftarKodePosPenyalur);
        noTelpPenyalur = findViewById(R.id.daftarTeleponPenyalur);
        noWAPenyalur = findViewById(R.id.daftarWAPenyalur);
        buttonDftrPenyalur = findViewById(R.id.buttonSignUpPenyalur);

        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        buttonDftrPenyalur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String namaPylr = namaPenyalur.getText().toString();
                final String emailIdPylr = emailPenyalur.getText().toString();
                final String pwdPylr = pwdPenyalur.getText().toString();
                final String kPwdPylr = kPwdPenyalur.getText().toString();
                final String alamatPylr = alamatPenyalur.getText().toString();
                final String kotaPylr = kotaPenyalur.getText().toString();
                final String provinsiPylr = provinsiPenyalur.getText().toString();
                final String kodePosPylr = kodePosPenyalur.getText().toString();
                final String noTelpPylr = noTelpPenyalur.getText().toString();
                final String WAPylr = noWAPenyalur.getText().toString();

                if (namaPylr.isEmpty() || emailIdPylr.isEmpty() || pwdPylr.isEmpty() || pwdPylr.length() < 8 || kPwdPylr.isEmpty() || !kPwdPylr.equals(pwdPylr) || noTelpPylr.isEmpty() || alamatPylr.isEmpty() || kotaPylr.isEmpty() || provinsiPylr.isEmpty() || kodePosPylr.isEmpty() || WAPylr.isEmpty()) {
                    if (namaPylr.isEmpty()) {
                        namaPenyalur.setError("Mohon isi nama lengkap anda");
                        namaPenyalur.requestFocus();
                    }
                    if (emailIdPylr.isEmpty()) {
                        emailPenyalur.setError("Mohon isi email anda");
                        emailPenyalur.requestFocus();
                    }
                    if (pwdPylr.isEmpty()) {
                        pwdPenyalur.setError("Mohon isi password anda");
                        pwdPenyalur.requestFocus();
                    }
                    if (pwdPylr.length() < 8) {
                        pwdPenyalur.setError("Password minimal harus 8 karakter");
                        pwdPenyalur.requestFocus();
                    }
                    if (kPwdPylr.isEmpty()) {
                        kPwdPenyalur.setError("Mohon konfirmasi password anda");
                        kPwdPenyalur.requestFocus();
                    }
                    if (!kPwdPylr.equals(pwdPylr)) {
                        kPwdPenyalur.setError("Konfirmasi password anda tidak sama dengan password anda");
                        kPwdPenyalur.requestFocus();
                    }
                    if (noTelpPylr.isEmpty()) {
                        noTelpPenyalur.setError("Mohon isi nomor telepon anda");
                        noTelpPenyalur.requestFocus();
                    }
                    if (alamatPylr.isEmpty()) {
                        alamatPenyalur.setError("Mohon isi alamat anda");
                        alamatPenyalur.requestFocus();
                    }
                    if (kotaPylr.isEmpty()) {
                        kotaPenyalur.setError("Mohon isi kota anda");
                        kotaPenyalur.requestFocus();
                    }
                    if (provinsiPylr.isEmpty()) {
                        provinsiPenyalur.setError("Mohon isi provinsi anda");
                        provinsiPenyalur.requestFocus();
                    }
                    if (kodePosPylr.isEmpty()) {
                        kodePosPenyalur.setError("Mohon isi kode pos anda");
                        kodePosPenyalur.requestFocus();
                    }
                    if (WAPylr.isEmpty()) {
                        noWAPenyalur.setError("Mohon isi nomor WA anda");
                        noWAPenyalur.requestFocus();
                    }
                } else {
                    final Map<String,String> userData = new HashMap<>();
                    userData.put("Nama Penyalur ", namaPylr);
                    userData.put("Email Penyalur ", emailIdPylr);
                    userData.put("Password Penyalur ", pwdPylr);
                    userData.put("Konfirmasi Password Penyalur ", kPwdPylr);
                    userData.put("Nomor Telepon Penyalur ", noTelpPylr);
                    userData.put("Alamat Penyalur ", alamatPylr);
                    userData.put("Kota Penyalur ", kotaPylr);
                    userData.put("Provinsi Penyalur ", provinsiPylr);
                    userData.put("Kode Pos Penyalur ", kodePosPylr);
                    userData.put("Nomor WA Penyalur ", WAPylr);
                    userData.put("Status ", statusPenyalur);



                    mAuth.createUserWithEmailAndPassword(emailIdPylr, pwdPylr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                user_id = mAuth.getCurrentUser().getUid();
                                mFirestore.collection("Data Penyalur").document(user_id).set(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                    }
                                });
                                Toast.makeText(DaftarPenyalurActivity.this, "Halo Selamat Datang!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(DaftarPenyalurActivity.this, HomePenyalurActivity.class);
                                startActivity(i);
                            }
                        }
                    });

                }
            }
        });

    }
}
