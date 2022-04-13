package com.hygge.vblog.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hygge.vblog.common.util.JwtUtil;
import com.hygge.vblog.domain.VUser;
import com.hygge.vblog.service.VUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/2/5 19:48
 * @description TODO
 */
@Component
public class AccountRealm extends AuthorizingRealm{

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    VUserService userService;

    /**
     * 为了让realm支持jwt的凭证校验
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
    /**
     * 身份信息效验(登录)
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;
        String userId = jwtUtil.parseJWT((String) jwtToken.getPrincipal()).getSubject();
        String userid = "userId";
        QueryWrapper<VUser> eq = new QueryWrapper<>();
        VUser user = userService.getById(Integer.valueOf(userId));
        if(user == null){
            throw new UnknownAccountException("用户不存在");
        }
        //用户存在返回一些基本信息 给shiro
        AccountProfile accountProfile = new AccountProfile();
        accountProfile.setId(user.getId());
        accountProfile.setUserName(user.getUserName());
        accountProfile.setEmail(user.getEmail());
        return new SimpleAuthenticationInfo(accountProfile, jwtToken.getCredentials(), getName());
    }
    /**
     * 权限验证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }
}
