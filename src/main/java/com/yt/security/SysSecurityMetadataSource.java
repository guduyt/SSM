package com.yt.security;

import com.entity.manual.model.Resource;
import com.entity.manual.model.Role;
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

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by yt on 2016-10-14.
 * 资源的访问权限的定义加载器
 */
@Service("sysSecurityMetadataSource")
public class SysSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    public static final Logger log= LoggerFactory.getLogger(SysSecurityMetadataSource.class);
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

    @Autowired
    private UserDAO userDAO;

    private Map<String, Collection<ConfigAttribute>> resourceMap = null;
    private RequestMatcher requestMatcher;
    private SessionRegistry sessionRegistry;


    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
         if(null==resourceMap || resourceMap.size()==0)
             resourceMap=loadResource();
        Collection<ConfigAttribute> collection = new ArrayList<ConfigAttribute>();

        HttpServletRequest request = ((FilterInvocation) object).getRequest();

        Iterator<String> iterator = resourceMap.keySet().iterator();
        while (iterator.hasNext()) {
            String requestURL = iterator.next().trim();
            RequestMatcher requestMatcher = new AntPathRequestMatcher(requestURL);
            if (requestMatcher.matches(request)) {
                //获得该uri所需要的角色列表
                collection.addAll(resourceMap.get(requestURL))  ;
            }
        }
        return collection;
    }



    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }


    public boolean supports(Class<?> clazz) {
        return true;
    }

    public HashMap<String,Collection<ConfigAttribute>> loadResource(){
             List<Resource> list=userDAO.queryResourcesAndRoles(null);
        HashMap<String,Collection<ConfigAttribute>> hashMap=new HashMap<>();
        list.forEach(p->{
            if(hashMap.containsKey(p.getResourcePath())) {
                Collection<ConfigAttribute> collects=hashMap.get(p.getResourcePath());

                collects.addAll(rolesToCollection(p.getListRole()));
                hashMap.put(p.getResourcePath(),collects);
            }else {
                Collection<ConfigAttribute> collects=new ArrayList<>();
                collects.addAll(rolesToCollection(p.getListRole()));
                hashMap.put(p.getResourcePath(),collects);
            }

        });

        return hashMap;
    }

    private Collection<ConfigAttribute> rolesToCollection(List<Role> list) {
        List<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
        for (Role role : list)
            configAttributes.add(new SecurityConfig(role.getRoleName()));
        return configAttributes;
    }

    public void  Refresh(){
        resourceMap.clear();
        resourceMap= loadResource();
    }
}
