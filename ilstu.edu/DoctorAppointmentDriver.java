/*
* Created on: Nov 11, 2024
*
* ULID: jrcarwa
* Class: IT 168
*/

/**
* Doctor Appointment driver class with main funcitonality.
*
* @author John Carway
*/

import java.util.Scanner;

public class DoctorAppointmentDriver {

    static Scanner input = new Scanner(System.in);
    static DoctorAppointmentBook doctorBook = new DoctorAppointmentBook();

    public static void main(String[] args) {
        int lastInput = -1;
        displayMenu();
        while (lastInput != 4) {
            // validate input to ensure its actually a number and is within the range.
            if (!input.hasNextInt()) {
                System.out.println("Invaild input. Please enter the number 1-4.");
                displayMenu();
                input.nextLine();
              } else {
                int tempInput = input.nextInt();
                if (tempInput >= 1 && tempInput <= 4) 
                    lastInput = tempInput;
                else
                  System.out.println("Invaild input. Please enter the number 1-4.");
              }

              if (lastInput == 1) {
                requestAppointment();
              } else if(lastInput == 2 || lastInput == 3) {
                printSchedules(lastInput);
              }
        }
        input.close();
        System.out.println("Goodbye.");
    }

    private static void displayMenu() {
        System.out.println("What would you like to do?");
        System.out.println("\t1: Schedule an appointment.");
        System.out.println("\t2: List Appointment Period Schedule.");
        System.out.println("\t3: List Full Schedule.");
        System.out.println("\t4: Quit.");
    }
    
    private static void requestAppointment() {
        int period = -1;
        int duration = -1;
        System.out.println("Please enter the appointment period. 1-10.");
        while (period == -1) {
            if (!input.hasNextInt()) {
                System.out.println("Invaild input. Please enter the number 1-10.");
                input.nextLine();
          } else {
            int tempInput = input.nextInt();
            if (tempInput >= 1 && tempInput <= 10) 
                period = tempInput;
            else
              System.out.println("Invaild input. Please enter the number 1-10.");
          }
        }
        
        System.out.println("Please enter the duration. 1-60");
        while (duration == -1) {
            if (!input.hasNextInt()) {
                System.out.println("Invaild input. Please enter the number 1-60.");
                input.nextLine();
          } else {
            int tempInput = input.nextInt();
            if (tempInput >= 1 && tempInput <= 60)
                duration = tempInput;
            else
              System.out.println("Invaild input. Please enter the number 1-60.");
          }
        }
        boolean appointmentMade = doctorBook.makeAppointment(period-1, duration-1);
        if (appointmentMade)
            System.out.println("Your " + duration + " minute doctor's appointment has been scheduled.");    
        else
            System.out.println("" + duration + " minutes is not available during appointment period " + period);
    }

    private static void printSchedules(int menuSelection) {
        if (menuSelection == 2) {
            // print specific one.
            int period = -1;

            System.out.println("Please enter appointment period to print. 1-10");
            while(period == -1) {
                if (!input.hasNextInt()) {
                    System.out.println("Invaild input. Please enter the number 1-10.");
                    input.nextLine();
              } else {
                int tempInput = input.nextInt();
                if (tempInput >= 1 && tempInput <= 60)
                    period = tempInput;
                else
                  System.out.println("Invaild input. Please enter the number 1-10.");
              }
            }
            System.out.println(doctorBook.getAppointmentPeriodList()[period-1]);
        } else
            System.out.println(doctorBook);
    }
}