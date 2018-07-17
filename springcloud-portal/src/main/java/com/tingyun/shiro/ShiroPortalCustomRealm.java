package com.tingyun.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


public class AuthRealm extends AuthorizingRealm {


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // token是用户输入的用户名和密码
        // 第一步从token中取出用户名
        String name = (String) authenticationToken.getPrincipal();

        // 第二步：根据用户输入的username从数据库查询
        Employee employee = null;
        try {
            employee = employeeService.findEmployeeByName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 如果查询不到返回null
        if(employee==null){
            throw new UnknownAccountException();//没找到帐号
        }

        // 从数据库查询到密码
        String password = employee.getPassword();

        //盐
        //String salt = "caishijun";

        // 如果查询到返回认证信息AuthenticationInfo

        //将activeUser设置simpleAuthenticationInfo
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(employee, password, this.getName());

        return simpleAuthenticationInfo;
    }
}