package com.hygge.vblog.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/2/5 20:15
 * @description TODO
 */
@Data
public class AccountProfile implements Serializable {

    private Integer id;
    private String userName;
    private String email;

}
