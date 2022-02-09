import java.io.IOException;

public class GeneticAlgo {
    Puzzle1 instance;
    float maxFitnessBefore = 0;
    float maxFitnessAfter = 0;
    int totalRounds = 0;
    int currentRound = 0;
    long secondsToRun = 0;
    long startTime = 0;

    public GeneticAlgo(Puzzle1 instance, int totalRounds, long secondsToRun) {
        this.instance = instance;
        maxFitnessBefore = instance.previousMaxScore;
        this.totalRounds = totalRounds;
        this.secondsToRun = secondsToRun;
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
            maxFitnessAfter = instance.previousMaxScore;
            instance.printFitnessOfPopulation();
        }
    }

    public static void main(String[] args) throws Exception {
        Puzzle1 puzzle1 = new Puzzle1(12);
        GeneticAlgo geneticAlgo = new GeneticAlgo(puzzle1, 5, 10);
        geneticAlgo.runGeneticAlgo();
        geneticAlgo.printResult();
    }

    public Boolean finished(){
        long currentTime = System.currentTimeMillis()/1000;
        long haveRan = currentTime - startTime;
        return currentRound == totalRounds || maxFitnessBefore == maxFitnessAfter || haveRan >= secondsToRun;
    }

    public void printResult(){
        System.out.println("the original score is " + maxFitnessBefore);
        System.out.println("after algorithm is " + maxFitnessAfter);
    }
}
