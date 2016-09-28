package com.example.config;

import com.example.SoapMessageFiltersSourceAdapter;
import com.example.SomeBean;
import com.example.SomeBean1;
import org.littleshoot.proxy.HttpProxyServer;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyConfiguration {

    @Value("${proxy.port}")
    private int port;

/*    @Autowired
    private SoapMessageFiltersSourceAdapter filtersSourceAdapter;
    @Bean
    public HttpProxyServer proxyServer() {
        System.out.println("SomeBean1" + someBean1());
        return DefaultHttpProxyServer.bootstrap()
                .withPort(port)
                .withFiltersSource(filtersSourceAdapter)
                .start();
    }*/


    @ConditionalOnProperty(name = "server.port")
    @Bean
    public SomeBean someBean() {
        return new SomeBean();
    }

    @ConditionalOnBean(value = SomeBean.class)
    @Bean
    public SomeBean1 someBean1() {
        return new SomeBean1();
    }

}
