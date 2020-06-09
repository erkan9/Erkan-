import java.util.ArrayList;
import java.util.Scanner;


/**
 * @author Erkan Kamber
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Person> people = new ArrayList<>();
        System.out.println("Enter the number of workers:");
        int countOfPeople = scanner.nextInt();

        for (int i = 0; i < countOfPeople; i++) {

            System.out.println("Enter the power of the Wrapper Engine: ");
            int wrapperEnginePower = scanner.nextInt();

            System.out.println("Enter the power of the Production Engine: ");
            int productionEnginePower = scanner.nextInt();

            System.out.println("Enter the power of the Heat Engine: ");
            int heatEnginePower = scanner.nextInt();

            WrapperEngine wrapperEngine = new WrapperEngine(wrapperEnginePower);
            ProductionEngine productionEngine = new ProductionEngine(productionEnginePower);
            com.src.HeatEngine heatEngine = new com.src.HeatEngine(heatEnginePower);

            double averagePowerOfEngine = (wrapperEngine.getPower()
                    + productionEngine.getPower() + heatEngine.getPower()) / 3.0;

            Engine engine = new Engine(averagePowerOfEngine);
            Machine machine = new Machine(engine);

            scanner.nextLine();
            System.out.print("Enter the name of the worker: ");
            String name = scanner.nextLine();
            Person person = new Person(name,machine);

            HeatManager heatManager = new HeatManager();
            heatManager.getPower().add(heatEnginePower);
            heatManager.getPower().add(productionEnginePower);
            heatManager.getPower().add(wrapperEnginePower);
            person.setHeatManager(heatManager);

            people.add(person);
        }
        people.forEach(p ->{
            System.out.printf("Average power of " +  p.getName() + "s' machines is: %.2f " +
                            "| HeatEngine: %d | ProductionEngine: %d | WrapperEngine: %d |\n"
                    , p.getMachine().getEngine().getPower()
                    , p.getHeatManager().getPower().get(0)
                    , p.getHeatManager().getPower().get(1)
                    , p.getHeatManager().getPower().get(2));
        });

        System.out.println("\n-----Както казваше Божинката----- ");
        System.out.println("Отивам да тръгна, като се върна, ще дойда");

        System.out.println("Error 404, Brain stopped working :)");
    }
}