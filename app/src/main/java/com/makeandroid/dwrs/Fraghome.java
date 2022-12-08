package com.makeandroid.dwrs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

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
