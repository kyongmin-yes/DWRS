package com.makeandroid.dwrs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Booking_end extends AppCompatActivity {

    private TextView back_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_end);

        back_main = findViewById(R.id.bookingback_main);
        back_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Booking_end.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}