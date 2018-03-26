package com.example;

import okhttp3.OkHttpClient;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Configuration
@EnableFeignClients
public class FeignConfiguration {

    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(FeignConfiguration.class);

    @ConditionalOnProperty(value = "feign.proxy.host")
    @Bean
    public feign.okhttp.OkHttpClient okHttpClient(@Value("${feign.proxy.host}") String proxyHost, @Value("${feign.proxy.port:-1}") int proxyPort) {
        logger.info("Using proxy {}:port", proxyHost, proxyPort);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
        return new feign.okhttp.OkHttpClient(
                new OkHttpClient.Builder()
                        .proxy(proxy)
                        .build());
    }

}
