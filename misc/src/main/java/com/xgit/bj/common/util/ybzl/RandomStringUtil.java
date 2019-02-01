package com.xgit.bj.common.util.ybzl;

import com.xgit.bj.common.util.security.CryptoUtil;
import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;

@Slf4j
public class RandomStringUtil {
    public static String RandomString(int length) {
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            byte[] bytes = new byte[length / 2];
            random.nextBytes(bytes);
            return CryptoUtil.byteArrayToHexString(bytes);
        } catch (Exception e) {
            log.error(",e");
        }
        return "";
    }
}
