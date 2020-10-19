package com.kygi.kakaoTask3.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@Table(name = "GIVERS")
public class Giver extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    뿌리는 토큰	GIVER_TOKEN
//    뿌릴 금액	GIVER_AMT
//    뿌릴 인원	GIVER_CNT
//    뿌리는 날짜 시간 	GIVER_DATE
//    뿌리는 사람 아이디	GIVER_X_USER_ID
//    뿌리는 사람 방 아이디	GIVER_X_ROOM_ID

    @Column(name = "GIVER_TOKEN", nullable = false)
    private String giverToken;

    @Column(name = "GIVER_AMT", nullable = false)
    private int giverAmt;

    @Column(name = "GIVER_CNT", nullable = false)
    private int giverCnt;

    @Column(name = "GIVER_X_USER_ID", nullable = false)
    private int giverXUserID;

    @Column(name = "GIVER_X_ROOM_ID", nullable = false)
    private String giverXRoomID;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pc_fid1", referencedColumnName = "id")
//    @OneToMany(mappedBy = "Giver")
    private List<Receiver> receivers = new ArrayList<>();

    public List<Receiver> getReceivers(){
        return receivers;
    }

    public Giver(String giverToken, int giverAmt, int giverCnt, int giverXUserID, String giverXRoomID)
    {
        this.giverToken = giverToken;
        this.giverAmt = giverAmt;
        this.giverCnt = giverCnt;
        this.giverXUserID = giverXUserID;
        this.giverXRoomID = giverXRoomID;
    }

}
