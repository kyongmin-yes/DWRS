package model;

public class Comments {

    private String Uid;
    private String nickname;
    private String comments;

    public Comments() {
    }

    public Comments(String Uid, String nickname, String comments) {
        this.Uid = Uid;
        this.nickname = nickname;
        this.comments = comments;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "Uid='" + Uid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
