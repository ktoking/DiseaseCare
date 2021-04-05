package com.fehead.diseaseCare;

import com.fehead.diseaseCare.entities.User;
import com.fehead.diseaseCare.service.IUserService;
import com.fehead.diseaseCare.utility.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;


/**
 * Unit test for simple CommunityMain8111.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest 
{

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private IUserService userService;

    @Test
    public void insertUserTest(){
        User user=new User();
        user.setAvatar("sdsa");
        user.setName("s");
        user.setSex(0);
        user.setPassword("ss");
        user.setBirthday(Timestamp.from(Instant.now()).toLocalDateTime());
        user.setEmail("ss");
        user.setPhone("sd");
        user.setStatus(1);
        user.setCreateTime(Timestamp.from(Instant.now()).toLocalDateTime());
        user.setPrice(new BigDecimal("0"));
        userService.createUser(user);
    }

}
