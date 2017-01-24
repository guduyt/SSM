package com.yt.security.listener;

import com.yt.commons.utils.Util;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

/**
 * Created by yt on 2016-11-03.
 */
@Component
public class FailureEventListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent authenticationFailureBadCredentialsEvent) {
        Util.log.info("登录失败");
    }
}