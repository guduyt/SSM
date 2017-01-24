package com.yt.security.listener;

import com.yt.commons.utils.Util;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * Created by yt on 2016-11-03.
 */
@Component
public class SuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
        WebAuthenticationDetails auth = (WebAuthenticationDetails) authenticationSuccessEvent.getAuthentication().getDetails() ;
        Util.log.info("登录成功"+auth.getRemoteAddress());
    }
}
