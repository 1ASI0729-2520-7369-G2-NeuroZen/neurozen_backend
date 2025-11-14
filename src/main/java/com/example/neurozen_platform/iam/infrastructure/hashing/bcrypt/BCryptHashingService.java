package com.example.neurozen_platform.iam.infrastructure.hashing.bcrypt;

import com.example.neurozen_platform.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}

