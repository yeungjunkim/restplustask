package com.kygi.kakaoTask3.controller;


import com.kygi.kakaoTask3.dto.*;
import com.kygi.kakaoTask3.repository.GiverRepository;
import com.kygi.kakaoTask3.service.GiverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@RestController
public class GiverController {

    @Autowired
    private GiverRepository giverRepository;

    private final GiverService giverService;

    public GiverController(final GiverService giverService) {
        this.giverService = giverService;
    }

    /**
     * 뿌리기 API
     * @param giverAmt
     * @param giverCnt
     * @param req
     * @return
     */
    @GetMapping("/givers/{giverAmt}/{giverCnt}")
    public TokenDTO getGiversTrans(@PathVariable int giverAmt, @PathVariable int giverCnt, HttpServletRequest req) {
        GiverService giverSvc = new GiverService(giverRepository);
        return giverSvc.makeGiversTrans(giverAmt, giverCnt, req);
    }


    /**
     * 받기 API
     */
    @GetMapping("/rcvmoney/{token}")
    public ReceiverAmtDTO getGiverMoney(@PathVariable (value = "token") String token, HttpServletRequest req) throws Exception {
        GiverService giverSvc = new GiverService(giverRepository);
        return giverSvc.makeGiverMoneyTrans(token, req);
    }


    /**
     * 조회 API
     */
    @GetMapping("/givers/status/{token}")
    public List<Object> getGiversList(@PathVariable (value = "token") String token, HttpServletRequest req) throws Exception {
        GiverService giverSvc = new GiverService(giverRepository);
        return giverSvc.makeGiverList(token, req);

    }

}
