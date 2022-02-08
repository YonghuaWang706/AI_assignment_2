import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class FileManipulation {

    public static ArrayList<Float> originalList;

    static{
        try {
            originalList = readInputP1();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Float> readInputP1() throws IOException {
        ArrayList<Float> numbers = new ArrayList<>();
        File file = new File("src/puzzle1.txt");
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
            bufferedWriter.write(String.valueOf(df.format(random.nextFloat() * 20 -10)) + "\n");
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }


    public static void main(String[] args) throws IOException {

        ArrayList<Float> floats;
        Set<Float> set;
        do {
            generateRandomInputP1();
            floats = readInputP1();
            set = new HashSet<>(floats);
            System.out.println(floats.size());
            System.out.println(set.size());
        } while (floats.size() != set.size());
    }
}
