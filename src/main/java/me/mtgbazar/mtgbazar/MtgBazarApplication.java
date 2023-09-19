package me.mtgbazar.mtgbazar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.io.IOException;

@SpringBootApplication
@EnableJpaRepositories
@EnableWebSecurity
public class MtgBazarApplication implements CommandLineRunner {

    public static void main(String[] args)  {
        SpringApplication.run(MtgBazarApplication.class, args);
    }
    @Override
    public void run(String[] args) throws IOException {

//        //create ObjectMapper instance
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        //read JSON file and convert to object
//        JsonOBJ rootObj = objectMapper.readValue(new File("resources/cards.json"), JsonOBJ.class);
//
//        //print object details
//        System.out.println(rootObj.getListCards().get(0).getClass().getName());
    }
}
