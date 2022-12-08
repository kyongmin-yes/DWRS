package model;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Post {
    private String Uid;
    private String title;
    private String contents;
    private String nickname;
    @ServerTimestamp
    private Date date;

    public Post() {
    }

    public Post(String uid, String title, String contents, String nickname) {
        Uid = uid;
        this.title = title;
        this.contents = contents;
        this.nickname = nickname;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    @Override
    public String toString() {
        return "Post{" +
                "Uid='" + Uid + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", nickname='" + nickname + '\'' +
                ", date=" + date +
                '}';
    }
}
