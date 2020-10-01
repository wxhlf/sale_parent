package entity;
import java.io.Serializable;
import java.util.List;

public class PageResult implements Serializable {

    private long pages;     //总页数
    private List list;      //当前页的记录

    public PageResult(long pages, List list) {
        this.pages = pages;
        this.list = list;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}