/*    */ package yb.ecp.fast.infra.constants;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum ErrorCode
/*    */ {
/*  8 */   Success(0, "操作成功"), 
/*  9 */   Failure(1, "操作失败"), 
/* 10 */   NeedLogin(2, "用户没有登录"), 
/* 11 */   NoAuthorization(3, "没有权限执行此操作"), 
/* 12 */   AccessDenied(4, "没有权限访问此接口"), 
/* 13 */   UnExceptedError(4, "未知的错误发生"), 
/* 14 */   IllegalArument(5, "参数错误"), 
/*    */   
/*    */ 
/*    */ 
/*    */ 
/* 19 */   YourErrorCodeGoesHere(1000, "你的失败码请在后面定义"), 
/*    */   
/*    */ 
/*    */ 
/*    */ 
/* 24 */   OAuthUnAuthorized(1116, "用户未授权"), 
/* 25 */   OAuthTokenIsNull(1102, "缺少accessToken参数"), 
/* 26 */   OAuthTokenInvalid(1103, "无效的accessToken");
/*    */   
/*    */ 
/*    */   private String desc;
/*    */   
/*    */   private int code;
/*    */   
/*    */ 
/*    */   private ErrorCode(int code, String desc)
/*    */   {
/* 36 */     this.desc = desc;
/* 37 */     this.code = code;
/*    */   }
/*    */   
/*    */   public String getDesc()
/*    */   {
/* 42 */     return this.desc;
/*    */   }
/*    */   
/*    */   public int getCode()
/*    */   {
/* 47 */     return this.code;
/*    */   }
/*    */ }