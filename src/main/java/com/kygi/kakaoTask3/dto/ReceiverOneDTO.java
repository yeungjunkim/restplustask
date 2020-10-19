package com.kygi.kakaoTask3.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
public class ReceiverOneDTO  implements Serializable {
    public static final String CREATED_AT       = "createdAt";
    public static final String GIVER_X_USER_ID  = "giverXUserID";
    public static final String GIVER_X_ROOM_ID  = "giverXRoomID";
    public static final String RECEIVER_ID      = "receiverID";
    public static final String RECEIVER_AMT     = "receiverAmt";
    public static final String CURRENT_AT       = "currentAt";

    private Date createdAt;
    private int giverXUserID;
    private String giverXRoomID;
    private BigInteger receiverID;
    private int receiverAmt;
    private Date currentAt;

    public ReceiverOneDTO(Map<String, Object> values) {
        this.createdAt = values.get(CREATED_AT) != null ? (Date) values.get(CREATED_AT): null;
        this.giverXUserID = values.get(GIVER_X_USER_ID) != null ? (int) values.get(GIVER_X_USER_ID) : null;
        this.giverXRoomID = values.get(GIVER_X_ROOM_ID) != null ? (String) values.get(GIVER_X_ROOM_ID) : null;
        this.receiverID = values.get(RECEIVER_ID) != null ?  (BigInteger)values.get(RECEIVER_ID) : null;
        this.receiverAmt = values.get(RECEIVER_AMT) != null ? (int) values.get(RECEIVER_AMT) : null;
        this.currentAt = values.get(CURRENT_AT) != null ? (Date) values.get(CURRENT_AT) : null;
    }

}
