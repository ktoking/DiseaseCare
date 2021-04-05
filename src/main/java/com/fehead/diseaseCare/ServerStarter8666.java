package com.fehead.diseaseCare;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = "com")
@EnableTransactionManagement //开启声明式事务
@MapperScan("com.fehead.diseaseCare.mapper")
@EnableSwagger2
public class ServerStarter8666
{
    public static void main( String[] args )
    {
        SpringApplication.run(ServerStarter8666.class,args);
    }



}
