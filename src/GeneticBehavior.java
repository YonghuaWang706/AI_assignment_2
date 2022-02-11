import java.io.IOException;

public interface GeneticBehavior {
    // population the population from the initial input, all sample should be legal and valid
    void initialize()  throws IOException;
    //sort the population based on fitness score
    void findMaxScore();
    //save the top <toBeSaved> sample to next generation
    void elitism(int toBeSaved);
    //remove sample with lowest fitness score
    void culling(int toBeCulled);
    //make next becomes previous
    void switchPopulation();
    //generate children for next population
    void produceTwoChildForNextGeneration() throws Exception;
    //check if next population is full
    boolean nextPopulationIsFull();
    //print fitness of all children for debugging
    void printFitnessOfPopulation();

    void updateTable();

    float getMaxFitness();
}
