import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Erkan Kamber
 *
 * NOTE: I want to thank my colleague Dinko Bahcevanov(AKA "The coolest guy") for helping me out with this homework.
 */

public class BoringJob {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<String> mothers = new ArrayList<>();
        ArrayList<String> children = new ArrayList<>();
        ArrayList<String> worker = new ArrayList<>();
        ArrayList<String> retired = new ArrayList<>();

        mainMethod(mothers, children, worker, retired, scanner);
    }

    public static void mainMethod(ArrayList<String> mothers, ArrayList<String> children
            , ArrayList<String> worker, ArrayList<String> retired, Scanner scanner) {

        placeCitizensIn(mothers, worker, retired);

        System.out.println();

        String input = scanner.nextLine();

        if ("SELECT".equalsIgnoreCase(input)) {

            selectCommand(mothers, "Mothers");
            selectCommand(worker, "Worker");
            selectCommand(retired, "Retired");

        } else if (input.contains("SELECT{TYPE}=") && !input.contains("SEX")) {

            String type = String.valueOf(input.charAt(14));
            printSecondSelection(type, mothers, retired, worker);

        } else if (input.contains("KID")) {

            String name = input.substring(43, input.length() - 2);

            if (mothers.get(0).contains(name)) {

                selectCommand(mothers, "Mothers");

            } else {

                System.out.println("There is no mother with that kid's name!");
            }

        } else if (input.contains("SEX")) {

            String type = String.valueOf(input.charAt(14));
            String sex = input.substring(input.length() - 2, input.length() - 1);

            printFourthSelection(mothers, worker, retired, type, sex);

        } else if (input.contains("SELECT{FNAME}=")) {

            String name = input.substring(15, input.length() - 1);

            printFifthSelection(mothers, name);
            printFifthSelection(retired, name);
            printFifthSelection(worker, name);

        } else {

            String age = input.substring(13, input.length() - 1);

            if (mothers.get(0).contains("{AGE}=[" + age + "]")) {

                selectCommand(mothers, "Mothers");

            } else if (worker.get(0).contains("{AGE}=[" + age + "]")) {

                selectCommand(worker, "Worker");

            } else if (retired.get(0).contains("{AGE}=[" + age + "]")) {

                selectCommand(retired, "Retired");

            } else System.out.println("Sorry no one is found");
        }
    }

    /**
     * Method that displays the Fifth option
     *
     * @param citizen    Array list citizens
     * @param nameToFind Searched name
     */
    private static void printFifthSelection(ArrayList<String> citizen, String nameToFind) {

        String lineToCompile = "[{A-Z}]+=\\[[A-Z]]@\\{[A-Z]+}=\\[([A-Z][a-z]+)]@\\{[A-Z]+}" +
                "=\\[([A-Z][a-z]+)]@\\{[A-Z]+}=\\[(\\d+)]@\\{[A-Z]+}=\\[([A-Z])]@\\{[A-Z]+}=\\[([A-Z][a-z]+)]" +
                "@\\{[A-Z]+}=\\[([\\w]+)]@";

        Pattern pattern = Pattern.compile(lineToCompile);

        Matcher matcher = pattern.matcher(citizen.get(0));

        if (matcher.find()) {

            String name = matcher.group(1);

            if (name.equalsIgnoreCase(nameToFind)) {

                System.out.println(name);

            } else System.out.println("nqma takuv");
        }
    }

    /**
     * Method that displays the fourth chosen option
     *
     * @param mothers Array list for Mothers
     * @param worker  Array list for Workers
     * @param retired Array list for Retired people
     * @param type    The type
     * @param sex     The sex/gender
     */
    private static void printFourthSelection(ArrayList<String> mothers, ArrayList<String> worker
            , ArrayList<String> retired, String type, String sex) {

        switch (type) {

            case "R":

                try {
                    System.out.println(retired.get(1));

                } catch (IndexOutOfBoundsException e) {

                    System.out.println("No such a person");
                }
                break;

            case "M":
                try {
                    System.out.println(mothers.get(1));
                    ;

                } catch (IndexOutOfBoundsException e) {

                    System.out.println("No such a person");
                }
                break;

            case "W":
                try {
                    System.out.println(worker.get(1));
                    ;
                    ;

                } catch (IndexOutOfBoundsException e) {

                    System.out.println("No such a person");
                }
                break;
        }
    }

    /**
     * Method that prints the second selection
     *
     * @param type    The type
     * @param worker  Array List for citizen type Workers
     * @param retired Array List for citizen type Retired people
     * @param mothers Array List for citizen type Mothers
     */
    private static void printSecondSelection(String type, ArrayList<String> worker
            , ArrayList<String> retired, ArrayList<String> mothers) {

        switch (type) {

            case "R":

                selectCommand(retired, "Retired");

                break;
            case "M":

                selectCommand(mothers, "Mothers");

                break;
            case "W":

                selectCommand(mothers, "Worker");

                break;
        }
    }

    /**
     * Method created to select a command
     *
     * @param citizen Array List citizens
     * @param type    chosen type
     */
    private static void selectCommand(ArrayList<String> citizen, String type) {

        String lineToCompile = "[{A-Z}]+=\\[[A-Z]]@\\{[A-Z]+}=\\[([A-Z][a-z]+)]@\\{[A-Z]+}" +
                "=\\[([A-Z][a-z]+)]@\\{[A-Z]+}=\\[(\\d+)]@\\{[A-Z]+}=\\[([A-Z])]@\\{[A-Z]+}=\\[([A-Z][a-z]+)]" +
                "@\\{[A-Z]+}=\\[([\\w]+)]@";

        Pattern pattern = Pattern.compile(lineToCompile);

        Matcher matcher = pattern.matcher(citizen.get(0));

        if (matcher.find()) {

            int age = Integer.parseInt(matcher.group(3));

            String firstName = matcher.group(1);
            String secondName = matcher.group(2);
            String sex = matcher.group(4);
            String address = matcher.group(5);
            String phoneNumber = matcher.group(6);

            System.out.print(firstName + " | " + secondName + " | " + age + " | " + sex + " | " + address + " | "
                    + phoneNumber + " ");

            citizen.add(sex);

            switcher(citizen, type, lineToCompile, pattern, matcher);
        }
    }

    /**
     * Method for switch cases
     *
     * @param citizen       array list of citizens
     * @param type          the type
     * @param lineToCompile the line compiler
     * @param pattern       a compiled representation of a regular expression
     * @param matcher       the engine that interprets the pattern and performs match operations against an input string
     */
    public static void switcher(ArrayList<String> citizen, String type, String lineToCompile, Pattern pattern, Matcher matcher) {
        switch (type) {

            case "Mothers":

                swtichCaseMother(citizen, lineToCompile, pattern, matcher);

                break;

            case "Worker":

                switchCaseWorker(citizen, lineToCompile, pattern, matcher);

                break;

            case "Retired":

                switchcaseRetired(citizen, lineToCompile, pattern, matcher);

                break;
        }
        System.out.println();
    }

    /**
     * Method created for switch case cases
     *
     * @param citizen       Array list for citizens
     * @param lineToCompile the entire line to get compiled
     * @param pattern       a compiled representation of a regular expression
     * @param matcher       the engine that interprets the pattern and performs match operations against an input string
     */
    public static void swtichCaseMother(ArrayList<String> citizen, String lineToCompile, Pattern pattern, Matcher matcher) {

        lineToCompile = "\\{[A-Z]+}=\\[\\{[A-Z]+}=\\[([A-Z][a-z]+)]@\\{[A-Z]+}" +
                "=\\[([A-Z][a-z]+)]@\\{[A-Z]+}=\\[(\\d)]]";

        int specialFieldIndex = citizen.get(0).indexOf("::");
        String[] arrayHoldingChildren = citizen.get(0).substring(specialFieldIndex + 2).split(":");

        for (String arrayHoldingChild : arrayHoldingChildren) {

            pattern = Pattern.compile(lineToCompile);
            matcher = pattern.matcher(arrayHoldingChild);

            if (matcher.find()) {

                String firstName = matcher.group(1);
                String lastName = matcher.group(2);

                int age = Integer.parseInt(matcher.group(3));

                System.out.print("Kid's name: " + firstName + " " + lastName + " | ");

                System.out.println("Kid's age: " + age);
            }
        }
    }

    /**
     * Method created for switch case cases
     *
     * @param citizen       Array list for citizens
     * @param lineToCompile the entire line to get compiled
     * @param pattern       a compiled representation of a regular expression
     * @param matcher       the engine that interprets the pattern and performs match operations against an input string
     */
    public static void switchCaseWorker(ArrayList<String> citizen, String lineToCompile, Pattern pattern, Matcher matcher) {

        lineToCompile = "\\{[\\w]+}::\\{[A-Z]+}=\\[(\\d+)]";

        pattern = Pattern.compile(lineToCompile);
        matcher = pattern.matcher(citizen.get(0));

        if (matcher.find()) {

            int salary = Integer.parseInt(matcher.group(1));

            System.out.println("Worker's salary: " + salary);
        }
    }

    /**
     * Method created for switch case cases
     *
     * @param citizen       Array list for citizens
     * @param lineToCompile the entire line to get compiled
     * @param pattern       a compiled representation of a regular expression
     * @param matcher       the engine that interprets the pattern and performs match operations against an input string
     */
    public static void switchcaseRetired(ArrayList<String> citizen, String lineToCompile, Pattern pattern, Matcher matcher) {

        lineToCompile = "\\{[\\w]+}::\\{[A-Z]+}=\\[(\\d+)]";

        pattern = Pattern.compile(lineToCompile);
        matcher = pattern.matcher(citizen.get(0));

        if (matcher.find()) {

            int pension = Integer.parseInt(matcher.group(1));

            System.out.println("Pension: " + pension);
        }
    }

    /**
     * Method that places all the citizens in lists
     *
     * @param mothers Array List for citizen type Mothers
     * @param worker  Array List for citizen type Workers
     * @param retired Array List for citizen type Retired people
     */
    public static void placeCitizensIn(ArrayList<String> mothers, ArrayList<String> worker, ArrayList<String> retired) {

        try {

            File citizensFile = new File("resources/citizens");

            FileReader citizensFileReader = new FileReader(citizensFile);

            BufferedReader bufferedCitizensReader = new BufferedReader(citizensFileReader);

            String line = bufferedCitizensReader.readLine();

            while (line != null) {

                String type = String.valueOf(line.charAt(8));

                switch (type) {
                    case "M":
                        mothers.add(line);

                        break;
                    case "W":
                        worker.add(line);

                        break;

                    case "R":
                        retired.add(line);

                        break;
                }
                line = bufferedCitizensReader.readLine();
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
        System.out.println();
    }
}