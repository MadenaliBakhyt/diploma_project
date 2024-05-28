package com.example.diplomaproject.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {
    public static Authentication getAuthInfo() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getCurrentName() {
        return getAuthInfo().getName();
    }

}