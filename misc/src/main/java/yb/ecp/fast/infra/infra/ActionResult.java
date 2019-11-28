/*    */ package yb.ecp.fast.infra.infra;
/*    */ 
/*    */ import yb.ecp.fast.infra.constants.ErrorCode;
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
/*    */ 
/*    */ public class ActionResult<T>
/*    */ {
/*    */   protected int code;
/*    */   protected String message;
/*    */   protected T value;
/*    */   
/*    */   public ActionResult()
/*    */   {
/* 24 */     this.code = 0;
/* 25 */     this.message = "成功";
/*    */   }
/*    */   
/*    */   public ActionResult(T value) {
/* 29 */     this.code = 0;
/* 30 */     this.message = "成功";
/* 31 */     this.value = value;
/*    */   }
/*    */   
/*    */ 
/*    */   public ActionResult(int code, String message, T value)
/*    */   {
/* 37 */     this.code = code;
/* 38 */     this.message = message;
/* 39 */     this.value = value;
/*    */   }
/*    */   
/*    */   public ActionResult(int code, String message) {
/* 43 */     this.code = code;
/* 44 */     this.message = message;
/* 45 */     this.value = null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public ActionResult(ErrorCode errorCode)
/*    */   {
/* 52 */     this.code = errorCode.getCode();
/* 53 */     this.message = errorCode.getDesc();
/* 54 */     this.value = null;
/*    */   }
/*    */   
/*    */   public ActionResult(ErrorCode errorCode, T value) {
/* 58 */     this.code = errorCode.getCode();
/* 59 */     this.message = errorCode.getDesc();
/* 60 */     this.value = value;
/*    */   }
/*    */   
/*    */ 
/*    */   public int getCode()
/*    */   {
/* 66 */     return this.code;
/*    */   }
/*    */   
/*    */   public void setCode(int code)
/*    */   {
/* 71 */     this.code = code;
/*    */   }
/*    */   
/*    */   public String getMessage()
/*    */   {
/* 76 */     return this.message;
/*    */   }
/*    */   
/*    */   public void setMessage(String message)
/*    */   {
/* 81 */     this.message = message;
/*    */   }
/*    */   
/*    */   public T getValue()
/*    */   {
/* 86 */     return (T)this.value;
/*    */   }
/*    */   
/*    */   public void setValue(T value)
/*    */   {
/* 91 */     this.value = value;
/*    */   }
/*    */ }
