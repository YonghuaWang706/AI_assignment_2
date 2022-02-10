import java.util.ArrayList;

public class Tower implements ChildBehaviorI<Tower>{

    private ArrayList<Piece> tower;
    private int score = 0;

    public Tower(ArrayList<Piece> tower) {
        this.tower = tower;
    }

    // The score of a tower is (10 + height^2 – piece cost to build the tower).
    public int getFitness() {
        if (isTowerLegal(tower)) {
            return 0;
        }

        score = 10 + (tower.size() * tower.size());

        for (Piece piece : tower) {
            score -= piece.getCost();
        }

        return score;
    }

    public boolean isTowerLegal(ArrayList<Piece> tower) {
        Piece base = tower.get(0);
        Piece top = tower.get(tower.size() - 1);

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
        for (int i = 1; i < tower.size() - 2; i++){
            Piece current = tower.get(i);
            if (!current.getType().equals("Wall")) {
                return false;
            }
        }

        // A piece in a tower can, at most, be as wide as the piece below it.
        for (int i = 1; i < tower.size() - 1; i++){
            Piece current = tower.get(i);
            Piece previous = tower.get(i - 1);
            if (current.getWidth() > previous.getWidth()) {
                return false;
            }
        }

        // A piece in a tower can support its strength value in pieces placed *above* it
        int totalWeight = 0;
        for (int i = tower.size() - 1; i >= 1; i--) {
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

    }

    @Override
    public CrossoverResult<Tower> crossover(Tower other) {
        return null;
    }
}