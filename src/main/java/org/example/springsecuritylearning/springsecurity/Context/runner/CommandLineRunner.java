package org.example.springsecuritylearning.springsecurity.Context.runner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;

public class CommandLineRunner implements ApplicationRunner {
    @Value("${app.name:Username}")
    private String username;

    private Environment env;

    public CommandLineRunner(Environment env){
        this.env=env;

    }
    public void run(ApplicationArguments args){
        args.getNonOptionArgs().forEach((o)->System.out.println("NonoptionArgs:"+o));

        if (args.containsOption("mode")){
            System.out.println("mode:"+args.getOptionValues("mode").get(0));

        }
      System.out.println("username " +username);
        System.out.println(env.getActiveProfiles());
        System.out.println(env.getActiveProfiles());





    }

}
