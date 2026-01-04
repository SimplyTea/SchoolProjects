/*
* Created on: Nov 11, 2024
*
* ULID: jrcarwa
* Class: IT 168
*/

/**
* Doctor Appointment Booking wrapper to contain all important appointment functionality.
*
* @author John Carway
*/

public class DoctorAppointmentBook {
    private AppointmentPeriod[] appointmentPeriodList;

    public DoctorAppointmentBook() {
        appointmentPeriodList = new AppointmentPeriod[10]; // Load the space in memory first for faster allocation of uh 10 * 60 classes?
        for(int appointmentPeriodListIndex = 0; appointmentPeriodListIndex < appointmentPeriodList.length; appointmentPeriodListIndex++) {
            appointmentPeriodList[appointmentPeriodListIndex] = new AppointmentPeriod();
        }
    }

    public void setAppointmentPeriodList(AppointmentPeriod[] appointmentPeriodList) {
        this.appointmentPeriodList = appointmentPeriodList;
    }

    public AppointmentPeriod[] getAppointmentPeriodList() {
        AppointmentPeriod[] deepCopy = new AppointmentPeriod[appointmentPeriodList.length];
        for(int appointmentPeriodListIndex = 0; appointmentPeriodListIndex < appointmentPeriodList.length; appointmentPeriodListIndex++) {
            deepCopy[appointmentPeriodListIndex] = appointmentPeriodList[appointmentPeriodListIndex]; // deep copy of deep copy >:D
        }
        return appointmentPeriodList;
    }

    private boolean isMinuteFree(int period, int minute) { // probably not how they want me to do it, but this works.
        return appointmentPeriodList[period].getAppointmentList()[minute].isAvailable();
    }

    private void reserveAppointment(int period, int startMinute, int duration) {
        // may need to redo.
        for(int targetedIndex = startMinute; targetedIndex < (startMinute + duration); targetedIndex++) {
            appointmentPeriodList[period].getAppointmentList()[targetedIndex].makeUnavailable();
        }
    }

    public int findFreeAppointment(int period, int duration) {
        // Have free time?
        int count = -1;
        int start = -1;
        for(int targetedIndex = 0; targetedIndex < appointmentPeriodList[period].getAppointmentList().length; targetedIndex++) {
            if (isMinuteFree(period, targetedIndex)) {
                if (count == -1) { // first available slot.
                    start = targetedIndex;
                    count = 0;
                }
                count++;
                if (count == duration) // this row has enough available slots.
                    return start;
            } else {
                count = -1;
                start = -1;
            }
        }
        return -1; // No free time. Am too busy.
    }

    public boolean makeAppointment(int period, int duration) {
        int appointmentTime = findFreeAppointment(period, duration);
        if (appointmentTime == -1)
            return false; // no availble time found D:
        reserveAppointment(period, appointmentTime, duration);
        return true; // found time :D
    }
    
    public String toString() {
        String callback = "";
        for(int index = 0; index < appointmentPeriodList.length; index++) {
            callback += "Showing appointments for appointment period: " + index + "\n";
            callback += appointmentPeriodList[index] + "\n";
        }
        return callback;
     }
}