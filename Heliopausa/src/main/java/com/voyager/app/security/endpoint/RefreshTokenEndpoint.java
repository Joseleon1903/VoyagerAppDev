package com.voyager.app.security.endpoint;

import com.voyager.app.entity.User;
import com.voyager.app.service.UserService;
import com.voyager.app.security.auth.jwt.extractor.TokenExtractor;
import com.voyager.app.security.auth.jwt.verifier.TokenVerifier;
import com.voyager.app.config.JwtSettings;
import com.voyager.app.config.WebSecurityConfig;
import com.voyager.app.exceptions.InvalidJwtToken;
import com.voyager.app.security.model.UserContext;
import com.voyager.app.security.model.token.JwtToken;
import com.voyager.app.security.model.token.JwtTokenFactory;
import com.voyager.app.security.model.token.RawAccessJwtToken;
import com.voyager.app.security.model.token.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * RefreshTokenEndpoint
 *
 */
@RestController
public class RefreshTokenEndpoint {

    @Autowired
    private JwtTokenFactory tokenFactory;

    @Autowired
    private JwtSettings jwtSettings;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenVerifier tokenVerifier;

    @Autowired
    @Qualifier("jwtHeaderTokenExtractor")
    private TokenExtractor tokenExtractor;
    
    @RequestMapping(value="/api/auth/token", method= RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody
    JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConfig.AUTHENTICATION_HEADER_NAME));
        
        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey()).orElseThrow(() -> new InvalidJwtToken());

        String jti = refreshToken.getJti();
        if (!tokenVerifier.verify(jti)) {
            throw new InvalidJwtToken();
        }

        String subject = refreshToken.getSubject();
        User user = userService.getByUsername(subject).orElseThrow(() -> new UsernameNotFoundException("User not found: " + subject));

        if (user.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());

        UserContext userContext = UserContext.create(user.getUsername(), authorities);

        return tokenFactory.createAccessJwtToken(userContext);
    }
}
