package top.zang.core;

public class RedisKey {

    //登录用户token缓存key
    public static String getLoginKey(Long userid , String userSource){
        return "token:"+userSource+":"+userid;
    }

}
