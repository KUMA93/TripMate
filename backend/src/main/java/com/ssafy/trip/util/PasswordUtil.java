package com.ssafy.trip.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
	
    // 비밀번호 암호화
    public static String encryptPassword(String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashedPassword;
    }

    // 비밀번호 복호화
    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

}