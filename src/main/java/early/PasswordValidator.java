package early;

public class PasswordValidator {

    public static String validate(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password can't be null");
        }
        if (password.length() < 8 || password.length() > 32) {
            throw new IllegalArgumentException("Password should be length [8, 32]");
        }
        if (!upperCaseCheck(password)) {
            throw new IllegalArgumentException("Password should contain at least one uppercase letter");
        }
        if (!lowerCaseCheck(password)) {
            throw new IllegalArgumentException("Password should contain at least one lowercase letter");
        }
        if (!digitCheck(password)) {
            throw new IllegalArgumentException("Password should contain at least one figure");
        }
        if (!specialSymbolCheck(password)) {
            throw new IllegalArgumentException("Password should contain at least one special symbol");
        }
        if (substringsCheck(password)) {
            throw new IllegalArgumentException("Password shouldn't contain substrings:"
                    + " qwerty, 12345, password, admin, user");
        }
        return password;
    }

    private static boolean upperCaseCheck(String password) {
        char[] array = password.toCharArray();
        for (char ch : array) {
            if (Character.isUpperCase(ch)) {
               return true;
            }
        }
        return false;
    }

    private static boolean lowerCaseCheck(String password) {
        char[] array = password.toCharArray();
        for (char ch : array) {
            if (Character.isLowerCase(ch)) {
                return true;
            }
        }
        return false;
    }

    private static boolean digitCheck(String password) {
        char[] array = password.toCharArray();
        for (char ch : array) {
            if (Character.isDigit(ch)) {
                return true;
            }
        }
        return false;
    }

    private static boolean specialSymbolCheck(String password) {
        char[] array = password.toCharArray();
        for (char ch : array) {
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
