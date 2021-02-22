package WeiBo.DAO;

import WeiBo.Bean.BossBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class BossDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(druidJDBCUtils.getDataSource());

    public BossBean findBoss(BossBean bossBean) {
        try {
            String sql = "select * from boss where ID = ? and password = ?";
            //没有找到对应的名字的密码就会抛出异常，不会返回空，所以利用异常来返回一个空
            //查询返回一个Boss对象
            BossBean bossBean1 = jdbcTemplate.queryForObject(sql,
                    new BeanPropertyRowMapper<BossBean>(BossBean.class), bossBean.getId(), bossBean.getPassword());
            return bossBean1;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int addBoss(BossBean bossBean) {
        String sql = "insert into boss value(?,?,?)";
        try{
            jdbcTemplate.update(sql,bossBean.getId(), bossBean.getPassword());
        }catch(Exception e){
            return 0;
        }
        return 1;
    }
}
