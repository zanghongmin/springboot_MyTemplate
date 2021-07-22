package top.zang.util;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import top.zang.config.token.MyToken;
import top.zang.core.MyException;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;


/**
 * jwt工具类
 */
public class MyJwtUtil {

    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    private static final String DEFAULT_JWT_ISSUER = "zangSpringboot";

    private static final String DEFAULT_SECRETE = "&SN23mhjauGFDSTcsssssz!ddddxxxx";

    /**
     * 创建jwt
     * @param myToken
     * @return
     */
    public static String createJwt(MyToken myToken) {
        Date now = new Date(System.currentTimeMillis());
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(DEFAULT_SECRETE);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder()
                .setSubject(MyJsonUtil.toJSONString(myToken))
                .setIssuedAt(new Date())
                .setIssuer(DEFAULT_JWT_ISSUER)
                .signWith(signatureAlgorithm, signingKey);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 30);//30天过期
        builder.setExpiration(calendar.getTime());
        return builder.compact();
    }

    /**
     * 解析jwt
     * @param jwt
     * @return
     */
    public static MyToken parseJwt(String jwt){
        if (StrUtil.isEmpty(jwt)) {
            throw new MyException("Token不能为空");
        }
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(DEFAULT_SECRETE))
                .parseClaimsJws(jwt).getBody();
        String tokenStr = claims.getSubject();
        return MyJsonUtil.parseObject(tokenStr, MyToken.class);
    }

}
