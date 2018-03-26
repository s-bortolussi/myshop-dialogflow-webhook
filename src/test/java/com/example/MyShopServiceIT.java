package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
/**
 * to Explore myShop API
 */
public class MyShopServiceIT {

    @Autowired
    MyShopService myShopService;

    @Value("${test.authorization.header}")
    String authorizationHeader;

    @Test
    public void placeOrder() {
        myShopService.placeOrder(authorizationHeader);
    }
}