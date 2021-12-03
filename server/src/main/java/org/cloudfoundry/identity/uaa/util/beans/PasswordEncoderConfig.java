package org.cloudfoundry.identity.uaa.util.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class PasswordEncoderConfig {

    private static Logger logger = LoggerFactory.getLogger(PasswordEncoderConfig.class);

    @Bean
    public PasswordEncoder nonCachingPasswordEncoder(Environment environment) {
        String encodingId = configuredDatabaseEncodingId(environment);
        logger.info(String.format("Building DelegatingPasswordEncoder with desired encoder {%s}", encodingId));

        DelegatingPasswordEncoder passwordEncoder = (DelegatingPasswordEncoder)PasswordEncoderFactories.createDelegatingPasswordEncoder(encodingId);
        passwordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());

        return passwordEncoder;
    }

    private String configuredDatabaseEncodingId(Environment environment) {
        return Optional.ofNullable(environment.getProperty("database.passwordEncodingId")).orElse("bcrypt");
    }
}
