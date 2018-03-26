package com.example;

import ai.api.model.AIRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

@RunWith(SpringRunner.class)
@JsonTest
public class AIRequestTest {

    @Autowired
    private JacksonTester<AIRequest> json;

    @Test
    public void testDeserialize() throws Exception {
        String content = JsonUtils.asJson("fulfillement_request.json");
        //can deserialize user access token ?
        assertThat((Map) this.json.parseObject(content).getOriginalRequest().getData().get("user"))
                .containsOnly(entry("access_token","1234"),entry("user_id","aaaaaa"));

    }
}
