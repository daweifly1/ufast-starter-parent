/*    */ package yb.ecp.fast.infra.util;
/*    */ 
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Calendar;
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ public class DateUtils
/*    */ {
/*    */   public static String getLastMonth()
/*    */   {
/* 12 */     Calendar cal = Calendar.getInstance();
/* 13 */     cal.add(2, -1);
/* 14 */     SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
/* 15 */     String lastMonth = dft.format(cal.getTime());
/* 16 */     return lastMonth;
/*    */   }
/*    */   
/*    */   public static Date getYestday()
/*    */   {
/* 21 */     Calendar cal = Calendar.getInstance();
/* 22 */     cal.add(5, -1);
/* 23 */     return cal.getTime();
/*    */   }
/*    */   
/*    */   public static long getCurrentHourSecond() {
/* 27 */     Calendar now = Calendar.getInstance();
/*    */     
/* 29 */     int year = now.get(1);
/* 30 */     int month = now.get(2);
/* 31 */     int day = now.get(5);
/* 32 */     int hour = now.get(11);
/*    */     
/* 34 */     now.set(year, month, day, hour, 0, 0);
/* 35 */     return now.getTimeInMillis() / 1000L;
/*    */   }
/*    */   
/*    */   public static long getCurrentDayMinute()
/*    */   {
/* 40 */     Calendar now = Calendar.getInstance();
/*    */     
/* 42 */     int year = now.get(1);
/* 43 */     int month = now.get(2);
/* 44 */     int day = now.get(5);
/*    */     
/* 46 */     now.set(year, month, day, 0, 0, 0);
/* 47 */     return now.getTimeInMillis() / 1000L / 60L;
/*    */   }
/*    */   
/*    */   public static String secToTime(int time) {
/* 51 */     String timeStr = null;
/* 52 */     int hour = 0;
/* 53 */     int minute = 0;
/* 54 */     int second = 0;
/* 55 */     if (time <= 0) {
/* 56 */       return "00:00";
/*    */     }
/* 58 */     minute = time / 60;
/* 59 */     if (minute < 60) {
/* 60 */       second = time % 60;
/* 61 */       timeStr = unitFormat(minute) + ":" + unitFormat(second);
/*    */     } else {
/* 63 */       hour = minute / 60;
/* 64 */       if (hour > 99)
/* 65 */         return "99:59:59";
/* 66 */       minute %= 60;
/* 67 */       second = time - hour * 3600 - minute * 60;
/* 68 */       timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
/*    */     }
/*    */     
/* 71 */     return timeStr;
/*    */   }
/*    */   
/*    */   public static String unitFormat(int i) {
/* 75 */     String retStr = null;
/* 76 */     if ((i >= 0) && (i < 10)) {
/* 77 */       retStr = "0" + Integer.toString(i);
/*    */     } else
/* 79 */       retStr = "" + i;
/* 80 */     return retStr;
/*    */   }
/*    */ }
