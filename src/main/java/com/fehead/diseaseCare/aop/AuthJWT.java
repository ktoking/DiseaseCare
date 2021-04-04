package com.fehead.diseaseCare.aop;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fehead.diseaseCare.controller.BaseController;
import com.fehead.diseaseCare.error.BusinessException;
import com.fehead.diseaseCare.error.EmBusinessError;
import com.fehead.diseaseCare.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Component
@Aspect
@Slf4j
public class AuthJWT extends BaseController {

    @Autowired
    private IUserService userService;

    //定义切面，所有的controller层都会监控
    @Pointcut("execution(* com.fehead.diseaseCare.controller..*.*(..))")
    public void doHander() { }


    @Around("doHander()")
    public Object exception(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            //进入controller层前
            beforePoint(joinPoint);
            //放行
            Object result = joinPoint.proceed();
            //返回数据前
            afterPoint(joinPoint, result);
            return result;
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw  new BusinessException(EmBusinessError.COMMON_ERROR,e.getMessage());
        }
    }

    private Boolean beforePoint(ProceedingJoinPoint joinPoint) throws Exception {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        // 从 http 请求头中取出 token
        String token = request.getHeader("token");

        //得到要进入的是哪个controller方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        //检查是否有passtoken注释，有则跳过认证，所以在controller层加了@Passtoken注解，这里我就不校验
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        //加了@UserLoginToken注解的我要进行校验
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (StringUtils.isEmpty(token)) {
                    throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"token为空" );
                }
//                // 获取 token 中的 user id
//                int userId;
//                try {
//                    userId = JwtUtil.getUserIdByToken(token);
//                } catch (JWTDecodeException j) {
//                    throw new BusinessException(EmBusinessError.USER_AUTH_ERROR,"获取用户UserId异常");
//                }
                // 验证 token  这里的 token 要和上面生成token的密钥一致才能解析成功
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("xxxxx4234{}d]]fxxxxdsadasdasdsad")).build();
                try {
                    //验证token
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new BusinessException(EmBusinessError.USER_AUTH_ERROR);
                }
                return true;
            }
        }
        return true;
    }


    private void afterPoint(ProceedingJoinPoint joinPoint, Object result) throws Exception {

    }

}
