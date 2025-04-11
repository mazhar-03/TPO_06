package com.example.demo.controller;

import com.example.demo.dto.PersonDTO;
import com.example.demo.service.FakeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FakeDataController {

    @Autowired
    private FakeDataService fakeDataService;

    @PostMapping("/generate")
    public String generateData(
            @RequestParam("quantity") int quantity,
            @RequestParam("language") String language,
            @RequestParam List<String> fields,
            Model model) {

        // Ensure basic fields are always included
        if (fields.isEmpty()) {
            fields.add("firstName");
            fields.add("lastName");
            fields.add("dateOfBirth");
        }

        // Call the FakeDataService to generate the data
        List<PersonDTO> generatedData = fakeDataService.generateData(quantity, language, fields);

        // Add the generated data to the model to be used in the result page
        model.addAttribute("generatedData", generatedData);
        model.addAttribute("fields", fields);  // Pass the selected fields as well
        return "result";  // Return the name of the result view (result.html)
    }


    @GetMapping("/")
    public String showForm() {
        return "form";  // The name of the form.html template
    }
}
