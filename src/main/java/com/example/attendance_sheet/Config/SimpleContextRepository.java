package com.example.attendance_sheet.Config;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.attendance_sheet.Config.UserDetails.UserDetails;
import com.example.attendance_sheet.Config.UserDetails.UserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;

public class SimpleContextRepository implements SecurityContextRepository {

    private static final String AUTHENTICATED_USER_ID_KEY = SimpleContextRepository.class.getName()
        + ".AUTHENTICATED_USER_ID";
    
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        HttpServletRequest request = requestResponseHolder.getRequest();
        HttpSession httpSession = request.getSession(false);

        Optional<Integer> authenticatedUserId = readUserIdFromSession(httpSession);

        if(authenticatedUserId.isPresent()) {
            UserDetails userDetails = this.userDetailsService.loadUserById(authenticatedUserId.get());
            if(userDetails != null) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null);
                context.setAuthentication(authentication);
            }
        }
        return context;
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = context.getAuthentication();

        if(authentication == null) {
            HttpSession httpSession = request.getSession(false);
            if(httpSession != null) {
                httpSession.removeAttribute(AUTHENTICATED_USER_ID_KEY);
            }
        } else if(authentication.getPrincipal() != null && authentication.getPrincipal() instanceof UserDetails){
            HttpSession httpSession = request.getSession(!response.isCommitted());
            if(httpSession != null) {
                UserDetails authorizedUser = (UserDetails) authentication.getPrincipal();
                httpSession.setAttribute(AUTHENTICATED_USER_ID_KEY, authorizedUser.getId());
            }
        }
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) {
            return false;
        }
        boolean contains = session.getAttribute(AUTHENTICATED_USER_ID_KEY) != null;
        return contains;
    }

    private Optional<Integer> readUserIdFromSession(HttpSession httpSession) {
        if(httpSession == null) {
            return Optional.empty();
        }

        Object authorizedUserIdFromSession = httpSession.getAttribute(AUTHENTICATED_USER_ID_KEY);
        if(authorizedUserIdFromSession == null) {
            return Optional.empty();
        }

        if(!(authorizedUserIdFromSession instanceof Integer)) {
            return Optional.empty();
        }

        return Optional.of((Integer)authorizedUserIdFromSession);
    }

}
