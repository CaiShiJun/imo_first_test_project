package com.tingyun.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * 凭证匹配器
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher{

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		
		//获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
        String inPassword = new String(upToken.getPassword());
        
        //获得数据库中的密码
        String dbPassword=(String) info.getCredentials();
        
        //进行密码的比对
        return this.equals(inPassword, dbPassword);
	}
	
}
