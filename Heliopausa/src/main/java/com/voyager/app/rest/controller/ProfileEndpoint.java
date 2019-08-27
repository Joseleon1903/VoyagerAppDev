package com.voyager.app.rest.controller;

import com.voyager.app.security.auth.JwtAuthenticationToken;
import com.voyager.app.security.model.UserContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * End-point for retrieving logged-in user details.
 */
@RestController
public class ProfileEndpoint {

    @RequestMapping(value="/api/me", method= RequestMethod.GET)
    public @ResponseBody UserContext get(JwtAuthenticationToken token) {

        return (UserContext) token.getPrincipal();
    }
}
