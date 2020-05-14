import java.util.Random;
import java.util.Scanner;

/**
 * Class that does sorting, finds positions, biggest/smallest number, average and sum
 *
 * @author Erkan Kamber
 */

public class PublicAdministration {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Колко числа ще въведете ?");
        int numbers = Integer.parseInt(scanner.nextLine());

        int chosenOption;
        int takenNumCounter = 1;

        boolean willYouUseProgram = true;

        if (numbers <= 0) {

            System.out.printf("Броят на числата не може да бъде %d", numbers);

        } else {

            int arraySize = numbers;
            int[] mainArray = new int[arraySize];

            System.out.println("Числата трябва да са между 0 и 100");

            for (int i = 0; i < arraySize; ) {

                System.out.printf("Въведете число номер: %d\n", takenNumCounter);

                mainArray[i] = Integer.parseInt(scanner.nextLine());

                if (mainArray[i] > 0 && mainArray[i] < 100) {

                    i++;
                    takenNumCounter++;

                } else {

                    System.out.println("Числото НЕ е между 0 и 100");
                }
            }

            optionDisplayer();

            chosenOption = chosenOption(scanner);

            while (willYouUseProgram) {

                if (chosenOption == 1) {

                    ascendingSort(arraySize, mainArray);
                } else if (chosenOption == 2) {

                    descendingSort(arraySize, mainArray);
                } else if (chosenOption == 3) {

                    findThePositionOfNum(arraySize, mainArray, scanner);
                } else if (chosenOption == 4) {

                    arrayScrambler(arraySize, mainArray);
                } else if (chosenOption == 5) {

                    sumAndAverageCalculator(arraySize, mainArray, chosenOption);
                } else if (chosenOption == 6) {

                    smallestAndBiggestNumFinder(arraySize, mainArray, chosenOption);
                } else if (chosenOption == 7) {

                    smallestAndBiggestNumFinder(arraySize, mainArray, chosenOption);
                } else if (chosenOption == 8) {

                    sumAndAverageCalculator(arraySize, mainArray, chosenOption);
                } else if (chosenOption == 9) {

                    isArraySymmetrical(arraySize, mainArray);
                } else if (chosenOption == 10) {

                    reversingArray(arraySize, mainArray);
                } else if (chosenOption == 11) {

                    displayEnteredNums(arraySize, mainArray);
                } else if (chosenOption == 12) {
                    break;
                }

                willYouUseProgram = willYouWorkMore(willYouUseProgram,scanner);

                if (willYouUseProgram) {

                    optionDisplayer();

                    chosenOption = chosenOption(scanner);
                }
            }
            System.out.println("Вие затворихте програмата");
        }
    }

    /**
     * Method that does Ascending sorting
     *
     * @param arraySize    The size of the mainArray
     * @param mainArray    The array that contains all the number, entered by the user
     */
    public static void ascendingSort(int arraySize, int[] mainArray) {

        for (int i = 0; i < arraySize; i++) {

            for (int j = 0; j < arraySize; j++) {

                if (mainArray[i] < mainArray[j]) {

                    int temporary = mainArray[i];
                    mainArray[i] = mainArray[j];
                    mainArray[j] = temporary;
                }
            }
        }

        displayEnteredNums(arraySize, mainArray);
    }

    /**
     * Method that does Descending sorting
     *
     * @param arraySize    The size of the mainArray
     * @param mainArray    The array that contains all the number, entered by the user
     */
    public static void descendingSort(int arraySize, int[] mainArray) {

        for (int i = 0; i < arraySize; i++) {

            for (int j = 0; j < arraySize; j++) {

                if (mainArray[i] > mainArray[j]) {

                    int temporary = mainArray[i];
                    mainArray[i] = mainArray[j];
                    mainArray[j] = temporary;
                }
            }
        }

        displayEnteredNums(arraySize, mainArray);
    }

    /**
     * Method that finds the position of a number from the Array
     *
     * @param arraySize The size of the mainArray
     * @param mainArray Array that contains all of the numbers, entered by the user
     */
    public static void findThePositionOfNum(int arraySize, int[] mainArray, Scanner scanner) {

       ascendingSort(arraySize, mainArray);

        int positionOfSearchedNum = 0;
        int firstNumOfArray = 0;

        int lastNumOfArray = arraySize - 1;

        int middleNumOfArray = (firstNumOfArray + lastNumOfArray) / 2;

        int findPositionOfThisNum;

        boolean isNumFound = false;

        System.out.println("\nПозицията на кое число искате да намерите ?");

        findPositionOfThisNum = scanner.nextInt();

        while (firstNumOfArray <= lastNumOfArray) {

            if (findPositionOfThisNum == mainArray[middleNumOfArray]) {

                positionOfSearchedNum = middleNumOfArray;

                isNumFound = true;
                break;

            } else if (findPositionOfThisNum < mainArray[middleNumOfArray]) {

                lastNumOfArray = middleNumOfArray - 1;
            } else {

                firstNumOfArray = middleNumOfArray + 1;
            }
            middleNumOfArray = (firstNumOfArray + lastNumOfArray) / 2;
        }
        if (isNumFound) {

            System.out.printf("Числото %d е в %d позиция", findPositionOfThisNum, ++positionOfSearchedNum);
        } else {

            System.out.printf("Числото %d не е намерено", findPositionOfThisNum);
        }
    }

    /**
     * Method that scrambles an array
     *
     * @param arraySize The size of the mainArray
     * @param mainArray The array that contains all the number, entered by the user
     */
    public static void arrayScrambler(int arraySize, int[] mainArray) {
        Random randomNum = new Random();

        for (int i = 0; i < arraySize; i++) {

            int randomIndexToSwap = randomNum.nextInt(arraySize);
            int temp = mainArray[randomIndexToSwap];

            mainArray[randomIndexToSwap] = mainArray[i];
            mainArray[i] = temp;
        }

        displayEnteredNums(arraySize, mainArray);
    }

    /**
     * Method that calculates the sum and the average of the numbers in the array
     *
     * @param arraySize The size of the mainArray
     * @param mainArray Array that contains all of the numbers, entered by the user
     */
    public static void sumAndAverageCalculator(int arraySize, int[] mainArray, int chosenOption) {

        double sumOfTheNumsInArray = 0.00;
        double averageOfNumsInArray;

        for (int i = 0; i < arraySize; i++) {

            sumOfTheNumsInArray += mainArray[i];
        }

        if (chosenOption == 5) {

            System.out.printf("Сборът на числата е: %.0f", sumOfTheNumsInArray);
        } else {

            averageOfNumsInArray = sumOfTheNumsInArray / arraySize;

            System.out.printf("Средно-аритметично на числата е: %.2f", averageOfNumsInArray);
        }
    }

    /**
     * Method that finds the biggest and the smallest number from an Array
     *
     * @param arraySize    The size of the mainArray
     * @param mainArray    The array that contains all the number, entered by the user
     * @param chosenOption The option that the user has chose
     */
    public static void smallestAndBiggestNumFinder(int arraySize, int[] mainArray, int chosenOption) {

        int biggestNumInArray = Integer.MIN_VALUE;
        int smallestNumInArray = Integer.MAX_VALUE;

        for (int i = 0; i < arraySize; i++) {

            if (mainArray[i] > biggestNumInArray) {

                biggestNumInArray = mainArray[i];
            }

            if (mainArray[i] < smallestNumInArray) {

                smallestNumInArray = mainArray[i];
            }
        }
        if (chosenOption == 6) {
            System.out.printf("Най-голямото число е: %d", biggestNumInArray);
        } else {
            System.out.printf("Най-малкото число е: %d", smallestNumInArray);
        }
    }

    /**
     * Method that checks if the array is symmetrical
     *
     * @param arraySize the size of the array
     * @param mainArray the array that contains all of the numbers
     */
    public static void isArraySymmetrical(int arraySize, int[] mainArray) {

        boolean isArraySimetric = false;

        if (arraySize % 2 == 0) {

            for (int i = 0; i < arraySize; i++) {

                if (mainArray[i] == mainArray[--arraySize]) {
                    isArraySimetric = true;
                } else {
                    isArraySimetric = false;
                    System.out.println("Масивът не е симетричен");
                    break;
                }
            }
        }
        if (isArraySimetric) {
            
            System.out.println("Масивът е симетричен");
        }
        
        displayEnteredNums(arraySize, mainArray);
    }

    /**
     * Method that reverses the array
     *
     * @param arraySize The size of the mainArray
     * @param mainArray The array that contains all the number, entered by the user
     */
    public static void reversingArray(int arraySize, int[] mainArray) {

        int[] array2 = new int[arraySize];
        int j = arraySize;

        for (int i = 0; i < arraySize; i++) {
            array2[j - 1] = mainArray[i];
            j = j - 1;
        }
        for (int k = 0; k < arraySize; k++) {
            System.out.printf("%d, ", array2[k]);
        }
    }

    /**
     * Method that displays the numbers in the array
     *
     * @param arraySize The size of the mainArray
     * @param mainArray Array that contains all of the numbers, entered by the user
     */
    public static void displayEnteredNums(int arraySize, int[] mainArray) {

        for (int i = 0; i < arraySize; i++) {

            System.out.printf("%d, ", mainArray[i]);
        }
    }

    /**
     * Method that checks if the user wants to keep using the program
     *
     * @param willYouUseProgram boolean checks if he wants to close the program
     * @return returns customers decision about turning off
     */
    public static boolean willYouWorkMore(boolean willYouUseProgram, Scanner scanner) {

        System.out.println("\nЖелаете ли да затворите програмата?");

        System.out.println("Да или Не");

        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("Да")) {
            willYouUseProgram = false;
        }
        return willYouUseProgram;
    }

    /**
     * Method that displays options to choose from
     */
    public static void optionDisplayer() {

        System.out.println("\n1.Сортиране на въведените числа във възходящ ред");
        System.out.println("2.Сортиране на въведените числа в низходящ ред");
        System.out.println("3.Търсене на позиция на конкретно число");
        System.out.println("4.Разбъркване на числата");
        System.out.println("5.Изчисляване на сбора на всички числа");
        System.out.println("6.Намиране на най-голямото число");
        System.out.println("7.Намиране на най-малкото число");
        System.out.println("8.Намиране средно-аритметично на числата");
        System.out.println("9.Проверка за симетричност на масива от числа");//
        System.out.println("10.Обръщане на масива от числа");
        System.out.println("11.Визуализирай въведените числа");
        System.out.println("12.Изход");
    }

    /**
     * Method that gets the option's number that user wants to use
     *
     * @return Returns the No[number] of chosen option
     */
    public static int chosenOption(Scanner scanner) {

        System.out.println("\nКоя опция желаете да използвате ?");

        return Integer.parseInt(scanner.nextLine());
    }
}
