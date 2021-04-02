package com.example.kusitmsrider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class UploadActivity extends AppCompatActivity implements View.OnClickListener{
    LinearLayout button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);


        button2 = findViewById(R.id.button1);
        button2.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.button1) {
            Intent intent = new Intent(this,ResultActivity.class);
            startActivity(intent);
        }
    }
}