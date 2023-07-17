package me.mtgbazar.mtgbazar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class MtgBazarApplication {

    public static void main(String[] args) {
        SpringApplication.run(MtgBazarApplication.class, args);
    }

}
