import java.util.Objects;

// For Puzzle 2
public class Piece {
    private String type;
    private int width;
    private int strength;
    private int cost;

    public Piece(String type, int width, int strength, int cost) {
        this.type = type;
        this.width = width;
        this.strength = strength;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Piece{" +
                Objects.hash(type, width, strength, cost) +
                '}';
    }

    public Piece(Piece p){
        this.type = p.type;
        this.width = p.width;
        this.strength = p.strength;
        this.cost = p.cost;


    }

    public String getType() {
        return type;
    }

    public void setType(String newType) {
        this.type = newType;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int newWidth) {
        this.width = newWidth;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int newStrength) {
        this.strength = newStrength;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int newCost) {
        this.cost = newCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return getWidth() == piece.getWidth() && getStrength() == piece.getStrength() && getCost() == piece.getCost() && Objects.equals(getType(), piece.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getWidth(), getStrength(), getCost());
    }
}
