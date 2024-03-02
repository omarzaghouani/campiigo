//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package utils;

import java.util.regex.Pattern;

public class Validation {
    public Validation() {
    }

    public static boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return email == null ? false : pat.matcher(email).matches();
    }

    public static boolean isNumberValid(String number) {
        String emailRegex = "^[0-9]*$";
        Pattern pat = Pattern.compile(emailRegex);
        return number == null ? false : pat.matcher(number).matches();
    }
}
