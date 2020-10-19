package com.kygi.kakaoTask3.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;


@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ReceiverListDTO implements Serializable {
    public static final String RECEIVER_AMT     = "receiverAmt";
    public static final String RECEIVER_X_USER_ID  = "receiverXUserID";

    private int receiverAmt;
    private int receiverXUserID;

    public ReceiverListDTO(Map<String, Object> values) {
        this.receiverAmt = values.get(RECEIVER_AMT) != null ? (int) values.get(RECEIVER_AMT) : null;
        this.receiverXUserID = values.get(RECEIVER_X_USER_ID) != null ? (int) values.get(RECEIVER_X_USER_ID) : null;
    }
}
