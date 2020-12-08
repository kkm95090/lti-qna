package com.lti.launch.db.mybatis.mapper.postgresql;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface PostgresSqlConnMapper {
    String value() default "";
}
