package shop.mtcoding.village.core.jwt;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.google.api.client.util.Value;
import shop.mtcoding.village.model.user.User;


public class MyJwtProvider {

    private static final String SUBJECT = "jwtstudy";
    private static final int EXP = 1000 * 60 * 60 * 24 * 7;
    public static final String TOKEN_PREFIX = "Bearer "; // 스페이스 필요함
    public static final String HEADER = "Authorization";
    private static final String SECRET = System.getenv("HS512_SECRET");

    public static String create(User user) {
        String jwt = JWT.create()
                .withSubject(SUBJECT)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXP))
                .withClaim("id", user.getId())
                .withClaim("role", user.getRole())
                .sign(Algorithm.HMAC512(SECRET));
        return TOKEN_PREFIX + jwt;
    }

    public static DecodedJWT verify(String jwt) throws SignatureVerificationException, TokenExpiredException {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET))
                .build().verify(jwt);
        return decodedJWT;
    }

    public static String createToken() {
        String jwtToken = JWT.create()
                .withSubject(SUBJECT)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXP))
                .sign(Algorithm.HMAC512(SECRET));
        return TOKEN_PREFIX + jwtToken;
    }


}