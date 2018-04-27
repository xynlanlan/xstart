package com.example.start.common.base;


import com.example.start.common.exception.ServiceException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.io.Serializable;
import java.util.List;

public class Pager<T> implements Serializable {

    private int pageSize = 10;

    private int pageIndex = 1;

    private int total;

    private int previous;

    private int next;

    private int first;

    private int last;

    private T condition;

    private List<T> result;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPrevious() {
        return previous;
    }

    public void setPrevious(int previous) {
        this.previous = previous;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public T getCondition() {
        return condition;
    }

    public void setCondition(T condition) {
        this.condition = condition;
    }

    public static interface Callback<T> {
        List<T> query(T t) throws ServiceException;
    }

    /**
     * 注意：需要借用Github下的PageHelper工具
     *
     * @param callback
     * @return
     * @throws ServiceException
     */
    public Pager<T> pagingQuery(Callback<T> callback) throws ServiceException {

        PageHelper.startPage(pageIndex, pageSize);

        Page<T> page = (Page<T>) callback.query(this.getCondition());

        int realTotal = (int) page.getTotal();

        initInfo(realTotal);

        setResult(page.getResult());

        return this;
    }

    /**
     * 注意：需要借用Github下的PageHelper工具
     *
     * @param callback
     * @return
     * @throws ServiceException
     */
    public Pager<T> pagingQueryBySearchBean(Callback<? super T> callback) throws ServiceException {

        return pagingQuery((Callback<T>) callback);
    }

    /**
     * 不分页，需要借用Github下的PageHelper工具
     *
     * @param callback
     * @param disabledPaging
     * @return
     * @throws ServiceException
     */
    public Pager<T> pagingQueryBySearchBean(Callback<? super T> callback, boolean disabledPaging) throws ServiceException {

        return pagingQuery((Callback<T>) callback, true);
    }

    /**
     * 不分页，需要借用Github下的PageHelper工具
     *
     * @param callback
     * @return
     * @throws ServiceException
     */
    public Pager<T> pagingQuery(Callback<T> callback, boolean disabledPaging) throws ServiceException {

        if (!disabledPaging) {
            return pagingQuery(callback);
        }

        Integer pageSize = this.getPageSize();

        this.setPageSize(Integer.MAX_VALUE);

        this.pagingQuery(callback);

        this.setPageSize(pageSize);

        return paging();
    }

    /**
     * 手动分页
     *
     * @param total
     * @param callback
     * @return
     * @throws ServiceException
     */
    public Pager<T> pagingQueryBySearchBean(Integer total, Callback<? super T> callback) throws ServiceException {

        return pagingQuery(total, (Callback<T>) callback);
    }

    /**
     * 手动分页
     *
     * @param total
     * @param callback
     * @return
     * @throws ServiceException
     */
    public Pager<T> pagingQuery(Integer total, Callback callback) throws ServiceException {

        initInfo(total);

        if (total == null || total == 0) {
            // 没有任何数据
            return this;
        }

        int startIndex = pageSize * (pageIndex - 1);

        if (this.getCondition() == null) {
            // 正在考虑是否是查询所有
            throw new IllegalArgumentException("user.find.illegalArg");
        }

        List query = callback.query(this.getCondition());

        if (query == null) {
            return this;
        }
        int endIndex = startIndex + pageSize;
        if (endIndex > total) {
            endIndex = total;
        }
        this.setResult(query.subList(startIndex, endIndex));
        return this;
    }


    /**
     * 注意：需要借用Github下的PageHelper工具
     *
     * @return
     * @throws ServiceException
     */
    private Pager<T> paging() throws ServiceException {

        int realTotal = (int) this.getResult().size();
        initInfo(realTotal);

        Integer startIndex = pageSize * (pageIndex - 1);
        Integer endIndex = startIndex + pageSize;
        endIndex = endIndex > total ? total : endIndex;

        setResult(this.getResult().subList(startIndex, endIndex));
        return this;
    }


    private void initInfo(Integer total) {

        this.total = total;

        // 设置页大小
        if (pageSize == 0) {
            // 设置默认
            pageSize = 10;
        }

        // 设置当前页
        if (pageIndex == 0) {
            pageIndex = 1;
        }

        // 设置上一页信息
        if (pageIndex > 1) {
            previous = pageIndex - 1;
        } else {
            previous = 1;
        }

        // 总页数
        last = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;

        // 设置下一次索引
        next = pageIndex + 1;
        if (next > last) {
            next = last;
        }

        // 设置头页索引
        first = 1;
    }
}