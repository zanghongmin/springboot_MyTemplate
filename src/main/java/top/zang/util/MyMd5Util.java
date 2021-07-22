package top.zang.util;

import cn.hutool.crypto.SecureUtil;
import top.zang.core.Constant;

public class MyMd5Util {
    public static void main(String[] args) {
        System.out.println(md5("zang"));
    }

    public static String md5(String str) {
        String md5Str = SecureUtil.md5(SecureUtil.md5(str + Constant.pwdSalt));
        return md5Str;
    }
}
