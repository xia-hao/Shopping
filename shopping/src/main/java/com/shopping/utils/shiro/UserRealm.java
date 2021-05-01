package com.shopping.utils.shiro;

/**
 * @PACKAGE_NAME: com.shopping.utils.shiro
 * @author: XIA
 * @NAME: UserRealm
 * @DATE: 2020/3/24
 **/

import com.shopping.domain.User;
import com.shopping.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        return null;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        //判断用户是否存在
        User loginUser=userService.getUserByLoginNameOrMobile(token.getUsername());

        if(loginUser==null){
            System.out.println("用户未登陆...");
            return null;
        }
        //判断密码
        return new SimpleAuthenticationInfo(loginUser,loginUser.getPassword(),"");
    }
}