package com.example.neurozen_platform.iam.domain.model.aggregates;

import com.example.neurozen_platform.iam.domain.model.valueobjects.Roles;
import com.example.neurozen_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class User extends AuditableAbstractAggregateRoot<User> {

  @NotBlank
  @Size(max = 50)
  @Column(unique = true)
  private String username;

  @NotBlank
  @Size(max = 100)
  @Email
  @Column(unique = true)
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;

  @NotBlank
  @Size(max = 50)
  private String firstName;

  @NotBlank
  @Size(max = 50)
  private String lastName;

  @ElementCollection(fetch = FetchType.EAGER)
  @Enumerated(EnumType.STRING)
  @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
  @Column(name = "role")
  private Set<Roles> roles = new HashSet<>();

  public User() {
  }

  public User(String username, String email, String password, String firstName, String lastName) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.roles = new HashSet<>();
  }

  public User addRole(Roles role) {
    this.roles.add(role);
    return this;
  }

  public User addRoles(List<Roles> roles) {
    var validatedRoleSet = Roles.validateRoleSet(roles);
    this.roles.addAll(validatedRoleSet);
    return this;
  }

  public String getFullName() {
    return firstName + " " + lastName;
  }
}

