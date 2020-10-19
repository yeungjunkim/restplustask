package com.kygi.kakaoTask3.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
public class GiverSumDetailDTO implements Serializable {
//    public static final String CREATED_AT           = "createdAt";
//    public static final String GIVER_AMT            = "giverAmt";
//    public static final String RECEIVER_TOT_AMT     = "receiverTotAmt";
//    public static final String GIVER_SUM_DTO        = "giverSumDTO";
//
//    private Date createdAt;
//    private int giverAmt;
//    private int receiverTotAmt;
    private GiverListDTO giverlistDTO;
    private List<ReceiverListDTO> receiverListDTO;

//    public GiverSumDetailDTO(Map<String, Object> values) {
//        this.createdAt = values.get(CREATED_AT) != null ? (Date) values.get(CREATED_AT): null;
//        this.giverAmt = values.get(GIVER_AMT) != null ? (int) values.get(GIVER_AMT) : null;
//        this.receiverTotAmt = values.get(RECEIVER_TOT_AMT) != null ? (int) values.get(RECEIVER_TOT_AMT) : null;
//        this.giverSumDTO = values.get(GIVER_SUM_DTO) != null ? (List<ReceiverListDTO>) values.get(GIVER_SUM_DTO) : null;
//    }

    public GiverSumDetailDTO() {
    }
}
