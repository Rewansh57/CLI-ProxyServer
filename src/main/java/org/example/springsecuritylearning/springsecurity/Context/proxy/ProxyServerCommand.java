package org.example.springsecuritylearning.springsecurity.Context.proxy;

import org.example.springsecuritylearning.springsecurity.Context.ProxyCachingApplication;
import org.springframework.boot.SpringApplication;
import picocli.CommandLine;

@CommandLine.Command(name="caching-proxy",mixinStandardHelpOptions = true)

public class ProxyServerCommand implements Runnable{

    @CommandLine.Option(names = "--port", required = true)
    private int port;

    @CommandLine.Option(names="--origin",required=true)
    private String origin ;
    public void run(){
        System.setProperty("server.port",String.valueOf(port));
        System.setProperty("proxy.origin",origin);

        SpringApplication.run(ProxyCachingApplication.class);


    }
    public static void main(String [] args){
        new CommandLine(new ProxyServerCommand()).execute(args);
    }


}
