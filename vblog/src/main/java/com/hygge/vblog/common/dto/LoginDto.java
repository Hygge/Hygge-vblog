package com.hygge.vblog.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/2/5 20:25
 * @description TODO
 */
@Data
public class LoginDto implements Serializable {

    @NotBlank(message = "userName不能为空")
    private String userName;
    @NotBlank(message = "password不能为空")
    private String password;
    private String captchaVerification;


}
