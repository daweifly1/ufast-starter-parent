/*    */ package yb.ecp.fast.infra.util;
/*    */ 
/*    */ public class Ref<T>
/*    */ {
/*    */   T value;
/*    */   
/*    */   public Ref(T value)
/*    */   {
/*  9 */     this.value = value;
/*    */   }
/*    */   
/* 12 */   public T get() { return (T)this.value; }
/*    */   
/*    */   public void set(T value) {
/* 15 */     this.value = value;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 20 */     return this.value.toString();
/*    */   }
/*    */ }

