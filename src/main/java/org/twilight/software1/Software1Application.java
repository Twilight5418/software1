package org.twilight.software1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.twilight.software1.mapper")
public class Software1Application {

    public static void main(String[] args) {
        SpringApplication.run(Software1Application.class, args);
    }

}
