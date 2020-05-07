package com.shopping.utils.shiro;

/**
 * @PACKAGE_NAME: com.shopping.utils.shiro
 * @author: XIA
 * @NAME: ShiroConfig
 * @DATE: 2020/3/24
 **/

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置类
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建Realm
     * @return
     */
    @Bean(name = "userRealm")
    public UserRealm getUserReaml(){
        return new UserRealm();
    }

    /**
     * 创建DefaultWebSecurityManager
     * @param userRealm
     * @return
     */
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建ShiroFilterFactoryBean
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/login");

        /**
         * Shiro内置过滤器，可以实现权限相关的拦截
         *      常用的过滤器有：
         *          anon：无需认证（登录）就可以访问
         *          authc：必须认证才能够访问
         *          user：如果使用rememberMe功能可以直接访问
         *          perms：该资源必须得到资源权限才可以访问
         *          role：该资源必须得到角色权限才可以访问
         */

        Map<String, Filter> filters = new HashMap<>();
        MyPassThruAuthenticationFilter authenticationFilter = new MyPassThruAuthenticationFilter();
        filters.put("authc",authenticationFilter);
        shiroFilterFactoryBean.setFilters(filters);

        Map<String,String> filterMap=new LinkedHashMap<String, String>();
        filterMap.put("/login","anon");
        filterMap.put("/img","anon");
        filterMap.put("/register","anon");
        filterMap.put("/loginOut","anon");
        filterMap.put("/user/getUser","anon");
        filterMap.put("/discuss/list","anon");
        filterMap.put("/shopCart/getCount","anon");
        filterMap.put("/collectible/get/*","anon");
        filterMap.put("/user/**","authc");
        filterMap.put("/discuss/**","authc");
        filterMap.put("/userDealrecord/**","authc");
        filterMap.put("/district/**","authc");
        filterMap.put("/payMent/**","authc");
        filterMap.put("/refund/**","authc");
        filterMap.put("/collectible/**","authc");
        filterMap.put("/order/**","authc");
        filterMap.put("/shopCart/**","authc");
        filterMap.put("/userAddress/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }
}