package com.wsx.tmall.util;

/**
 * Created by frank on 2018/4/25.
 */
public class Page {
    // 开始页数
    private int start;
    // 每页显示个数
    private int count;
    // 总个数
    private int total;
    // 参数
    private String param;
    // 默认每页显示条数
    private static final int defaultCount = 5;

    // 判断是否有上一页
    public boolean isHasPrevious() {
        if (start == 0)
            return false;
        return true;
    }

    // 判断是否有下一页
    public boolean isHasNext() {
        if (start == getLast())
            return false;
        return true;
    }

    // 获取总页数
    public int getTotalPage() {
        int totalPage;
        if (total % count == 0)
            totalPage = total / count;
        else
            totalPage = total / count + 1;
        if (totalPage == 0)
            totalPage = 1;
        return totalPage;
    }

    // 获取最后一页开始
    public int getLast() {
        int last;
        if (total % count == 0)
            last = total - count;
        else
            last = total - total % count;
        last = last < 0 ? 0 : last;
        return last;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "Page{" +
                "start=" + start +
                ", count=" + count +
                ", total=" + total +
                ", param='" + param + '\'' +
                '}';
    }

    public Page(int start, int count) {
        this();
        this.start = start;
        this.count = count;
    }

    public Page() {
        count = defaultCount;
    }
}
