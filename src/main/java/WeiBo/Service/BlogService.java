package WeiBo.Service;

import WeiBo.Bean.BlogBean;

import java.util.List;

public interface BlogService {
    public List<BlogBean> findBlogsByBoosId(String bossId);
    public List<BlogBean> findBlogs();
    public void addBlog(BlogBean blogBean);
    public void deleteBlog(String bossId,String creatAtAndName);
    public void updateBlog(BlogBean blogBean);
    public int totalBlogs(String bossId);
    public int attentions(String bossId);
    public int Fans(String bossId);
    public void addAttentions(String bossId,String peopleId);
    public void deleteAttentions(String BossId, String peopleId);
    public void modifyHead(String headUri, String bossId);
}

