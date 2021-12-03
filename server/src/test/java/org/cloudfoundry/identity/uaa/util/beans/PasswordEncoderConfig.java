package org.cloudfoundry.identity.uaa.util.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderConfig {

    private static Logger logger = LoggerFactory.getLogger(PasswordEncoderConfig.class);

    @Bean
    public PasswordEncoder nonCachingPasswordEncoder() {

        String encodingId = "noop";

        logger.info(String.format("TEST CONTEXT - Building DelegatingPasswordEncoder with desired encoder {%s}", encodingId));

        DelegatingPasswordEncoder passwordEncoder = (DelegatingPasswordEncoder)PasswordEncoderFactories.createDelegatingPasswordEncoder(encodingId);
        passwordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());

        return passwordEncoder;
    }
}