package com.example.neurozen_platform.iam.application.internal.commandservices;

import com.example.neurozen_platform.iam.application.internal.outboundservices.hashing.HashingService;
import com.example.neurozen_platform.iam.domain.model.aggregates.User;
import com.example.neurozen_platform.iam.domain.model.commands.SignInCommand;
import com.example.neurozen_platform.iam.domain.model.commands.SignUpCommand;
import com.example.neurozen_platform.iam.domain.model.valueobjects.Roles;
import com.example.neurozen_platform.iam.domain.services.UserCommandService;
import com.example.neurozen_platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.example.neurozen_platform.iam.infrastructure.tokens.jwt.BearerTokenService;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private HashingService hashingService;

  @Autowired
  private BearerTokenService tokenService;

  @Override
  public Optional<User> handle(SignUpCommand command) {
    if (userRepository.existsByUsername(command.username())) {
      throw new RuntimeException("Username already exists");
    }
    if (userRepository.existsByEmail(command.email())) {
      throw new RuntimeException("Email already exists");
    }

    var user = new User(
      command.username(),
      command.email(),
      hashingService.encode(command.password()),
      command.firstName(),
      command.lastName()
    );

    if (command.roles() != null && !command.roles().isEmpty()) {
      user.addRoles(command.roles());
    } else {
      user.addRole(Roles.ROLE_USER);
    }

    var savedUser = userRepository.save(user);
    return Optional.of(savedUser);
  }

  @Override
  public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
    var user = userRepository.findByUsername(command.username());

    if (user.isEmpty()) {
      throw new RuntimeException("Invalid username or password");
    }

    if (!hashingService.matches(command.password(), user.get().getPassword())) {
      throw new RuntimeException("Invalid username or password");
    }

    var token = tokenService.generateToken(user.get().getUsername());
    return Optional.of(new ImmutablePair<>(user.get(), token));
  }
}

