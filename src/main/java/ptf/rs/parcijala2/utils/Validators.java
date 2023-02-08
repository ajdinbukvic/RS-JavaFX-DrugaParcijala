package ptf.rs.parcijala2.utils;

public class Validators {
    public static void checkRequiredField(String content, String fieldName) {
        if (content.isBlank()) throw new IllegalStateException(fieldName + " is required");
    }

    public static void checkMaxLength(String content, int length, String fieldName) {
        if (content.length() > length) throw new IllegalStateException(fieldName + " cannot be longer than " + length + " characters");
    }

    public static void checkValidDoubleNumber(String content, String fieldName) {
        try {
            Double.parseDouble(content);
        } catch (Exception ignored) {
            throw new IllegalStateException("\"" + fieldName + "\" must be a valid number!");
        }
    }

    public static void checkValidIntNumber(String content, String fieldName) {
        try {
            Integer.parseInt(content);
        } catch (Exception ignored) {
            throw new IllegalStateException("\"" + fieldName + "\" must be a valid integer!");
        }
    }

    public static void checkNegativeNumber(String content, String fieldName) {
        //try/catch izostavljen jer se validacija za validan broj poziva prije ove metode
        if (Double.parseDouble(content) < 0) throw new IllegalStateException("\"" + fieldName + "\" must be a positive number!");
    }
}
