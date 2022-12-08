package com.makeandroid.dwrs;

import static com.makeandroid.dwrs.UserAccount.Uid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.makeandroid.dwrs.adapters.Postadapter;
import com.makeandroid.dwrs.adapters.Postcommentadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Comments;
import model.Post;

public class Boardread extends AppCompatActivity implements RecyclerViewitemclickListener.OnItemClickListener {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();//사용자 가져옴
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    private RecyclerView mCommentsRecyclerView;

    private TextView mTitleText, mContentsText, mNameText;

    private EditText mcomments;
    private Button mwritecomments;

    private Postcommentadapter madapter;
    private List<Comments> mdatas;

    private String id;
    private String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boardread);

        mTitleText = findViewById(R.id.boardread_title);
        mContentsText = findViewById(R.id.boardread_contents);
        mNameText = findViewById(R.id.boardread_nickname);

        mCommentsRecyclerView = findViewById(R.id.boardread_recyclerView);
        mCommentsRecyclerView.addOnItemTouchListener(new RecyclerViewitemclickListener(this, mCommentsRecyclerView, this));

        Intent getIntent = getIntent();
        id = getIntent().getStringExtra(Uid);
        Log.e("ITEM USERACCOUNT UID", id);

        mcomments = findViewById(R.id.boardread_comment_text);
        mwritecomments = findViewById(R.id.boardread_comment_writebtn);

        mStore.collection(UserAccount.post).document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                if (task.getResult() != null) {
                                    Map<String, Object> snap = task.getResult().getData();//map에 받음
                                    String title = String.valueOf(snap.get(UserAccount.Title));
                                    String contents = String.valueOf(snap.get(UserAccount.Contents));
                                    String name = String.valueOf(snap.get(UserAccount.Nickname));

                                    mTitleText.setText(title);
                                    mContentsText.setText(contents);
                                    mNameText.setText(name);
                                }
                            } else {
                                Toast.makeText(Boardread.this, "삭제된 글입니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

        if (mAuth.getCurrentUser() != null) {
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

            mwritecomments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mAuth.getCurrentUser() != null) {
                        String postid = mStore.collection(UserAccount.post).document().getId();// 안겹치게 id 부여
                        Map<String, Object> data = new HashMap<>();
                        data.put(Uid, postid);
                        data.put(UserAccount.Comments, mcomments.getText().toString());
                        data.put(UserAccount.Nickname, nickname);
                        mStore.collection(UserAccount.comments).document(postid).set(data, SetOptions.merge());
                        Toast.makeText(Boardread.this, "댓글이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = getIntent();//새로고침
                        startActivity(intent);
                    }

                }
            });

        }
    }

    @Override
    protected void onStart() {//리사이클러 안에 값 넣어주는 함수
        super.onStart();
        mdatas = new ArrayList<>(); // 리스트 객체 생성
        mStore.collection(UserAccount.comments)//저장소에서 포스트에 해당하는 데이터 불러오기
                .get()//가져오기
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {//성공시
                            if (task.getResult() != null) {//결과값이 null이 아니면
                                mdatas.clear();//쓰레기 값 비워줌
                                for (DocumentSnapshot snapshot : task.getResult()) {//모두 불러오려면 for문 써야함
                                    Map<String, Object> shot = snapshot.getData();
                                    String uid = String.valueOf(shot.get(Uid));
                                    String nickname = String.valueOf(shot.get(UserAccount.Nickname));
                                    String comments = String.valueOf(shot.get(UserAccount.Comments));
                                    Comments data = new Comments(uid, nickname, comments); //post클래스의 생성자에서 들어감 -> 객체 생성
                                    mdatas.add(data);//리스트에 저장


                                }

                                madapter = new Postcommentadapter(mdatas);//어댑터에 리스트 저장
                                mCommentsRecyclerView.setAdapter(madapter);//리사이클러뷰에 리스트 저장

                            }
                        }
                    }
                });
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onItemLongClick(View view, int position) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("삭제 하시겠습니까?");
        dialog.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {//데이터 삭제!!
                mStore.collection(UserAccount.comments).document(mdatas.get(position).getUid()).delete();
                Toast.makeText(Boardread.this, "삭제 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Boardread.this, "취소 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setTitle("삭제 알림");
        dialog.show();
    }
}