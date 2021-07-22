package top.zang.core;

//全局常量
public class Constant {
    //MD5密码盐
    public final static String pwdSalt = "test123456";
    //普通key在redis的缓存时间5万秒
    public final static long commonTime = 50000;
    //登录token有效期
    public final static long tokenTime = 30*86400l;
    //分页列表-不用分页时，最大返回数量
    public final static int maxPagesize = 9999;
    //设置为空时字符串 []  ， 统一使用json格式存储
    public final static String ids_null = "[]";
}
