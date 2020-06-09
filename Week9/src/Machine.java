
public class Machine {
    private Double height;
    private String colour;
    private Engine engine;
    private double power;
    public Machine(Engine engine) {
        this.engine = engine;
    }

    public Engine getEngine() {
        return engine;
    }

    public String getColour() {
        return colour;
    }

    public Double getHeight() {
        return height;
    }
}
