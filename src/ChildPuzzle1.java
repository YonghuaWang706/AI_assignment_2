import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ChildPuzzle1 implements ChildBehaviorI<ChildPuzzle1>{
    ArrayList<Float> representation;
    HashMap<Float, Integer> indexMap = new HashMap<>();
    float fitness;
    public ChildPuzzle1(ArrayList<Float> representation) {
        this.representation = representation;
        int size = representation.size();
        for (int i = 0; i < size; i++) {
            indexMap.put(representation.get(i), i);
        }
        this.fitness = getFitness();
    }

    private float getFitness(){
        float product = 1.0f, sum = 0, difference = 0;
        for (int i = 0; i < 10; i++) {
            product *= representation.get(i);
        }
        for (int i = 10; i < 20; i++) {
            product += representation.get(i);
        }
        float max = 0, min = 0;
        for (int i = 20; i < 30; i++) {
            float cur = representation.get(i);
            if(cur > max){
                max = cur;
                continue;
            }
            if(cur < min){
                min = cur;
            }
        }
        difference = max - min;
        return product + sum + difference;
    }

    public int getIndexOfNumber(float num){
        return indexMap.get(num);
    }

    public float getNumberGivenIndex(int index){
        return representation.get(index);
    }


    //mutation, update hashmap as well
    @Override
    public void mutate() {
        Random random = new Random(System.currentTimeMillis());
        float probability = random.nextFloat();

        int p1 = 0;
        int p2 = 0;
        if (probability < 0.05) {
            p1 = random.nextInt(40);
            p2 = random.nextInt(40);
            while (p2 == p1) {
                p2 = random.nextInt(40);
            }
        }
        float temp = representation.get(p1);
        indexMap.put(representation.get(p2), p1);
        indexMap.put(temp, p2);
        representation.set(p1, representation.get(p2));
        representation.set(p2, temp);
    }

    @Override
    public CrossoverResult<ChildPuzzle1> crossover(ChildPuzzle1 other){
        Random random = new Random(System.currentTimeMillis());
        int splitPoint = random.nextInt(38) + 1; //at more left 1, right most 38
        ArrayList<Integer> duplicateIndex1 = new ArrayList<>();
        ArrayList<Integer> duplicateIndex2 = new ArrayList<>();//index to be refilled with missing number
        ArrayList<Float> duplicateValue1 = new ArrayList<>();//values to be added back
        ArrayList<Float> duplicateValue2 = new ArrayList<>();

        ArrayList<Float> out1 = new ArrayList<>();
        ArrayList<Float> out2 = new ArrayList<>();
        for (int i = 0; i < splitPoint; i++) {
            float cur1 = other.getNumberGivenIndex(i);
            float cur2 = this.getNumberGivenIndex(i);
            out1.add(cur1);
            out2.add(cur2);
            //check duplicate -> if the exchanged number appear after split point
            int originalPosition1 = this.getIndexOfNumber(cur1);
            int originalPosition2 = other.getIndexOfNumber(cur2);
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
            out1.add(this.getNumberGivenIndex(i));
            out2.add(other.getNumberGivenIndex(i));
        }

        int duplicateNum = duplicateIndex1.size();
        for (int i = 0; i < duplicateNum; i++) {
            out1.set(duplicateIndex1.get(i), duplicateValue1.get(i));
            out2.set(duplicateIndex2.get(i), duplicateValue2.get(i));
        }

        ChildPuzzle1 c1 = new ChildPuzzle1(out1);
        ChildPuzzle1 c2 = new ChildPuzzle1(out2);
        c1.mutate();
        c2.mutate();
        return new CrossoverResult<>(c1, c2);
    }
}
