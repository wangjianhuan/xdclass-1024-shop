package net.xdclass.config;

import feign.RequestInterceptor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author WJH
 * @date 2021/7/16 上午11:34
 * @QQ 1151777592
 */
@Configuration
@Data
@Slf4j
public class AppConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private String redisPort;

    @Value("${spring.redis.password}")
    private String redisPwd;

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        //单机方式
        config.useSingleServer().setPassword(redisPwd).setAddress("redis://"+redisHost+":"+redisPort);

        return Redisson.create(config);
    }

    /**
     * 避免存储的KEY乱码，但hash结构的依旧乱码
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(factory);

        RedisSerializer redisSerializer = new StringRedisSerializer();

        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setValueSerializer(redisSerializer);

        return redisTemplate;
    }

    /**
     * feign调用丢失token，
     * 解决：新增拦截器并加入token
     * @return
     */
    @Bean("requestInterceptor")
    public RequestInterceptor requestInterceptor(){
        return template -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if(attributes!=null){
                HttpServletRequest request = attributes.getRequest();
                if (null == request){
                    return;
                }
                log.info(request.getHeaderNames().toString());
                template.header("token", request.getHeader("token"));
            }else {
                log.warn("requestInterceptor获取Header空指针异常");
            }
        };
    }
}
