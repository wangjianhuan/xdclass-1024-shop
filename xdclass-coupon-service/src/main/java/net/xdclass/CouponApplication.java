package net.xdclass;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author WJH
 * @class xdclass-1024-shop UserApplication
 * @date 2021/7/11 上午11:53
 * @QQ 1151777592
 */
@SpringBootApplication
@MapperScan("net.xdclass.mapper")
@EnableTransactionManagement
public class CouponApplication {
    public static void main(String[] args) {
        SpringApplication.run(CouponApplication.class,args);
    }
}
