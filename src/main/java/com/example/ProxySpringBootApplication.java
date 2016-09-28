package com.example;

import org.littleshoot.proxy.HttpFiltersSourceAdapter;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProxySpringBootApplication {

	@Value("${proxy.port}")
	private int port;

	@Autowired
	private HttpFiltersSourceAdapter filtersSourceAdapter;

	public static void main(String[] args) {
		SpringApplication.run(ProxySpringBootApplication.class, args);
	}

	@Bean
	public CommandLineRunner proxyRunner() {
		return args -> {
			DefaultHttpProxyServer.bootstrap()
					.withPort(port)
					.withFiltersSource(filtersSourceAdapter)
					.start();
		};
	}
}
