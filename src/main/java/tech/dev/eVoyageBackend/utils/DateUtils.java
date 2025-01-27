package tech.dev.eVoyageBackend.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
    public static LocalDate calculateNextExecutionDate(LocalDate currentExecutionDate, String frequency, Date paymentDate) {
        String resolvedFrequency = frequency != null ? frequency.toLowerCase() : "month";

        // Déterminer la date initiale
        LocalDate nextExecutionDate = (currentExecutionDate != null)
                ? currentExecutionDate
                : (paymentDate != null) ? convertToLocalDate(paymentDate) : LocalDate.now();

        // Calcul basé sur la fréquence
        switch (resolvedFrequency) {
            case "month":
                while (!nextExecutionDate.isAfter(LocalDate.now())) {
                    nextExecutionDate = nextExecutionDate.plusMonths(1);
                }
                break;
            case "week":
                while (!nextExecutionDate.isAfter(LocalDate.now())) {
                    nextExecutionDate = nextExecutionDate.plusWeeks(1);
                }
                break;
            case "day":
                while (!nextExecutionDate.isAfter(LocalDate.now())) {
                    nextExecutionDate = nextExecutionDate.plusDays(1);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown payment frequency: " + resolvedFrequency);
        }

        LocalDate endOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        return nextExecutionDate.isAfter(endOfMonth) ? null : nextExecutionDate;
    }

    private static LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static String getCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        return LocalDateTime.now().format(formatter);
    }
}
