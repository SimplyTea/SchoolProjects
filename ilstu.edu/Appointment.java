/*
* Created on: Nov 11, 2024
*
* ULID: jrcarwa
* Class: IT 168
*/

/**
* Class to represent each minute in an appointment period.
*
* @author John Carway
*/

public class Appointment {
    private boolean available; // idk why I can't just set it to true here.

    public Appointment() {
        available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void makeUnavailable() {
        available = false;
    }
    
    public String toString() {
        return "Available: " + available;
    }
}