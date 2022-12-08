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

public class Postcommentadapter extends RecyclerView.Adapter<Postcommentadapter.CommentsViewHolder> {

    private List<Comments> datas; //Post는 모델패키지의 포스트를 리스트에 가둬줌

    public Postcommentadapter(List<Comments> datas) {//어댑터의 생성자

        this.datas = datas;

    }
    @NonNull
    @Override
    public Postcommentadapter.CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Postcommentadapter.CommentsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comments, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Postcommentadapter.CommentsViewHolder holder, int position) {
        Comments data = datas.get(position);//포지션은 위에서 부터 1,2,3,4... 그런 포지션을 의미
        holder.nickname.setText(data.getNickname());
        holder.comments.setText(data.getComments());


    }

    @Override
    public int getItemCount() {
        return datas.size();//총 길이를 가져온다
    }

    class CommentsViewHolder extends RecyclerView.ViewHolder{

        private TextView nickname;
        private TextView comments;

        public CommentsViewHolder(@NonNull View itemView) {
            super(itemView);

            nickname = itemView.findViewById(R.id.item_comments_nickname);
            comments = itemView.findViewById(R.id.item_comments_comments);


        }
    }
}
