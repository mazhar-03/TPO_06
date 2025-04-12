package com.example.demo.controller;

import com.example.demo.dto.PersonDTO;
import com.example.demo.service.FakeDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class FakeDataController {

    private final FakeDataService fakeDataService;

    public FakeDataController(FakeDataService fakeDataService) {
        this.fakeDataService = fakeDataService;
    }

    @RequestMapping("/generate")
    public String generateData(
            @RequestParam("numOfData") String numOfDataStr,
            @RequestParam("language") String language,
            @RequestParam(required = false) List<String> fields ,
            Model model) {

        if (numOfDataStr == null || numOfDataStr.trim().isEmpty()) {
            model.addAttribute("errorMessage", " The number of data entries to be generated CAN NOT be empty. Should be a Natural Number");
            return "form";
        }

        int numOfData;
        try {
            numOfData = Integer.parseInt(numOfDataStr);
            if (numOfData < 0) {
                model.addAttribute("errorMessage", " The number of data entries to be generated CAN NOT be less than 0");
                return "form";
            }
        } catch (NumberFormatException e) {
            model.addAttribute("errorMessage", " Please enter a valid numeric value.");
            return "form";
        }

        if (fields == null)
            fields = new ArrayList<>();

        if (fields.isEmpty()) {
            fields.add("firstName");
            fields.add("lastName");
            fields.add("dateOfBirth");
        }

        List<String> validLanguages = Arrays.asList("ar", "ko", "tr", "ja", "ru", "vi", "es", "pl",
                "de", "hu","it", "fr", "sv", "en");
        if(!validLanguages.contains(language)) {
            model.addAttribute("errorMessage", "Make sure that selected language is supported => " + language);
            return "form";
        }

        try{
            List<PersonDTO> generatedData = fakeDataService.generateData(numOfData, language, fields);
            Map<String, String> languageHeaders = getTranslatedHeaders(language);

            model.addAttribute("generatedData", generatedData);
            model.addAttribute("fields", fields);
            model.addAttribute("languageHeaders", languageHeaders);
            return "form";
        }catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "form";
        }
    }

    @GetMapping("/form")
    public String showForm() {
        return "form";
    }

    private Map<String, String> getTranslatedHeaders(String lang) {
        Map<String, String> headers = new HashMap<>();
        switch (lang) {
            case "en":
                headers.put("firstName", "First Name");
                headers.put("lastName", "Last Name");
                headers.put("dateOfBirth", "Date of Birth");
                headers.put("address", "Address");
                headers.put("university", "University");
                headers.put("country", "Nationality");
                headers.put("job", "Job");
                headers.put("email", "Email");
                headers.put("phone", "Phone");
                headers.put("sex", "Sex");
                headers.put("maritalStatus", "Marital Status");
                headers.put("kidsNumber", "Number of Kids");
                break;
            case "sv":
                headers.put("firstName", "Förnamn");
                headers.put("lastName", "Efternamn");
                headers.put("dateOfBirth", "Födelsedatum");
                headers.put("address", "Adress");
                headers.put("university", "Universitet");
                headers.put("country", "Nationalitet");
                headers.put("job", "Jobb");
                headers.put("email", "E-post");
                headers.put("phone", "Telefon");
                headers.put("sex", "Kön");
                headers.put("maritalStatus", "Civilstånd");
                headers.put("kidsNumber", "Antal barn");
                break;
            case "fr":
                headers.put("firstName", "Prénom");
                headers.put("lastName", "Nom");
                headers.put("dateOfBirth", "Date de Naissance");
                headers.put("address", "Adresse");
                headers.put("university", "Université");
                headers.put("country", "Nationalité");
                headers.put("job", "Emploi");
                headers.put("email", "E-mail");
                headers.put("phone", "Téléphone");
                headers.put("sex", "Sexe");
                headers.put("maritalStatus", "État Civil");
                headers.put("kidsNumber", "Nombre d'enfants");
                break;
            case "it":
                headers.put("firstName", "Nome");
                headers.put("lastName", "Cognome");
                headers.put("dateOfBirth", "Data di Nascita");
                headers.put("address", "Indirizzo");
                headers.put("university", "Università");
                headers.put("country", "Nazionalità");
                headers.put("job", "Lavoro");
                headers.put("email", "Email");
                headers.put("phone", "Telefono");
                headers.put("sex", "Sesso");
                headers.put("maritalStatus", "Stato Civile");
                headers.put("kidsNumber", "Numero di Figli");
                break;
            case "hu":
                headers.put("firstName", "Keresztnév");
                headers.put("lastName", "Vezetéknév");
                headers.put("dateOfBirth", "Születési Dátum");
                headers.put("address", "Cím");
                headers.put("university", "Egyetem");
                headers.put("country", "Állampolgárság");
                headers.put("job", "Foglalkozás");
                headers.put("email", "Email");
                headers.put("phone", "Telefon");
                headers.put("sex", "Nem");
                headers.put("maritalStatus", "Családi Állapot");
                headers.put("kidsNumber", "Gyerekek Száma");
                break;
            case "de":
                headers.put("firstName", "Vorname");
                headers.put("lastName", "Nachname");
                headers.put("dateOfBirth", "Geburtsdatum");
                headers.put("address", "Adresse");
                headers.put("university", "Universität");
                headers.put("country", "Staatsangehörigkeit");
                headers.put("job", "Beruf");
                headers.put("email", "E-Mail");
                headers.put("phone", "Telefon");
                headers.put("sex", "Geschlecht");
                headers.put("maritalStatus", "Familienstand");
                headers.put("kidsNumber", "Anzahl der Kinder");
                break;
            case "es":
                headers.put("firstName", "Nombre");
                headers.put("lastName", "Apellido");
                headers.put("dateOfBirth", "Fecha de Nacimiento");
                headers.put("address", "Dirección");
                headers.put("university", "Universidad");
                headers.put("country", "Nacionalidad");
                headers.put("job", "Trabajo");
                headers.put("email", "Correo Electrónico");
                headers.put("phone", "Teléfono");
                headers.put("sex", "Sexo");
                headers.put("maritalStatus", "Estado Civil");
                headers.put("kidsNumber", "Número de Hijos");
                break;
            case "pl":
                headers.put("firstName", "Imię");
                headers.put("lastName", "Nazwisko");
                headers.put("dateOfBirth", "Data Urodzenia");
                headers.put("address", "Adres");
                headers.put("university", "Uniwersytet");
                headers.put("country", "Narodowość");
                headers.put("job", "Zawód");
                headers.put("email", "Email");
                headers.put("phone", "Telefon");
                headers.put("sex", "Płeć");
                headers.put("maritalStatus", "Stan Cywilny");
                headers.put("kidsNumber", "Liczba Dzieci");
                break;
            case "vi":
                headers.put("firstName", "Tên");
                headers.put("lastName", "Họ");
                headers.put("dateOfBirth", "Ngày Sinh");
                headers.put("address", "Địa Chỉ");
                headers.put("university", "Đại Học");
                headers.put("country", "Quốc Tịch");
                headers.put("job", "Nghề Nghiệp");
                headers.put("email", "Email");
                headers.put("phone", "Điện Thoại");
                headers.put("sex", "Giới Tính");
                headers.put("maritalStatus", "Tình Trạng Hôn Nhân");
                headers.put("kidsNumber", "Số Con");
                break;
            case "ru":
                headers.put("firstName", "Имя");
                headers.put("lastName", "Фамилия");
                headers.put("dateOfBirth", "Дата Рождения");
                headers.put("address", "Адрес");
                headers.put("university", "Университет");
                headers.put("country", "Гражданство");
                headers.put("job", "Профессия");
                headers.put("email", "Электронная Почта");
                headers.put("phone", "Телефон");
                headers.put("sex", "Пол");
                headers.put("maritalStatus", "Семейное Положение");
                headers.put("kidsNumber", "Количество Детей");
                break;
            case "ja":
                headers.put("firstName", "名");
                headers.put("lastName", "姓");
                headers.put("dateOfBirth", "生年月日");
                headers.put("address", "住所");
                headers.put("university", "大学");
                headers.put("country", "国籍");
                headers.put("job", "職業");
                headers.put("email", "メール");
                headers.put("phone", "電話番号");
                headers.put("sex", "性別");
                headers.put("maritalStatus", "配偶者の有無");
                headers.put("kidsNumber", "子供の数");
                break;
            case "tr":
                headers.put("firstName", "İsim");
                headers.put("lastName", "Soyisim");
                headers.put("dateOfBirth", "Doğum Tarihi");
                headers.put("address", "Adres");
                headers.put("university", "Üniversite");
                headers.put("country", "Uyruk");
                headers.put("job", "Meslek");
                headers.put("email", "E-posta");
                headers.put("phone", "Telefon");
                headers.put("sex", "Cinsiyet");
                headers.put("maritalStatus", "Medeni Durum");
                headers.put("kidsNumber", "Çocuk Sayısı");
                break;
            case "ko":
                headers.put("firstName", "이름");
                headers.put("lastName", "성");
                headers.put("dateOfBirth", "생년월일");
                headers.put("address", "주소");
                headers.put("university", "대학교");
                headers.put("country", "국적");
                headers.put("job", "직업");
                headers.put("email", "이메일");
                headers.put("phone", "전화번호");
                headers.put("sex", "성별");
                headers.put("maritalStatus", "혼인 여부");
                headers.put("kidsNumber", "자녀 수");
                break;
            case "ar":
                headers.put("firstName", "الاسم الأول");
                headers.put("lastName", "اسم العائلة");
                headers.put("dateOfBirth", "تاريخ الميلاد");
                headers.put("address", "العنوان");
                headers.put("university", "الجامعة");
                headers.put("country", "الجنسية");
                headers.put("job", "الوظيفة");
                headers.put("email", "البريد الإلكتروني");
                headers.put("phone", "رقم الهاتف");
                headers.put("sex", "الجنس");
                headers.put("maritalStatus", "الحالة الاجتماعية");
                headers.put("kidsNumber", "عدد الأطفال");
                break;
            default:
                headers.put("firstName", "First Name");
                headers.put("lastName", "Last Name");
                headers.put("dateOfBirth", "Date of Birth");
                headers.put("address", "Address");
                headers.put("university", "University");
                headers.put("country", "Nationality");
                headers.put("job", "Job");
                headers.put("email", "Email");
                headers.put("phone", "Phone");
                headers.put("sex", "Sex");
                headers.put("maritalStatus", "Marital Status");
                headers.put("kidsNumber", "Number of Kids");
                break;
        }
        return headers;
    }

}
