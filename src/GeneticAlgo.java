import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GeneticAlgo {
    private static Random random = new Random(System.currentTimeMillis());
    public static CrossoverResult crossover(ChildPuzzle1 c1, ChildPuzzle1 c2){
        int splitPoint = random.nextInt(38) + 1; //at more left 1, right most 38
        System.out.println("split point is " + splitPoint);
        ArrayList<Integer> duplicateIndex1 = new ArrayList<>();
        ArrayList<Integer> duplicateIndex2 = new ArrayList<>();//index to be refilled with missing number
        ArrayList<Float> duplicateValue1 = new ArrayList<>();//values to be added back
        ArrayList<Float> duplicateValue2 = new ArrayList<>();

        ArrayList<Float> out1 = new ArrayList<>();
        ArrayList<Float> out2 = new ArrayList<>();
        for (int i = 0; i < splitPoint; i++) {
            float cur1 = c2.getNumberGivenIndex(i);
            float cur2 = c1.getNumberGivenIndex(i);
            out1.add(cur1);
            out2.add(cur2);
            //check duplicate -> if the exchanged number appear after split point
            int originalPosition1 = c1.getIndexOfNumber(cur1);
            int originalPosition2 = c2.getIndexOfNumber(cur2);
            if(originalPosition1 >= splitPoint){//same number appears at right side of split point, duplicate!
                duplicateIndex1.add(originalPosition1);// the index of the original number becomes a new slot to be filled in(the number already appear on the left side
                duplicateValue2.add(cur1);// the other array now lacks that value due to crossover
            }
            if(originalPosition2 >= splitPoint){//same number appears at right side of split point, duplicate!
                duplicateIndex2.add(originalPosition2);// the index of the original number becomes a new slot to be filled in(the number already appear on the left side
                duplicateValue1.add(cur2);// the other array now lacks that value due to crossover
            }
        }
        for (int i = splitPoint; i < 40; i++) {
            out1.add(c1.getNumberGivenIndex(i));
            out2.add(c2.getNumberGivenIndex(i));
        }

        int duplicateNum = duplicateIndex1.size();
        for (int i = 0; i < duplicateNum; i++) {
            out1.set(duplicateIndex1.get(i), duplicateValue1.get(i));
            out2.set(duplicateIndex2.get(i), duplicateValue2.get(i));
        }

        return new CrossoverResult(new ChildPuzzle1(out1), new ChildPuzzle1(out2));
    }




    public static void main(String[] args) throws IOException {
        ArrayList<Float> a1 = new ArrayList<>(FileManipulation.originalList);
        ArrayList<Float> a2 = new ArrayList<>(FileManipulation.originalList);
        Collections.shuffle(a1);
        Collections.shuffle(a2);
        System.out.println(a1);
        System.out.println(a2);
        ChildPuzzle1 c1 = new ChildPuzzle1(a1);
        ChildPuzzle1 c2 = new ChildPuzzle1(a2);

        CrossoverResult result = crossover(c1, c2);
        result.print();
    }

}
