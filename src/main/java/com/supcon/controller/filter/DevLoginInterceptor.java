package com.supcon.controller.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.supcon.entity.OrderPlatformUser;
import com.supcon.util.CookieUtil;
import com.supcon.util.MD5;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * LoginInterceptor 登陆拦截器.
 *
 * @author Liyl
 * @date 2016/5/24
 */
@Component
public class DevLoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	boolean flag = false;
        
        //从session中获取userId，用于存cookie
        OrderPlatformUser user = (OrderPlatformUser) request.getSession().getAttribute("sessionLoginUser");
        String userId = null;
        if (user != null) {
            userId = String.valueOf(user.getId());
        }
        //每次访问都查看cookie中是否有公用链接，若没有则存入
        CookieUtil.saveCommonCookies(userId, request, response);

        String path = request.getContextPath();
        String url = request.getRequestURL().toString();
        
        if (!flag) {
            if (user == null) {
            	request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
                // response.sendRedirect(path+"/jsp/login");
                return flag;
            } else {
                if ("".equals(user.getUserName()) || user.getUserName() == null
                        || "".equals(user.getPassword()) || user.getPassword() == null) { //未登录，返回登录页面
                	request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
                	// response.sendRedirect(path + "/jsp/login");
                    return flag;
                } else { //已经登录，判断是否勾选记住密码功能
                    Cookie[] cookies = request.getCookies();
                    if (cookies != null && cookies.length > 0) {
                        String loginName = "";
                        String password = "";
                        for (Cookie cookie : cookies) {
                            if (cookie.getName().equals("username")) {
                                String loginNameCookie = cookie.getValue();
                                try {
                                    loginName = URLDecoder.decode(loginNameCookie, "UTF-8");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                            } else if (cookie.getName().equals("password")) {
                                String passwordCookie = cookie.getValue();
                                try {
                                    password = URLDecoder.decode(passwordCookie, "UTF-8");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        if (loginName != null && !"".equals(loginName)
                                && password != null && !"".equals(password)) {
                            //验证用户名密码的正确性
                            OrderPlatformUser platformUser = new OrderPlatformUser();
                            platformUser.setUserName(loginName);
                            platformUser.setPassword(MD5.parseStrToMd5L32(password));
//                            Result result = platformUserService.userLogin(platformUser);
//                            if (!"200".equals(result.getResultCode().code() + "")) {
//                                //若验证失败，则将cookie置为空
//                                DevLogonUtils.remeberMe("", "", "", request, response);
//                                response.sendRedirect(path + "/index");
//                                return flag;
//                            }
                        }
                    }
                    flag = true;
                    return flag;
                }
            }
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object
            handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
