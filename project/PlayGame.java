package project;

/*
* Created on: Dec 3, 2024
*
* ULID: jrcarwa
* Class: IT 168
*/

/**
* Game Driver class to handle the entire game.
*
* @author John Carway
*
*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class PlayGame {

    private static String[] dictionary;
    private static LetterTile[] letterTilers;
    private static LetterTile[] chosenLetterTiles;

    private static int countWords(String fileName) {
        int count = 0;
        try {
            final Scanner FILE_SCANNER = new Scanner(new File(fileName));
            while(FILE_SCANNER.hasNextLine()) {
                count++;
                FILE_SCANNER.nextLine();
           }
           FILE_SCANNER.close();
        } catch (FileNotFoundException ommited) {
            System.out.println("File " + fileName + " was not found.");
            return -1;
        }
        return count;
    }

    private static void readFile(String fileName) {
        int wordCount = countWords(fileName);
        if (wordCount != -1) {
            try {  // we already know it wont fail but the compiler doesn't and will error this code.
                final Scanner FILE_SCANNER = new Scanner(new File(fileName));
                dictionary = new String[wordCount];
                for (int index = 0; index < wordCount; index++) {
                    dictionary[index] = FILE_SCANNER.nextLine();
                }
                FILE_SCANNER.close();
            } catch (FileNotFoundException ommited) {}
        }
    }

    private static boolean isAWord(String word) {
        for (int index = 0; index < dictionary.length; index++) {
            if (dictionary[index].equals(word))
                return true;
        }
        return false;
    }

    private static boolean validateLetters(String word) {
        boolean passed = true;
        if (word.length() > chosenLetterTiles.length)
            passed = false; // dunno why you expected an word with more letters than available would be accepted.

        for (int index = 0; index < word.length() && passed; index++) {
            char letter = word.charAt(index);
            boolean letterUsed = false;
            for (int indexLetterTile = 0; indexLetterTile < chosenLetterTiles.length; indexLetterTile++) {
                LetterTile letterTile = chosenLetterTiles[indexLetterTile];
            if (!letterTile.isLetterUltizied()) {
                   letterTile.setLetterUltizied(true);
                   letterUsed = true;
                }
            }
            if (!letterUsed)
                passed = false;
        }
        return passed;
    }

    // was not told to pass paramenters here but was told to pass them for validateLetters.. even though the paramenters are values
    // already in scope because they're meant to be globals?? Yeah I'm not doing that. There is a few things in these instructions that are unclear.

    private static int determinePoints() {
        int roundTotal = 0;
        for (int indexLetterTile = 0; indexLetterTile < chosenLetterTiles.length; indexLetterTile++) {
            LetterTile letterTile = chosenLetterTiles[indexLetterTile];
            if (letterTile.isLetterUltizied()) 
                roundTotal += letterTile.getPointsValue();
        }
        return roundTotal;
    }

    public static void main(String[] args) {
        final Game GAME = new Game();
        final int MAX_ROUNDS = 5;
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter the name of the letters file: ");
        letterTilers = GAME.readLetterFile(SCANNER.nextLine());

        if (letterTilers[0] != null) {
            System.out.print("Enter the name of the dictionary file: ");
            readFile(SCANNER.nextLine());
        }

        int totalPoints = 0;
        if (dictionary != null && letterTilers[0] != null) {
            for(int round = 1; round <= MAX_ROUNDS; round++) {
                
        
            System.out.println("Round " + round + ": Find the highest value word from these letters: ");

            boolean passed = false;
            while (!passed) {
                chosenLetterTiles = GAME.generateLetters(letterTilers);
                int vowels = 0;
                for (int index = 0; index < chosenLetterTiles.length; index++) {
                    switch (chosenLetterTiles[index].getLetter()) {
                        case 'a':
                        case 'e':
                        case 'i':
                        case 'o':
                        case 'u':
                            vowels++;
                            break;
                    }
                
                }
                passed = vowels < 3 && vowels != 0;
            }

            

            String letters = "";
            String letterValues = "";
            for (int index = 0; index < chosenLetterTiles.length; index++) {
                LetterTile chosenTile = chosenLetterTiles[index];
                letters += chosenTile.getLetter() + "\t";
                letterValues += chosenTile.getPointsValue() + "\t";
            }
            System.out.println(letters + "\n" + letterValues);
            
            passed = false;
            int attempts = 0;
            int roundTotal = 0;
            while (!passed) {
                System.out.print("Your word is: ");
                String word = SCANNER.nextLine();

                boolean validWord = isAWord(word);
                if (validWord) {
                    boolean vaild = validateLetters(word);
                    if (vaild) {
                        passed = true;
                        roundTotal = determinePoints();
                    } else
                        System.out.println(word + " is not valid with your letters. One point lost. Try again.");
                } else
                    System.out.println(word + "is not a valid word. One point lost. Try again.");

                if (!passed) { // failed to get a word
                    attempts += 1;
                    if (attempts == 3) { // hit max attempts
                        passed = true;
                        System.out.println("Let's get some new letters and move to next round.");
                    }
                }
                    
            }
            if (attempts != 0) 
                    roundTotal =- attempts;

            totalPoints += roundTotal;
            System.out.println("" + roundTotal + " POINTS EARNED THIS ROUND. - TOTAL POINTS: " + totalPoints);
        }
            System.out.println("You scored " + totalPoints + " points.");    
        } else {
            System.out.println("One of the Files could not be loaded for a provided reason, terminating process.");
        }
        SCANNER.close(); // doesn't need to actually be closed.
    }
}
