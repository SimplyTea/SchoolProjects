import java.text.DecimalFormat;

public class DecimalTest {
    static public void main(String[] args) {
        DecimalFormat formatter;
        //formatter = new DecimalFormat("0.00"); // requires one whole number and forces or rounds a double stack decimal number
        // Construstor takes in a string with patterns '0' // '#' // '.' // ',' // '%'
        /*
            '0' is a required digit, included if 0.
            '#' is an optional digit, supresses automatically if 0.
            '%' times the number by 100 or divides by 100 and sets it to be a percent format.

        */
       double numberTest = 34.7;
       System.out.println(formatter.format(numberTest) + 5); // 34.705 (formatter turns the numberTest to a string and order of types converts 5 to a string.)
    }
}