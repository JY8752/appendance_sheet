package com.example.attendance_sheet.Common.Exception;

public class UserRoleNotFoundException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UserRoleNotFoundException(String message) {
        super(message);
    }

    public UserRoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
}