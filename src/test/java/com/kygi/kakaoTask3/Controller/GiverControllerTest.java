package com.kygi.kakaoTask3.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kygi.kakaoTask3.controller.GiverController;
import com.kygi.kakaoTask3.dto.TokenDTO;
import com.kygi.kakaoTask3.model.Giver;
import com.kygi.kakaoTask3.service.GiverService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class GiverControllerTest {

    private GiverController giverController;
    private MockMvc mvc;

    @MockBean
    private GiverService giverService;
    private ObjectMapper objectMapper;
    private TokenDTO retToken;
    private String getMethodUrl;

    @BeforeEach
    @DisplayName("뿌리기 거래 사전동작")
    void setUp(@Autowired GiverController giverController) throws Exception {
        // MockMvc
        mvc = MockMvcBuilders.standaloneSetup(giverController).build();

        // when
        getMethodUrl = "/givers/100000/3";
        final ResultActions actions = mvc.perform(get(getMethodUrl));

        // then
        actions
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = actions.andReturn().getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        retToken = mapper.readValue(content, TokenDTO.class);

    }

//    @Test
//    @DisplayName("뿌리기 거래 생성")
//    void getGiversTrans() throws Exception {
//        // when
//        getMethodUrl = "/givers/100000/3";
//        final ResultActions actions = mvc.perform(get(getMethodUrl));
//
//        // then
//        actions
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String content = actions.andReturn().getResponse().getContentAsString();
//
//        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
//        retToken = mapper.readValue(content, TokenDTO.class);
//
//    }



    @Test
    @DisplayName("받기 거래 생성")
    void getGiverMoney() throws Exception {

        final ResultActions actions = mvc.perform(get("/rcvmoney/" + retToken.getToken()));

        // then
        actions
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("조회 거래 처리 ")
    void getGiversList() throws Exception {
        /* 가져오기를 한 다음에 뿌리기 거래 데이터가 생성되므로 가져오기 거래를 추가함. */
        final ResultActions actions = mvc.perform(get("/rcvmoney/" + retToken.getToken()));

        // then
        actions
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        // when
        /* 조회거래. */
        final ResultActions actions1 = mvc.perform(get("/givers/status/" + retToken.getToken()));

        // then
        actions1
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}
