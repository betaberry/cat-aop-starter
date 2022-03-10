package com.wyz;


import com.wyz.CatAopService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatAopAutoConfiguration {
    @Bean
    public CatAopService catAopService () {
        return new CatAopService();
    }
}
