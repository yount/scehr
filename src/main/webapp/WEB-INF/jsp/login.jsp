<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String key = String.valueOf(request.getAttribute("key"));
    String username = "";
    String password = "";
    Cookie[] cookies = request.getCookies();
    if(cookies != null) {
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals("username")) {
                username = cookie.getValue();
            } else if(cookie.getName().equals("password")) {
                password = cookie.getValue();
            }
        }
    }
%>
<%@include file="../../staticResources/login.html" %>
