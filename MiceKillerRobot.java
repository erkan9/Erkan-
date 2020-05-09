import java.util.Random;
import java.util.Scanner;

/***
 *
 * @author Erkan Kamber
 */

public class MiceKillerRobot {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final byte HIT_CAPACITY_OF_ROBOT = 4;
        byte robotsFullBatteryInPercent = 100;

        String turnOffRobot = "No";

        while (!turnOffRobot.equalsIgnoreCase("Yes")) {

            for (byte hitCounter = 1; hitCounter <= HIT_CAPACITY_OF_ROBOT; hitCounter++) {
                robotsFullBatteryInPercent -= 25;

                // Navigation of the Killer Robot

                String robotsMovementAfterSpot = killerRobotMovement();
                System.out.printf("Movement of the Robot -> %s\n", robotsMovementAfterSpot);

                // Is the mouse in front of the Killer Robot ?
                isTheMouseInFront();

                // To what Killer Robot did damage ?
                System.out.printf("%15s %d", "Hit №", hitCounter);
                isDamageDoneToMouse();

                // Communication with the owner
                timeToCommunicateWithOwner();

                System.out.printf("Battery Left: %d%%\n", robotsFullBatteryInPercent);

                if (robotsFullBatteryInPercent == 0) {
                    // Is the Killer Robot charging himself ?
                    isTimeToRecharge();

                    //Turn off the Robot ?
                    turnOffRobot = isTimeToTurnOffRobot();
                }
            }
        }
        System.out.println("The Robot is turned off");
    }


    public static String killerRobotMovement() {
        Scanner objectInFront = new Scanner(System.in);

        System.out.println("What is this in front of the Killer Robot ?");

        String objectInFrontOfRobot = objectInFront.nextLine();
        String robotsMovementAfterSpot = "Somehow the object is passed";

        if (objectInFrontOfRobot.equalsIgnoreCase("Wall")) {
            robotsMovementAfterSpot = "Go Sideways";
        } else if (objectInFrontOfRobot.equalsIgnoreCase("Chair")) {
            robotsMovementAfterSpot = "Jump";
        } else if (objectInFrontOfRobot.equalsIgnoreCase("Nothing")) {
            robotsMovementAfterSpot = "Forward";
        }
        return robotsMovementAfterSpot;
    }

    public static void isTheMouseInFront() {
        Scanner isTheMouseInFront = new Scanner(System.in);

        System.out.println("Give the number of the Pixels around the Killer Robot");

        for (; ; ) {

            int numberOfPixelsAround = Integer.parseInt(isTheMouseInFront.nextLine());

            if (numberOfPixelsAround % 2 == 0) {
                System.out.println("Mouse is in front of the Killer Robot!");
                break;
            }
            System.out.println("Give Number of the Pixels around the Killer Robot again");
        }
    }

    public static void isDamageDoneToMouse() {

        Random numGeneratorForHits = new Random();
        int randomGeneratedNum = numGeneratorForHits.nextInt(10) + 1;

        if (randomGeneratedNum == 5) {
            System.out.println("\nDamage done to the furniture");
            System.out.println("Keep looking for another mouse");
        } else {
            System.out.println("\nDamage done to the mouse");
            System.out.println("Keep looking for more mice");
        }
    }

    public static void timeToCommunicateWithOwner() {

        Random numGenerator = new Random();
        int randomGeneratedNum = numGenerator.nextInt(3) + 1;

        if (randomGeneratedNum % 2 != 0) {

            System.out.println("*Killer Robot goes to communicate with its owner*");

            for (byte robotCountingNums = 10; robotCountingNums >= 1; robotCountingNums--) {

                if (robotCountingNums % 2 == 0) {
                    System.out.printf("I am a Robottttt %s\n", robotCountingNums);
                }
            }
        }
    }

    public static void isTimeToRecharge() {

        System.out.println("It is time to recharge the robot");
        for (byte checker = 0; checker < 1; ) {

            Random numberGeneratorForCharging = new Random();
            int randomlyGeneratedFirstNum = numberGeneratorForCharging.nextInt(1000) + 1;
            int randomlyGeneratedSecondNum = numberGeneratorForCharging.nextInt(1000) + 1;

            if (randomlyGeneratedFirstNum > randomlyGeneratedSecondNum) {
                System.out.println("There is Electricity");
                checker++;
            } else if (randomlyGeneratedSecondNum > randomlyGeneratedFirstNum) {
                System.out.println("There is no Electricity");
                checker++;
            }
        }
    }

    public static String isTimeToTurnOffRobot() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want to turn off the Robot\nYes or No");
        String turnOff = scanner.nextLine();

        return turnOff;
    }
}
