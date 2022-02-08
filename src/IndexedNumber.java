import java.util.Objects;

public class IndexedNumber {
    float value;
    int index;

    public IndexedNumber(float value, int index) {
        this.value = value;
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        IndexedNumber that = (IndexedNumber) o;
        return Float.compare(that.value, value) == 0 && index == that.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, index);
    }
}
