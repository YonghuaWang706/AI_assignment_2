import java.io.IOException;
import java.util.Scanner;

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
        Scanner scan = new Scanner(System.in);

        System.out.print("Please enter the puzzle number (1 or 2): ");
        int puzzleNumb = scan.nextInt();
        if (puzzleNumb < 1 || puzzleNumb > 2) {
            System.out.println("Puzzle number must be 1 or 2! Exiting...");
            System.exit(1);
        }

        System.out.print("What file contains the puzzle information? ");
        String puzzleFile = scan.next();

        System.out.print("How long should I work on this for (in seconds)? ");
        long duration = scan.nextLong();
        while (duration < 0L) {
            System.out.print("Puzzle duration must be a positive number! Try again: ");
            duration = scan.nextLong();
        }
        System.out.print("How large of a population (Make it an even number)? ");
        int population = scan.nextInt();

        if (puzzleNumb == 1) {
            GeneticBehavior puzzle = new Puzzle1(population, puzzleFile);
            GeneticAlgo geneticAlgo = new GeneticAlgo(puzzle, 1000, duration);
            geneticAlgo.runGeneticAlgo();
            geneticAlgo.printResult();
        }
        if (puzzleNumb == 2) {
            GeneticBehavior puzzle = new Puzzle2(population, puzzleFile);
            GeneticAlgo geneticAlgo = new GeneticAlgo(puzzle, 1000, duration);
            geneticAlgo.runGeneticAlgo();
            geneticAlgo.printResult();
        }
        scan.close();

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
