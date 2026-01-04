/*
* Created on: Oct 28, 2024
*
*/

/**
*
* @author N/A
*
*/

import java.util.Scanner;

public class DoorDashDelivery {

    private static Scanner scan = new Scanner(System.in);

    // there's no way this is actually how it's meant to be done..
    private static Order orderOne;
    private static Order orderTwo;
    private static Order orderThree;
    private static Order orderFour;
    private static Order orderFive;

    public static void main(String[] args) {
        System.out.println("Welcome to DoorDash! Which restaurant would you like to order from?");

        while (true) {
         String restaruant = "N/A";

         // I took your advice and this time I made it only check the type of the input once per input.
         // Instead of having it check once and on a rare execption check twice.

         while (true) {
          System.out.println("1 - Chick-fil-A");
          System.out.println("2 - Panera");
          System.out.print("Enter your choice: ");
          if (!scan.hasNextInt()) {
            System.out.println("Invaild input. Please enter the number 1 or 2.");
            scan.next();
          } else {
            int tempInput = scan.nextInt();
            if (tempInput == 1 || tempInput == 2) {
              if (tempInput == 1) {
                restaruant = "Chick-fil-A";
              } else
                restaruant = "Panera";
              break;
            } else
              System.out.println("Invaild input. The number you entered was not 1 or 2.");
          }
         }
         
         int selection = 0;
       while (true) {
          displayRestaurantMenu(restaruant);
          if (!scan.hasNextInt()) {
            System.out.println("Invaild input. Please enter the number 1 or 2.");
            scan.next();
          } else {
            int tempInput = scan.nextInt();
            if (tempInput == 1 || tempInput == 2) {
              selection = tempInput;
              break;
            } else
              System.out.println("Invaild input. The number you entered was not 1 or 2.");
          }
         }
        handleSelection(restaruant, selection);

        int orderEndedSelection = 0;
        while (true) {
          System.out.println("1 - Continue");
          System.out.println("2 - Proceed to checkout");
          System.out.println("3 - Cancel and Quit");
          System.out.print("What would you like to do?: ");
          if (!scan.hasNextInt()) {
            System.out.println("Invaild input. Please enter the number 1, 2 or 3.");
            scan.next();
          } else {
            int tempInput = scan.nextInt();
            if (tempInput == 1 || tempInput == 2 || tempInput == 3) {
              orderEndedSelection = tempInput;
              break;
            } else
              System.out.println("Invaild input. The number you entered was not 1 or 2 or 3.");
          }
        }
      
        if (orderEndedSelection == 2) { // Checkout
          break;
        } else if (orderEndedSelection == 3) {
          orderOne = null;
          orderTwo = null;
          orderThree = null;
          orderFour = null;
          orderFive = null;
          break;
        } // Quit

      }

      scan.close();
      printDeliverySummary();
    }

    private static void displayRestaurantMenu(String restaruant) {
      System.out.println("Welcome to " + restaruant + "!");
      if (restaruant.equals("Chick-fil-A")) {
        System.out.println("1 - Meal (Comes with a side and a drink)");
        System.out.println("2 - Sandwich");
      } else {
        System.out.println("1 - You pick two (Comes with a side and a drink)");
        System.out.println("2 - Soup");
      }
      System.out.print("Please select an option number from the above menu: ");
    }

    private static void handleSelection(String restaruant, int selection) {
      String subSelection = "N/A";

      // I have decided to make all of these use the same input verifcation for my ease.
      Char selectionOne;
      Char selectionTwo;
      String selectionOneChoice;
      String selectionTwoChoice;
      String restaruantSelectionName;
      boolean combo = false;

      if (restaruant.equals("Chick-fil-A")) {
        if (selection == 1) {
          selectionOne = 'D';
          selectionTwo = 'R';
          restaruantSelectionName = "meals: ";
          selectionOneChoice = "Deluxe";
          selectionTwoChoice = "Regular";
          combo = true;
        } else {
          selectionOne = 'G';
          selectionTwo = 'C';
          restaruantSelectionName = "sandwiches: ";
          selectionOneChoice = "Grilled chicken Sandwich";
          selectionTwoChoice = "Crispy chicken Sandwich";
        }
      } else {
          if (selection == 1) {
          selectionOne = 'K';
          selectionTwo = 'U';
          restaruantSelectionName = "meals: ";
          selectionOneChoice = "Turkey Sandwich with Salad";
          selectionTwoChoice = "Tuna Sandwich with Salad";
          combo = true;
          } else {
            selectionOne = 'T';
            selectionTwo = 'V';
            restaruantSelectionName = "soups: ";
            selectionOneChoice = "Tomato Soup";
            selectionTwoChoice = "Vegetable Soup";
        }
      }

      while (true) {
        System.out.println("Here are the options for " + restaruant + " " + restaruantSelectionName);
        System.out.println("\t" + selectionOne + " - " + selectionOneChoice);
        System.out.println("\t" + selectionTwo + " - " + selectionTwoChoice);
        System.out.print("Please make a selection from the above options(" + selectionOne + "/" + selectionTwo + "): ");
        Char tempCharacter = scan.next().charAt(0);
        if (tempCharacter == selectionOne || tempCharacter == selectionTwo) {
          if (selectionOne == tempCharacter) {
            subSelection = selectionOneChoice;
          } else
          subSelection = selectionTwoChoice;
          break;
        } else
          System.out.println("Invaild input. Please enter either (" + selectionOne + "/" + selectionTwo + ")");
      }

      int quanity = 1;
      while (true) {
        System.out.print("Enter quantity: ");
        if (!scan.hasNextInt()) {
          System.out.println("Invaild input. Please enter a positive number.");
          scan.next();
        } else {
          int tempInput = scan.nextInt();
          if (tempInput >= 1) {
            quanity = tempInput;
            break;
          } else
            System.out.println("Invaild input. Please enter a positive number.");
        }
      }

      boolean drink = combo;
      boolean side = combo;

      if (!combo) { // combos automatically have drink and side included.
        while (true) {
          System.out.print("Do you want to add a drink? (true/false): ");
          if (!scan.hasNextBoolean()) {
            System.out.println("Invaild input. Please enter a boolean.");
            scan.nextLine();
          } else {
            drink = scan.nextBoolean();
            scan.nextLine();
            break;
          }
        }
        while (true) {
          System.out.print("Do you want to add a side? (true/false): ");
          if (!scan.hasNextBoolean()) {
            System.out.println("Invaild input. Please enter a boolean.");
            scan.nextLine();
          } else {
            side = scan.nextBoolean();
            scan.nextLine();
            break;
          }
        }
      }
      
      System.out.print("Enter your name for the order: ");
      String personName = scan.next();

      Order personOrder = new Order(restaruant, personName, subSelection, quanity, combo);
      if (!combo) {
        Order.setDrink(drink);
        Order.setSide(side);
      }
      determineOrder(personOrder);
    }

    private static void determineOrder(Order order) {
      if (orderOne == null) {
        orderOne = order;
      } else if(orderTwo == null) {
        orderTwo = order;
      } else if(orderThree == null) {
        orderThree = order;
      } else if(orderFour == null) {
        orderFour = order;
      } else if(orderFive == null) {
        orderFive = order;
      } else
        System.out.println("You have reached the maximum number of orders for the day.");
    }

    private static void printDeliverySummary() {
      if (orderOne != null) {
        System.out.println(orderOne);
      }
      if (orderTwo != null) {
        System.out.println(orderTwo);
      }
      if (orderThree != null) {
        System.out.println(orderThree);
      }
      if (orderFour != null) {
        System.out.println(orderFour);
      }
      if (orderFive != null) {
        System.out.println(orderFive);
      }

      System.out.println("Thank you for using DoorDash!");
    }

}