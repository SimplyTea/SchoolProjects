package project;

/*
* Created on: Dec 3, 2024
*
* ULID: jrcarwa
* Class: IT 168
*/

/**
* LetterTile class to store letters and their point values.
*
* @author John Carway
*
*/

public class LetterTile {
    
    private char letter;
    private int pointsValue;
    private boolean letterUltizied;

    public LetterTile(char letter, int points) {
        this.letter = letter;
        this.pointsValue = points;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public int getPointsValue() {
        return pointsValue;
    }

    public void setPointsValue(int pointsValue) {
        this.pointsValue = pointsValue;
    }

    public boolean isLetterUltizied() {
        return letterUltizied;
    }

    public void setLetterUltizied(boolean letterUltizied) {
        this.letterUltizied = letterUltizied;
    }

    public String toString() {
        return "Letter: " + letter + "\nPoints: " + pointsValue;
    }

}
