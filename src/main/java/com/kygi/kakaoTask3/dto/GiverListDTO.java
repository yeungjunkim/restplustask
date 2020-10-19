package com.kygi.kakaoTask3.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class GiverListDTO {
    private Date createdAt;
    private int giverAmt;
    private int receiverTotAmt;
}
