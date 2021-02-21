package WeiBo.Bean;

import java.util.Date;
import java.util.List;

public class BlogBean {
    private String bossId;
    private String think;
    private String creatAtAndName;
    private List<PictureBean> listPic;

    private List<CommentBean> listCom;

    public List<CommentBean> getListCom() {
        return listCom;
    }

    public void setListCom(List<CommentBean> listCom) {
        this.listCom = listCom;
    }

    public List<PictureBean> getListPic() {
        return listPic;
    }

    public void setListPic(List<PictureBean> listPic) {
        this.listPic = listPic;
    }

    public String getBossId() {
        return bossId;
    }

    public void setBossId(String bossId) {
        this.bossId = bossId;
    }

    public String getThink() {
        return think;
    }

    public void setThink(String think) {
        this.think = think;
    }

    public String getCreatAtAndName() {
        return creatAtAndName;
    }

    public void setCreatAtAndName(String creatAtAndName) {
        this.creatAtAndName = creatAtAndName;
    }
}
