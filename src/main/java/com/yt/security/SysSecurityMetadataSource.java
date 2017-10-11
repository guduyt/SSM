package com.yt.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import com.entity.manual.model.Resource;
import com.entity.manual.model.Role;

/**
 * Created by yt on 2016-10-14.
 * 资源的访问权限的定义加载器
 */
@Service("sysSecurityMetadataSource")
public class SysSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private UserDAO userDAO;

    private Map<String, Collection<ConfigAttribute>> resourceMap = null;
    private RequestMatcher requestMatcher;
    private SessionRegistry sessionRegistry;

    public static final Logger log = LoggerFactory.getLogger(SysSecurityMetadataSource.class);

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public RequestMatcher getRequestMatcher() {
        return requestMatcher;
    }

    public void setRequestMatcher(RequestMatcher requestMatcher) {
        this.requestMatcher = requestMatcher;
    }

    public SessionRegistry getSessionRegistry() {
        return sessionRegistry;
    }

    public void setSessionRegistry(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object){
        if (null == resourceMap || resourceMap.size() == 0)
            resourceMap = loadResource();
        Collection<ConfigAttribute> collection = new ArrayList<>();

        HttpServletRequest request = ((FilterInvocation) object).getRequest();

        Iterator<String> iterator = resourceMap.keySet().iterator();
        while (iterator.hasNext()) {
            String requestURL = iterator.next().trim();
            RequestMatcher requestMatcherRole = new AntPathRequestMatcher(requestURL);
            if (requestMatcherRole.matches(request)) {
                //获得该uri所需要的角色列表
                collection.addAll(resourceMap.get(requestURL));
            }
        }
        return collection;
    }


    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    public Map<String, Collection<ConfigAttribute>> loadResource() {
        List<Resource> list = userDAO.queryResourcesAndRoles(null);
        Map<String, Collection<ConfigAttribute>> hashMap = new HashMap<>();
        list.forEach(p -> {
            if (hashMap.containsKey(p.getResourcePath())) {
                Collection<ConfigAttribute> collects = hashMap.get(p.getResourcePath());

                collects.addAll(rolesToCollection(p.getListRole()));
                hashMap.put(p.getResourcePath(), collects);
            } else {
                Collection<ConfigAttribute> collects = new ArrayList<>();
                collects.addAll(rolesToCollection(p.getListRole()));
                hashMap.put(p.getResourcePath(), collects);
            }

        });

        return hashMap;
    }

    private Collection<ConfigAttribute> rolesToCollection(List<Role> list) {
        List<ConfigAttribute> configAttributes = new ArrayList<>();
        for (Role role : list)
            configAttributes.add(new SecurityConfig(role.getRoleName()));
        return configAttributes;
    }

    public void refresh() {
        resourceMap.clear();
        resourceMap = loadResource();
    }
}
