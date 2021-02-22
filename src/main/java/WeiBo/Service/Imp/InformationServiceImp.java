package WeiBo.Service.Imp;

import WeiBo.DAO.InformationDao;
import WeiBo.DAO.druidJDBCUtils;
import WeiBo.Bean.PageBean;
import WeiBo.Bean.InformationBean;
import WeiBo.Service.InformationService;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class InformationServiceImp implements InformationService {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(druidJDBCUtils.getDataSource());


    InformationDao informationDAO = new InformationDao();


    @Override
    public void addInformation(String bossId,String name) {

        informationDAO.addInformation(bossId,name);
    }

    @Override
    public int updateInformation(InformationBean InformationBean) {

        return informationDAO.updateInformation(InformationBean);
    }

    @Override
    public InformationBean findInformation(String bossId) {

        return  informationDAO.findInformation(bossId);
    }









    @Override
    public PageBean<InformationBean> findUserByPage(int currentPage, int rows) {
        PageBean<InformationBean> userPageBean = new PageBean<>();
        userPageBean.setRows(rows);

        int start = (currentPage - 1) * rows;
        List<InformationBean> usersPage = findUsersPage(start, rows);
        userPageBean.setList(usersPage);
        int total = totalUsers();
        userPageBean.setTotal(total);
        int pages = total % rows == 0 ? total / rows : (total / rows) + 1;
        userPageBean.setPages(pages);
        if (currentPage >= pages) {
            currentPage = pages - 1;
        }
        userPageBean.setCurrentPage(currentPage);

        return userPageBean;
    }

    private int totalUsers() {
        String sql = "select count(*) from information";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    private List<InformationBean> findUsersPage(int start, int rows) {
        String sql = "select * from information limit ?,?";
        List<InformationBean> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<InformationBean>(InformationBean.class), start, rows);
        return query;
    }
}
