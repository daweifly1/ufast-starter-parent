/*     */ package yb.ecp.fast.infra.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.ServerSocket;
/*     */ import java.net.Socket;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class CloseUtils
/*     */ {
/*  31 */   private static final Log logger = LogFactory.getLog(CloseUtils.class);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void close(ResultSet rs)
/*     */   {
/*  42 */     if (rs != null)
/*     */     {
/*     */       try
/*     */       {
/*  46 */         rs.close();
/*     */       }
/*     */       catch (SQLException e) {
/*  49 */         logger.error("close ResultSet failed.", e);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void close(Statement stmt)
/*     */   {
/*  63 */     if (stmt != null)
/*     */     {
/*     */       try
/*     */       {
/*  67 */         stmt.close();
/*     */       }
/*     */       catch (SQLException e) {
/*  70 */         logger.error("close Statement failed.", e);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void close(PreparedStatement stmt)
/*     */   {
/*  84 */     if (stmt != null)
/*     */     {
/*     */       try
/*     */       {
/*  88 */         stmt.close();
/*     */       }
/*     */       catch (SQLException e) {
/*  91 */         logger.error("close PreparedStatement failed.", e);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void close(Connection connection)
/*     */   {
/* 105 */     if (connection != null)
/*     */     {
/*     */       try
/*     */       {
/* 109 */         connection.close();
/*     */       }
/*     */       catch (SQLException e) {
/* 112 */         logger.error("close Connection failed.", e);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void close(AutoCloseable... closes)
/*     */   {
/* 126 */     for (AutoCloseable closeable : closes)
/*     */     {
/* 128 */       if (closeable != null)
/*     */       {
/*     */         try
/*     */         {
/* 132 */           closeable.close();
/*     */         }
/*     */         catch (Exception e) {
/* 135 */           logger.error("close closeable failed.", e);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void close(Socket psocket)
/*     */   {
/* 150 */     if (psocket != null)
/*     */     {
/*     */       try
/*     */       {
/* 154 */         psocket.close();
/*     */       }
/*     */       catch (IOException e) {
/* 157 */         logger.error("close socket failed.", e);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void close(ServerSocket psocket)
/*     */   {
/* 171 */     if (psocket != null)
/*     */     {
/*     */       try
/*     */       {
/* 175 */         psocket.close();
/*     */       }
/*     */       catch (IOException e) {
/* 178 */         logger.error("close socket failed.", e);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void close(Object... objs)
/*     */   {
/* 193 */     for (Object obj : objs)
/*     */     {
/* 195 */       if (obj != null)
/*     */       {
/*     */         try
/*     */         {
/* 199 */           Class<?> objClass = obj.getClass();
/* 200 */           Method meyhod = objClass.getMethod("close", new Class[0]);
/*     */           
/* 202 */           if (null != meyhod)
/*     */           {
/* 204 */             meyhod.invoke(obj, new Object[0]);
/*     */           }
/*     */           else {
/* 207 */             logger.error("This object can not be close, because this object don not have close method.");
/*     */           }
/*     */         }
/*     */         catch (Exception e) {
/* 211 */           logger.error("close Object failed.", e);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }
