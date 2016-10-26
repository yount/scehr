package com.supcon.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * CookieUtil 公用参数存cookie
 *
 * @author Liyl
 * @date 2016/9/20
 */
public class CookieUtil {

    /**
     * 保存公用参数
     * @param userId
     * @param request
     * @param response
     */
    public static void saveCommonCookies(String userId, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Cookie[] cookies = request.getCookies();
        String id = null;
        String actionPath = null;
        String imgCodeUrl = null;
        String loginUrl = null;
        String indexUrl = null;
        String registrationUrl = null;
        String sessionID = null;

        if (cookies != null && cookies.length > 0) {
            for (Cookie commonCookie : cookies) {
                if (commonCookie.getName().equals("userId")) {
                    id = commonCookie.getValue().toString();
                }
                if (commonCookie.getName().equals("actionPath")) {
                    actionPath = commonCookie.getValue().toString();
                }

                if (commonCookie.getName().equals("imgCodeUrl")) {
                    imgCodeUrl = commonCookie.getValue().toString();
                }
                if (commonCookie.getName().equals("loginUrl")) {
                    loginUrl = commonCookie.getValue().toString();
                }
                if (commonCookie.getName().equals("indexUrl")) {
                    indexUrl = commonCookie.getValue().toString();
                }
                if (commonCookie.getName().equals("registrationUrl")) {
                    registrationUrl = commonCookie.getValue().toString();
                }
                if (commonCookie.getName().equals("sessionID")) {
                  sessionID = commonCookie.getValue().toString();
              }
            }
        }

        if (id == null || actionPath == null || imgCodeUrl == null
                || loginUrl == null || indexUrl == null || registrationUrl == null || sessionID == null) {
            Map<String,String> paramMap = new HashMap<String, String>();
            String path = (request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + request.getContextPath() + "/");
            if(userId == null || "".equals(userId)) {
                paramMap.put("userId", userId);
            } else {
                paramMap.put("userId", URLEncoder.encode(userId, "UTF-8"));
            }
            sessionID = request.getSession().getId();
            paramMap.put("actionPath", URLEncoder.encode(path, "UTF-8"));
            paramMap.put("pagePath", URLEncoder.encode(path, "UTF-8"));
            paramMap.put("imgCodeUrl", URLEncoder.encode(path + "devCode", "UTF-8"));
            paramMap.put("loginUrl", URLEncoder.encode(path + "loginPage", "UTF-8"));
            paramMap.put("indexUrl", URLEncoder.encode(path + "index", "UTF-8"));
            paramMap.put("registrationUrl", URLEncoder.encode(path + "registerPage", "UTF-8"));
            paramMap.put("imgPath", URLEncoder.encode(path + "/staticResources/img/", "UTF-8"));
            paramMap.put("staticPath", URLEncoder.encode(path, "UTF-8"));
            paramMap.put("sessionID", URLEncoder.encode(sessionID,"UTF-8"));
            saveCookie(paramMap, request, response);
        }
    }


    /**
     * 将公用参数保存至cookie中
     *
     * @param paramMap 保存参数map
     */
    public static void saveCookie(Map<String, String> paramMap, HttpServletRequest
            request, HttpServletResponse response) {

        Iterator entries = paramMap.entrySet().iterator();

        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();

            Cookie cookie = new Cookie(key, value);
            //2:设置Cookie的有效路径（指定当前项目）
            cookie.setPath(request.getContextPath());
            //3.设置cookie的有效时间
//            cookie.setMaxAge(7 * 24 * 60 * 60);
            //4：将Cookie存放到response中
            response.addCookie(cookie);
        }
    }
}
