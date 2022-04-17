package com.hygge.vblog.shiro;

import com.hygge.vblog.common.result.Result;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname JwtController
 * @Description TODO    异常控制器
 * @Version 1.0.0
 * @Date 2022/4/17 21:15
 * @Created by hygge
 */
@RestController
public class JwtController {

    @RequestMapping("/expiredJwtException")
    public Result getExpiredCredentialsException(){
        throw new ExpiredCredentialsException("token已失效，请重新登录");
    }

}
