package org.example.springsecuritylearning.springsecurity.Context;

import org.example.springsecuritylearning.springsecurity.Context.proxy.ProxyServerCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import picocli.CommandLine;

@SpringBootApplication
@EnableCaching
public class ProxyCachingApplication {public static void main(String [] args){
    SpringApplication.run(ProxyCachingApplication.class);

    new CommandLine(new ProxyServerCommand()).execute(args);
}

}
