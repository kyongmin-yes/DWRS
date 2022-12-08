package com.makeandroid.dwrs;

import android.accounts.Account;
import android.content.Intent;
import android.hardware.usb.UsbRequest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

public class Fraginfo extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseFirestore mStore;

    private TextView info_nicknameshow;
    private TextView report_lost, report_breakdown;
    private TextView quit_system, logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fraginfo, container, false);

        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();

        info_nicknameshow = v.findViewById(R.id.info_nicknameshow);

        report_lost = v.findViewById(R.id.info_report_lost);
        report_breakdown = v.findViewById(R.id.info_report_breakdown);

        logout = v.findViewById(R.id.info_logout);
        quit_system = v.findViewById(R.id.info_quit_system);

        report_lost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Boardmain.class);
                startActivity(intent);
            }
        });

        if (mAuth.getCurrentUser() != null) {
            mStore.collection(UserAccount.user).document(mAuth.getCurrentUser().getUid())//nickname 가져옴
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.getResult() != null) {
                                info_nicknameshow.setText(task.getResult().get(UserAccount.Nickname) + " 님");//text에 삽입하는 법
                            }
                        }
                    });// 특정데이터 가져오기!!

        }
        logout.setOnClickListener(new View.OnClickListener() {//로그아웃
            @Override
            public void onClick(View v) {
                mAuth.getInstance().signOut();
                Toast.makeText(getActivity(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                ActivityCompat.finishAffinity(getActivity());
            }
        });

        quit_system.setOnClickListener(new View.OnClickListener() {//탈퇴
            @Override
            public void onClick(View v) {
                mAuth.getCurrentUser().delete(); // 탈퇴처리 함수
                Toast.makeText(getActivity(), "탈퇴 처리 되었습니다.", Toast.LENGTH_SHORT).show();
                ActivityCompat.finishAffinity(getActivity());

            }
        });

        report_breakdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Boardmain2.class);
                startActivity(intent);
            }
        });



            return v;
        }
    }


