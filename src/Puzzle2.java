import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Puzzle2 implements GeneticBehavior{
    ArrayList<Tower> previousPopulation;//8?
    ArrayList<Tower> nextPopulation;//8?
    float previousMaxScore;
    Random random = new Random(System.currentTimeMillis());
    int populationSize;
    float[] fitnessTable;


    public Puzzle2(int populationSize) throws IOException {
        this.previousPopulation = new ArrayList<>();
        this.nextPopulation = new ArrayList<>();
        this.populationSize = populationSize;
        fitnessTable = new float[populationSize];
        initialize();
        findMaxScore();
        printFitnessOfPopulation();
    }

    //initialize the population from inout, should only call once
    @Override
    public void initialize() throws IOException {
        ArrayList<Piece> initialSample = FileManipulation.readInputP2("src/puzzle2.txt");
        for (int i = 0; i < populationSize; i++) {
            ArrayList<Piece> curSample = new ArrayList<>(initialSample);
            Collections.shuffle(curSample,random);
//            Tower tower = new Tower(curSample);
//            previousPopulation.add(tower);
        }
    }

    //Sort the list based on fitness in decreasing order
    @Override
    public void  findMaxScore(){
        previousPopulation.sort((o1, o2) -> Float.compare(o2.getFitness(), o1.getFitness()));
        float ret = previousPopulation.get(0).getFitness();
        this.previousMaxScore = ret;
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
        CrossoverResult<Tower> result = previousPopulation.get(firstIndex).crossover(previousPopulation.get(secondIndex));
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
        return nextPopulation.size() == populationSize;
    }

    @Override
    public void printFitnessOfPopulation(){
        System.out.println("this generation's fitness: ");
        for (Tower tower : previousPopulation) {
            System.out.print(tower.getFitness() + "\t");
        }
        System.out.println();
    }
}
