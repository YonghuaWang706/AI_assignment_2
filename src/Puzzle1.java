import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Puzzle1 implements GeneticBehavior{
    ArrayList<ChildPuzzle1> previousPopulation;//8?
    ArrayList<ChildPuzzle1> nextPopulation;//8?
    float previousMaxScore;
    Random random = new Random(System.currentTimeMillis());
    int populationSize;
    float[] fitnessTable;


    public Puzzle1(int populationSize, String path) throws IOException {
        this.previousPopulation = new ArrayList<>();
        this.nextPopulation = new ArrayList<>();
        this.populationSize = populationSize;
        fitnessTable = new float[populationSize];
        initialize(path);
        findMaxScore();
        printFitnessOfPopulation();
    }

    //initialize the population from inout, should only call once
    @Override
    public void initialize(String path) throws IOException {
        ArrayList<Float> initialSample = FileManipulation.readInputP1(path);
        for (int i = 0; i < populationSize; i++) {
            ArrayList<Float> curSample = new ArrayList<>(initialSample);
            Collections.shuffle(curSample,random);
            ChildPuzzle1 childPuzzle1 = new ChildPuzzle1(curSample);
            previousPopulation.add(childPuzzle1);
        }
    }

    //Sort the list based on fitness in decreasing order
    @Override
    public void findMaxScore(){
        previousPopulation.sort((o1, o2) -> Float.compare(o2.fitness, o1.fitness));
        this.previousMaxScore = previousPopulation.get(0).fitness;
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

    @Override
    public float getMaxFitness() {
        return previousMaxScore;
    }

    public float getNumberFromBins(int index) {
        return previousPopulation.get(0).getNumberGivenIndex(index);
    }

    public static void main(String[] args) throws IOException {
        Puzzle1 puzzle1 = new Puzzle1(6, "src/puzzle1.txt");
        puzzle1.findMaxScore();
        puzzle1.updateTable();
        for (float v : puzzle1.fitnessTable) {
            System.out.println(v);
        }


    }

    public void printBestChildBin(){
        System.out.println("Bins (one per line):");
        for (int i = 0; i < 40; i++) {
            System.out.print(getNumberFromBins(i) + " ");
            if (i == 9 || i == 19 || i == 29) {
                System.out.print("\n");
            }
        }
    }

    @Override
    public void elitism(int toBeSaved){
        for (int i = 0; i < toBeSaved; i++) {
            nextPopulation.add(previousPopulation.get(i));
        }
    }
    @Override
    public void culling(int toBeCulled){
        for (int i = 0; i < toBeCulled; i++) {
            previousPopulation.remove(previousPopulation.size() -1 );
        }
    }
    @Override
    public void switchPopulation(){
        previousPopulation.clear();
        previousPopulation.addAll(nextPopulation);
        nextPopulation.clear();
    }

    @Override
    public void produceTwoChildForNextGeneration() throws Exception {
        float first = random.nextFloat();
        int firstIndex = locateIndexGivenRandom(first);
        float second = random.nextFloat();
        int secondIndex = locateIndexGivenRandom(second);
        while(firstIndex == secondIndex){
            second = random.nextFloat();
            secondIndex = locateIndexGivenRandom(second);
        }
        CrossoverResult<ChildPuzzle1> result = previousPopulation.get(firstIndex).crossover(previousPopulation.get(secondIndex));
        nextPopulation.add(result.child1);
        nextPopulation.add(result.child2);
    }

    public int locateIndexGivenRandom(float rand) throws Exception {
        for (int i = 0; i < populationSize; i++) {
            if(rand <= fitnessTable[i]){
                return i;
            }
        }
        
        throw new Exception("end of execution of program due to one sample dominates the population already");
        //should never reach here
    }

    @Override
    public boolean nextPopulationIsFull(){
        return nextPopulation.size() >= populationSize;
    }

    @Override
    public void printFitnessOfPopulation(){
        System.out.println("this generation's fitness: ");
        for (ChildPuzzle1 childPuzzle1 : previousPopulation) {
            System.out.print(childPuzzle1.fitness + "\t");
        }
        System.out.println();
    }
}
