
public class Engine {

    private Double power;
    private HeatManager heatManager;

    public Engine(Double power) {
        this.power = power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    public Double getPower() {
        return power;
    }

    public HeatManager getHeatManager() {
        return heatManager;
    }

}