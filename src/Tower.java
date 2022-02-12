import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Tower implements ChildBehaviorI<Tower> {

    private ArrayList<Piece> tower;
    private int score = 0;

    public Tower(ArrayList<Piece> tower) {
        this.tower = tower;
    }

    public ArrayList<Piece> getTower() {
        return tower;
    }

    public void setTower(ArrayList<Piece> tower) {
        this.tower = tower;
    }

    // Get the height (size) of the tower
    public int getTowerHeight() {
        return tower.size();
    }

    // The score of a tower is (10 + height^2 – piece cost to build the tower).
    public int getFitness() {
        if (!isTowerLegal()) {
            return 0;
        }

        score = 10 + (getTowerHeight() * getTowerHeight());

        for (Piece piece : tower) {
            score -= piece.getCost();
        }

        return score;
    }

    public boolean isTowerLegal() {
        Piece base = tower.get(0);
        Piece top = tower.get(getTowerHeight() - 1);

        // Towers must have a door as the bottom-most piece
        if (!base.getType().equals("Door")) {
            return false;
        }

        // Towers *must* have a lookout as the top-most piece
        if (!top.getType().equals("Lookout")) {
            return false;
        }

        // Pieces between the top and bottom of a tower (if any) must be wall segments
        // (towers can only have one door and one lookout)
        for (int i = 1; i < getTowerHeight() - 2; i++) {
            Piece current = tower.get(i);
            if (!current.getType().equals("Wall")) {
                return false;
            }
        }

        // A piece in a tower can, at most, be as wide as the piece below it.
        for (int i = 1; i < getTowerHeight() - 1; i++) {
            Piece current = tower.get(i);
            Piece previous = tower.get(i - 1);
            if (current.getWidth() > previous.getWidth()) {
                return false;
            }
        }

        // A piece in a tower can support its strength value in pieces placed *above* it
        int totalWeight = 0;
        for (int i = getTowerHeight() - 1; i >= 1; i--) {
            Piece previous = tower.get(i - 1);
            totalWeight += 1;
            if (previous.getStrength() < totalWeight) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void mutate() {
        if (getTowerHeight() < 4) {
            return;
        }
        // Randomly swap any two pieces of the tower other than the top and bottom
        Random random = new Random();
        float probability = random.nextFloat();
        if (probability <= 0.2){
            int minIndex = 1; // exclude bottom
            int maxIndex = getTowerHeight() - 2; // exclude top
            int firstIndex = minIndex + random.nextInt((maxIndex - minIndex)) + 1;
            int secondIndex =minIndex + random.nextInt((maxIndex - minIndex)) + 1;
            while(secondIndex == firstIndex){
                secondIndex =minIndex + random.nextInt((maxIndex - minIndex)) + 1;
            }
            ArrayList<Piece> testSamplePieces = this.getTower();
            Piece tempPiece = new Piece(testSamplePieces.get(firstIndex));
            testSamplePieces.set(firstIndex,testSamplePieces.get(secondIndex));
            testSamplePieces.set(secondIndex, tempPiece);
            Tower tempTower = new Tower(testSamplePieces);
            if(tempTower.isTowerLegal()){
                this.setTower(testSamplePieces);
            }
        }
    }

    @Override
    public CrossoverResult<Tower> crossover(Tower other) {
        int splitPoint1 = ThreadLocalRandom.current().nextInt(1, this.getTowerHeight());
        int splitPoint2 = ThreadLocalRandom.current().nextInt(1, this.getTowerHeight());

        LinkedHashSet<Piece> out1 = new LinkedHashSet<>();
        LinkedHashSet<Piece> out2 = new LinkedHashSet<>();
        for (int i = 0; i < splitPoint1; i++) {
            Piece cur1 = other.tower.get(i);
            out1.add(cur1);
        }
        for (int i = 0; i < splitPoint2; i++) {
            Piece cur2 = this.tower.get(i);
            out2.add(cur2);
        }

        for (int i = splitPoint1; i < this.getTowerHeight(); i++) {
            out1.add(this.tower.get(i));
        }

        for (int i = splitPoint2; i < other.getTowerHeight(); i++) {
            out2.add(this.tower.get(i));
        }

        Tower t1 = new Tower(new ArrayList<>(out1));
        Tower t2 = new Tower(new ArrayList<>(out2));
        t1.mutate();
        t2.mutate();
        return new CrossoverResult<>(t1, t2);
    }

    @Override
    public String toString() {
        for (int i = 0; i < tower.size(); i++) {
            System.out.print(tower.get(i) + "\t");
        }
        System.out.println();
        return "Tower{" +
                "score=" + score +
                '}';
    }
}