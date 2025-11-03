package com.example.neurozen_platform.appointment.domain.model.commands;

public record CreatePatientCommand(String firstName,
    String lastName,
    String email,
    String phoneNumber,
    String district) {

        public CreatePatientCommand {
            if (firstName == null || firstName.isBlank()) throw new IllegalArgumentException("First name cannot be null or blank");
            if (lastName == null || lastName.isBlank()) throw new IllegalArgumentException("Last name cannot be null or blank");
            if (email == null || email.isBlank()) throw new IllegalArgumentException("Email cannot be null or blank");
            if (phoneNumber == null || phoneNumber.isBlank()) throw new IllegalArgumentException("Phone number cannot be null or blank");
            if (district == null || district.isBlank()) throw new IllegalArgumentException("District cannot be null or blank");
        }
}
