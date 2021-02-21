package WeiBo.Service;

import WeiBo.Bean.CommentBean;

public interface CommentService {
    public void addComment(CommentBean commentBean);

    public void deleteComment(String bossId,String creatAt) ;

}
