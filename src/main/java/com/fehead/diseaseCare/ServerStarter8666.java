package com.fehead.diseaseCare;


import com.fehead.diseaseCare.nettySocket.NettyServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;
import java.util.TimeZone;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = "com")
@EnableTransactionManagement //开启声明式事务
@MapperScan("com.fehead.diseaseCare.mapper")
@EnableSwagger2
@EnableAsync
public class ServerStarter8666 implements CommandLineRunner {

    @Autowired
    NettyServer nettyServer;

    public static void main( String[] args )
    {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        System.out.println(new Date());
        SpringApplication.run(ServerStarter8666.class,args);
    }


    @Override
    public void run(String... args) throws Exception {
        nettyServer.start();
    }
}
