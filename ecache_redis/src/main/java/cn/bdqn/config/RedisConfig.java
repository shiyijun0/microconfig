package cn.bdqn.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.*;

@Configuration
public class RedisConfig {
    private static JedisCluster jedis;
   /* @Bean
    public JedisCluster JedisClusterFactory() {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 6379));
       // Jedis连接池配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲连接数, 默认8个
        jedisPoolConfig.setMaxIdle(100);
        // 最大连接数, 默认8个
        jedisPoolConfig.setMaxTotal(500);
        //最小空闲连接数, 默认0
        jedisPoolConfig.setMinIdle(0);
        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        jedisPoolConfig.setMaxWaitMillis(2000); // 设置2秒
        //对拿到的connection进行validateObject校验
        jedisPoolConfig.setTestOnBorrow(true);
        jedis = new JedisCluster(jedisClusterNodes, jedisPoolConfig);

       // JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes);
       // return jedisCluster;
        return  jedis;
    }*/
    @Bean
    public Jedis JedisFactory() {
       // JedisPool pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1", "6379");
        Jedis jedis = new Jedis("127.0.0.1", 6379);

       // jedis.auth("root");
        return jedis;
    }
}
