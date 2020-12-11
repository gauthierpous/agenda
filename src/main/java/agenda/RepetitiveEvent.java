package agenda;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * Description : A repetitive Event
 */
public class RepetitiveEvent extends Event {

    /**
     * Constructs a repetitive event
     *
     * @param title the title of this event
     * @param start the start of this event
     * @param duration myDuration in seconds
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     */
    private final ChronoUnit frequency;
    private ArrayList<LocalDate> joursExceptionnels = new ArrayList<>();

    public RepetitiveEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency) {
        super(title, start, duration);
        this.frequency = frequency;
    }

    /**
     * Adds an exception to the occurrence of this repetitive event
     *
     * @param date the event will not occur at this date
     */
    public void addException(LocalDate date) {
        this.joursExceptionnels.add(date);
    }

    /**
     *
     * @return the type of repetition
     */
    public ChronoUnit getFrequency() {
        return this.frequency;
    }

    @Override
    public boolean isInDay(LocalDate aDay) {
        LocalDate dateTest;
        dateTest = this.getStart().toLocalDate();

        //Tant que la date en paramètre est après le début de l'évènement,
        // Ajouter +1 répétition à l'évènement
        while (aDay.isAfter(dateTest) || aDay.equals(dateTest)) {
            if (aDay.isEqual(dateTest)) {
                this.isInDay = true;
            }
            dateTest = dateTest.plus(1, frequency);
        }
        
        //Tester si le jour ne porte pas d'exception
        this.joursExceptionnels.forEach((j) -> {
            if (aDay.isEqual(j)) {
                this.isInDay = false;
            }
        });
        return this.isInDay;
    }
}
