package com.kygi.kakaoTask3.service;

import com.kygi.kakaoTask3.Util.CommonUtils;
import com.kygi.kakaoTask3.dto.*;
import com.kygi.kakaoTask3.model.Giver;
import com.kygi.kakaoTask3.model.Receiver;
import com.kygi.kakaoTask3.repository.GiverRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GiverService {

    private final GiverRepository giverRepository;

    public GiverService(final GiverRepository giverdRepository) {
        this.giverRepository = giverdRepository;
    }

    public TokenDTO makeGiversTrans(int giverAmt, int giverCnt, HttpServletRequest req)
    {

        int xUserID     = -1 ;
        String xRoomID  = "";

        log.info("rInteger.parseInt(req.getHeader(\"X_USER_ID\")) = {}", req.getHeader("X_USER_ID"));
        log.info("String.valueOf(req.getHeader(\"X_ROOM_ID\") = {}", req.getHeader("X_ROOM_ID"));

        if(req != null) {
            xRoomID = String.valueOf(req.getHeader("X_ROOM_ID"));
        }
        if (xUserID <= 0)
            xUserID = 12345;

        if(xRoomID == null || "".equals(xRoomID) || "null".equals(xRoomID) )
            xRoomID = "TEST-123";

        log.info("xUserID = {}", xUserID);
        log.info("xRoomID = {}", xRoomID);

        int[] splitAmt = CommonUtils.randSum(giverCnt, 100, giverAmt);

        String token = CommonUtils.getAlphaNumericString(3);
        Giver giver = new Giver(token, giverAmt, giverCnt, xUserID, xRoomID);

        Receiver receiver = null;

        for (int sa : splitAmt)
        {
            receiver = new Receiver(sa,0, xRoomID);
            giver.getReceivers().add(receiver);
        }

        Giver save1 = giverRepository.save(giver);

        Map<String, Object> pushMap = new HashMap<String, Object>();
        pushMap.put("token", token);

        TokenDTO dto = new TokenDTO(pushMap);

        return dto;

    }


    public ReceiverAmtDTO makeGiverMoneyTrans(String token, HttpServletRequest req) throws Exception {

        /* 분배건 하나를 조회해서 그 금액을 내려줌 */
        int xUserID = -1 ;
        String xRoomID = "";

        if(req != null) {

            xRoomID = String.valueOf(req.getHeader("X_ROOM_ID"));
        }
        if (xUserID <= 0)
            xUserID = 11111;

        if(xRoomID == null || "".equals(xRoomID) || "null".equals(xRoomID) )
            xRoomID = "TEST-123";


        int receiverCnt = giverRepository.findByReceiverCnt(token, String.valueOf(xUserID));


        if ( receiverCnt > 0 )
        {
            log.info("이미 돈을 받으셨습니다.");
            throw new Exception("이미 돈을 받으셨습니다");
        }

        List<Map<String, Object>> results = giverRepository.findByReceiveOneDetail(token);
        List<ReceiverOneDTO> rcvOneDTO = results.stream().map(result -> new ReceiverOneDTO(result))
                .collect(Collectors.toList());

        ReceiverAmtDTO rAmtDTO = null;
        Map<String, Object> receiverAmtMap = new HashMap<String, Object>();

        if (rcvOneDTO.size() > 0) {

            if (rcvOneDTO.get(0).getGiverXUserID() == xUserID) {
                log.info("자신이 뿌린껀에 대해서는 받아가실 수 없습니다.");
                throw new Exception("자신이 뿌린껀에 대해서는 받아가실 수 없습니다.");
            }


            /* 뿌리기 호출한 대화방과 동일한 대회방만 가능함 */
            if (!xRoomID.equals(String.valueOf(rcvOneDTO.get(0).getGiverXRoomID()))) {
                log.info("뿌리기를 당사자가 없는 동일하지 않은 채팅방에서는 받으실 수 없습니다.");
                throw new Exception("뿌리기를 당사자가 없는 동일하지 않은 채팅방에서는 받으실 수 없습니다.");
            }
            /* 뿌린 건은 10분간만 유효합니다. 뿌린지 10분이 지난건은 받기 실패요청이 일어납니다. */
            //        Optional<Receiver> rcv = receiverRepository.findById((long) 4);

            if (CommonUtils.passedTimeCheck(rcvOneDTO.get(0).getCurrentAt(), rcvOneDTO.get(0).getCreatedAt(), 0, 0, 10, 0)) {
                log.info("받을 수 있는 10분 동안의 시간이 끝났습니다..");
                throw new Exception("받을 수 있는 10분 동안의 시간이 끝났습니다.");

            }
            giverRepository.updateReceiverID("" + rcvOneDTO.get(0).getReceiverID(), xUserID);
            receiverAmtMap.put("receiverAmt", rcvOneDTO.get(0).getReceiverAmt());
        }
        else
        {
            receiverAmtMap.put("receiverAmt", 0);
        }

        rAmtDTO = new ReceiverAmtDTO(receiverAmtMap);
        return rAmtDTO;

    }


    public List<Object> makeGiverList(String token, HttpServletRequest req) throws Exception {
        int xUserID = -1 ;
        String xRoomID = "";

        if(req != null) {

//            xUserID = req.getHeader("X_USER_ID");
            xRoomID = String.valueOf(req.getHeader("X_ROOM_ID"));
        }
        if (xUserID <= 0)
//            x_user_id = -1;
            xUserID = 12345;

        if(xRoomID == null || "".equals(xRoomID) || "null".equals(xRoomID) )
            xRoomID = "TEST-123";

        GiverSumDetailDTO gsdtlDTO = new GiverSumDetailDTO();
        List<Object> retObj = new ArrayList<Object>();

        Map<String, Object> diffDate = giverRepository.findByCreatedAtAndCurentAt(token, ""+xUserID);
        /* 날짜 체크로직이 있어서 7일이 넘지 않으면 값을 내려야 함 */

        log.info("diffDate.size() = {}", diffDate.size());


        if(diffDate.size() == 0)
        {
            log.info("시간 값이 조회되지 않았습니다. ");
            return retObj;
        }

        boolean chkPassed = CommonUtils.passedTimeCheck((Date)diffDate.get("currentAt"),
                (Date)diffDate.get("createdAt"), 7, 0, 10, 0);

        if(chkPassed)
        {
            log.info("조회할 수 있는 7일이 지났습니다.");
            throw new Exception("조회할 수 있는 7일이 지났습니다.");
        }

        Map<String, Object> giverSum = giverRepository.findByGiverSum(token, ""+xUserID);
        List<Map<String, Object>> results = giverRepository.findByReceiverDetail(token, ""+xUserID);
        List<ReceiverListDTO> rcvListDto = results.stream().map(result -> new ReceiverListDTO(result))
                .collect(Collectors.toList());

        if(giverSum.size() == 0) {
            log.info("유저아이디가 맞지 않거나 토큰값이 맞지 않습니다.");
            throw new Exception("유저아이디가 맞지 않거나 토큰값이 맞지 않습니다.");
        }

            else {
            retObj.add(giverSum);
            retObj.add(rcvListDto);
        }

        return retObj;

    }

}
