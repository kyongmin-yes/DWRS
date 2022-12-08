package com.makeandroid.dwrs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.makeandroid.dwrs.adapters.Postadapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Post;

public class Boardmain extends AppCompatActivity implements View.OnClickListener, RecyclerViewitemclickListener.OnItemClickListener{

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    private RecyclerView mPostRecyclerView;
    private ImageButton back_info;

    private Postadapter madapter;
    private List<Post> mdatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boardmain);

        mPostRecyclerView = findViewById(R.id.boardmain_recyclerView);

        findViewById(R.id.boardmain_write).setOnClickListener(this);

        mPostRecyclerView.addOnItemTouchListener(new RecyclerViewitemclickListener(this, mPostRecyclerView, this));
        //리사이클러뷰를 터치했을때 동작

        back_info = findViewById(R.id.boardmain_backbtn);
        back_info.setOnClickListener(new View.OnClickListener() {//'<'을 눌렀을 때
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Boardmain.this, MainActivity.class);
                startActivity(intent);//화면전환
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        mdatas = new ArrayList<>(); // 리스트 객체 생성
        mStore.collection(UserAccount.post)//저장소에서 포스트에 해당하는 데이터 불러오기
                .orderBy(UserAccount.Timestamp, Query.Direction.DESCENDING)// 시간 찍는 쿼리
                .get()//가져오기
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {//성공시
                            if (task.getResult() != null) {//결과값이 null이 아니면
                                mdatas.clear();//쓰레기 값 비워줌
                                for (DocumentSnapshot snapshot : task.getResult()) {//모두 불러오려면 for문 써야함
                                    Map<String, Object> shot = snapshot.getData();
                                    String uid = String.valueOf(shot.get(UserAccount.Uid));
                                    String title = String.valueOf(shot.get(UserAccount.Title));
                                    String contents = String.valueOf(shot.get(UserAccount.Contents));
                                    String nickname = String.valueOf(shot.get(UserAccount.Nickname));
                                    Post data = new Post(uid, title, contents, nickname); //post클래스의 생성자에서 들어감 -> 객체 생성
                                    mdatas.add(data);//리스트에 저장

                                }

                                madapter = new Postadapter(mdatas);//어댑터에 리스트 저장
                                mPostRecyclerView.setAdapter(madapter);//리사이클러뷰에 리스트 저장
                            }
                        }
                    }
                });
    }

    @Override//onclick, onitem은 리사이클러 아이템 리스너가 생성.
    public void onClick(View v){
        startActivity(new Intent(this, Boardwrite.class));
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, Boardread.class);
        intent.putExtra(UserAccount.Uid, mdatas.get(position).getUid());
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int position) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("삭제 하시겠습니까?");
        dialog.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {//데이터 삭제!!
                mStore.collection(UserAccount.post).document(mdatas.get(position).getUid()).delete();
                Toast.makeText(Boardmain.this, "삭제 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Boardmain.this, "취소 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setTitle("삭제 알림");
        dialog.show();
    }
}

