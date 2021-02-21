package WeiBo.Service.Imp;

import WeiBo.DAO.BossDao;
import WeiBo.DAO.druidJDBCUtils;
import WeiBo.Bean.BossBean;
import WeiBo.Service.BossService;
import org.springframework.jdbc.core.JdbcTemplate;

public class bossServiceImp implements BossService {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(druidJDBCUtils.getDataSource());

    BossDao bossDao = new BossDao();

    @Override
    public BossBean findBoss(BossBean bossBean) {

        return bossDao.findBoss(bossBean);
    }


    @Override
    public int addBoss(BossBean bossBean) {
        int i = bossDao.addBoss(bossBean);
        return i;
    }
}




