package com.makeandroid.dwrs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Booking_option extends AppCompatActivity {

    private Button option_finish;
    private ImageButton back_select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_option);

        option_finish = findViewById(R.id.bookingoption_finish);
        back_select = findViewById(R.id.bookingoptionback_select);

        option_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Booking_option.this, Booking_end.class);
                startActivity(intent);
            }
        });

        back_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Booking_option.this, Booking_select.class);
                startActivity(intent);
            }
        });
    }
}