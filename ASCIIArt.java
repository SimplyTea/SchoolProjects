import java.util.Scanner;

public class ASCIIArt {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int lastChoice = 4; // init to something other than 0 and out of bounds so it doesn't quit asap.
        char space = ' '; // Let's save this in memory so we don't append to the heap each time just to grabage collect it.
        // Although using /t for tabs is a good idea, the examples shown only using single spaces. So I will assume we do not need tabs.

        while (lastChoice != 0) {
            System.out.println("Input an integer from 0-3 to draw one of the following ASCII shapes:");
            System.out.println("0 -/ Exit");
            System.out.println("1 -/ Rectangle");
            System.out.println("2 -/ X");
            System.out.println("3 -/ Square");
            System.out.print("Your choice. > ");

            // However, because we have yet to go over methods prior to today, I'm assuming I cannot use them in this.
            // So this will just be left as a 'hacky fix' for a while statement/condition to verify input integerity.
            // This will also be done for all other input sanity checks in this program.

            // validate input by user
            boolean validatedInput = false;         
            if (input.hasNextInt()) {
                int choiceTemp = input.nextInt();
                if (choiceTemp >= 0 && choiceTemp <= 3) {
                    lastChoice = choiceTemp;
                    validatedInput = true;
                }
            }
            
            while (!validatedInput) {
                if (input.hasNext() && !input.hasNextInt()) {
                    System.out.println("The choice you inputed does not fit the range of an integer from 0-3.");
                    System.out.print("Please input a choice from 0-3. > ");
                    input.next();
                } else if (input.hasNextInt()) {
                        int choiceTemp = input.nextInt();
                        if (choiceTemp >= 0 && choiceTemp <= 3) {
                            lastChoice = choiceTemp;
                            validatedInput = true;
                        }
                    }
                
            }

            validatedInput = false; // we're likely gonna need to reuse this, as I don't want to create another varible-
                                    // while we have one in memory already.
            int height = 0;
            int width = 0;
            char chosenChar;
                                    
            switch (lastChoice) { // I personally like using switch statements instead of if chains for similar datatypes.
                case 3: // Sqaure

                // We know this is a sqaure, so it will all be the same length.
                System.out.print("Please enter a height length for a square side. > ");
                if (input.hasNextInt()) {
                    int numTemp = input.nextInt();
                        if (numTemp > 0) {
                            height = numTemp;
                            width = height * 2;
                            validatedInput = true;
                        } else {
                            System.out.println("Please make sure the integer you enter is positive.");
                        }
                }

                while(!validatedInput) {
                    if (input.hasNext() && !input.hasNextInt()) {
                        System.out.println("The height length you inputed is not an integer.");
                        System.out.print("Please input a integer for the height length. > ");
                        input.next();
                    } else if (input.hasNextInt()) {
                        int numTemp = input.nextInt();
                        if (numTemp > 0) {
                            height = numTemp;
                            width = height * 2;
                            validatedInput = true;
                        } else {
                            System.out.println("Please make sure the integer you enter is positive.");
                        }
                    }
                
                }

                // now let's get the char they want it to use
                System.out.print("What is the character you would like to use? > ");
                chosenChar = input.next().charAt(0);

                for (int h = 1; h <= height; h++) {
                    for (int w = 1; w <= width; w++) {
                        System.out.print(chosenChar);
                    }
                    System.out.println(); // skips this line!
                }
                
                    break;
                case 2: // X

                    /* 
                        Drawing an 'x' with output isn't quite possible with anything less than 5,
                        and isn't possible with even numbers. Thankfully, the limits are provided as 9-25.
                        This means we can only do it in 9 or 19. However, we aren't allowed to hardcore this.
                        So get ready for some small math!
                    */ 

                    // Let's first ask them for the number of characters within the limit.
                    int characterAmount = 0;
                    System.out.print("Please enter the integer number of characters. Within a range of 9-25. > ");
                    if (input.hasNextInt()) {
                        int numTemp = input.nextInt();
                        if (numTemp >= 9 && numTemp <= 25 && numTemp % 2 != 0) {
                            characterAmount = numTemp;
                        } else {
                            System.out.println("The number you entered is not within the range 9-25 or is even.");
                            input.next();
                        }
                    }

                    while(characterAmount == 0) {
                        if (input.hasNext() && !input.hasNextInt()) {
                            System.out.println("The number you had entered is not an integer");
                            System.out.print("Please input a integer for the number of characters > ");
                            input.next();
                        } else {
                            int numTemp = input.nextInt();
                            if (numTemp >= 9 && numTemp <= 25) {
                                characterAmount = numTemp;
                            } else {
                                System.out.println("The number you entered is not within the range 9-25.");
                                input.next();
                            }
                        }
                    }

                    // now let's get the char they want it to use
                    System.out.print("What is the character you would like to use? > ");
                    chosenChar = input.next().charAt(0);
                    characterAmount = (characterAmount + 1)/2;

                    for (int h = 0; h < characterAmount; h++) {
                        for (int i = 0; i < characterAmount; i++) {
                            if (h == i || i == characterAmount -1 - h) {
                                System.out.print(chosenChar);
                            } else {
                                System.out.print(space);
                            }
                        }
                        System.out.println(); // Skips the line
                    }

                    break;
                case 1: // Rectangle

                    // first input to verify is the height/length
                    System.out.print("Please enter a integer height for the rectangle. > ");
                    if (input.hasNextInt()) {
                        int numTemp = input.nextInt();
                        if (numTemp > 0) {
                            height = numTemp;
                            validatedInput = true;
                        }
                    }

                    while(!validatedInput) {
                        if (input.hasNext() && !input.hasNextInt()) {
                            System.out.println("The height you inputed is not an integer.");
                            System.out.print("Please input a integer for the height. > ");
                            input.next();
                        } else if (input.hasNextInt()) {
                            int numTemp = input.nextInt();
                            if (numTemp > 0) {
                                height = numTemp;
                                validatedInput = true;
                            } 
                        }
                    
                    }

                    validatedInput = false; // once again, we shall reuse this.

                    // Now we have the height! Let's get the width.
                    System.out.print("Please enter a integer width for the rectangle. > ");
                    if (input.hasNextInt()) {
                        int numTemp = input.nextInt();
                        if (numTemp > 0) {
                            width = numTemp;
                            validatedInput = true;
                        }
                        
                    }

                    while(!validatedInput) {
                        if (input.hasNext() && !input.hasNextInt()) {
                            System.out.println("The width you inputed is not an integer.");
                            System.out.print("Please input a integer for the width. > ");
                            input.next();
                        } else if (input.hasNextInt()) {
                            int numTemp = input.nextInt();
                            if (numTemp > 0) {
                                width = numTemp;
                                validatedInput = true;
                            }
                        }
                    }

                    // Now let's get the char they want to use.
                    System.out.print("What is the character you would like to use? > ");
                    chosenChar = input.next().charAt(0);

                    for (int h = 1; h <= height; h++) {
                        for (int w = 1; w <= width; w++) {
                            if (w == 1 || w == width || h == 1 || h == height) {
                                System.out.print(chosenChar);
                            } else {
                                System.out.print(space);
                            }
                        }
                        System.out.println(); // Skips to the next line.
                    }

                    // On this one we don't need to 'break;' as it's the last one and will only slow compiler time.
                    break; // I am adding it only so I can develop the habit better in the future.
            }

            System.out.println(); // empty just so it skips a line next time, personal preference so that it doesn't look weird.
        }
        input.close(); // Let's not allow for possible memory leaks.

        // They had entered 0, and therefore quit the program.
        System.out.println("Thank you for using the ASCIIArt generator program. Goodbye!");
    }
}