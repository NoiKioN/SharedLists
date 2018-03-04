package com.nnightknights.sharedlists.list.database;

public class QueryData {
    private QueryType queryType;
    private Boolean distinct;
    private String tableName;
    private String[] columnNames;
    private String selection;
    private String[] selectionArguments;
    private String groupBy;
    private String having;
    private String orderBy;
    private String limit;

    public QueryData(){
        queryType = QueryType.TYPE_NORMAL;
        distinct = false;
        tableName = null;
        columnNames = null;
        selection = null;
        selectionArguments = null;
        groupBy = null;
        having = null;
        orderBy = null;
        limit = null;
    }

    public QueryType getQueryType() {
        return queryType;
    }

    public QueryData setQueryType(QueryType queryType) {
        this.queryType = queryType;
        return this;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public QueryData setDistinct(Boolean distinct) {
        this.distinct = distinct;
        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public QueryData setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public QueryData setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
        return this;
    }

    public String getSelection() {
        return selection;
    }

    public QueryData setSelection(String selectedColumnNames) {
        this.selection = selectedColumnNames;
        return this;
    }

    public String[] getSelectionArguments() {
        return selectionArguments;
    }

    public QueryData setSelectionArguments(String... selectionArguments) {
        this.selectionArguments = selectionArguments;
        return this;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public QueryData setGroupBy(String groupBy) {
        this.groupBy = groupBy;
        return this;
    }

    public String getHaving() {
        return having;
    }

    public QueryData setHaving(String having) {
        this.having = having;
        return this;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public QueryData setOrderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public void close(){
        queryType = QueryType.TYPE_NORMAL;
        distinct = false;
        tableName = null;
        columnNames = null;
        selection = null;
        selectionArguments = null;
        groupBy = null;
        having = null;
        orderBy = null;
        limit = null;
    }
}
