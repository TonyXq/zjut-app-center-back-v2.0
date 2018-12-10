package com.xdbigdata.app_center.config;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.session.web.http.CookieSerializer;
import org.springframework.util.Base64Utils;

/**
 * @author ZhuTao
 * @create 2017-08-25 18:44
 */
public class CustomerCookiesSerializer implements CookieSerializer {

    private String cookieName = "SESSION";
    private Boolean useSecureCookie;
    private boolean useHttpOnlyCookie = this.isServlet3();
    private String cookiePath;
    private int cookieMaxAge = -1;
    private String domainName;
    private Pattern domainNamePattern;
    private String jvmRoute;
    private boolean useBase64Encoding;
    private String rememberMeRequestAttribute;

    // 修改getCookiePath方法体
    private String getCookiePath(HttpServletRequest request) {
        if (this.cookiePath == null) {
            // 此处改为返回根路径
            return "/";
        }
        return this.cookiePath;
    }

    public void setCookiePath(String cookiePath) {
        this.cookiePath = cookiePath;
    }

    public void setUseSecureCookie(boolean useSecureCookie) {
        this.useSecureCookie = useSecureCookie;
    }

    public void setDomainName(String domainName) {
        if (this.domainNamePattern != null) {
            throw new IllegalStateException(
                    "Cannot set both domainName and domainNamePattern");
        }
        this.domainName = domainName;
    }

    public void setDomainNamePattern(String domainNamePattern) {
        if (this.domainName != null) {
            throw new IllegalStateException(
                    "Cannot set both domainName and domainNamePattern");
        }
        this.domainNamePattern = Pattern.compile(domainNamePattern,
                Pattern.CASE_INSENSITIVE);
    }

    public void setJvmRoute(String jvmRoute) {
        this.jvmRoute = "." + jvmRoute;
    }

    public void setUseBase64Encoding(boolean useBase64Encoding) {
        this.useBase64Encoding = useBase64Encoding;
    }

    public void setRememberMeRequestAttribute(String rememberMeRequestAttribute) {
        if (rememberMeRequestAttribute == null) {
            throw new IllegalArgumentException(
                    "rememberMeRequestAttribute cannot be null");
        }
        this.rememberMeRequestAttribute = rememberMeRequestAttribute;
    }


    @Override
    public void writeCookieValue(CookieValue cookieValue) {
        HttpServletRequest request = cookieValue.getRequest();
        HttpServletResponse response = cookieValue.getResponse();
        String requestedCookieValue = cookieValue.getCookieValue();
        String actualCookieValue = this.jvmRoute == null ? requestedCookieValue : requestedCookieValue + this.jvmRoute;
        Cookie sessionCookie = new Cookie(this.cookieName, this.useBase64Encoding ? this.base64Encode(actualCookieValue) : actualCookieValue);
        sessionCookie.setSecure(this.isSecureCookie(request));
        sessionCookie.setPath(this.getCookiePath(request));
        String domainName = this.getDomainName(request);
        if (domainName != null) {
            sessionCookie.setDomain(domainName);
        }

        if (this.useHttpOnlyCookie) {
            sessionCookie.setHttpOnly(true);
        }

        if ("".equals(requestedCookieValue)) {
            sessionCookie.setMaxAge(0);
        } else if (this.rememberMeRequestAttribute != null && request.getAttribute(this.rememberMeRequestAttribute) != null) {
            sessionCookie.setMaxAge(2147483647);
        } else {
            sessionCookie.setMaxAge(this.cookieMaxAge);
        }

        response.addCookie(sessionCookie);
    }


    private boolean isSecureCookie(HttpServletRequest request) {
        return this.useSecureCookie == null ? request.isSecure() : this.useSecureCookie;
    }


    private String getDomainName(HttpServletRequest request) {
        if (this.domainName != null) {
            return this.domainName;
        } else {
            if (this.domainNamePattern != null) {
                Matcher matcher = this.domainNamePattern.matcher(request.getServerName());
                if (matcher.matches()) {
                    return matcher.group(1);
                }
            }

            return null;
        }
    }


    private boolean isServlet3() {
        try {
            ServletRequest.class.getMethod("startAsync");
            return true;
        } catch (NoSuchMethodException var2) {
            return false;
        }
    }


    @Override
    public List<String> readCookieValues(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        List<String> matchingCookieValues = new ArrayList();
        if (cookies != null) {
            Cookie[] var4 = cookies;
            int var5 = cookies.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                Cookie cookie = var4[var6];
                if (this.cookieName.equals(cookie.getName())) {
                    String sessionId = this.useBase64Encoding ? this.base64Decode(cookie.getValue()) : cookie.getValue();
                    if (sessionId != null) {
                        if (this.jvmRoute != null && sessionId.endsWith(this.jvmRoute)) {
                            sessionId = sessionId.substring(0, sessionId.length() - this.jvmRoute.length());
                        }

                        matchingCookieValues.add(sessionId);
                    }
                }
            }
        }

        return matchingCookieValues;
    }

    private String base64Decode(String base64Value) {
        try {
            byte[] decodedCookieBytes = Base64Utils.decode(base64Value.getBytes());
            return new String(decodedCookieBytes);
        } catch (Exception var3) {
            return null;
        }
    }

    private String base64Encode(String value) {
        byte[] encodedCookieBytes = Base64Utils.encode(value.getBytes());
        return new String(encodedCookieBytes);
    }
}
