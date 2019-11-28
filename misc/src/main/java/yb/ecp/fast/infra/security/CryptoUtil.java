/*     */ package yb.ecp.fast.infra.security;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.math.BigInteger;
/*     */ import java.security.Key;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.SecureRandom;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.KeyGenerator;
/*     */ import javax.crypto.Mac;
/*     */ import javax.crypto.SecretKey;
/*     */ import javax.crypto.SecretKeyFactory;
/*     */ import javax.crypto.spec.DESKeySpec;
/*     */ import javax.crypto.spec.SecretKeySpec;
/*     */ import sun.misc.BASE64Decoder;
/*     */ import sun.misc.BASE64Encoder;
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
/*     */ public class CryptoUtil
/*     */ {
/*     */   private static final String KEY_MD5 = "MD5";
/*     */   private static final String KEY_SHA = "SHA";
/*     */   public static final String KEY_MAC = "HmacSHA1";
/*     */   public static final String KEY_MAC_256 = "HmacSHA256";
/*     */   public static final String ALGORITHM = "AES";
/*     */   
/*     */   public static byte[] decryptBASE64(String key)
/*     */     throws Exception
/*     */   {
/*  44 */     return new BASE64Decoder().decodeBuffer(key);
/*     */   }
/*     */   
/*     */   public static String decryptBASE642String(String key) throws Exception {
/*  48 */     return new String(new BASE64Decoder().decodeBuffer(key));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String encryptBASE64(byte[] key)
/*     */     throws Exception
/*     */   {
/*  59 */     return new BASE64Encoder().encodeBuffer(key);
/*     */   }
/*     */   
/*     */   public static String encryptBASE64String(String key) throws Exception {
/*  63 */     return new BASE64Encoder().encodeBuffer(key.getBytes());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static byte[] encryptMD5(byte[] data)
/*     */     throws Exception
/*     */   {
/*  75 */     MessageDigest md5 = MessageDigest.getInstance("MD5");
/*  76 */     md5.update(data);
/*     */     
/*  78 */     return md5.digest();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static byte[] encryptSHA(byte[] data)
/*     */     throws Exception
/*     */   {
/*  90 */     MessageDigest sha = MessageDigest.getInstance("SHA");
/*  91 */     sha.update(data);
/*     */     
/*  93 */     return sha.digest();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String initMacKey()
/*     */     throws Exception
/*     */   {
/* 103 */     KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA1");
/* 104 */     SecretKey secretKey = keyGenerator.generateKey();
/* 105 */     return encryptBASE64(secretKey.getEncoded());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static byte[] encryptHMAC(byte[] data, String key)
/*     */     throws Exception
/*     */   {
/* 117 */     SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), "HmacSHA1");
/* 118 */     Mac mac = Mac.getInstance(secretKey.getAlgorithm());
/* 119 */     mac.init(secretKey);
/* 120 */     return mac.doFinal(data);
/*     */   }
/*     */   
/*     */   public static String encryptHMAC(String text, String key) throws Exception {
/* 124 */     SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), "HmacSHA1");
/* 125 */     Mac mac = Mac.getInstance(secretKey.getAlgorithm());
/* 126 */     mac.init(secretKey);
/* 127 */     byte[] cippher = mac.doFinal(text.getBytes("UTF-8"));
/* 128 */     return encryptBASE64(cippher);
/*     */   }
/*     */   
/*     */   public static String byteArrayToHexString(byte[] b) {
/* 132 */     StringBuilder hs = new StringBuilder();
/*     */     
/* 134 */     for (int n = 0; (b != null) && (n < b.length); n++) {
/* 135 */       String stmp = Integer.toHexString(b[n] & 0xFF);
/* 136 */       if (stmp.length() == 1)
/* 137 */         hs.append('0');
/* 138 */       hs.append(stmp);
/*     */     }
/* 140 */     return hs.toString().toLowerCase();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String encryptHMACSha256(String message, String secret)
/*     */     throws Exception
/*     */   {
/* 149 */     String hash = "";
/* 150 */     Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
/* 151 */     SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
/* 152 */     sha256_HMAC.init(secret_key);
/* 153 */     byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
/* 154 */     hash = byteArrayToHexString(bytes);
/* 155 */     return hash;
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
/*     */   private static Key toKey(byte[] key)
/*     */     throws Exception
/*     */   {
/* 180 */     SecretKey secretKey = null;
/* 181 */     if (("AES".equals("DES")) || ("AES".equals("DESede"))) {
/* 182 */       DESKeySpec dks = new DESKeySpec(key);
/* 183 */       SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("AES");
/* 184 */       secretKey = keyFactory.generateSecret(dks);
/*     */     }
/*     */     else {
/* 187 */       secretKey = new SecretKeySpec(key, "AES");
/*     */     }
/* 189 */     return secretKey;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static byte[] decrypt(byte[] data, String key)
/*     */     throws Exception
/*     */   {
/* 201 */     Key k = toKey(decryptBASE64(key));
/* 202 */     Cipher cipher = Cipher.getInstance("AES");
/* 203 */     cipher.init(2, k);
/* 204 */     return cipher.doFinal(data);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static byte[] encrypt(byte[] data, String key)
/*     */     throws Exception
/*     */   {
/* 216 */     Key k = toKey(decryptBASE64(key));
/* 217 */     Cipher cipher = Cipher.getInstance("AES");
/* 218 */     cipher.init(1, k);
/* 219 */     return cipher.doFinal(data);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String initKey()
/*     */     throws Exception
/*     */   {
/* 229 */     return initKey(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String initKey(String seed)
/*     */     throws Exception
/*     */   {
/* 240 */     SecureRandom secureRandom = null;
/* 241 */     if (seed != null) {
/* 242 */       secureRandom = new SecureRandom(decryptBASE64(seed));
/*     */     } else {
/* 244 */       secureRandom = new SecureRandom();
/*     */     }
/* 246 */     KeyGenerator kg = KeyGenerator.getInstance("AES");
/* 247 */     kg.init(secureRandom);
/* 248 */     SecretKey secretKey = kg.generateKey();
/* 249 */     return encryptBASE64(secretKey.getEncoded());
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/*     */     try {
/* 254 */       String s = "这是一个伟大的开端";
/* 255 */       String b = encryptBASE64(s.getBytes("UTF-8"));
/* 256 */       System.out.println("BASE64加密后:" + b);
/* 257 */       byte[] c = decryptBASE64(b);
/* 258 */       System.out.println("BASE64解密后:" + new String(c, "UTF-8"));
/*     */       
/* 260 */       c = encryptMD5(s.getBytes());
/* 261 */       System.out.println("MD5   加密后:" + new BigInteger(c).toString(16));
/*     */       
/* 263 */       c = encryptSHA(s.getBytes());
/* 264 */       System.out.println("SHA   加密后:" + new BigInteger(c).toString(16));
/*     */       
/* 266 */       String key = initMacKey();
/* 267 */       System.out.println("HMAC密匙:" + key);
/* 268 */       c = encryptHMAC(s.getBytes(), key);
/* 269 */       System.out.println("HMAC  加密后:" + new BigInteger(c).toString(16));
/*     */       
/* 271 */       key = initKey();
/* 272 */       System.out.println("AES密钥:\t" + key);
/* 273 */       c = encrypt(s.getBytes("UTF-8"), key);
/* 274 */       System.out.println("AES   加密后:" + new BigInteger(c).toString(16));
/* 275 */       c = decrypt(c, key);
/* 276 */       System.out.println("AES   解密后:" + new String(c, "UTF-8"));
/*     */     }
/*     */     catch (UnsupportedEncodingException e) {
/* 279 */       e.printStackTrace();
/*     */     }
/*     */     catch (Exception e) {
/* 282 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

