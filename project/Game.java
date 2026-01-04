package project;

/*
* Created on: Dec 3, 2024
*
* ULID: jrcarwa
* Class: IT 168
*/

/**
* Game Helper Class to help with the driver class.
*
* @author John Carway
*/

import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;

public class Game { // We were not told to store anything in this class, so I don't know why it even exists but okay.

    public LetterTile[] readLetterFile(String fileName) {
        final int LETTER_COUNT = 48; // instructions say 98 and 96 but the provided list only has 48 values meaning it will error.
        // was not told to increase letter count with amount in the file, as instructions stated a clear value to use.
        LetterTile[] letterTile = new LetterTile[LETTER_COUNT];

        try {
            final Scanner SCANNED_FILE = new Scanner(new File(fileName));
            SCANNED_FILE.useDelimiter(",");
            for (int index = 0; index < (LETTER_COUNT); index++) {
                if(SCANNED_FILE.hasNext() && SCANNED_FILE.hasNextInt()) {
                    letterTile[index] = new LetterTile(SCANNED_FILE.next().charAt(0), SCANNED_FILE.nextInt());
                }
            }
            SCANNED_FILE.close();
        } catch(FileNotFoundException e) {
            System.out.println("File needs to exist boi.");
        }

        return letterTile;
    }

    public LetterTile[] generateLetters(LetterTile[] letterTiles) {
        final int GENERATED_LETTER_COUNT = 7;
        LetterTile[] generatedLetters = new LetterTile[GENERATED_LETTER_COUNT];
        final Random RANDOM = new Random();

        for (int index = 0; index < generatedLetters.length; index++) {
            LetterTile chosen = letterTiles[RANDOM.nextInt(letterTiles.length)];
            generatedLetters[index] = new LetterTile(chosen.getLetter(), chosen.getPointsValue());
        }

        return generatedLetters;
    }

}