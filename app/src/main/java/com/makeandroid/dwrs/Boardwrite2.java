package com.makeandroid.dwrs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class Boardwrite2 extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();//사용자 가져옴
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();//DB가져옴

    private EditText mtitle, mcontents;


    private String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boardwrite2);

        mtitle = findViewById(R.id.boardwrite2_title);
        mcontents = findViewById(R.id.boardwrite2_contents);

        findViewById(R.id.boardwrite2_btn).setOnClickListener(this);

        if(mAuth.getCurrentUser() != null) {
            mStore.collection(UserAccount.user).document(mAuth.getCurrentUser().getUid())//nickname 가져옴
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.getResult() != null) {
                                nickname = (String) task.getResult().getData().get(UserAccount.Nickname);
                            }
                        }
                    });// 특정데이터 가져오기!!
    }
}
    @Override
    public void onClick(View v){
        if(mAuth.getCurrentUser() != null){
            String postid = mStore.collection(UserAccount.machineBreakdown).document().getId();// 안겹치게 id 부여
            Map<String, Object> data = new HashMap<>();
            data.put(UserAccount.Uid, postid);
            data.put(UserAccount.Title, mtitle.getText().toString());
            data.put(UserAccount.Contents, mcontents.getText().toString());
            data.put(UserAccount.Nickname, nickname);
            data.put(UserAccount.Timestamp, FieldValue.serverTimestamp());
            data.put(UserAccount.machineBreakdown, null);
            mStore.collection(UserAccount.machineBreakdown).document(postid).set(data, SetOptions.merge());
            finish();
        }
    }
}