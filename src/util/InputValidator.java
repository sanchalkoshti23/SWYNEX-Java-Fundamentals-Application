package util;

public class InputValidator {
    public static boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static boolean isValidSeverity(String severity) {
        if (severity == null) {
            return false;
        }

        String normalizedSeverity = severity.trim();
        return normalizedSeverity.equalsIgnoreCase("LOW")
                || normalizedSeverity.equalsIgnoreCase("MEDIUM")
                || normalizedSeverity.equalsIgnoreCase("HIGH");
    }

    public static boolean isValidCategory(String category) {
        if (category == null) {
            return false;
        }

        String normalizedCategory = category.trim();
        return normalizedCategory.equalsIgnoreCase("PHISHING")
                || normalizedCategory.equalsIgnoreCase("MALWARE")
                || normalizedCategory.equalsIgnoreCase("UNAUTHORIZED_ACCESS")
                || normalizedCategory.equalsIgnoreCase("DATA_BREACH");
    }
}
