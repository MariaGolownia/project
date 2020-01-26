package by.javatr.bicrent.entity.valid;

public class PasswordValidation {
    // Валидация соответствия пароля необходимой сложности
    public Boolean ifPasswordCorrespondsToComplexity (String password) {
        Boolean ifPasswordCorrespondsTo = false;
        String sample = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
//        String sample = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
//        ^  - start-of-string
//        (?=.*[0-9])- a digit must occur at least once
//         (?=.*[a-z])- a lower case letter must occur at least once
//        (?=.*[A-Z]) - an upper case letter must occur at least once
//        (?=.*[@#$%^&+=]) - a special character must occur at least once
//        (?=\S+$)- no whitespace allowed in the entire string
//        .{8,} - anything, at least eight places though
//        $ - end-of-string
        if (sample.matches(password)) {
            ifPasswordCorrespondsTo = true;
        }
      return ifPasswordCorrespondsTo;
    }
}
