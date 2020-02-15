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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DaftarMajikanActivity extends AppCompatActivity {
    private EditText namaMajikan, emailMajikan, pwdMajikan, kPwdMajikan, alamatMajikan, kotaMajikan, provinsiMajikan, kodePosMajikan, noTelpMajikan;
    Button buttonDftrMajikan;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;
    String userId;
    String statusMajikan = "majikan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_majikan);

        noTelpMajikan = findViewById(R.id.daftarTeleponMajikan);
        namaMajikan = findViewById(R.id.daftarNamaMajikan);
        emailMajikan = findViewById(R.id.daftarEmailMajikan);
        pwdMajikan = findViewById(R.id.daftarPasswordMajikan);
        kPwdMajikan = findViewById(R.id.daftarKPasswordMajikan);
        alamatMajikan = findViewById(R.id.daftarAlamatMajikan);
        kotaMajikan = findViewById(R.id.daftarKotaMajikan);
        provinsiMajikan = findViewById(R.id.daftarProvinsiMajikan);
        kodePosMajikan = findViewById(R.id.daftarKodePosMajikan);
        buttonDftrMajikan = findViewById(R.id.buttonSignUpMajikan);

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();

        buttonDftrMajikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaMjkn = namaMajikan.getText().toString();
                final String emailIdMjkn = emailMajikan.getText().toString();
                final String pwdMjkn = pwdMajikan.getText().toString();
                String konfirmpwd = kPwdMajikan.getText().toString();
                String alamatMjkn = alamatMajikan.getText().toString();
                String kotaMjkn = kotaMajikan.getText().toString();
                String provinsiMjkn = provinsiMajikan.getText().toString();
                String kodePosMjkn = kodePosMajikan.getText().toString();
                String noTelpMjkn = noTelpMajikan.getText().toString();

                if (namaMjkn.isEmpty() || emailIdMjkn.isEmpty() || pwdMjkn.isEmpty() || pwdMjkn.length() < 8 || konfirmpwd.isEmpty() || !konfirmpwd.equals(pwdMjkn) || noTelpMjkn.isEmpty() || alamatMjkn.isEmpty() || kotaMjkn.isEmpty() || provinsiMjkn.isEmpty() || kodePosMjkn.isEmpty()) {
                    if (namaMjkn.isEmpty()) {
                        namaMajikan.setError("Mohon isi nama lengkap anda");
                        namaMajikan.requestFocus();
                    }
                    if (emailIdMjkn.isEmpty()) {
                        emailMajikan.setError("Mohon isi email anda");
                        emailMajikan.requestFocus();
                    }
                    if (pwdMjkn.isEmpty()) {
                        pwdMajikan.setError("Mohon isi password anda");
                        pwdMajikan.requestFocus();
                    }
                    if (pwdMjkn.length() < 8) {
                        pwdMajikan.setError("Password minimal harus 8 karakter");
                        pwdMajikan.requestFocus();
                    }
                    if (konfirmpwd.isEmpty()) {
                        kPwdMajikan.setError("Mohon konfirmasi password anda");
                        kPwdMajikan.requestFocus();
                    }
                    if (!konfirmpwd.equals(pwdMjkn)) {
                        kPwdMajikan.setError("Konfirmasi password anda tidak sama dengan password anda");
                        kPwdMajikan.requestFocus();
                    }
                    if (noTelpMjkn.isEmpty()) {
                        noTelpMajikan.setError("Mohon isi nomor telepon anda");
                        noTelpMajikan.requestFocus();
                    }
                    if (alamatMjkn.isEmpty()) {
                        alamatMajikan.setError("Mohon isi alamat anda");
                        alamatMajikan.requestFocus();
                    }
                    if (kotaMjkn.isEmpty()) {
                        kotaMajikan.setError("Mohon isi kota anda");
                        kotaMajikan.requestFocus();
                    }
                    if (provinsiMjkn.isEmpty()) {
                        provinsiMajikan.setError("Mohon isi provinsi anda");
                        provinsiMajikan.requestFocus();
                    }
                    if (kodePosMjkn.isEmpty()) {
                        kodePosMajikan.setError("Mohon isi kode pos anda");
                        kodePosMajikan.requestFocus();
                    }
                } else {
                    final Map<String, Object> userMap = new HashMap<>();
                    userMap.put("Nama Majikan ", namaMjkn);
                    userMap.put("Email Majikan ", emailIdMjkn);
                    userMap.put("Password Majikan ", pwdMjkn);
                    userMap.put("Konfirmasi Password Majikan ", konfirmpwd);
                    userMap.put("Nomor Telepon Majikan ", noTelpMjkn);
                    userMap.put("Alamat Majikan ", alamatMjkn);
                    userMap.put("Kota Majikan ", kotaMjkn);
                    userMap.put("Provinsi Majikan ", provinsiMjkn);
                    userMap.put("Kode Pos Majikan ", kodePosMjkn);
                    userMap.put("Status ", statusMajikan);

                    mAuth.createUserWithEmailAndPassword(emailIdMjkn, pwdMjkn).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
//                                userId = mAuth.getCurrentUser().getUid();
//                                CollectionReference data_majikan = mFirestore.collection("Data Majikan");
//                                data_majikan.document(userId).set(userMap);
                                //create user id dulu, get uid, baru simpan di database
                                userId = mAuth.getCurrentUser().getUid();
                                mFirestore.collection("Data Majikan").document(userId).set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                    }
                                });
                                Toast.makeText(DaftarMajikanActivity.this, "Halo Selamat Datang!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(DaftarMajikanActivity.this, HomeMajikanActivity.class);
                                startActivity(i);
                            }
                        }
                    });



//                    mFirestore.collection("Data Majikan").document(userId).set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//
//                        }
//                    });


                }
            }
        });

    }
}


