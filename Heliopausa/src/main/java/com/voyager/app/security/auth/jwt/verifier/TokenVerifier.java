package com.voyager.app.security.auth.jwt.verifier;

public interface TokenVerifier {
    public boolean verify(String jti);
}
