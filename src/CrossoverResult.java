import java.util.HashMap;
import java.util.HashSet;

public class CrossoverResult {
    public CrossoverResult(ChildPuzzle1 child1, ChildPuzzle1 child2) {
        this.child1 = child1;
        this.child2 = child2;
    }

    ChildPuzzle1 child1;
    ChildPuzzle1 child2;

    public void print(){
        System.out.println(child1.representation);
        System.out.println(child2.representation);
        HashSet<Float> set1 = new HashSet<>(child1.representation);
        HashSet<Float> set2 = new HashSet<>(child2.representation);
        System.out.println("set1's size "+ set1.size());
        System.out.println("set2's size "+ set2.size());

    }


}
