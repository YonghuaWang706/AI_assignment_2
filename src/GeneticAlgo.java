import java.io.IOException;

public class GeneticAlgo {
    GeneticBehavior instance;
    float maxFitnessBefore = 0;
    float maxFitnessAfter = 0;
    int totalRounds = 0;
    int currentRound = 0;
    long secondsToRun = 0;
    long startTime = 0;

    public GeneticAlgo(GeneticBehavior instance, int totalRounds, long secondsToRun) {
        this.instance = instance;
        this.totalRounds = totalRounds;
        this.secondsToRun = secondsToRun;
        maxFitnessBefore = instance.getMaxFitness();
    }

    /*
    while(not done):
        instance.elitism
        instance.culling
        while(population not full):
            selection
            crossover
            mutation
            add to population

     */
    public void runGeneticAlgo() throws Exception {
        startTime = System.currentTimeMillis()/ 1000;
        while(!finished()){
            currentRound++;
            System.out.println("iteration " +currentRound);
            instance.elitism(0);
            instance.culling(0);
            instance.updateTable();
            System.out.println("after culling");
            while(!instance.nextPopulationIsFull()){
                instance.produceTwoChildForNextGeneration();
            }
            System.out.println("produce next generation");
            instance.switchPopulation();
            maxFitnessAfter = instance.getMaxFitness();
            instance.findMaxScore();
            instance.printFitnessOfPopulation();
        }
    }

    public static void main(String[] args) throws Exception {
        GeneticBehavior puzzle = new Puzzle2(2);
        GeneticAlgo geneticAlgo = new GeneticAlgo(puzzle, 10, 10);
        geneticAlgo.runGeneticAlgo();
        geneticAlgo.printResult();
    }

    public Boolean finished(){
        long currentTime = System.currentTimeMillis()/1000;
        long haveRan = currentTime - startTime;
        return haveRan >= secondsToRun || currentRound == totalRounds;
    }

    public void printResult(){
        System.out.println("the original score is " + maxFitnessBefore);
        System.out.println("after algorithm is " + maxFitnessAfter);
    }
}
