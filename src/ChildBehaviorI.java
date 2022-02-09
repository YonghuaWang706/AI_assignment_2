public interface ChildBehaviorI<T> {
    //randomly swap two pieces in the representation
    void mutate();
    //this piece crossover piece a, result saved in crossoverResult
    CrossoverResult<T> crossover(T a);

}
