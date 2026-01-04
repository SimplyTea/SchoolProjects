public class RandTest {
    static public void main(String[] args) {
        char grade = "a";

        switch(grade) {
            case "A":
            case "a":
                System.out.println("High Grade!");
                break;
            case "B":
            case "b":
                System.out.println("Good Grade!");
                break;
            case "C":
            case "c":
                System.out.println("Passing Grade!");
                break;
            case "f":
            case "F":
            case "D":
            case "d":
                System.out.println("Failing Grade!");
                break;
            default:
                System.out.println("Invalid Grade!");
        }
        
    }
}