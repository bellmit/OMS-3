package com.baiyang.oms.rest.modular.example;

import com.baiyang.oms.core.common.SimpleObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 常规控制器
 *
 * @author fengshuonan
 * @date 2017-08-23 16:02
 */
@Controller
@Slf4j
@RequestMapping("/hello")
public class ExampleController {

    @RequestMapping("")
    public ResponseEntity hello(@RequestBody SimpleObject simpleObject) {
        log.info("hello");
        System.out.println(simpleObject.getUser());
        return ResponseEntity.ok("请求成功!");
    }

    @RequestMapping("/test")
    public ResponseEntity test() {
        log.info("test");
        return ResponseEntity.ok("请求成功!");
    }
}
