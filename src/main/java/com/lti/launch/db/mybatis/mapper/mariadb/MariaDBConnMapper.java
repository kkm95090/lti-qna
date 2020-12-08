package com.lti.launch.db.mybatis.mapper.mariadb;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MariaDBConnMapper {
    String value() default "";
}
