import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;


public class FileManipulation {

    public static ArrayList<Float> readInputP1(String inputPath) throws IOException {
        ArrayList<Float> numbers = new ArrayList<>();
        File file = new File(inputPath);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String str = "";
        while((str = bufferedReader.readLine()) != null){
            numbers.add(Float.parseFloat(str));
        }
        return numbers;
    }

    public static void generateRandomInputP1() throws IOException {
        DecimalFormat df = new DecimalFormat("0.0");
        File file = new File("src/puzzle1.txt");
        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 40; i++) {
            bufferedWriter.write(df.format(random.nextFloat() * 20 - 10) + "\n");
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public static ArrayList<Piece> readInputP2(String inputPath) throws IOException {
        ArrayList<Piece> pieces = new ArrayList<>();
        File file = new File(inputPath);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String str = "";
        while((str = bufferedReader.readLine()) != null){
            String[] attributes = str.split("\t");
            Piece cur = new Piece(attributes[0], getIntFromString(attributes[1]), getIntFromString(attributes[2]), getIntFromString(attributes[3]));
            pieces.add(cur);
        }
        return pieces;
    }

    public static int getIntFromString(String s){
        return Integer.parseInt(s.trim());
    }

}
