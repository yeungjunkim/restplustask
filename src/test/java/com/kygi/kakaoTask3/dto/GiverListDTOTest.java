package com.kygi.kakaoTask3.dto;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class GiverListDTOTest {

    Date date = new Date();


    @Test
    void setCreatedAt() {
        GiverListDTO glDTO = new GiverListDTO(date, 0, 0);
        assertEquals(date, glDTO.getCreatedAt());
    }

    @Test
    void setGiverAmt() {
        GiverListDTO glDTO = new GiverListDTO(date, 30, 0);
        assertEquals(30, glDTO.getGiverAmt());
    }

    @Test
    void setReceiverTotAmt() {
        GiverListDTO glDTO = new GiverListDTO(date, 30, 10000);
        assertEquals(10000, glDTO.getReceiverTotAmt());
    }
}
