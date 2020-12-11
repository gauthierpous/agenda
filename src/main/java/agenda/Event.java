package agenda;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


public class Event {

    /**
     * The myTitle of this event
     */
    private String myTitle;
    
    /**
     * The starting time of the event
     */
    private LocalDateTime myStart;

    /**
     * The durarion of the event 
     */
    private Duration myDuration;
    
    /**
     * La liste de tous les jours de l'évènement
     */
    private ArrayList<LocalDateTime> joursEvent = new ArrayList<>();
    
    /**
     * Le booleen indiquant si l'évèment est dans un jour choisi
     */
    private boolean isInDay = false;


    /**
     * Constructs an event
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     */
    public Event(String title, LocalDateTime start, Duration duration) {
        this.myTitle = title;
        this.myStart = start;
        this.myDuration = duration;
    }

    /**
     * Tests if an event occurs on a given day
     *
     * @param aDay the day to test
     * @return true if the event occurs on that day, false otherwise
     */
    public boolean isInDay(LocalDate aDay) {
        
        //On détermine la date de fin de l'évènement
        LocalDateTime myEnd = this.myStart.plus(this.myDuration.toMinutes(), ChronoUnit.MINUTES);
        
        //On calcule la différence de jour entre le début et la fin de l'évènement
        int jourDebut = this.myStart.getDayOfMonth();
        int jourFin = myEnd.getDayOfMonth();
        int difference = jourFin - jourDebut;
        
        //On ajoute le jour de début à la liste
        this.joursEvent.add(this.myStart);
        
        //Ajout des autres jours de l'évènement
        for (int i = 1; i <= difference; i++) {
            this.joursEvent.add(this.myStart.plus(i, ChronoUnit.DAYS));
        }
        
        //Test de la présence du jour en paramètre dans la liste
        this.joursEvent.forEach(event -> {
            if (event.toLocalDate().equals(aDay)) {
                System.out.println("C'est bon, l'évènement ce déroule bien aujourd'hui");
                this.isInDay = true;
            }
        });
        return this.isInDay;
        
    }
   
    /**
     * @return the myTitle
     */
    public String getTitle() {
        return myTitle;
    }

    /**
     * @return the myStart
     */
    public LocalDateTime getStart() {
        return myStart;
    }


    /**
     * @return the myDuration
     */
    public Duration getDuration() {
        return myDuration;
    }
    
    @Override
    public String toString(){
        return this.myTitle;
    }

   
    
}
