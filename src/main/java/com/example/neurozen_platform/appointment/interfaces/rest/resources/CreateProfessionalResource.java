package com.example.neurozen_platform.appointment.interfaces.rest.resources;


public record CreateProfessionalResource(
    String firstName,
    String lastName,
    String email,
    String phoneNumber,
    String district,
    String speciality
) {

    public CreateProfessionalResource {
        if (firstName == null || firstName.isBlank()) throw new IllegalArgumentException("First name cannot be null or blank");
        if (lastName == null || lastName.isBlank()) throw new IllegalArgumentException("Last name cannot be null or blank");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email cannot be null or blank");
        if (phoneNumber == null || phoneNumber.isBlank()) throw new IllegalArgumentException("Phone number cannot be null or blank");
        if (district == null || district.isBlank()) throw new IllegalArgumentException("District cannot be null or blank");
        if (speciality == null || speciality.isBlank()) throw new IllegalArgumentException("Speciality cannot be null or blank");
    }

}

