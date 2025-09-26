package vn.BAITAP.TestAPI.domain.dto;

public class ObjectPaginate<T> {
    private int current;
    private int limit;
    private int pages;
    private int sumObj;

    private T data;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getSumObj() {
        return sumObj;
    }

    public void setSumObj(int sumObj) {
        this.sumObj = sumObj;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
