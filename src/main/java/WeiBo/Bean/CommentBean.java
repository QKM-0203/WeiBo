package WeiBo.Bean;

public class CommentBean {
    private String bossId;
    private String comment;
    private String creatAt;
    private String head;
    private String name;
    private String blogCreatAtAndName;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getBlogCreatAtAndName() {
        return blogCreatAtAndName;
    }

    public void setBlogCreatAtAndName(String blogCreatAtAndName) {
        this.blogCreatAtAndName = blogCreatAtAndName;
    }

    public String getBossId() {
        return bossId;
    }

    public void setBossId(String bossId) {
        this.bossId = bossId;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(String creatAt) {
        this.creatAt = creatAt;
    }
}
