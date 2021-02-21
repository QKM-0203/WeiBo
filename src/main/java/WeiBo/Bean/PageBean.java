package WeiBo.Bean;

import java.util.List;

public class PageBean<T>{
    private int total;  //总记录数
    private int pages;   //有多少页
    private int currentPage; //当前页码
    private int rows;   //每页显示记录数
    private List<T> list;  //每页的数据

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "total=" + total +
                ", pages=" + pages +
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                ", list=" + list +
                '}';
    }
}
