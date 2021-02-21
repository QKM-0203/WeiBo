package WeiBo.Service;

import WeiBo.Bean.PageBean;
import WeiBo.Bean.InformationBean;

public interface InformationService {
    public void addInformation(String bossId);
    public InformationBean findInformation(String bossId);
    public void updateInformation(InformationBean InformationBean);
    public PageBean<InformationBean> findUserByPage(int currentPage, int rows) ;
}
