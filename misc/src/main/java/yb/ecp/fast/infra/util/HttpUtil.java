/*    */ package yb.ecp.fast.infra.util;
/*    */ 
/*    */ import com.fasterxml.jackson.databind.ObjectMapper;
/*    */ import java.io.PrintWriter;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import yb.ecp.fast.infra.infra.log.LogHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HttpUtil
/*    */ {
/*    */   public static <T> boolean addJsonBodyToResponse(HttpServletResponse response, T value)
/*    */   {
/*    */     try
/*    */     {
/* 16 */       ObjectMapper objectMapper = new ObjectMapper();
/* 17 */       String json = objectMapper.writeValueAsString(value);
/* 18 */       response.addHeader("content-type", "application/json;charset=utf-8");
/* 19 */       response.setCharacterEncoding("UTF-8");
/* 20 */       PrintWriter printWriter = response.getWriter();
/* 21 */       printWriter.write(json);
/* 22 */       printWriter.flush();
/* 23 */       printWriter.close();
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 27 */       LogHelper.fatal(e.getMessage(), e);
/* 28 */       return false;
/*    */     }
/* 30 */     return true;
/*    */   }
/*    */ }