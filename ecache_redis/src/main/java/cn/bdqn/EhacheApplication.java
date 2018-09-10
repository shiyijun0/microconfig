package cn.bdqn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
//@ComponentScan("cn.bdqn.ehache")
@MapperScan("cn.bdqn.mapper")
/*@EnableTransactionManagement
@EnableAspectJAutoProxy(exposeProxy = true)*/
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class EhacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(EhacheApplication.class, args);
    }
}
