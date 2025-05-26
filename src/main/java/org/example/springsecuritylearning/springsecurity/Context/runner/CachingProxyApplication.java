//package org.example.springsecuritylearning.springsecurity.Context.runner;
//
//import org.springframework.aop.framework.ProxyConfig;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//
//public class CachingProxyApplication implements CommandLineRunner {
//    @Autowired
//    private ProxyConfig proxyConfig;
//
//
//    public void run(String ...args){
//        for (String arg: args){
//            if (arg.startsWith("--origin")){
//                proxyConfig.setOrigin(arg.substring("--origin=".length()));
//
////            }
//            else if (arg.startsWith("--proxy")){
//                CacheService.clearCache();
//            }
//        }
//    }
//}
