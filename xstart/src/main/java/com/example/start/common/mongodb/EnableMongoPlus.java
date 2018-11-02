package com.example.start.common.mongodb;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({MongoPlusAutoConfiguration.class})
public @interface EnableMongoPlus {
    
}
