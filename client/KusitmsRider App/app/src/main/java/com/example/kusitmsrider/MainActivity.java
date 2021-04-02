package com.example.kusitmsrider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    LinearLayout button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.button1) {
            Intent intent = new Intent(this, UploadActivity.class);
            startActivity(intent);
        }
    }
}