package com.example.neurozen_platform.iam.application.internal.queryservices;

import com.example.neurozen_platform.iam.domain.model.aggregates.User;
import com.example.neurozen_platform.iam.domain.model.queries.GetAllUsersQuery;
import com.example.neurozen_platform.iam.domain.model.queries.GetUserByIdQuery;
import com.example.neurozen_platform.iam.domain.model.queries.GetUserByUsernameQuery;
import com.example.neurozen_platform.iam.domain.services.UserQueryService;
import com.example.neurozen_platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public List<User> handle(GetAllUsersQuery query) {
    return userRepository.findAll();
  }

  @Override
  public Optional<User> handle(GetUserByIdQuery query) {
    return userRepository.findById(query.userId());
  }

  @Override
  public Optional<User> handle(GetUserByUsernameQuery query) {
    return userRepository.findByUsername(query.username());
  }
}

