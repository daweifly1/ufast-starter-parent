/*    */ package yb.ecp.fast.infra.infra;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Page
/*    */ {
/*    */   protected int curPage;
/*    */   
/*    */ 
/*    */ 
/*    */   protected int pageSize;
/*    */   
/*    */ 
/*    */ 
/*    */   private int totalRows;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public Page() {}
/*    */   
/*    */ 
/*    */ 
/*    */   public Page(int curPage, int pageSize, int totalRows)
/*    */   {
/* 27 */     this.curPage = curPage;
/* 28 */     this.pageSize = pageSize;
/* 29 */     this.totalRows = totalRows;
/*    */   }
/*    */   
/*    */   public int getCurPage()
/*    */   {
/* 34 */     return this.curPage;
/*    */   }
/*    */   
/*    */   public void setCurPage(int curPage)
/*    */   {
/* 39 */     this.curPage = curPage;
/*    */   }
/*    */   
/*    */   public void setCurPage(int curPage, int pageSize)
/*    */   {
/* 44 */     this.curPage = ((curPage - 1) * pageSize);
/*    */   }
/*    */   
/*    */   public int getPageSize()
/*    */   {
/* 49 */     return this.pageSize;
/*    */   }
/*    */   
/*    */   public void setPageSize(int pageSize)
/*    */   {
/* 54 */     this.pageSize = pageSize;
/*    */   }
/*    */   
/*    */   public void setPageSize(int pageSize, int curPage)
/*    */   {
/* 59 */     this.pageSize = (pageSize * curPage);
/*    */   }
/*    */   
/*    */   public int getTotalRows()
/*    */   {
/* 64 */     return this.totalRows;
/*    */   }
/*    */   
/*    */   public void setTotalRows(int totalRows)
/*    */   {
/* 69 */     this.totalRows = totalRows;
/*    */   }
/*    */ }
