package br.com.marketupdate.service;

import br.com.marketupdate.entity.Usuario;

public class RegisterService {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@gmail\\.com$";
    private static final String NAME_REGEX = "^(?>[A-Za-z]+(\\s(?:de\\s)?[A-Za-z]+)*+)$";
    private static final String PASSWORD_REGEX = "^[a-zA-Z0-9]+$";
    private RegisterService() {
    }
    public static boolean isValidEmail(String email) {
        return email != null && email.trim().matches(EMAIL_REGEX);
    }

    public static boolean isValidName(String name) {
        return name != null && name.trim().matches(NAME_REGEX);
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }
    public static boolean isValidUser(Usuario user) {
        if (user == null) {
            return false;
        }
        return isValidName(user.getName()) && isValidEmail(user.getEmail()) && isValidPassword(user.getPassword());
    }
}
