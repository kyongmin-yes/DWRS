package com.makeandroid.dwrs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Booking_select extends AppCompatActivity {

    private ImageButton back_main;
    private ImageButton wm_no1, wm_no2, wm_no3, wm_no4;

    private int breakdowm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingselect);

        wm_no1 = findViewById(R.id.bookingselect_wm_no1);
        wm_no2 = findViewById(R.id.bookingselect_wm_no2);
        wm_no3 = findViewById(R.id.bookingselect_wm_no3);
        wm_no4 = findViewById(R.id.bookingselect_wm_no4);

        back_main = findViewById(R.id.bookingselect_backmain);
        back_main.setOnClickListener(new View.OnClickListener() {//뒤로가기
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Booking_select.this, MainActivity.class);
                startActivity(intent);
            }
        });




    }
}