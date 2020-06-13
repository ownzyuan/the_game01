package com.zy.config.shiro;

import com.zy.entity.Admin;
import com.zy.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;

    /**
     * 管理员必须登录了才会被 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        Admin currentAdmin = (Admin) subject.getPrincipal();
        if (currentAdmin.getPerms()!=null){
            info.addStringPermission("admin:login");
        }
        info.addStringPermission(currentAdmin.getPerms());

        return info;
    }

    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        Admin admin = adminService.getByUserName(token.getUsername());
        if (admin == null) {
            return null;
        }

        return new SimpleAuthenticationInfo(admin, admin.getPassword(), "");
    }
}
