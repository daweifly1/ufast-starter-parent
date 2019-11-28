/*    */ package yb.ecp.fast.infra.constants;
/*    */ 
/*    */ public enum AccessLabel
/*    */ {
/*  5 */   DenyAll("最低权限", 0), 
/*  6 */   Client("客户端访问", 1), 
/*  7 */   Oauth2("Oauth2 服务器访问", 2), 
/*  8 */   Inner("内部服务调用", 3), 
/*    */   
/* 10 */   FullTrusted("信任域访问", 100);
/*    */   
/*    */   private String name;
/*    */   private int levelNum;
/*    */   
/*    */   private AccessLabel(String name, int levelNum)
/*    */   {
/* 17 */     this.name = name;
/* 18 */     this.levelNum = levelNum;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 22 */     return this.name;
/*    */   }
/*    */   
/* 25 */   public void setName(String name) { this.name = name; }
/*    */   
/*    */   public int getLevelNum() {
/* 28 */     return this.levelNum;
/*    */   }
/*    */   
/* 31 */   public void setLevelNum(int levelNum) { this.levelNum = levelNum; }
/*    */ }
