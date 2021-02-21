package WeiBo.Service.Imp;

import WeiBo.Bean.CommentBean;
import WeiBo.DAO.CommentDao;
import WeiBo.Service.CommentService;

public class CommentServiceImpl implements CommentService {
    CommentDao commentDao =new CommentDao();
    @Override
    public void addComment(CommentBean commentBean) {
       commentDao.addComment(commentBean);
    }

    @Override
    public void deleteComment(String bossId, String creatAt) {
       commentDao.deleteComment(bossId,creatAt);
    }
}
