package com.yt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import java.io.IOException;

/**
 * Created by yt on 2016-10-17.
 * 权限拦截器
 */
@Service("sysFilter")
public class SysFilter extends AbstractSecurityInterceptor implements Filter {
    @Autowired
    @Qualifier("sysSecurityMetadataSource")
    private SecurityMetadataSource securityMetadataSource;

    @Autowired
    @Qualifier("sysAccessDecisionManager")
    private AccessDecisionManager accessDecisionManager;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager ;

    @PostConstruct
    public void init(){
        super.setAccessDecisionManager(accessDecisionManager);
        super.setAuthenticationManager(authenticationManager);
    }


    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

     @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);
        InterceptorStatusToken token = super.beforeInvocation(fi);

        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            super.afterInvocation(token,null);
        }


    }

    @Override
    public void destroy() {

    }
}
