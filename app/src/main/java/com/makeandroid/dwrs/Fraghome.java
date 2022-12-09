package com.makeandroid.dwrs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import model.Post;

public class Fraghome extends Fragment {

    private Button reservation;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fraghome, container, false);

        reservation = v.findViewById(R.id.home_btn_booking);



            reservation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//예약하기 -> 세탁기 선택 창으로
                    Intent intent = new Intent(getActivity(), Booking_select.class);
                    startActivity(intent);
                }
            });

            return v;
        }
    }