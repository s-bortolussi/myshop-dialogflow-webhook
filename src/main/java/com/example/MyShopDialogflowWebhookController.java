package com.example;

import ai.api.model.AIOriginalRequest;
import ai.api.model.AIRequest;
import ai.api.model.Fulfillment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

import static com.example.JsonUtils.*;

@RestController
public class MyShopDialogflowWebhookController {

    Logger logger = LoggerFactory.getLogger(MyShopDialogflowWebhookController.class);

    private MyShopService myShopService;

    public MyShopDialogflowWebhookController(MyShopService myShopService) {
        this.myShopService = myShopService;
    }

    @PostMapping("/webhook")
    public Fulfillment webhook(@RequestBody AIRequest request) {
        logger.debug("request : " + toJson(request));
        String userAccessToken = getUserAccessToken(request);

        myShopService.placeOrder(getAuthorizationHeader(userAccessToken));

        Fulfillment response = new Fulfillment();
        response.setSpeech(generateTextResponse());
        response.setDisplayText(generateTextResponse());

        return response;
    }

    private String getUserAccessToken(AIRequest request) {
        return Optional.ofNullable(request.getOriginalRequest())
                .map(AIOriginalRequest::getData)
                .map(data -> data.get("user"))
                .map(user -> ((Map) user).get("accessToken"))
                .map(String::valueOf)
                .orElseThrow(() -> new RuntimeException(String.format("Cannot find user accessToken in request")));
    }

    private String getAuthorizationHeader(String userAccessToken) {
        return String.format("Bearer %s", userAccessToken);
    }

    private String generateTextResponse() {
        return String.format("commande effectuée");
    }

}