package agenda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Description : An agenda that stores events
 */
public class Agenda {
    /**
     * Adds an event to this agenda
     *
     * @param e the event to add
     */
    private ArrayList<Event> listeEvenements = new ArrayList<>();
    private ArrayList<Event> listeEvenementsPourUnJour = new ArrayList<>();
    private ArrayList<Event> listeEvenementsParTitre = new ArrayList<>();
    private boolean isFree = true;
    
    
    public void addEvent(Event e) {
        this.listeEvenements.add(e);
    }

    /**
     * Computes the events that occur on a given day
     *
     * @param day the day to test
     * @return and iteraror to the events that occur on that day
     */
    public List<Event> eventsInDay(LocalDate day) {
        this.listeEvenements.forEach((e) -> {
            if (e.isInDay(day)) {
                this.listeEvenementsPourUnJour.add(e);
            }
        });
        return this.listeEvenementsPourUnJour;
    }
    
    /**
     * Trouver les événements de l'agenda en fonction de leur titre
     * @param title le titre à rechercher
     * @return les événements qui ont le même titre
     */
    public List<Event> findByTitle(String title) {
        this.listeEvenements.forEach((e) -> {
            if (e.getTitle().equals(title)) {
                this.listeEvenementsParTitre.add(e);
            }
        });
        return this.listeEvenementsParTitre;
    }
    
    /**
     * Déterminer s’il y a de la place dans l'agenda pour un événement
     * @param e L'événement à tester (on se limitera aux événements simples)
     * @return vrai s’il y a de la place dans l'agenda pour cet événement
     */
    public boolean isFreeFor(Event e) {
        LocalDate jourEvenement;
        jourEvenement = e.getStart().toLocalDate();
        LocalDateTime debut = e.getStart();
        LocalDateTime fin = e.getEnd();
        
        this.eventsInDay(jourEvenement ).forEach((evenement) -> {
            //heure de début de A après heure de fin de B 
            //ou heure de fin de A avant heure de début de B
            if (debut.isAfter(evenement.getEnd()) || fin.isBefore(evenement.getStart())) {
                this.isFree = true;            
            }
        });
        return this.isFree;
    }
}
