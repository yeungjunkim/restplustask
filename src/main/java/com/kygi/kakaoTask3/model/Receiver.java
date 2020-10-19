package com.kygi.kakaoTask3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "RECEIVERS")
public class Receiver  extends AuditModel{
//    RECEIVER_AMT
//            RECEIVER_DATE
//    RECEIVER'_X_USER_ID
//    RECEIVER_X_ROOM_ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "RECEIVER_AMT", nullable = false)
    private int receiverAmt;

    @Column(name = "RECEIVER_X_USER_ID")
    private int receiverXUserID;

    @Column(name = "RECEIVER_X_ROOM_ID", nullable = false)
    private String receiverXRoomID;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "giver_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private Giver giver;



    public Receiver(int receiverAmt, int receiverXUserID, String receiverXRoomID)
    {
        this.receiverAmt = receiverAmt;
        this.receiverXUserID = receiverXUserID;
        this.receiverXRoomID = receiverXRoomID;
    }

}
