package com.baiyang.oms.rest.modular.auth.controller;

import com.baiyang.oms.core.exception.GunsException;
import com.baiyang.oms.modular.auth.util.JwtTokenUtil;
import com.baiyang.oms.rest.common.exception.BizExceptionEnum;
import com.baiyang.oms.rest.modular.auth.controller.dto.AuthResponse;
import com.baiyang.oms.rest.modular.auth.controller.dto.Request;
import com.baiyang.oms.rest.modular.auth.controller.dto.Result;
import com.baiyang.oms.rest.modular.auth.converter.BaseTransferEntity;
import com.baiyang.oms.rest.modular.auth.controller.dto.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 请求验证的
 *
 * @author fengshuonan
 * @Date 2017/8/24 14:22
 */
@RestController
@RequestMapping(value = "/auth")
public class BaoRequestController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
//
//    @Resource(name = "simpleValidator")
//    private IReqValidator reqValidator;

    @RequestMapping(value = "/logisticsNo")
    public String logisticsNo(String jwt
    		,@RequestBody Request request
    		,String record
//    		,@RequestBody BaseTransferEntity record
    		) {
    	System.out.println("dddd===="+jwt);
//    	System.out.println(jwtTokenUtil.generateToken("ddds", "dss"));
    	System.out.println("request==="+request);
    	System.out.println("record==="+record);
//        boolean validate = reqValidator.validate(authRequest);

//        if (validate) {
//            final String randomKey = jwtTokenUtil.getRandomKey();
//            final String token = jwtTokenUtil.generateToken(authRequest.getUserName(), randomKey);
//            return ResponseEntity.ok(new AuthResponse(token, randomKey));
//        } else {
//            throw new GunsException(BizExceptionEnum.AUTH_REQUEST_ERROR);
//        }
        return "200";
    }
}
