package com.example.neurozen_platform.shared.infrastructure.persistence.jpa.configuration;

import com.example.neurozen_platform.appointment.domain.model.aggregates.MedicalAppointment;
import com.example.neurozen_platform.appointment.domain.model.aggregates.Patient;
import com.example.neurozen_platform.appointment.domain.model.aggregates.Professional;
import com.example.neurozen_platform.appointment.domain.model.valueobjects.AppointmentStatus;
import com.example.neurozen_platform.appointment.infrastructure.persistence.jpa.repositories.AppointmentRepository;
import com.example.neurozen_platform.appointment.infrastructure.persistence.jpa.repositories.PatientRepository;
import com.example.neurozen_platform.appointment.infrastructure.persistence.jpa.repositories.ProfessionalRepository;
import com.example.neurozen_platform.assessment.domain.model.aggregates.StressAssessment;
import com.example.neurozen_platform.assessment.domain.model.valueobjects.AssessmentLevel;
import com.example.neurozen_platform.assessment.domain.model.valueobjects.CategoryScores;
import com.example.neurozen_platform.assessment.infrastructure.persistence.jpa.repositories.AssessmentRepository;
import com.example.neurozen_platform.iam.domain.model.aggregates.User;
import com.example.neurozen_platform.iam.domain.model.valueobjects.Roles;
import com.example.neurozen_platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.example.neurozen_platform.profile.domain.model.aggregates.Profile;
import com.example.neurozen_platform.profile.domain.model.valueobjects.ProfileType;
import com.example.neurozen_platform.profile.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class DataSeeder implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(DataSeeder.class);

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ProfileRepository profileRepository;

  @Autowired
  private PatientRepository patientRepository;

  @Autowired
  private ProfessionalRepository professionalRepository;

  @Autowired
  private AssessmentRepository assessmentRepository;

  @Autowired
  private AppointmentRepository appointmentRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public void run(String... args) throws Exception {
    // Check if data already exists
    if (userRepository.count() > 0) {
      logger.info("Database already contains data. Skipping seeding.");
      return;
    }

    logger.info("Starting database seeding...");

    // Seed users
    List<User> users = seedUsers();

    // Seed profiles
    List<Profile> psychologists = seedPsychologistProfiles(users);
    List<Profile> patients = seedPatientProfiles(users);

    // Seed Patient and Professional aggregates
    List<Patient> patientAggregates = seedPatientAggregates(patients);
    List<Professional> professionalAggregates = seedProfessionalAggregates(psychologists);

    // Seed assessments
    seedAssessments(patients);

    // Seed appointments
    seedAppointments(patientAggregates, professionalAggregates);

    logger.info("Database seeding completed successfully!");
  }

  private List<User> seedUsers() {
    logger.info("Seeding users...");
    List<User> users = new ArrayList<>();

    // Create psychologist users
    users.add(createUser("dr.sarah.johnson@neurozen.com", "Dr. Sarah", "Johnson", "password123", Roles.ROLE_PSYCHOLOGIST));
    users.add(createUser("dr.michael.chen@neurozen.com", "Dr. Michael", "Chen", "password123", Roles.ROLE_PSYCHOLOGIST));
    users.add(createUser("dr.emily.rodriguez@neurozen.com", "Dr. Emily", "Rodriguez", "password123", Roles.ROLE_PSYCHOLOGIST));
    users.add(createUser("dr.david.kim@neurozen.com", "Dr. David", "Kim", "password123", Roles.ROLE_PSYCHOLOGIST));
    users.add(createUser("dr.lisa.patel@neurozen.com", "Dr. Lisa", "Patel", "password123", Roles.ROLE_PSYCHOLOGIST));

    // Create patient users
    users.add(createUser("john.doe@example.com", "John", "Doe", "password123", Roles.ROLE_USER));
    users.add(createUser("jane.smith@example.com", "Jane", "Smith", "password123", Roles.ROLE_USER));
    users.add(createUser("robert.brown@example.com", "Robert", "Brown", "password123", Roles.ROLE_USER));
    users.add(createUser("maria.garcia@example.com", "Maria", "Garcia", "password123", Roles.ROLE_USER));
    users.add(createUser("james.wilson@example.com", "James", "Wilson", "password123", Roles.ROLE_USER));

    userRepository.saveAll(users);
    logger.info("Created {} users", users.size());
    return users;
  }

  private User createUser(String email, String firstName, String lastName, String password, Roles role) {
    var user = new User(email, email, passwordEncoder.encode(password), firstName, lastName);
    user.addRole(role);
    return user;
  }

  private List<Profile> seedPsychologistProfiles(List<User> users) {
    logger.info("Seeding psychologist profiles...");
    List<Profile> psychologists = new ArrayList<>();

    // Dr. Sarah Johnson - Anxiety & Stress Specialist
    psychologists.add(new Profile(
      users.get(0).getId(),
      "Dr. Sarah",
      "Johnson",
      "dr.sarah.johnson@neurozen.com",
      "+1-555-0101",
      "Anxiety & Stress",
      "PSY-12345",
      "Experienced psychologist specializing in anxiety and stress management with over 15 years of practice. I use evidence-based approaches including CBT and mindfulness techniques.",
      15,
      Arrays.asList("English", "Spanish"),
      Arrays.asList("Anxiety", "Stress", "Panic Disorders", "Mindfulness"),
      120.00,
      "https://randomuser.me/api/portraits/women/1.jpg"
    ));

    // Dr. Michael Chen - Depression & Mood Disorders
    psychologists.add(new Profile(
      users.get(1).getId(),
      "Dr. Michael",
      "Chen",
      "dr.michael.chen@neurozen.com",
      "+1-555-0102",
      "Depression & Mood Disorders",
      "PSY-23456",
      "Specializing in depression and mood disorders. I provide compassionate, evidence-based treatment to help you regain balance and joy in your life.",
      12,
      Arrays.asList("English", "Mandarin"),
      Arrays.asList("Depression", "Bipolar Disorder", "Mood Disorders", "CBT"),
      130.00,
      "https://randomuser.me/api/portraits/men/2.jpg"
    ));

    // Dr. Emily Rodriguez - Trauma & PTSD
    psychologists.add(new Profile(
      users.get(2).getId(),
      "Dr. Emily",
      "Rodriguez",
      "dr.emily.rodriguez@neurozen.com",
      "+1-555-0103",
      "Trauma & PTSD",
      "PSY-34567",
      "Trauma-informed therapist with expertise in PTSD, complex trauma, and healing. I create a safe space for processing difficult experiences.",
      10,
      Arrays.asList("English", "Spanish", "Portuguese"),
      Arrays.asList("PTSD", "Trauma", "EMDR", "Somatic Therapy"),
      140.00,
      "https://randomuser.me/api/portraits/women/3.jpg"
    ));

    // Dr. David Kim - Relationship & Family Therapy
    psychologists.add(new Profile(
      users.get(3).getId(),
      "Dr. David",
      "Kim",
      "dr.david.kim@neurozen.com",
      "+1-555-0104",
      "Relationship & Family Therapy",
      "PSY-45678",
      "Family and couples therapist helping individuals and families build stronger, healthier relationships through effective communication and understanding.",
      8,
      Arrays.asList("English", "Korean"),
      Arrays.asList("Couples Therapy", "Family Therapy", "Communication", "Conflict Resolution"),
      125.00,
      "https://randomuser.me/api/portraits/men/4.jpg"
    ));

    // Dr. Lisa Patel - Work-Life Balance & Burnout
    psychologists.add(new Profile(
      users.get(4).getId(),
      "Dr. Lisa",
      "Patel",
      "dr.lisa.patel@neurozen.com",
      "+1-555-0105",
      "Work-Life Balance & Burnout",
      "PSY-56789",
      "Specializing in workplace stress, burnout prevention, and work-life balance. I help professionals thrive both personally and professionally.",
      7,
      Arrays.asList("English", "Hindi"),
      Arrays.asList("Burnout", "Work Stress", "Career Counseling", "Life Coaching"),
      115.00,
      "https://randomuser.me/api/portraits/women/5.jpg"
    ));

    // Set ratings and availability
    psychologists.get(0).updateRating(4.9, 127);
    psychologists.get(0).setNextAvailable("Tomorrow at 2:00 PM");
    
    psychologists.get(1).updateRating(4.8, 98);
    psychologists.get(1).setNextAvailable("Today at 4:00 PM");
    
    psychologists.get(2).updateRating(5.0, 156);
    psychologists.get(2).setNextAvailable("Next Monday at 10:00 AM");
    
    psychologists.get(3).updateRating(4.7, 84);
    psychologists.get(3).setNextAvailable("Tomorrow at 11:00 AM");
    
    psychologists.get(4).updateRating(4.9, 112);
    psychologists.get(4).setNextAvailable("Today at 3:00 PM");

    profileRepository.saveAll(psychologists);
    logger.info("Created {} psychologist profiles", psychologists.size());
    return psychologists;
  }

  private List<Profile> seedPatientProfiles(List<User> users) {
    logger.info("Seeding patient profiles...");
    List<Profile> patients = new ArrayList<>();

    patients.add(new Profile(
      users.get(5).getId(),
      "John",
      "Doe",
      "john.doe@example.com",
      "+1-555-0201",
      "Downtown",
      ProfileType.PATIENT
    ));

    patients.add(new Profile(
      users.get(6).getId(),
      "Jane",
      "Smith",
      "jane.smith@example.com",
      "+1-555-0202",
      "Uptown",
      ProfileType.PATIENT
    ));

    patients.add(new Profile(
      users.get(7).getId(),
      "Robert",
      "Brown",
      "robert.brown@example.com",
      "+1-555-0203",
      "Midtown",
      ProfileType.PATIENT
    ));

    patients.add(new Profile(
      users.get(8).getId(),
      "Maria",
      "Garcia",
      "maria.garcia@example.com",
      "+1-555-0204",
      "Westside",
      ProfileType.PATIENT
    ));

    patients.add(new Profile(
      users.get(9).getId(),
      "James",
      "Wilson",
      "james.wilson@example.com",
      "+1-555-0205",
      "Eastside",
      ProfileType.PATIENT
    ));

    profileRepository.saveAll(patients);
    logger.info("Created {} patient profiles", patients.size());
    return patients;
  }

  private List<Patient> seedPatientAggregates(List<Profile> patientProfiles) {
    logger.info("Seeding patient aggregates...");
    List<Patient> patients = new ArrayList<>();

    for (Profile profile : patientProfiles) {
      patients.add(new Patient(profile.getId()));
    }

    patientRepository.saveAll(patients);
    logger.info("Created {} patient aggregates", patients.size());
    return patients;
  }

  private List<Professional> seedProfessionalAggregates(List<Profile> psychologistProfiles) {
    logger.info("Seeding professional aggregates...");
    List<Professional> professionals = new ArrayList<>();

    for (Profile profile : psychologistProfiles) {
      professionals.add(new Professional(profile.getId()));
    }

    professionalRepository.saveAll(professionals);
    logger.info("Created {} professional aggregates", professionals.size());
    return professionals;
  }

  private void seedAssessments(List<Profile> patientProfiles) {
    logger.info("Seeding stress assessments...");
    List<StressAssessment> assessments = new ArrayList<>();

    // John Doe - High stress
    Map<String, Integer> answers1 = new HashMap<>();
    answers1.put("work_1", 4);
    answers1.put("work_2", 3);
    answers1.put("work_3", 4);
    answers1.put("sleep_1", 3);
    answers1.put("sleep_2", 4);
    answers1.put("physical_1", 3);
    answers1.put("emotional_1", 4);

    assessments.add(new StressAssessment(
      patientProfiles.get(0).getUserId(),
      78,
      AssessmentLevel.HIGH,
      Arrays.asList(
        "Consider scheduling a session with a mental health professional",
        "Practice daily meditation or mindfulness exercises",
        "Establish a consistent sleep schedule",
        "Engage in regular physical activity"
      ),
      new CategoryScores(80, 75, 70, 85),
      answers1
    ));

    // Jane Smith - Moderate stress
    Map<String, Integer> answers2 = new HashMap<>();
    answers2.put("work_1", 2);
    answers2.put("work_2", 2);
    answers2.put("work_3", 3);
    answers2.put("sleep_1", 2);
    answers2.put("sleep_2", 2);
    answers2.put("physical_1", 1);
    answers2.put("emotional_1", 2);

    assessments.add(new StressAssessment(
      patientProfiles.get(1).getUserId(),
      52,
      AssessmentLevel.MODERATE,
      Arrays.asList(
        "Continue monitoring your stress levels",
        "Practice deep breathing exercises when feeling overwhelmed",
        "Maintain work-life balance",
        "Consider joining a support group"
      ),
      new CategoryScores(55, 50, 45, 60),
      answers2
    ));

    // Robert Brown - Low stress
    Map<String, Integer> answers3 = new HashMap<>();
    answers3.put("work_1", 1);
    answers3.put("work_2", 0);
    answers3.put("work_3", 1);
    answers3.put("sleep_1", 1);
    answers3.put("sleep_2", 0);
    answers3.put("physical_1", 0);
    answers3.put("emotional_1", 1);

    assessments.add(new StressAssessment(
      patientProfiles.get(2).getUserId(),
      28,
      AssessmentLevel.LOW,
      Arrays.asList(
        "Great job managing your stress!",
        "Continue your current healthy habits",
        "Stay active and maintain social connections"
      ),
      new CategoryScores(30, 25, 20, 35),
      answers3
    ));

    assessmentRepository.saveAll(assessments);
    logger.info("Created {} stress assessments", assessments.size());
  }

  private void seedAppointments(List<Patient> patients, List<Professional> professionals) {
    logger.info("Seeding appointments...");
    List<MedicalAppointment> appointments = new ArrayList<>();

    // Upcoming appointment - John Doe with Dr. Sarah Johnson
    MedicalAppointment apt1 = new MedicalAppointment(
      patients.get(0),
      professionals.get(0),
      "2025-11-15T14:00:00",
      "Initial Consultation",
      "online",
      "Anxiety and stress management"
    );
    apt1.confirmAppointment();
    appointments.add(apt1);

    // Upcoming appointment - Jane Smith with Dr. Michael Chen
    MedicalAppointment apt2 = new MedicalAppointment(
      patients.get(1),
      professionals.get(1),
      "2025-11-16T10:00:00",
      "Follow-up Session",
      "in-person",
      "Depression and mood management"
    );
    apt2.confirmAppointment();
    appointments.add(apt2);

    // Requested appointment - Robert Brown with Dr. Emily Rodriguez
    appointments.add(new MedicalAppointment(
      patients.get(2),
      professionals.get(2),
      "2025-11-18T15:00:00",
      "Initial Consultation",
      "online",
      "Trauma processing"
    ));

    // Completed appointment - Maria Garcia with Dr. David Kim
    MedicalAppointment apt4 = new MedicalAppointment(
      patients.get(3),
      professionals.get(3),
      "2025-11-05T11:00:00",
      "Couples Therapy",
      "in-person",
      "Relationship counseling"
    );
    apt4.confirmAppointment();
    apt4.completeAppointment();
    appointments.add(apt4);

    // Canceled appointment - James Wilson with Dr. Lisa Patel
    MedicalAppointment apt5 = new MedicalAppointment(
      patients.get(4),
      professionals.get(4),
      "2025-11-10T09:00:00",
      "Initial Consultation",
      "online",
      "Work-life balance"
    );
    apt5.confirmAppointment();
    apt5.cancelAppointment();
    appointments.add(apt5);

    appointmentRepository.saveAll(appointments);
    logger.info("Created {} appointments", appointments.size());
  }
}
