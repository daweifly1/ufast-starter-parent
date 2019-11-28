/*    */ package yb.ecp.fast.infra.infra;
/*    */ 
/*    */ 
/*    */ public class SearchCommonVO<T>
/*    */ {
/*    */   private int pageNum;
/*    */   
/*    */   private int pageSize;
/*    */   private T filters;
/*    */   private String sort;
/*    */   
/*    */   public String getSort()
/*    */   {
/* 14 */     return this.sort;
/*    */   }
/*    */   
/*    */   public void setSort(String sort)
/*    */   {
/* 19 */     this.sort = sort;
/*    */   }
/*    */   
/*    */   public Integer getPageNum() {
/* 23 */     return Integer.valueOf(this.pageNum);
/*    */   }
/*    */   
/*    */   public void setPageNum(int pageNum) {
/* 27 */     this.pageNum = pageNum;
/*    */   }
/*    */   
/*    */   public Integer getPageSize() {
/* 31 */     return Integer.valueOf(this.pageSize);
/*    */   }
/*    */   
/*    */   public void setPageSize(int pageSize) {
/* 35 */     this.pageSize = pageSize;
/*    */   }
/*    */   
/*    */   public T getFilters() {
/* 39 */     return (T)this.filters;
/*    */   }
/*    */   
/*    */   public void setFilters(T filters) {
/* 43 */     this.filters = filters;
/*    */   }
/*    */ }
