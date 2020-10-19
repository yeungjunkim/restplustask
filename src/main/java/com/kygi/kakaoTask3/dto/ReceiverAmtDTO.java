package com.kygi.kakaoTask3.dto;


import lombok.*;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ReceiverAmtDTO implements Serializable {
    public static final String RECEIVER_AMT       = "receiverAmt";

    private int receiverAmt;

    public ReceiverAmtDTO(Map<String, Object> values) {
        this.receiverAmt = values.get(RECEIVER_AMT) != null ? (int) values.get(RECEIVER_AMT): null;
    }
}
