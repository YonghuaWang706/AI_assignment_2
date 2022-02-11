import java.io.IOException;
import java.util.ArrayList;

public class Test {
    public static void main(String args[]) throws IOException {
        // Tower mutation test
        // Get pieces
        ArrayList<Piece> pieces1 = FileManipulation.readInputP2("src/puzzle2.txt");

        Tower tower1 = new Tower(pieces1);
        System.out.println(tower1);
        tower1.mutate();
        System.out.println(tower1);
        // If mutation fails, return same tower

    }
}