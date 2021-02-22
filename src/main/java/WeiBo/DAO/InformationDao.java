package WeiBo.DAO;

import WeiBo.Bean.InformationBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class InformationDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(druidJDBCUtils.getDataSource());

    public  InformationBean findInformation(String bossId) {
            try{
                String sql = "select * from information where bossId = ?";
                //没有找到对应的名字的密码就会抛出异常，不会返回空，所以利用异常来返回一个空
                //查询返回一个Information对象
                InformationBean informationBean = jdbcTemplate.queryForObject(sql,
                        new BeanPropertyRowMapper< InformationBean>( InformationBean.class),bossId);
                return informationBean;
            }catch(DataAccessException e){
                e.printStackTrace();
                return null;
            }
        }


    public void addInformation(String bossId,String name) {
        String sql = "insert into information value(?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,bossId,name,null,null,null,null,null,null,null);
    }


    public int updateInformation(InformationBean InformationBean) {
        String sql = "update information set NAME = ?,SEX = ?,AGE = ?,birth = ?,BIRTHAREA = ?,QQ = ?,MAIL = ?,own = ? where bossId = ?";
         try {
             jdbcTemplate.update(sql, InformationBean.getName(), InformationBean.getSex(),
                     InformationBean.getAge(), InformationBean.getBirth(),InformationBean.getBirthArea(), InformationBean.getQq(), InformationBean.getMail(), InformationBean.getOwn(),
                     InformationBean.getBossId());
             String sql2 = "update boss set name = ? where ID = ?";
             jdbcTemplate.update(sql2,InformationBean.getName(),InformationBean.getBossId());
             return 1;
         }catch (DataAccessException e){
             return 0;
         }

       }


}
