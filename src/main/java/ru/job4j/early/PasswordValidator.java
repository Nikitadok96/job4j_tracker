package ru.job4j.early;

public class PasswordValidator {

    public static String validate(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password can't be null");
        }
        if (password.length() < 8 || password.length() > 32) {
            throw new IllegalArgumentException("Password should be length [8, 32]");
        }
        charValidate(password);
        if (substringsCheck(password)) {
            throw new IllegalArgumentException("Password shouldn't contain substrings:"
                    + " qwerty, 12345, password, admin, user");
        }
        return password;
    }

    private static void charValidate(String password) {
        char[] array = password.toCharArray();
        boolean isUpperCaseValid = false;
        boolean isLowerCaseValid = false;
        boolean isDigitValid = false;
        boolean isSpecialSymbol = false;
        for (char ch : array) {
            if (Character.isUpperCase(ch)) {
                isUpperCaseValid = true;
            }
            if (Character.isLowerCase(ch)) {
                isLowerCaseValid = true;
            }
            if (Character.isDigit(ch)) {
                isDigitValid = true;
            }
            if (!Character.isLetterOrDigit(ch)) {
                isSpecialSymbol = true;
            }
            if (isUpperCaseValid && isLowerCaseValid && isDigitValid && isSpecialSymbol) {
                break;
            }
        }
        if (!isUpperCaseValid) {
            throw new IllegalArgumentException("Password should contain at least one uppercase letter");
        }
        if (!isLowerCaseValid) {
            throw new IllegalArgumentException("Password should contain at least one lowercase letter");
        }
        if (!isDigitValid) {
            throw new IllegalArgumentException("Password should contain at least one figure");
        }
        if (!isSpecialSymbol) {
            throw new IllegalArgumentException("Password should contain at least one special symbol");
        }
    }

    private static boolean substringsCheck(String password) {
        String[] substrings = {"qwerty", "12345", "password", "admin", "user"};
        for (String value : substrings) {
            if (password.toLowerCase().contains(value.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
