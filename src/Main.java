import java.util.Scanner;
//import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Please enter the puzzle number (1 or 2): ");
        int puzzle = scan.nextInt();
        if (puzzle < 1 || puzzle > 2) {
            System.out.println("Puzzle number must be 1 or 2! Exiting...");
            System.exit(1);
        }

        System.out.print("What file contains the puzzle information? ");
        String puzzleFile = scan.next();

        System.out.print("How long should I work on this for (in seconds)? ");
        long duration = scan.nextLong();
        while (duration < 0L) {
            System.out.print("Puzzle duration must be a positive number! Try again: ");
            duration = scan.nextLong();
        }
        System.out.print("How large of a population (Make it an even number)? ");
        int population = scan.nextInt();

        if (puzzle == 1) {

        }
        // if puzzle is 1 run Puzzle1(puzzleFile, duration)
        // if puzzle is 2 run Puzzle2(puzzleFile, duration)

        scan.close();
    }
}
