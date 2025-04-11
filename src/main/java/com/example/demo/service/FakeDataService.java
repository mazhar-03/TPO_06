package com.example.demo.service;

import com.example.demo.dto.PersonDTO;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Date;

@Service
public class FakeDataService {

    public List<PersonDTO> generateData(int quantity, String language, List<String> fields) {
        Faker faker = new Faker(new Locale(language)); // Initialize Faker with the given locale
        List<PersonDTO> people = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            PersonDTO person = new PersonDTO();

            // Basic fields
            person.setFirstName(faker.name().firstName());
            person.setLastName(faker.name().lastName());

            // Generate a random birthday and format it to a string (dd-MM-yyyy)
            Date birthDate = faker.date().birthday();  // Correct method for generating a birthdate
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            person.setDateOfBirth(sdf.format(birthDate)); // Set the formatted birthdate

            // Add additional fields based on user selection
            if (fields.contains("address")) {
                person.setAddress(faker.address().fullAddress());
            }
            if (fields.contains("university")) {
                person.setUniversity(faker.educator().university());
            }
            if (fields.contains("country")) {
                person.setCountry(faker.address().country());
            }
            if (fields.contains("job")) {
                person.setJob(faker.job().title());
            }
            if (fields.contains("email")) {
                person.setEmail(faker.internet().emailAddress());
            }
            if (fields.contains("phone")) {
                person.setPhone(faker.phoneNumber().phoneNumber());
            }
            if (fields.contains("sex")) {
                person.setSex(faker.options().option("Male", "Female"));
            }
            if (fields.contains("maritalStatus")) {
                person.setMaritalStatus(faker.options().option("Single", "Married"));
            }
            if (fields.contains("kidsNumber")) {
                person.setKidsNumber(faker.number().numberBetween(0, 5));
            }

            // Add the generated person to the list
            people.add(person);
        }

        return people;
    }
}
