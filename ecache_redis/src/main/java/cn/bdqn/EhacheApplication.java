package cn.bdqn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;

@SpringBootApplication
@EnableCaching
//@ComponentScan("cn.bdqn.ehache")
@EnableTransactionManagement
@EnableAspectJAutoProxy(exposeProxy = true)
public class EhacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(EhacheApplication.class, args);
    }
}
