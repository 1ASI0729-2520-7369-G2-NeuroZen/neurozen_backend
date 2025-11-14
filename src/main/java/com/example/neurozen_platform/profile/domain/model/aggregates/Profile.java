package com.example.neurozen_platform.profile.domain.model.aggregates;

import com.example.neurozen_platform.profile.domain.model.valueobjects.ProfileType;
import com.example.neurozen_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

  @Column(nullable = false)
  private Long userId;

  @NotBlank
  @Column(nullable = false)
  private String firstName;

  @NotBlank
  @Column(nullable = false)
  private String lastName;

  @Email
  @Column(nullable = false, unique = true)
  private String email;

  private String phoneNumber;

  private String district;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ProfileType profileType;

  // Psychologist-specific fields
  private String specialty;

  private String license;

  @Column(length = 1000)
  private String about;

  private Integer experience; // years of experience

  @ElementCollection
  @CollectionTable(name = "profile_languages", joinColumns = @JoinColumn(name = "profile_id"))
  @Column(name = "language")
  private List<String> languages = new ArrayList<>();

  @ElementCollection
  @CollectionTable(name = "profile_specialties", joinColumns = @JoinColumn(name = "profile_id"))
  @Column(name = "specialty")
  private List<String> specialties = new ArrayList<>();

  private Double rating;

  private Integer reviewCount;

  private Double price;

  private String imageUrl;

  private String nextAvailable;

  public Profile() {
    this.languages = new ArrayList<>();
    this.specialties = new ArrayList<>();
    this.rating = 0.0;
    this.reviewCount = 0;
  }

  public Profile(Long userId, String firstName, String lastName, String email, 
                 String phoneNumber, String district, ProfileType profileType) {
    this();
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.district = district;
    this.profileType = profileType;
  }

  // Psychologist constructor
  public Profile(Long userId, String firstName, String lastName, String email,
                 String phoneNumber, String specialty, String license, String about,
                 Integer experience, List<String> languages, List<String> specialties,
                 Double price, String imageUrl) {
    this();
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.specialty = specialty;
    this.license = license;
    this.about = about;
    this.experience = experience;
    this.languages = languages != null ? new ArrayList<>(languages) : new ArrayList<>();
    this.specialties = specialties != null ? new ArrayList<>(specialties) : new ArrayList<>();
    this.price = price;
    this.imageUrl = imageUrl;
    this.profileType = ProfileType.PSYCHOLOGIST;
    this.rating = 0.0;
    this.reviewCount = 0;
  }

  public void updateBasicInfo(String firstName, String lastName, String phoneNumber, String district) {
    if (firstName != null && !firstName.isBlank()) {
      this.firstName = firstName;
    }
    if (lastName != null && !lastName.isBlank()) {
      this.lastName = lastName;
    }
    if (phoneNumber != null) {
      this.phoneNumber = phoneNumber;
    }
    if (district != null) {
      this.district = district;
    }
  }

  public void updatePsychologistInfo(String specialty, String about, Integer experience,
                                      List<String> languages, List<String> specialties, Double price) {
    if (this.profileType != ProfileType.PSYCHOLOGIST) {
      throw new IllegalStateException("Cannot update psychologist info for non-psychologist profile");
    }
    if (specialty != null) {
      this.specialty = specialty;
    }
    if (about != null) {
      this.about = about;
    }
    if (experience != null) {
      this.experience = experience;
    }
    if (languages != null) {
      this.languages = new ArrayList<>(languages);
    }
    if (specialties != null) {
      this.specialties = new ArrayList<>(specialties);
    }
    if (price != null) {
      this.price = price;
    }
  }

  public void updateRating(Double rating, Integer reviewCount) {
    this.rating = rating;
    this.reviewCount = reviewCount;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public void setNextAvailable(String nextAvailable) {
    this.nextAvailable = nextAvailable;
  }

  public String getFullName() {
    return firstName + " " + lastName;
  }

  public boolean isPsychologist() {
    return this.profileType == ProfileType.PSYCHOLOGIST;
  }

  public boolean isPatient() {
    return this.profileType == ProfileType.PATIENT;
  }
}

