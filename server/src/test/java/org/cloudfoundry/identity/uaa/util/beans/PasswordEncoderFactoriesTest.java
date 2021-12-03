package org.cloudfoundry.identity.uaa.util.beans;

import org.junit.Test;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordEncoderFactoriesTest {
    @Test
    public void createDelegatingPasswordEncoder() {
        String encodingId = "sha256";
        assertTrue(PasswordEncoderFactories.createDelegatingPasswordEncoder(encodingId) instanceof DelegatingPasswordEncoder);
    }
}
