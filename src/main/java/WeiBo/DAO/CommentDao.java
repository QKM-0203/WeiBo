package WeiBo.DAO;

import WeiBo.Bean.BlogBean;
import WeiBo.Bean.CommentBean;
import org.springframework.jdbc.core.JdbcTemplate;

public class CommentDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(druidJDBCUtils.getDataSource());

    public void addComment(CommentBean commentBean) {
        String sql = "insert into comment value(?,?,?,?)";
        jdbcTemplate.update(sql,commentBean.getComment(),commentBean.getCreatAt(),commentBean.getBlogCreatAtAndName(),commentBean.getBossId()
        );
    }

    public void deleteComment(String bossId,String creatAt) {
       String sql = "delete comment from comment where bossId = ? and creatAt= ?";
        jdbcTemplate.update(sql,bossId,creatAt);
    }
}
