package org.example.springsecuritylearning.springsecurity.Context;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {
   public static void main(String [] args){
       SpringApplication.run(SpringSecurityApplication.class, args);

   }
   public void run(String ...args){
       Give give=new Give();

       System.out.println("Spring Security Application started");
       give.output(10);
       give.output(10);

   }

}
@Cacheable
class Give{

    Give(){
        System.out.println("Give");

    }
    public void output(int value){
        System.out.println(value);

    }

}
