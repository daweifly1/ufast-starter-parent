/*    */ package yb.ecp.fast.infra.infra;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PageActionResult<T>
/*    */   extends ActionResult<T>
/*    */ {
/*    */   private Page page;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public PageActionResult() {}
/*    */   
/*    */ 
/*    */ 
/*    */   public PageActionResult(int code, String message, T value, Page page)
/*    */   {
/* 20 */     this.code = code;
/* 21 */     this.message = message;
/* 22 */     this.value = value;
/* 23 */     this.page = page;
/*    */   }
/*    */   
/*    */   public Page getPage()
/*    */   {
/* 28 */     return this.page;
/*    */   }
/*    */   
/*    */   public void setPage(Page page)
/*    */   {
/* 33 */     this.page = page;
/*    */   }
/*    */ }
