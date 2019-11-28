/*    */ package yb.ecp.fast.infra.helper;
/*    */ 
/*    */ import com.fasterxml.jackson.core.JsonGenerator;
/*    */ import com.fasterxml.jackson.core.JsonProcessingException;
/*    */ import com.fasterxml.jackson.databind.JsonSerializer;
/*    */ import com.fasterxml.jackson.databind.SerializerProvider;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class LongJsonSerializer
/*    */   extends JsonSerializer<Long>
/*    */ {
/*    */   public void serialize(Long value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
/*    */     throws IOException, JsonProcessingException
/*    */   {
/* 15 */     String text = value == null ? null : String.valueOf(value);
/* 16 */     if (text != null) {
/* 17 */       jsonGenerator.writeString(text);
/*    */     }
/*    */   }
/*    */ }
