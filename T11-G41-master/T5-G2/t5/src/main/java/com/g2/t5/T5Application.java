package com.g2.t5;
//import org.hibernate.mapping.List;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//import com.g2.Interfaces.turnRepository;
//import com.g2.Model.Turns;

@SpringBootApplication
public class T5Application {
 /*    @Autowired
    private turnRepository turnRepo; 
    */
    public static void main(String[] args) {
        SpringApplication.run(T5Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

/*     @Override
    public void run(String... args) throws Exception{
        List<Turns> turn = turnRepo.findAll();
        turn.forEach(System.out :: println);
    }
    */

}

