package WeiBo.Service;

import WeiBo.Bean.BossBean;

public interface BossService {
    public BossBean findBoss(BossBean bossBean);
    public int addBoss(BossBean bossBean);
}
