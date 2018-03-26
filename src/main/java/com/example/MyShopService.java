package com.example;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "myshop", url = "${myshop.service.url}")
public interface MyShopService {

    @PostMapping("/order")
    void placeOrder(@RequestHeader("Authorization") String authorization);

}
