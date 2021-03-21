package com.fehead.diseaseCare;

import com.fehead.diseaseCare.utility.RedisUtil;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


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


}
