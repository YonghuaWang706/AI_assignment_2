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
}
