package com.fehead.diseaseCare.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fehead.diseaseCare.entities.User;
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
                .withAudience(user.getId()+"")
                .withExpiresAt(date)
                .sign(Algorithm.HMAC256("xxxxx4234{}d]]fxxxxdsadasdasdsad"));
        return token;
    }

    public static int getUserIdByToken(){
        ServletRequestAttributes requestAttributes = ServletRequestAttributes.class.
                cast(RequestContextHolder.getRequestAttributes());
        HttpServletRequest req = requestAttributes.getRequest();
        String token = req.getHeader("token");
       int  userId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
       return userId;
    }
}
