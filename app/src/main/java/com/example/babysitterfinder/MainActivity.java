package com.example.babysitterfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button daftarMajikan, daftarPenyalur, masukMajikan, masukPenyalur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        daftarMajikan = findViewById(R.id.signUpMajikan);
        daftarPenyalur = findViewById(R.id.signUpPenyalur);
        masukMajikan = findViewById(R.id.loginMajikan);
        masukPenyalur = findViewById(R.id.loginMajikan);

        daftarMajikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(MainActivity.this, DaftarMajikanActivity.class);
                startActivity(signup);
            }
        });
        daftarPenyalur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(MainActivity.this, DaftarPenyalurActivity.class);
                startActivity(signup);
            }
        });
        masukPenyalur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(MainActivity.this, LoginPenyalurActivity.class);
                startActivity(signup);
            }
        });
        masukMajikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(MainActivity.this, LoginMajikanActivity.class);
                startActivity(signup);
            }
        });
    }
}
