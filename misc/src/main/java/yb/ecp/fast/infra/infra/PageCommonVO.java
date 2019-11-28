/*    */ package yb.ecp.fast.infra.infra;
/*    */ 
/*    */ import com.github.pagehelper.PageInfo;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PageCommonVO<T>
/*    */ {
/*    */   private PageInfo pageInfo;
/*    */   
/*    */   public void setPageInfo(PageInfo pageInfo)
/*    */   {
/* 22 */     this.pageInfo = pageInfo;
/*    */   }
/*    */   
/*    */   public PageInfo getPageInfo() {
/* 26 */     return this.pageInfo;
/*    */   }
/*    */   
/*    */   public void setPageInfoList(List<T> list) {
/* 30 */     this.pageInfo.setList(list);
/*    */   }
/*    */   
/*    */   public List<T> getPageInfoList() {
/* 34 */     return this.pageInfo.getList();
/*    */   }
/*    */ }
