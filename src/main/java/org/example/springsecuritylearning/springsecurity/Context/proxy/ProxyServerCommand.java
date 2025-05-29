package org.example.springsecuritylearning.springsecurity.Context.proxy;

import org.example.springsecuritylearning.springsecurity.Context.service.CacheService;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

@CommandLine.Command(name="caching-proxy",mixinStandardHelpOptions = true)
@Component
public class ProxyServerCommand implements Runnable{
    private CacheService cacheService;


    @CommandLine.Option(names = "--port", required = true)
    private int port;

    @CommandLine.Option(names="--origin",required=true)
    private String origin ;
    @CommandLine.Option(names="--clearcache")
    private boolean clearCache;

    public void run(){
        if (clearCache) {
            cacheService.clearAll();
            System.out.println("Clear cache complete.");



        }
        else {


            System.setProperty("server.port", String.valueOf(port));
            System.setProperty("proxy.origin", origin);


        }

    }



}
