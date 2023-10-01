package Task_3;

public class Validation {
    public boolean isValidName(String name) {

        return name.matches("[a-zA-Z ]+") && !name.isEmpty();
    }

    public boolean isValidDateOfBirth(String dob) {
        return dob.matches("\\d{2}-\\d{2}-\\d{4}");
    }

    public boolean isValidEmail(String email) {

        return email.matches("[a-zA-Z0-9]+@gmail\\.com");
    }

    public boolean isValidContact(String contact) {
        return contact.matches("\\d{10}");
    }

    public boolean isValidSubject(String subject) {
        return !subject.isEmpty() && subject.matches("[a-zA-Z ]+");
    }

    public boolean isValidYear(String year) {
        return year.equals("1st") || year.equals("2nd") || year.equals("3rd");
    }
    
}
 
