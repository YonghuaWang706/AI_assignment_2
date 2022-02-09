import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Puzzle1 {
    ArrayList<ChildPuzzle1> previousPopulation;//8?
    ArrayList<ChildPuzzle1> nextPopulation;//8?
    float previousMaxScore;
    Random random = new Random(System.currentTimeMillis());
    int populationSize;
    float[] fitnessTable;


    public Puzzle1(int populationSize) throws IOException {
        this.previousPopulation = new ArrayList<>();
        this.nextPopulation = new ArrayList<>();
        this.populationSize = populationSize;
        fitnessTable = new float[populationSize];
        initialize();
        findMaxScore();
        System.out.println("the original fitness of population");
        System.out.println(previousMaxScore);
    }

    //initialize the population from inout, should only call once
    public void initialize() throws IOException {
        ArrayList<Float> initialSample = FileManipulation.readInputP1();
        for (int i = 0; i < populationSize; i++) {
            ArrayList<Float> curSample = new ArrayList<>(initialSample);
            Collections.shuffle(curSample,random);
            ChildPuzzle1 childPuzzle1 = new ChildPuzzle1(curSample);
            previousPopulation.add(childPuzzle1);
        }
    }

    //Sort the list based on fitness in decreasing order
    public float  findMaxScore(){
        previousPopulation.sort((o1, o2) -> Float.compare(o2.fitness, o1.fitness));
        float ret = previousPopulation.get(0).fitness;
        this.previousMaxScore = ret;
        return ret;
    }

    // calculate the probability to be selected. The last element has
    // the least value and is used of offset, so ignored for selection
    public void updateTable(){
        float sum = 0;
        int size = previousPopulation.size();
        float min = previousPopulation.get(size -1).fitness;
        for (int i = 0; i < size; i++) {
            float cur = previousPopulation.get(i).fitness;
            fitnessTable[i] = (float) Math.sqrt(cur - min);
            sum += fitnessTable[i];
        }
        fitnessTable[0] = fitnessTable[0] / sum;
        for (int i = 1; i < size; i++) {
            fitnessTable[i] = ((fitnessTable[i] / sum) + fitnessTable[i - 1]);
        }
    }

    public static void main(String[] args) throws IOException {
        Puzzle1 puzzle1 = new Puzzle1(6);
        puzzle1.findMaxScore();
        puzzle1.updateTable();

    }

    public void elitism(int toBeSaved){
        for (int i = 0; i < toBeSaved; i++) {
            nextPopulation.add(previousPopulation.get(i));
        }
    }

    public void culling(int toBeCulled){
        for (int i = 0; i < toBeCulled; i++) {
            previousPopulation.remove(previousPopulation.size() -1 );
        }
    }

    public void switchPopulation(){
        previousPopulation.clear();
        previousPopulation.addAll(nextPopulation);
        nextPopulation.clear();
    }

    public void produceTwoChildForNextGeneration() throws Exception {
        float first = random.nextFloat();
        int firstIndex = locateIndexGivenRandom(first);
        float second = random.nextFloat();
        int secondIndex = locateIndexGivenRandom(second);
        while(firstIndex == secondIndex){
            second = random.nextFloat();
            secondIndex = locateIndexGivenRandom(second);
        }
        CrossoverResult result = GeneticAlgoHelper.crossover(previousPopulation.get(firstIndex), previousPopulation.get(secondIndex));
        nextPopulation.add(result.child1);
        nextPopulation.add(result.child2);
    }

    public int locateIndexGivenRandom(float rand) throws Exception {
        for (int i = 0; i < populationSize; i++) {
            if(rand <= fitnessTable[i]){
                return i;
            }
        }
        
        throw new Exception("cannot find index of child");
        //should never reach here
    }
    
    public boolean nextPopulationIsFull(){
        return nextPopulation.size() == populationSize;
    }

    public void printFitnessOfPopulation(){
        System.out.println("this generation's fitness: ");
        for (ChildPuzzle1 childPuzzle1 : previousPopulation) {
            System.out.print(childPuzzle1.fitness + "\t");
        }
        System.out.println();
    }
}
