package cn.edu.gxu.oisback.controller;

import cn.edu.gxu.oisback.utils.FirewallUtil;
import cn.edu.gxu.oisback.utils.IpUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OnlineJudgeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = IpUtil.loadRemoteUserIP(request);
        if(FirewallUtil.isActive(ip)) {

        }
        // 未激活IP
        return false;
    }
}
