package WeiBo.DAO;

import WeiBo.Bean.BlogBean;
import WeiBo.Bean.CommentBean;
import WeiBo.Bean.PictureBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BlogDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(druidJDBCUtils.getDataSource());

    public List<BlogBean> findBlogsByBoosId(String bossId) {

            String sql = "select * from Blog where bossId = ? order by creatAtAndName DESC";
            return   forEachBlog(sql, bossId);

    }

    public List<BlogBean> findBlogs() {

            String sql = "select * from Blog order by creatAtAndName DESC ";
            return forEachBlog(sql);

    }

    public List<BlogBean> forEachBlog(String sql,Object...args){
        List<BlogBean> blogBeans = jdbcTemplate.query(sql, new BeanPropertyRowMapper<BlogBean>(BlogBean.class),args);
        String sql1 = "select boss.name ,boss.head from boss where ID = ?";
        for (BlogBean blogBean : blogBeans) {
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql1, blogBean.getBossId());
            blogBean.setHead((String)maps.get(0).get("head"));
            blogBean.setName((String)maps.get(0).get("name"));
        }
        for (BlogBean blogBean : blogBeans) {
            String creatAt = blogBean.getCreatAtAndName();
            List<PictureBean> pictures = findPictures(creatAt);
            String creatAtAndName = blogBean.getCreatAtAndName();
            List<CommentBean> comment = findComment(creatAtAndName);
            blogBean.setListCom(comment);
            blogBean.setListPic(pictures);
        }
        return blogBeans;
    }

    public List<PictureBean> findPictures(String creatAtAndName) {

        String sql = "select * from picture where creatAtAndName = ?";
         return    jdbcTemplate.query(sql, new BeanPropertyRowMapper<PictureBean>(PictureBean.class),creatAtAndName);


    }

    public void addPictures(BlogBean blogBean){
        String sql = "insert into picture value(?,?)";
        List<PictureBean> listPic = blogBean.getListPic();
        List<Object[]> objects = new ArrayList<>();
        for(int i = 0; i < listPic.size();i++){
            String[] args = new String[2];
            args[0] = listPic.get(i).getCreatAtAndName();
            args[1] = listPic.get(i).getPictureUri();
            objects.add(args);
        }
        jdbcTemplate.batchUpdate(sql,objects);
    }

    public void addBlog(BlogBean blogBean) {
        String sql = "insert into Blog value(?,?,?)";
        jdbcTemplate.update(sql,blogBean.getBossId(),blogBean.getThink(),blogBean.getCreatAtAndName()
               );
        if(blogBean.getListPic()!= null){
            addPictures(blogBean);
        }
    }

    public void deleteBlog(String bossId,String creatAtAndName) {
        String sql1 = "delete picture from picture where creatAtAndName = ?";
        String sql = "delete Blog from Blog where bossId = ? and creatAtAndName = ?";
        String sql2 = "delete comment from comment where blogCreatAtAndName = ?";
        jdbcTemplate.update(sql1,creatAtAndName);
        jdbcTemplate.update(sql2,creatAtAndName);
        jdbcTemplate.update(sql,bossId,creatAtAndName);
    }


    public void updateBlog(BlogBean blogBean) {
        String sql = "update Blog set think = ?,creatAtAndName = ? where bossId = ?";
        jdbcTemplate.update(sql, blogBean.getThink(),blogBean.getCreatAtAndName(),blogBean.getBossId());

    }

    public List<CommentBean> findComment(String blogCreatAtAndName){
        String sql = "select * from comment where blogCreatAtAndName = ? order by creatAt DESC";
        List<CommentBean> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<CommentBean>(CommentBean.class), blogCreatAtAndName);
        String sql1 = "select boss.name ,boss.head from boss where ID = ?";
        for (CommentBean commentBean : query) {
             List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql1, commentBean.getBossId());
             commentBean.setHead((String)maps.get(0).get("head"));
             commentBean.setName((String)maps.get(0).get("name"));
        }
        return  query;
    }




    public int totalBlogs(String bossId){
        String sql = "select count(*) from Blog where bossId = ?";
        return   jdbcTemplate.queryForObject(sql,Integer.class,bossId);

    }


    public int attentions(String bossId){
        String sql = "select count(*) from AttentionAndFans where bossId = ?";
        return jdbcTemplate.queryForObject(sql,Integer.class,bossId);
    }

    public void addAttention(String bossId,String attention ){
        String sql = "insert into AttentionAndFans value (?,?)";
        jdbcTemplate.update(sql,bossId,attention);
    }
    public int Fans(String bossId){
        String sql = "select count(*) from AttentionAndFans where attention = ?";
        return jdbcTemplate.queryForObject(sql,Integer.class,bossId);
    }

    public void deleteAttention(String bossId, String peopleId) {
        String sql = "delete AttentionAndFans from AttentionAndFans where name = ? and attention = ?";
         jdbcTemplate.update(sql,bossId,peopleId);

    }

    public void modifyHead(String headUri, String bossId) {
        String sql = "update boss set head = ? where ID = ?";
        jdbcTemplate.update(sql,headUri,bossId);
    }


}
