/*    */ package yb.ecp.fast.infra.util;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ 
/*    */ 
/*    */ public class ObjectUtil
/*    */ {
/*    */   public static byte[] serialize(Object object)
/*    */   {
/* 13 */     ObjectOutputStream objectOutputStream = null;
/* 14 */     ByteArrayOutputStream byteArrayOutputStream = null;
/*    */     try {
/* 16 */       byteArrayOutputStream = new ByteArrayOutputStream();
/* 17 */       objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
/* 18 */       objectOutputStream.writeObject(object);
/* 19 */       return byteArrayOutputStream.toByteArray();
/*    */ 
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 24 */       e.printStackTrace();
/*    */     }
/* 26 */     return null;
/*    */   }
/*    */   
/* 29 */   public static Object deserialize(byte[] bytes) { ByteArrayInputStream byteArrayInputStream = null;
/*    */     try {
/* 31 */       byteArrayInputStream = new ByteArrayInputStream(bytes);
/* 32 */       ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
/* 33 */       return objectInputStream.readObject();
/*    */     }
/*    */     catch (Exception e) {
/* 36 */       e.printStackTrace();
/*    */     }
/* 38 */     return null;
/*    */   }
/*    */ }
