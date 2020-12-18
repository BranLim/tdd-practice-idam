package com.zuhlke.idam.domain;

import com.zuhlke.idam.domain.AuthenticatedUser;

public class AuthenticationResult {
    private AuthenticatedUser authenticatedUser;
    private boolean failed;
    private String message;

    public AuthenticationResult(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }

    public AuthenticationResult(boolean failed, String message) {
        this.failed = failed;
        this.message = message;
    }

    public boolean failed() {
        return failed;
    }

    public String getMessage() {
        return message;
    }


    public AuthenticatedUser getAuthenticatedUser() {
        return authenticatedUser;
    }
}
