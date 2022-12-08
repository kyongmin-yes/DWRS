package com.makeandroid.dwrs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private EditText memail, mpassword, mnickname;//이메일, 비밀번호, 닉네임
    private CheckBox agree;//약관동의
    private ImageButton back_login;//뒤로가기
    private Button msignupbtn;//회원가입 버튼


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        memail = findViewById(R.id.signup_email);
        mpassword = findViewById(R.id.signup_password);
        mnickname = findViewById(R.id.signup_nickname);

        agree = findViewById(R.id.signup_checkBox_agree);
        back_login = findViewById(R.id.signup_backbtn);

        msignupbtn = findViewById(R.id.signup_btn);
        //xml파일의 id와 class의 변수 매칭

        back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(agree.isChecked()==true){
                    msignupbtn.setVisibility(View.VISIBLE);
                }
                else
                {
                    msignupbtn.setVisibility(View.INVISIBLE);
                }
            }
        });

        msignupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //회원가입 버튼을 클릭했을때
                mAuth.createUserWithEmailAndPassword(memail.getText().toString(), mpassword.getText().toString())
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    FirebaseUser user = mAuth.getCurrentUser();//현재 유저 정보 가져옴
                                    if(user != null){
                                        Map<String, Object> userMap = new HashMap<>();//DB에 데이터를 넣는 방법
                                        userMap.put(UserAccount.Uid, user.getUid());//Uid(고유번호) 지정
                                        userMap.put(UserAccount.Email, memail.getText().toString()); //email
                                        userMap.put(UserAccount.Password, mpassword.getText().toString());//password
                                        userMap.put(UserAccount.Nickname, mnickname.getText().toString());//nicknmae
                                        mStore.collection(UserAccount.user).document(user.getUid()).set(userMap, SetOptions.merge());//DB스토어에 저장
                                        //user의 Uid안에 넣는다. user -> Uid-> ...
                                        finish();
                                    }
                                }else {
                                    Toast.makeText(SignupActivity.this, "회원가입 실패", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}