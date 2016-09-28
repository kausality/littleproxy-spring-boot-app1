package com.example;

import io.netty.bootstrap.ServerBootstrap;
import org.littleshoot.proxy.HttpProxyServer;
import org.littleshoot.proxy.HttpProxyServerBootstrap;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class ProxyServer {

  /*  @Value("${proxy.port}")
    private int port;

    @Autowired
    private SoapMessageFiltersSourceAdapter filtersSourceAdapter;

    @Autowired
    private DefaultHttpProxyServer serverBootstrap;*/

    public void start() {
    /*    serverBootstrap.bootstrap()
                .withPort(port)
                .withFiltersSource(filtersSourceAdapter)
                .start();*/
    }

    @PreDestroy
    public void stop() {
        //serverBootstrap.stop();
    }
}
