package com.makeandroid.dwrs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class Booking_select extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private ImageButton back_main;
    private ImageButton wm_no1, wm_no2, wm_no3, wm_no4;

    private boolean machine1, machine2, machine3, machine4;

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



        if (mAuth.getCurrentUser() != null) {
            mStore.collection(Machinebreakdown.machinebreakdown).document("1sbrdUjt7muz9D4cPV6H")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.getResult() != null){
                                machine1 = (boolean) task.getResult().getData().get("machine1");
                                machine2 = (boolean) task.getResult().getData().get("machine2");
                                machine3 = (boolean) task.getResult().getData().get("machine3");
                                machine4 = (boolean) task.getResult().getData().get("machine4");

                                if(machine1 == false)
                                    wm_no1.setBackgroundResource(R.drawable.grayround);
                                if(machine2 == false)
                                    wm_no2.setBackgroundResource(R.drawable.grayround);
                                if(machine3 == false)
                                    wm_no3.setBackgroundResource(R.drawable.grayround);
                                if(machine4 == false)
                                    wm_no4.setBackgroundResource(R.drawable.grayround);
                            }
                        }
                    });



        wm_no1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(machine1 == true){
                    Intent intent = new Intent(Booking_select.this, Booking_option.class);
                    startActivity(intent);
                }
                else
                {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(Booking_select.this);
                    dlg.setMessage("사용할 수 없는 세탁기 입니다.");
                    dlg.setPositiveButton("확인", null);
                    dlg.show();
                }
            }
        });

            wm_no2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(machine2 == true){
                        Intent intent = new Intent(Booking_select.this, Booking_option.class);
                        startActivity(intent);
                    }
                    else
                    {
                        AlertDialog.Builder dlg = new AlertDialog.Builder(Booking_select.this);
                        dlg.setMessage("사용할 수 없는 세탁기 입니다.");
                        dlg.setPositiveButton("확인", null);
                        dlg.show();
                    }
                }
            });

            wm_no3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(machine3 == true){
                        Intent intent = new Intent(Booking_select.this, Booking_option.class);
                        startActivity(intent);
                    }
                    else
                    {
                        AlertDialog.Builder dlg = new AlertDialog.Builder(Booking_select.this);
                        dlg.setMessage("사용할 수 없는 세탁기 입니다.");
                        dlg.setPositiveButton("확인", null);
                        dlg.show();
                    }
                }
            });

            wm_no4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(machine4 == true){
                        Intent intent = new Intent(Booking_select.this, Booking_option.class);
                        startActivity(intent);
                    }
                    else
                    {
                        AlertDialog.Builder dlg = new AlertDialog.Builder(Booking_select.this);
                        dlg.setMessage("사용할 수 없는 세탁기 입니다.");
                        dlg.setPositiveButton("확인", null);
                        dlg.show();
                    }
                }
            });
        }
    }
}