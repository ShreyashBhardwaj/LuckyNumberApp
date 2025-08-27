package com.example.luckynumberintentsexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    TextView setLuckyNumber;
    Button share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setLuckyNumber=findViewById(R.id.luckyNumber);
        share = findViewById(R.id.shareResult);

       //To Get Data from the previous Intent
        Intent i = getIntent();
        String userName = i.getStringExtra("name"); //Can also be written as String userName = getIntent().getStringExtra("name");

        int randomNo = generateRandomNumber();

        setLuckyNumber.setText(""+randomNo);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    shareData(userName,randomNo);
            }
        });
    }

    public void shareData(String name, int number){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT,name+" Got Lucky Today!!");
        i.putExtra(Intent.EXTRA_TEXT,name+"Your Lucky Number is"+number);
        startActivity(Intent.createChooser(i,"Choose a Platform"));
    }

    public int generateRandomNumber(){
        Random r = new Random();
        int upperLimit = 1000;
        return r.nextInt(upperLimit);
    }
}