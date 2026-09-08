package com.oodles.drdemoclaimsprocessor;

import jakarta.servlet.http.Cookie;

public class SessionCookies {

    /** Session cookie attached after a successful login. */
    public Cookie issue(String token) {
        Cookie cookie = new Cookie("session", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }

    /** Non-sensitive preference cookie. */
    public Cookie preference(String theme) {
        Cookie cookie = new Cookie("theme", theme);
        cookie.setPath("/");
        return cookie;
    }
}
