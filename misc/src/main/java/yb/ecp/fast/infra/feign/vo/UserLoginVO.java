/*    */ package yb.ecp.fast.infra.feign.vo;
/*    */ 
/*    */ 
/*    */ public class UserLoginVO
/*    */ {
/*    */   private String loginName;
/*    */   
/*    */   private String password;
/*    */   private String code;
/*    */   private String authId;
/*    */   
/*    */   public String getLoginName()
/*    */   {
/* 14 */     return this.loginName;
/*    */   }
/*    */   
/*    */   public void setLoginName(String loginName) {
/* 18 */     this.loginName = loginName;
/*    */   }
/*    */   
/*    */   public String getPassword() {
/* 22 */     return this.password;
/*    */   }
/*    */   
/*    */   public void setPassword(String password) {
/* 26 */     this.password = password;
/*    */   }
/*    */   
/*    */   public String getCode() {
/* 30 */     return this.code;
/*    */   }
/*    */   
/*    */   public void setCode(String code) {
/* 34 */     this.code = code;
/*    */   }
/*    */   
/*    */   public String getAuthId() {
/* 38 */     return this.authId;
/*    */   }
/*    */   
/*    */   public void setAuthId(String authId) {
/* 42 */     this.authId = authId;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 47 */     return "UserLoginVO{loginName='" + this.loginName + '\'' + ", pass='" + this.password + '\'' + ", code='" + this.code + '\'' + '}';
/*    */   }
/*    */ }
