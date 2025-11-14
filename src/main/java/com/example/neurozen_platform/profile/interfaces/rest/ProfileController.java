package com.example.neurozen_platform.profile.interfaces.rest;

import com.example.neurozen_platform.profile.domain.model.queries.GetAllPsychologistsQuery;
import com.example.neurozen_platform.profile.domain.model.queries.GetProfileByIdQuery;
import com.example.neurozen_platform.profile.domain.model.queries.GetProfileByUserIdQuery;
import com.example.neurozen_platform.profile.domain.model.queries.GetPsychologistsBySpecialtyQuery;
import com.example.neurozen_platform.profile.domain.services.ProfileCommandService;
import com.example.neurozen_platform.profile.domain.services.ProfileQueryService;
import com.example.neurozen_platform.profile.interfaces.rest.resources.CreatePatientProfileResource;
import com.example.neurozen_platform.profile.interfaces.rest.resources.CreatePsychologistProfileResource;
import com.example.neurozen_platform.profile.interfaces.rest.resources.ProfileResource;
import com.example.neurozen_platform.profile.interfaces.rest.resources.UpdateProfileResource;
import com.example.neurozen_platform.profile.interfaces.rest.transform.CreatePatientProfileCommandFromResourceAssembler;
import com.example.neurozen_platform.profile.interfaces.rest.transform.CreatePsychologistProfileCommandFromResourceAssembler;
import com.example.neurozen_platform.profile.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import com.example.neurozen_platform.profile.interfaces.rest.transform.UpdateProfileCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
@Tag(name = "Profiles", description = "Profile management endpoints for patients and psychologists")
public class ProfileController {

  @Autowired
  private ProfileCommandService profileCommandService;

  @Autowired
  private ProfileQueryService profileQueryService;

  @PostMapping("/patients")
  @Operation(summary = "Create patient profile", description = "Creates a new patient profile")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Patient profile created successfully"),
    @ApiResponse(responseCode = "400", description = "Invalid input data"),
    @ApiResponse(responseCode = "409", description = "Profile already exists")
  })
  public ResponseEntity<ProfileResource> createPatientProfile(@RequestBody CreatePatientProfileResource resource) {
    var command = CreatePatientProfileCommandFromResourceAssembler.toCommandFromResource(resource);
    var profile = profileCommandService.handle(command);
    
    if (profile.isEmpty()) {
      return ResponseEntity.badRequest().build();
    }
    
    var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
    return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
  }

  @PostMapping("/psychologists")
  @Operation(summary = "Create psychologist profile", description = "Creates a new psychologist profile with professional details")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Psychologist profile created successfully"),
    @ApiResponse(responseCode = "400", description = "Invalid input data"),
    @ApiResponse(responseCode = "409", description = "Profile already exists")
  })
  public ResponseEntity<ProfileResource> createPsychologistProfile(@RequestBody CreatePsychologistProfileResource resource) {
    var command = CreatePsychologistProfileCommandFromResourceAssembler.toCommandFromResource(resource);
    var profile = profileCommandService.handle(command);
    
    if (profile.isEmpty()) {
      return ResponseEntity.badRequest().build();
    }
    
    var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
    return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
  }

  @GetMapping("/{profileId}")
  @Operation(summary = "Get profile by ID", description = "Retrieves a profile by its ID")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Profile found"),
    @ApiResponse(responseCode = "404", description = "Profile not found")
  })
  public ResponseEntity<ProfileResource> getProfileById(
    @Parameter(description = "Profile ID") @PathVariable Long profileId) {
    var query = new GetProfileByIdQuery(profileId);
    var profile = profileQueryService.handle(query);
    
    if (profile.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    
    var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
    return ResponseEntity.ok(profileResource);
  }

  @GetMapping("/user/{userId}")
  @Operation(summary = "Get profile by user ID", description = "Retrieves a profile by user ID")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Profile found"),
    @ApiResponse(responseCode = "404", description = "Profile not found")
  })
  public ResponseEntity<ProfileResource> getProfileByUserId(
    @Parameter(description = "User ID") @PathVariable Long userId) {
    var query = new GetProfileByUserIdQuery(userId);
    var profile = profileQueryService.handle(query);
    
    if (profile.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    
    var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
    return ResponseEntity.ok(profileResource);
  }

  @GetMapping("/psychologists")
  @Operation(summary = "Get all psychologists", description = "Retrieves all psychologist profiles")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Psychologists retrieved successfully")
  })
  public ResponseEntity<List<ProfileResource>> getAllPsychologists() {
    var query = new GetAllPsychologistsQuery();
    var profiles = profileQueryService.handle(query);
    var profileResources = profiles.stream()
      .map(ProfileResourceFromEntityAssembler::toResourceFromEntity)
      .toList();
    return ResponseEntity.ok(profileResources);
  }

  @GetMapping("/psychologists/search")
  @Operation(summary = "Search psychologists by specialty", description = "Searches psychologists by specialty")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Psychologists found")
  })
  public ResponseEntity<List<ProfileResource>> searchPsychologistsBySpecialty(
    @Parameter(description = "Specialty to search for") @RequestParam String specialty) {
    var query = new GetPsychologistsBySpecialtyQuery(specialty);
    var profiles = profileQueryService.handle(query);
    var profileResources = profiles.stream()
      .map(ProfileResourceFromEntityAssembler::toResourceFromEntity)
      .toList();
    return ResponseEntity.ok(profileResources);
  }

  @PutMapping("/{profileId}")
  @Operation(summary = "Update profile", description = "Updates basic profile information")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Profile updated successfully"),
    @ApiResponse(responseCode = "404", description = "Profile not found"),
    @ApiResponse(responseCode = "400", description = "Invalid input data")
  })
  public ResponseEntity<ProfileResource> updateProfile(
    @Parameter(description = "Profile ID") @PathVariable Long profileId,
    @RequestBody UpdateProfileResource resource) {
    var command = UpdateProfileCommandFromResourceAssembler.toCommandFromResource(profileId, resource);
    var profile = profileCommandService.handle(command);
    
    if (profile.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    
    var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
    return ResponseEntity.ok(profileResource);
  }
}

