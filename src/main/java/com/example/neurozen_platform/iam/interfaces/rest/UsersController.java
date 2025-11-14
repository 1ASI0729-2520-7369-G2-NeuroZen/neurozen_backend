package com.example.neurozen_platform.iam.interfaces.rest;

import com.example.neurozen_platform.iam.domain.model.queries.GetAllUsersQuery;
import com.example.neurozen_platform.iam.domain.model.queries.GetUserByIdQuery;
import com.example.neurozen_platform.iam.domain.services.UserQueryService;
import com.example.neurozen_platform.iam.interfaces.rest.resources.UserResource;
import com.example.neurozen_platform.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User Management Endpoints")
public class UsersController {

  @Autowired
  private UserQueryService userQueryService;

  @Operation(
    summary = "Get all users",
    description = "Retrieves a list of all registered users. Requires ADMIN role.",
    security = @SecurityRequirement(name = "bearerAuth")
  )
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      description = "Successfully retrieved users list",
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = UserResource.class)
      )
    ),
    @ApiResponse(
      responseCode = "403",
      description = "Access denied - ADMIN role required",
      content = @Content
    )
  })
  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<UserResource>> getAllUsers() {
    var users = userQueryService.handle(new GetAllUsersQuery());
    var userResources = users.stream()
      .map(UserResourceFromEntityAssembler::toResourceFromEntity)
      .collect(Collectors.toList());
    return ResponseEntity.ok(userResources);
  }

  @Operation(
    summary = "Get user by ID",
    description = "Retrieves a specific user by their ID. Requires authentication.",
    security = @SecurityRequirement(name = "bearerAuth")
  )
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      description = "Successfully retrieved user",
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = UserResource.class)
      )
    ),
    @ApiResponse(
      responseCode = "404",
      description = "User not found",
      content = @Content
    ),
    @ApiResponse(
      responseCode = "401",
      description = "Unauthorized - Authentication required",
      content = @Content
    )
  })
  @GetMapping("/{userId}")
  public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {
    var user = userQueryService.handle(new GetUserByIdQuery(userId));
    if (user.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
    return ResponseEntity.ok(userResource);
  }
}

