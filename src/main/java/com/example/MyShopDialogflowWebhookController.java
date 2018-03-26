package com.example;

import ai.api.model.AIRequest;
import ai.api.model.Fulfillment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MyShopDialogflowWebhookController {

    Logger logger = LoggerFactory.getLogger(MyShopDialogflowWebhookController.class);

    private MyShopService myShopService;

    public MyShopDialogflowWebhookController(MyShopService myShopService) {
        this.myShopService = myShopService;
    }

    @PostMapping("/webhook")
    public Fulfillment webhook(@RequestBody AIRequest request) {
        logger.debug("request : " + request);
        String userAccessToken = getUserAccessToken(request);

        myShopService.placeOrder(getAuthorizationHeader(userAccessToken));

        Fulfillment response = new Fulfillment();
        response.setSpeech(generateTextResponse());
        response.setDisplayText(generateTextResponse());

        return response;
    }

    private String getUserAccessToken(@RequestBody AIRequest request) {
        Map userDetails = (Map) request.getOriginalRequest().getData().get("user");
        return String.valueOf(userDetails.get("access_token"));
    }

    private String getAuthorizationHeader(String userAccessToken) {
        return String.format("Bearer %s", userAccessToken);
    }

    private String generateTextResponse() {
        return String.format("commande effectuée");
    }

}