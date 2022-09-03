package moderJavaInaction.ch2;


public class Apple {

    private int weight = 0;
    private Ch2Main.Color color;

    public Apple(int weight, Ch2Main.Color color) {
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Ch2Main.Color getColor() {
        return color;
    }

    public void setColor(Ch2Main.Color color) {
        this.color = color;
    }

    @SuppressWarnings("boxing")
    @Override
    public String toString() {
        return String.format("Apple{color=%s, weight=%d}", color, weight);
    }

}