package com.fehead.diseaseCare.utility;

import com.auth0.jwt.JWT;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class JwtUtil {

    public static int getUserIdByToken(){
        ServletRequestAttributes requestAttributes = ServletRequestAttributes.class.
                cast(RequestContextHolder.getRequestAttributes());
        HttpServletRequest req = requestAttributes.getRequest();
        String token = req.getHeader("token");
       int  userId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
       return userId;
    }
}
