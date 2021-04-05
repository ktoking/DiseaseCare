package com.fehead.diseaseCare.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fehead.diseaseCare.entities.User;
import com.fehead.diseaseCare.entities.model.UserIdRoleInfo;
import com.fehead.diseaseCare.error.BusinessException;
import com.fehead.diseaseCare.error.EmBusinessError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtUtil {

    public static String makeToken(User user) {
        //withAudience()存入需要保存在token的信息，这里我把用户ID存入token中
        String token="";
        Date date = new Date(System.currentTimeMillis()+1000*60*2*24);
        token= JWT.create()
                .withClaim("userId",user.getId())
                .withClaim("role",user.getRole())
                .withExpiresAt(date)
                .sign(Algorithm.HMAC256("xxxxx4234{}d]]fxxxxdsadasdasdsad"));
        return token;
    }

    public static UserIdRoleInfo getUserIdByToken(){
        ServletRequestAttributes requestAttributes = ServletRequestAttributes.class.
                cast(RequestContextHolder.getRequestAttributes());
        HttpServletRequest req = requestAttributes.getRequest();
        String token = req.getHeader("token");
        UserIdRoleInfo userIdRoleInfo=new UserIdRoleInfo();
        JWTVerifier build = JWT.require(Algorithm.HMAC256("xxxxx4234{}d]]fxxxxdsadasdasdsad")).build();
        DecodedJWT jwt=null;
        try {
            jwt = build.verify(token);
        }catch (Exception e){
            throw new BusinessException(EmBusinessError.USER_AUTH_ERROR);
        }
        int  userId = jwt.getClaim("userId").asInt();
        int  role =jwt.getClaim("role").asInt();
        userIdRoleInfo.setUserId(userId);
        userIdRoleInfo.setRole(role);
        return userIdRoleInfo;
    }
}
