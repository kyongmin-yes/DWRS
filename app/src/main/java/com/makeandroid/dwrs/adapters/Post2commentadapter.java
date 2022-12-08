package com.makeandroid.dwrs.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeandroid.dwrs.R;

import java.util.List;

import model.Comments;
import model.Comments2;
import model.Post2;

public class Post2commentadapter extends RecyclerView.Adapter<Post2commentadapter.Comments2ViewHolder> {

    private List<Comments2> datas; //Post는 모델패키지의 포스트를 리스트에 가둬줌

    public Post2commentadapter(List<Comments2> datas) {//어댑터의 생성자

        this.datas = datas;

    }
    @NonNull
    @Override
    public Post2commentadapter.Comments2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Post2commentadapter.Comments2ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comments2, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Post2commentadapter.Comments2ViewHolder holder, int position) {
        Comments2 data = datas.get(position);//포지션은 위에서 부터 1,2,3,4... 그런 포지션을 의미
        holder.nickname.setText(data.getNickname());
        holder.comments.setText(data.getComments());


    }

    @Override
    public int getItemCount() {
        return datas.size();//총 길이를 가져온다
    }

    class Comments2ViewHolder extends RecyclerView.ViewHolder{

        private TextView nickname;
        private TextView comments;

        public Comments2ViewHolder(@NonNull View itemView) {
            super(itemView);

            nickname = itemView.findViewById(R.id.item_comments2_nickname);
            comments = itemView.findViewById(R.id.item_comments2_comments);


        }
    }
}