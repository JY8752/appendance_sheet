package com.example.attendance_sheet.Config;

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionTrackingMode;

import org.springframework.boot.web.servlet.ServletContextInitializer;

public class CookieOnlySessionTrackingServletContextInitializer implements ServletContextInitializer{

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException{
        servletContext.setSessionTrackingModes(Set.of(SessionTrackingMode.COOKIE));
    }
    
}