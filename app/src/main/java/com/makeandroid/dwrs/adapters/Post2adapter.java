package com.makeandroid.dwrs.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeandroid.dwrs.R;

import java.util.List;

import model.Post;
import model.Post2;

public class Post2adapter extends RecyclerView.Adapter<Post2adapter.Post2ViewHolder>{

    private List<Post2> datas; //Post는 모델패키지의 포스트를 리스트에 가둬줌

    public Post2adapter(List<Post2> datas) {//어댑터의 생성자

        this.datas = datas;
}

    @NonNull
    @Override
    public Post2adapter.Post2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Post2adapter.Post2ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post2, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Post2adapter.Post2ViewHolder holder, int position) {
        Post2 data = datas.get(position);//포지션은 위에서 부터 1,2,3,4... 그런 포지션을 의미
        holder.title.setText(data.getTitle());//그 포지션에 title넣겠다.//홀더라는 것은 각각 하나를 의미 -> getter setter 사용!!
        holder.contents.setText(data.getContents());// 내용도 넣겠다.
        holder.nickname.setText("작성자 : " + data.getNickname());

    }

    @Override
    public int getItemCount() {
        return datas.size();//총 길이를 가져온다
    }

    class Post2ViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView contents;
        private TextView nickname;

        public Post2ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.item_Post2_title);
            contents = itemView.findViewById(R.id.item_Post2_contents);
            nickname = itemView.findViewById(R.id.item_post2_nickname);

        }
    }
}
