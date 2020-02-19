package com.group4.itss.config;

import lombok.val;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    @Qualifier("mapper_facade")
    public MapperFacade mapper() {
        val defaultFactory = new DefaultMapperFactory.Builder().build();
        return defaultFactory.getMapperFacade();
    }
}
