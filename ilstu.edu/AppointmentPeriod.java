/*
* Created on: Nov 11, 2024
*
* ULID: jrcarwa
* Class: IT 168
*/

/**
* Appointment period class to act as a holder for the appointment minutes.
*
* @author John Carway
*/

public class AppointmentPeriod {
    private Appointment[] appointmentList;
    // I realistically feel like a private boolean[] would be better.
    // but this is what the program requires.

    public AppointmentPeriod() {
        appointmentList = new Appointment[60]; // load the space in memory first for faster allocation-
        for(int index = 0; index < appointmentList.length; index++) { // and to ensure no OutOfBounds/MemoryRunTime/blah blah fails.
            appointmentList[index] = new Appointment();
        }
    }

    public Appointment[] getAppointmentList() {
        Appointment[] deepCopyList = new Appointment[appointmentList.length];
        // I see that the arrays have a .clone method but im assuming I can't use this..
        for(int i = 0; i < appointmentList.length; i++) {
            deepCopyList[i] = appointmentList[i];
        }
        return deepCopyList;
    }

    public String toString() {
        // needs to return a 'Minute 0: Available: false' etc...
        String callback = "";
        for(int appointmentIndex = 0; appointmentIndex < appointmentList.length; appointmentIndex++) {
            callback += "Minute: " + appointmentIndex + " " + appointmentList[appointmentIndex] + "\n";
        }
        return callback;
    }

}