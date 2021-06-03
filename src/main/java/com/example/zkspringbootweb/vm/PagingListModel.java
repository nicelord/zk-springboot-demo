package com.example.zkspringbootweb.vm;

import io.ebean.ExpressionList;
import org.zkoss.zul.AbstractListModel;
import org.zkoss.zul.FieldComparator;
import org.zkoss.zul.event.ListDataEvent;
import org.zkoss.zul.ext.Sortable;

import java.util.Comparator;
import java.util.List;

public class PagingListModel<T> extends AbstractListModel<T> implements Sortable<T> {

    private final int pageSize;
    private int totalSize; //the actual number of the data, not the cached page size
    private List<T> cachedPage;
    private int currentPageIndex;
    private final PagingDataProvider<T> dataProvider;


    public PagingListModel(int pageSize, PagingDataProvider<T> dataProvider) {
        if (pageSize <= 0)
            throw new IllegalArgumentException("page size should be positive integer");
        this.pageSize = pageSize;
        totalSize = dataProvider.expr().findCount();
        cachedPage = dataProvider.expr().setFirstRow(0)
                .setMaxRows(pageSize)
                .findPagedList()
                .getList();
        this.dataProvider = dataProvider;
    }


    @Override
    public T getElementAt(int index) {

        int offset = index % pageSize; //offset index in a page
        int targetPageIndex = index / pageSize;
        int firstRow = targetPageIndex * pageSize;

        if (currentPageIndex != targetPageIndex) {
            cachedPage = dataProvider.expr()
                    .setMaxRows(pageSize)
                    .setFirstRow(firstRow)
                    .findPagedList()
                    .getList();
            currentPageIndex = targetPageIndex;
        }

        return cachedPage.get(offset);
    }

    @Override
    public int getSize() {
        return totalSize;
    }

    @Override
    public void sort(Comparator<T> cmpr, boolean ascending) {
        if (cmpr instanceof FieldComparator) {
            String rawOrderBy = ((FieldComparator) cmpr).getOrderBy();
            cachedPage = dataProvider.expr()
                    .setMaxRows(pageSize)
                    .setFirstRow(currentPageIndex * pageSize)
                    .order(rawOrderBy)
                    .findPagedList()
                    .getList();
        }
        fireEvent(ListDataEvent.STRUCTURE_CHANGED, -1, -1);
    }

    @Override
    public String getSortDirection(Comparator<T> cmpr) {
        return "ascending";
    }

    interface PagingDataProvider<T> {
        ExpressionList<T> expr();
    }
}
