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
        if (!upperCaseCheck(array)) {
            throw new IllegalArgumentException("Password should contain at least one uppercase letter");
        }
        if (!lowerCaseCheck(array)) {
            throw new IllegalArgumentException("Password should contain at least one lowercase letter");
        }
        if (!digitCheck(array)) {
            throw new IllegalArgumentException("Password should contain at least one figure");
        }
        if (!specialSymbolCheck(array)) {
            throw new IllegalArgumentException("Password should contain at least one special symbol");
        }
    }

    private static boolean upperCaseCheck(char[] password) {
        for (char ch : password) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }

    private static boolean lowerCaseCheck(char[] password) {
        for (char ch : password) {
            if (Character.isLowerCase(ch)) {
                return true;
            }
        }
        return false;
    }

    private static boolean digitCheck(char[] password) {
        for (char ch : password) {
            if (Character.isDigit(ch)) {
                return true;
            }
        }
        return false;
    }

    private static boolean specialSymbolCheck(char[] password) {
        for (char ch : password) {
            if (!Character.isLetterOrDigit(ch)) {
                return true;
            }
        }
        return false;
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
