/*    */ package yb.ecp.fast.infra.helper;
/*    */ 
/*    */ import com.fasterxml.jackson.core.JsonParser;
/*    */ import com.fasterxml.jackson.core.JsonProcessingException;
/*    */ import com.fasterxml.jackson.databind.DeserializationContext;
/*    */ import com.fasterxml.jackson.databind.JsonDeserializer;
/*    */ import java.io.IOException;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class LongJsonDeserializer extends JsonDeserializer<Long>
/*    */ {
/* 13 */   private static final Logger logger = LoggerFactory.getLogger(LongJsonDeserializer.class);
/*    */   
/*    */   public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException
/*    */   {
/* 17 */     String value = jsonParser.getText();
/*    */     try {
/* 19 */       return value == null ? null : Long.valueOf(Long.parseLong(value));
/*    */     } catch (NumberFormatException e) {
/* 21 */       logger.error("解析长整形错误", e); }
/* 22 */     return null;
/*    */   }
/*    */ }

