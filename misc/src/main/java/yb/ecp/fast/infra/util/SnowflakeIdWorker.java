/*     */ package yb.ecp.fast.infra.util;
/*     */ 
/*     */ import yb.ecp.fast.infra.infra.log.LogHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SnowflakeIdWorker
/*     */ {
/*  12 */   private final long twepoch = 1483200000000L;
/*     */   
/*     */ 
/*  15 */   private final long workerIdBits = 5L;
/*     */   
/*     */ 
/*  18 */   private final long datacenterIdBits = 5L;
/*     */   
/*     */ 
/*  21 */   private final long maxWorkerId = 31L;
/*     */   
/*     */ 
/*  24 */   private final long maxDatacenterId = 31L;
/*     */   
/*     */ 
/*  27 */   private final long sequenceBits = 12L;
/*     */   
/*     */ 
/*  30 */   private final long workerIdShift = 12L;
/*     */   
/*     */ 
/*  33 */   private final long datacenterIdShift = 17L;
/*     */   
/*     */ 
/*  36 */   private final long timestampLeftShift = 22L;
/*     */   
/*     */ 
/*  39 */   private final long sequenceMask = 4095L;
/*     */   
/*     */ 
/*     */   private long workerId;
/*     */   
/*     */ 
/*     */   private long datacenterId;
/*     */   
/*     */ 
/*  48 */   private long sequence = 0L;
/*     */   
/*     */ 
/*  51 */   private long lastTimestamp = -1L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SnowflakeIdWorker(long workerId, long datacenterId)
/*     */   {
/*  59 */     if ((workerId > 31L) || (workerId < 0L)) {
/*  60 */       throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", new Object[] { Long.valueOf(31L) }));
/*     */     }
/*  62 */     if ((datacenterId > 31L) || (datacenterId < 0L)) {
/*  63 */       throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", new Object[] { Long.valueOf(31L) }));
/*     */     }
/*  65 */     this.workerId = workerId;
/*  66 */     this.datacenterId = datacenterId;
/*  67 */     LogHelper.monitor("======Snow falke id worker is initilized ====");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized long nextId()
/*     */   {
/*  75 */     long timestamp = timeGen();
/*     */     
/*     */ 
/*  78 */     if (timestamp < this.lastTimestamp)
/*     */     {
/*  80 */       throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", new Object[] {Long.valueOf(this.lastTimestamp - timestamp) }));
/*     */     }
/*     */     
/*     */ 
/*  84 */     if (this.lastTimestamp == timestamp) {
/*  85 */       this.sequence = (this.sequence + 1L & 0xFFF);
/*     */       
/*  87 */       if (this.sequence == 0L)
/*     */       {
/*  89 */         timestamp = tilNextMillis(this.lastTimestamp);
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  94 */       this.sequence = 0L;
/*     */     }
/*     */     
/*     */ 
/*  98 */     this.lastTimestamp = timestamp;
/*     */     
/*     */ 
/* 101 */     return timestamp - 1483200000000L << 22 | this.datacenterId << 17 | this.workerId << 12 | this.sequence;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected long tilNextMillis(long lastTimestamp)
/*     */   {
/* 113 */     long timestamp = timeGen();
/* 114 */     while (timestamp <= lastTimestamp) {
/* 115 */       timestamp = timeGen();
/*     */     }
/* 117 */     return timestamp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected long timeGen()
/*     */   {
/* 125 */     return System.currentTimeMillis();
/*     */   }
/*     */ }

