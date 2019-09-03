package com.voyager.app.rest.controller.closed;

import com.voyager.app.security.auth.JwtAuthenticationToken;
import com.voyager.app.security.model.UserContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * End-point for retrieving logged-in user details.
 */
@RestController
public class ProfileEndpoint {

    private static Logger log = LogManager.getLogger(ProfileEndpoint.class);


    @RequestMapping(value="/api/me", method= RequestMethod.GET)
    public @ResponseBody UserContext get(JwtAuthenticationToken token) {
        log.info("entering api / me");

        return (UserContext) token.getPrincipal();
    }
}
