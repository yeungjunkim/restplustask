package com.kygi.kakaoTask3.repository;

import com.kygi.kakaoTask3.dto.GiverListDTO;
import com.kygi.kakaoTask3.dto.ReceiverListDTO;
import com.kygi.kakaoTask3.model.Giver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@Repository
public interface GiverRepository extends JpaRepository<Giver, Long> {
        Collection<Giver> findAllByGiverTokenAndGiverXUserID(String giverToken, int giverXUserID);


    @Query(nativeQuery = true, value="SELECT now() AS currentAt, "+
            "       s.CREATED_AT   AS createdAt "+
            "  FROM GIVERS AS s JOIN RECEIVERS AS r "+
            " WHERE s.id = r.pc_fid1 " +
            "   AND s.GIVER_TOKEN        = :giver_token "+
            "   AND s.GIVER_X_USER_ID    = :giver_x_user_id "+
            "   AND r.RECEIVER_X_USER_ID > '0' limit 1")
    Map<String, Object> findByCreatedAtAndCurentAt(@Param("giver_token") String giverToken, @Param("giver_x_user_id") String giverXUserID);


    @Query(nativeQuery = true, value="SELECT MAX(s.CREATED_AT) AS createdAt, MAX(s.GIVER_AMT) AS giverAmt, "+
                                     "       SUM(r.RECEIVER_AMT) AS receiverTotAmt " +
                                     "  FROM GIVERS AS s JOIN RECEIVERS AS r " +
                                     " WHERE s.id                 = r.pc_fid1 " +
                                     "   AND s.GIVER_TOKEN        = :giver_token "+
                                     "   AND s.GIVER_X_USER_ID    = :giver_x_user_id "+
                                     "   AND r.RECEIVER_X_USER_ID > '0'")
    Map<String, Object> findByGiverSum(@Param("giver_token") String giverToken, @Param("giver_x_user_id") String giverXUserID);

    @Query(nativeQuery = true, value="SELECT r.RECEIVER_AMT AS receiverAmt, r.RECEIVER_X_USER_ID AS receiverXUserID "+
                                     "  FROM GIVERS AS s JOIN RECEIVERS AS r "+
                                     " WHERE s.id = r.pc_fid1 " +
                                     "   AND s.GIVER_TOKEN        = :giver_token "+
                                     "   AND s.GIVER_X_USER_ID    = :giver_x_user_id "+
                                     "   AND r.RECEIVER_X_USER_ID > '0'")
    List<Map<String, Object>> findByReceiverDetail(@Param("giver_token") String giverToken, @Param("giver_x_user_id") String giverXUserID);

    @Query(nativeQuery = true, value="SELECT COUNT(r.RECEIVER_X_USER_ID) as cntReceiver " +
                                     "  FROM GIVERS AS s JOIN RECEIVERS AS r "+
                                     " WHERE s.id                 = r.pc_fid1 "+
                                     "   AND s.GIVER_TOKEN        = :giver_token "+
                                     "   AND r.RECEIVER_X_USER_ID = :receiver_x_user_id")
    int findByReceiverCnt(@Param("giver_token") String giverToken, @Param("receiver_x_user_id") String receiverXUserID);

    @Query(nativeQuery = true, value="SELECT s.CREATED_AT AS createdAt, s.GIVER_X_USER_ID AS giverXUserID, "+
                                     "       s.GIVER_X_ROOM_ID AS giverXRoomID, r.id AS receiverID, r.RECEIVER_AMT AS receiverAmt, "+
                                     "       r.RECEIVER_X_USER_ID as receiverXUserID, "+
                                     "       now() AS currentAt "+
                                     "  FROM GIVERS AS s JOIN RECEIVERS AS r "+
                                     " WHERE s.id                 = r.pc_fid1 "+
                                     "   AND s.GIVER_TOKEN        = :giver_token "+
                                     "   AND r.RECEIVER_X_USER_ID = '0' "+
                                     " ORDER BY r.id limit 1")
    List<Map<String, Object>>  findByReceiveOneDetail(@Param("giver_token") String giverToken);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="UPDATE RECEIVERS R set R.RECEIVER_X_USER_ID = :receiver_x_user_id WHERE R.ID = :receiver_id")
    void updateReceiverID(@Param("receiver_id") String id, @Param("receiver_x_user_id") int receiverXUserID);
//
//
//
//    SELECT s.created_at, s.giver_x_user_id, s.giver_x_room_id, r.id, r.RECEIVER_AMT AS receiverAmt, r.RECEIVER_X_USER_ID as receiverXUserID FROM GIVERS AS s JOIN RECEIVERS AS r WHERE s.id = r.pc_fid1 AND s.GIVER_TOKEN        = 'MP2' AND s.GIVER_X_USER_ID    = '12345' AND r.RECEIVER_X_USER_ID = '' order by r.id limit 1;
//

//    SELECT  count(r.RECEIVER_X_USER_ID) as cnt_receiver FROM GIVERS AS s JOIN RECEIVERS AS r WHERE s.id = r.pc_fid1 AND s.GIVER_TOKEN        = 'MP2' AND r.RECEIVER_X_USER_ID = '11111'
//
//
//



}

