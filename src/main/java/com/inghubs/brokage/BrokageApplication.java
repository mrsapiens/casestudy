package com.inghubs.brokage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.inghubs.brokage.repository")
public class BrokageApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrokageApplication.class, args);
    }

}
