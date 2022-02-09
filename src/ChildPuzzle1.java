import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ChildPuzzle1 {
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
    public ChildPuzzle1 mutate() {
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
        return this;
    }
}
