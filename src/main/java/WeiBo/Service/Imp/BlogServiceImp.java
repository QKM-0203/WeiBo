package WeiBo.Service.Imp;

import WeiBo.Bean.BlogBean;
import WeiBo.DAO.BlogDao;
import WeiBo.Service.BlogService;

import java.util.List;

public class BlogServiceImp implements BlogService {
    BlogDao blogDao = new BlogDao();

    @Override
    public List<BlogBean> findBlogsByBoosId(String bossId) {
        return blogDao.findBlogsByBoosId(bossId) ;
    }

    @Override
    public List<BlogBean> findBlogs() {
        return blogDao.findBlogs();
    }

    @Override
    public void addBlog(BlogBean blogBean) {
        blogDao.addBlog(blogBean);
    }

    @Override
    public void deleteBlog(String bossId,String creatAtAndName) {
        blogDao.deleteBlog(bossId,creatAtAndName);
    }

    @Override
    public void updateBlog(BlogBean blogBean) {
       blogDao.updateBlog(blogBean);
    }
    @Override
    public int totalBlogs(String bossId){
        return blogDao.totalBlogs(bossId);
    }

    @Override
    public int attentions(String bossId){
        return  blogDao.attentions(bossId);
    }

    @Override
    public int Fans(String bossId){
        return  blogDao.Fans(bossId);
    }

    @Override
    public void addAttentions(String bossId,String peopleId){
         blogDao.addAttention(bossId,peopleId);
    }

    @Override
    public void deleteAttentions(String bossId, String peopleId) {
           blogDao.deleteAttention(bossId,peopleId);
    }
}
