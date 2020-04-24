import java.util.Random;
import java.util.Scanner;

public class Mice_Killer_Robot {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int hitCapacityOfRobot = 4;

        for (int hitCounter = 1; hitCounter <= hitCapacityOfRobot; hitCounter++) {

            // 1.Navigation of the Killer Robot / killerRobotMovement()
            System.out.println("What is this in front of the Killer Robot ?");
            String objectInFrontOfRobot = scanner.nextLine();

            String robotsMovementAfterSpot = killerRobotMovement(objectInFrontOfRobot);

            System.out.printf("Movement of the Robot -> %s\n", robotsMovementAfterSpot);

            // 2.Is the mouse in front of the Killer Robot
            System.out.println("Number of the Pixels around the Killer Robot");

            for (int isTheMouseInFrontOfRobot = 0; isTheMouseInFrontOfRobot < 1; ) {

                int numberOfPixelsAround = Integer.parseInt(scanner.nextLine());

                if (numberOfPixelsAround % 2 == 0) {
                    isTheMouseInFrontOfRobot++;
                    System.out.println("Mouse is in front of the Killer Robot!");
                } else {
                    System.out.println("Number of the Pixels around the Killer Robot again");
                }
            }

            // 2.To what Killer Robot did damage ? / isDamageDoneToMouse()
            System.out.printf("%15s %d", "Hit №", hitCounter);
            boolean isDamageDoneToMouse = isDamageDoneToMouse();

            if (isDamageDoneToMouse) {
                System.out.println("\nDamage done to the mouse");
                System.out.println("Keep looking for more mice\n");
            } else {
                System.out.println("Damage done to the furniture");
                System.out.println("Keep looking for another mouse");
            }

            // 4. Communication with the owner / timeToCommunicateWithOwner()
            if (hitCounter % 2 != 0) {
                timeToCommunicateWithOwner();
            }
        }

        //3.Is the Killer Robot charging himself ? / isElectricityOn
        System.out.println("*Killer Robot needs to get recharged*");

        boolean isThereElectricity = isElectricityOn();

        if (isThereElectricity) {
            System.out.println("There is Electricity");
        } else {
            System.out.println("There is no Electricity");
        }
    }

    public static String killerRobotMovement(String objectInFrontOfRobot) {
        String robotsMovementAfterSpot = "";

        if (objectInFrontOfRobot.equalsIgnoreCase("Wall")) {
            robotsMovementAfterSpot = "Go Sideway";
        } else if (objectInFrontOfRobot.equalsIgnoreCase("Chair")) {
            robotsMovementAfterSpot = "Jump";
        } else if (objectInFrontOfRobot.equalsIgnoreCase("Nothing")) {
            robotsMovementAfterSpot = "Forward";
        } else {
            robotsMovementAfterSpot = "Somehow the object is passed";
        }
        return robotsMovementAfterSpot;
    }
    public static boolean isDamageDoneToMouse() {
        boolean isDamageDoneToMouse = true;

        Random numGeneratorForHits = new Random();
        int randomGeneratedNum = numGeneratorForHits.nextInt(10) + 1;

        if (randomGeneratedNum == 5) {
            isDamageDoneToMouse = false;
        }
        return isDamageDoneToMouse;
    }
    public static void timeToCommunicateWithOwner() {

        System.out.println("*Killer Robot goes to communicate with its owner*");

        for (int robotCountingNums = 10; robotCountingNums >= 1; robotCountingNums--) {
            if (robotCountingNums % 2 == 0) {
                System.out.printf("I am a Robottttt %s\n", robotCountingNums);
            }
        }
    }
    public static boolean isElectricityOn() {
        boolean isElectricityOn = false;

        for (int checker = 0; checker < 1; ) {

            Random numberGeneratorForCharging = new Random();
            int randomlyGeneratedFirstNum = numberGeneratorForCharging.nextInt(1000) + 1;
            int randomlyGeneratedSecondNum = numberGeneratorForCharging.nextInt(1000) + 1;

            if (randomlyGeneratedFirstNum > randomlyGeneratedSecondNum) {
                isElectricityOn = true;
                checker++;
            } else if (randomlyGeneratedSecondNum > randomlyGeneratedFirstNum) {
                isElectricityOn = false;
                checker++;
            }
        }
        return isElectricityOn;
    }
}