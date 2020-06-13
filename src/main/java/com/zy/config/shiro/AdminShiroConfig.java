package com.zy.config.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.zy.utils.ShiroConfigUtil;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class AdminShiroConfig {

    @Bean(name = "adminRealm")
    public AdminRealm adminRealm() {
        return new AdminRealm();
    }

    @Bean(name = "adminManager")
    public DefaultWebSecurityManager getAdminManager(@Qualifier("adminRealm") AdminRealm adminRealm) {
        DefaultWebSecurityManager adminManager = new DefaultWebSecurityManager();

        //关联EmployeeRealm
        adminManager.setRealm(adminRealm);
        return adminManager;
    }

    @Bean(name = "adminShiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryAdmin(@Qualifier("adminManager") DefaultWebSecurityManager adminManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        //设置安全管理器
        bean.setSecurityManager(adminManager);

        /**
         * anon：无需认证就可以访问
         * authc：必须认证了才能访问
         * user：必须用有了 记住我 功能才能用
         * perms：拥有对某个资源的权限才能访问
         * role：拥有某个角色权限才能访问
         */
        //添加拦截
        Map<String ,String > map = new LinkedHashMap<>();

        //不过滤
        map.put("/layui/**","anon");
        map.put("/bootstrap/**","anon");
        map.put("/img/**","anon");
        map.put("/js/**","anon");
        map.put("/css/**","anon");
        map.put("/lib/**","anon");
        map.put("/fonts/**","anon");
        map.put("/icons/** ","anon");
        map.put("/page/home/**","anon");
        map.put("/page/login/**","anon");
        map.put("/page/register/**","anon");
        map.put("/page/404/**","anon");
        map.put("/loginController/login","anon");

        //管理页面
        map.put("/manageLol","perms[admin:login]");
        map.put("/manageForces","perms[admin:login]");
        map.put("/add","perms[admin:login]");
        map.put("/add/*","perms[admin:login]");
        map.put("/up","perms[admin:login]");
        map.put("/up/*","perms[admin:login]");
        map.put("/delete","perms[admin:login]");

        bean.setFilterChainDefinitionMap(map);

        //未登录就直接跳在登录页面
        bean.setLoginUrl("/admin/");

        return bean;
    }

    public ShiroDialect getShiroDialect(){
        return ShiroConfigUtil.getShiroDialect();
    }

}
