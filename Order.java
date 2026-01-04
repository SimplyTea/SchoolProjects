/*
* Created on: Oct 28, 2024
*
* ULID: jrcarwa
* Class: IT 168
*/

/**
* Class to create and handle an order made by a user. 
*
* @author John Carway
*
*/

import java.text.DecimalFormat;

public class Order {
    // statics
    

    // integers
    private static int totalOrders = 0;
    

    // Decimal Formaters
    private static DecimalFormat moneyFormat = new DecimalFormat("$0.00");
    private static DecimalFormat orderFormat = new DecimalFormat("0000");

    // private/instance


    // Strings
    private String restaurant; // the file didn't say to add this, but it never said how we are meant to track restaruant without-
    // having to hardcode a check for every entry name and it's responding restaurant. which in a real world situation would not work as
    // intended if two restaurants had the same item name but different prices or same item name in general as the toString would also fail.
    private String personName;
    private String entry;

    // doubles
    private double price;
    private double totalPrice;
    final double deluxePrice = 9.5, regularPrice = 8.25, sandwichPrice = 5.99, duelPickPrice = 12.0, soupPrice = 6, drinkComboPrice = 2.47, drinkPrice = 2.6, sideComboPrice = 4.5, sidePrice = 5.0, deliveryFee = 2.0;

    

    // integers
    private int uidNumber;
    private int quanity;

    // booleans
    private boolean combo;
    private static boolean drink;
    private static boolean side;

    public Order(String restaurant, String personName, String entry, int quanity, boolean combo) {
        totalOrders += 1;
        uidNumber = totalOrders;
        this.restaurant = restaurant;
        this.personName = personName; // once again, the instructions said 'item name' but didn't specify why item name and entry were the same thing-
        // and never said where we are meant to get the name of the person who ordered.
        this.entry = entry;
        this.quanity = quanity;
        this.combo = combo;
        if (combo) {
            drink = true;
            side = true;    
        }
    }


    // getters

    public String getPersonName() {
        return personName;
    }

    public String getEntry() {
        return entry;
    }

    public double getPrice() {
        calculatePrice(); // incase something was changed since last call.
        return price;
    }

    public double getTotalPrice() {
        calculatePrice(); // incase something was changed since last call.
        return totalPrice;
    }

    public int getUIDNumber() {
        return uidNumber;
    }

    public int getQuanity() {
        return quanity;
    }

    public boolean getCombo() {
        return combo;
    }

    public boolean getDrink() {
        return drink;
    }

    public boolean getSide() {
        return side;
    }

    // setters
    
    public static void setDrink(boolean bool) {
        drink = bool;
    }

    public static void setSide(boolean bool) {
        side = bool;
    }

    private void calculatePrice() {
        double calculatedTotal = quanity;
        // instead of having to type 0.0 and have it do calculatedTotal = quantity * price
        // I thought this would be easier.

        if (restaurant.equals("Chick-fil-A")) {
            
            // since this will only set price we don't need brackets.
            if (entry.equals("Deluxe"))
                calculatedTotal *= deluxePrice;
             else if (entry.equals("Regular")) 
                calculatedTotal *= regularPrice;
             else // it's a sandwich
                calculatedTotal *= sandwichPrice;

        } else { // it has to be the other one.

            // since this will only set price we don't need brackets.
            if (entry.equals("Turkey Sandwich with Salad") || entry.equals("Tuna Sandwich with Salad"))
                calculatedTotal *= duelPickPrice;
             else // it's a soup
                calculatedTotal *= soupPrice;
        }
        
        price = calculatedTotal;

        if (drink) {
            if (combo) 
                calculatedTotal += drinkComboPrice;
            else
                calculatedTotal += drinkPrice;
        }
            

        if (side) {
            if (combo) 
                calculatedTotal += sideComboPrice;
            else
                calculatedTotal += sidePrice;
        }

        // Never shown if the total was adding or mutiplying the drinks and sides based on the quantity so I assumed it's adding.
            

        calculatedTotal += (calculatedTotal * 0.09);

        // delivery fee not included in tax
        calculatedTotal += deliveryFee;

        totalPrice = calculatedTotal;

        // For some reason some of the test data was incorrect on the final calculation? I don't understand this..
        // All my numbers expect the total matched.
        // Sample output 3 stated:
        // Subtotal: 24.00
        // Taxes: 2.16
        // Fee: 2.00
        // Total: 27.47
        // Which doesn't make sense as 24 + 2.16 + 2 adds to 28.16 and that's not even including the drink or sides
        // from the fact it's a combo meal.
    }


    // default methods overrides.

    public String toString() {
        String orderReciept = "";
        calculatePrice(); // incase something was changed since last call.
        orderReciept += "Order Number: " + orderFormat.format(uidNumber);
        orderReciept += "\nOrder Name: " + personName;
        orderReciept += "\nOrdered from: " + restaurant;
        orderReciept += "\nItem Type: " + entry;
        orderReciept += "\nQuantity: " + quanity;
        orderReciept += "\nSubtotal: " + moneyFormat.format(price);
        orderReciept += "\nTaxes: " + moneyFormat.format(price * 0.09);
        orderReciept += "\nDelivery fee: $2.00";
        orderReciept += "\nTotal (including drinks and sides): " + moneyFormat.format(totalPrice);
        return orderReciept;
    }

}