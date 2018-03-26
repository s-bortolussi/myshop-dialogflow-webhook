package com.example;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.example.JsonUtils.asJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MyShopDialogflowWebhookController.class)
public class MyShopDialogflowWebhookControllerTest {

    public static final String AUTHORIZATION_HEADER_BASIC = "Basic dXNlcjpwYXNzd29yZA=="; //base64(user:password)

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MyShopService myShopService;

    @Test
    public void webhook() throws Exception {
        this.mockMvc.perform(post("/webhook")
                .header("Authorization", AUTHORIZATION_HEADER_BASIC)
                .content(asJson("fulfillement_request.json"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.speech", Matchers.equalTo("commande effectuée")))
                .andExpect(jsonPath("$.displayText", Matchers.equalTo("commande effectuée")));
    }



}