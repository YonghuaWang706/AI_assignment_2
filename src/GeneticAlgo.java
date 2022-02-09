import java.io.IOException;

public class GeneticAlgo {
    Puzzle1 instance;
    float maxFitnessBefore = 0;
    float maxFitnessAfter = 0;
    int totalRounds = 0;
    int currentRound = 0;

    public GeneticAlgo(Puzzle1 instance, int totalRounds) {
        this.instance = instance;
        maxFitnessBefore = instance.previousMaxScore;
        this.totalRounds = totalRounds;
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
        while(!finished()){
            currentRound++;
            System.out.println("iteration " +currentRound);
            instance.elitism(2);
            instance.culling(2);
            instance.updateTable();
            System.out.println("after culling");
            while(!instance.nextPopulationIsFull()){
                instance.produceTwoChildForNextGeneration();
            }
            System.out.println("produce next generation");
            instance.switchPopulation();
            maxFitnessAfter = instance.findMaxScore();
            instance.printFitnessOfPopulation();
        }
    }

    public static void main(String[] args) throws Exception {
        Puzzle1 puzzle1 = new Puzzle1(6);
        GeneticAlgo geneticAlgo = new GeneticAlgo(puzzle1, 2);
        geneticAlgo.runGeneticAlgo();
        geneticAlgo.printResult();
    }

    public Boolean finished(){
        return currentRound == totalRounds || maxFitnessBefore == maxFitnessAfter;
    }

    public void printResult(){
        System.out.println("the original score is " + maxFitnessBefore);
        System.out.println("after algorithm is " + maxFitnessAfter);
    }
}
