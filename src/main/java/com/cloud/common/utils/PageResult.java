package com.cloud.common.utils;


import java.util.List;

public class PageResult
{
  private Integer page;
  private Integer pageSize;
  private Long totalPage = Long.valueOf(0L);
  private Long totalRows;
  private List rows;
  private Integer pageNumber;
  private Long total;

  public PageResult()
  {
  }



  public Integer getPage()
  {
    return this.page;
  }

  public void setPage(Integer page) {
    this.page = page;
    this.pageNumber = page;
  }

  public Integer getPageSize() {
    return this.pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public Long getTotalPage() {
    if (getPageSize() != null) {
      return Long.valueOf((getTotalRows().longValue() + getPageSize().intValue() - 1L) / getPageSize().intValue());
    }
    return this.totalPage;
  }

  public Long getTotalRows()
  {
    return this.totalRows;
  }

  public void setTotalRows(Long totalRows) {
    this.totalRows = totalRows;
    this.total = totalRows;
  }

  public void setTotalPage(Long totalPage) {
    this.totalPage = totalPage;
  }

  public List getRows()
  {
    return this.rows;
  }

  public void setRows(List rows)
  {
    this.rows = rows;
  }

  public Integer getPageNumber() {
    return this.pageNumber;
  }

  public void setPageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
    this.page = pageNumber;
  }

  public Long getTotal() {
    return this.total;
  }

  public void setTotal(Long total) {
    this.total = total;
    this.totalRows = total;
  }

  public String toString()
  {
    return "PageResult{当前页page=" + this.page + ", 总行数totalRows=" + this.totalRows + ", 页宽度pageSize=" + this.pageSize + ", 总页数totalPage=" + this.totalPage + ", 当前页pageNumber=" + this.pageNumber + ", 总行数total=" + this.total + '}';
  }
}