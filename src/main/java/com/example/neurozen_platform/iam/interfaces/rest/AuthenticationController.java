package com.example.neurozen_platform.iam.interfaces.rest;

import com.example.neurozen_platform.iam.domain.services.UserCommandService;
import com.example.neurozen_platform.iam.interfaces.rest.resources.AuthenticatedUserResource;
import com.example.neurozen_platform.iam.interfaces.rest.resources.SignInResource;
import com.example.neurozen_platform.iam.interfaces.rest.resources.SignUpResource;
import com.example.neurozen_platform.iam.interfaces.rest.resources.UserResource;
import com.example.neurozen_platform.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import com.example.neurozen_platform.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import com.example.neurozen_platform.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import com.example.neurozen_platform.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Endpoints")
public class AuthenticationController {

  @Autowired
  private UserCommandService userCommandService;

  @Operation(
    summary = "Register a new user",
    description = "Creates a new user account with the provided information. Available roles: ROLE_USER, ROLE_PSYCHOLOGIST, ROLE_ADMIN"
  )
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "201",
      description = "User successfully registered",
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = UserResource.class)
      )
    ),
    @ApiResponse(
      responseCode = "400",
      description = "Invalid input data or username/email already exists",
      content = @Content
    )
  })
  @PostMapping("/sign-up")
  public ResponseEntity<UserResource> signUp(@RequestBody SignUpResource resource) {
    try {
      var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(resource);
      var user = userCommandService.handle(signUpCommand);
      
      if (user.isEmpty()) {
        return ResponseEntity.badRequest().build();
      }
      
      var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
      return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @Operation(
    summary = "Sign in to the platform",
    description = "Authenticates a user and returns a JWT token for subsequent requests"
  )
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      description = "Successfully authenticated",
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = AuthenticatedUserResource.class)
      )
    ),
    @ApiResponse(
      responseCode = "401",
      description = "Invalid username or password",
      content = @Content
    )
  })
  @PostMapping("/sign-in")
  public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody SignInResource resource) {
    try {
      var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(resource);
      var authenticatedUser = userCommandService.handle(signInCommand);
      
      if (authenticatedUser.isEmpty()) {
        return ResponseEntity.notFound().build();
      }
      
      var user = authenticatedUser.get().getLeft();
      var token = authenticatedUser.get().getRight();
      var authenticatedUserResource = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(user, token);
      
      return ResponseEntity.ok(authenticatedUserResource);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }
}

