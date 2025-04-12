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

    public List<PersonDTO> generateData(int numOfData, String language, List<String> fields) {
        Faker faker = new Faker(new Locale(language));
        List<PersonDTO> people = new ArrayList<>();

        for (int i = 0; i < numOfData; i++) {
            PersonDTO person = new PersonDTO();

            //basuic
            person.setFirstName(faker.name().firstName());
            person.setLastName(faker.name().lastName());
            person.setDateOfBirth(faker.timeAndDate().birthday(1, 99, "dd/MM/yyyy"));

            //additional
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
                person.setMaritalStatus(faker.options().option("Single", "Married", "Divorced"));
            }
            if (fields.contains("kidsNumber")) {
                person.setKidsNumber(faker.number().numberBetween(0, 3));
            }

            people.add(person);
        }

        return people;
    }
}
