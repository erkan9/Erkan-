
public class Person {

    private Machine machine;
    private String name;
    private HeatManager heatManager;

    public void setHeatManager(HeatManager heatManager) {
        this.heatManager = heatManager;
    }

    public HeatManager getHeatManager() {
        return heatManager;
    }

    public Person(String name, Machine machine){
        this.name = name;
        this.machine = machine;
    }

    public Machine getMachine() {
        return machine;
    }

    public String getName() {
        return name;
    }
}