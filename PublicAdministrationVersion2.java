import java.util.Scanner;

/**
 * @author Erkan Kamber
 */

public class PublicAdministrationVersion2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int chosenOption;
        int counterForEnters;
        int[] arrayForNums;
        int mostPopularNumber;

        int arrayForNumsSize   = 0;
        int arrayForWordsSize  = 0;
        int currentWordsLenght = 0;

        String workWith = "числата";
        String[] arrayForWords;

        mainMenuOptionsDisplayer();

        chosenOption = chosenOpetion(scanner);

        while (chosenOption != 3) {

            counterForEnters = 1;

            if (chosenOption == 1) {

                arrayForNumsSize = arraySizeValidator(scanner, workWith);

                arrayForNums = new int[arrayForNumsSize];

                System.out.println("Числата трябва да са в интервала " +
                        "от нула и до 10 000");

                for (int i = 0; i < arrayForNumsSize; ) {

                    System.out.printf("Въведете число No: [%d]\n", counterForEnters);

                    arrayForNums[i] = Integer.parseInt(scanner.nextLine());

                    if (arrayForNums[i] >= 0 && arrayForNums[i] <= 10_000) {

                        i++;
                        counterForEnters++;

                    } else {

                        System.out.println("Числото Не е в интервала " +
                                "от 0 и до 10 000");
                    }
                }

                optionsForNumsDisplayer();

                chosenOption = chosenOpetion(scanner);

                while (chosenOption != 7) {

                    if (chosenOption == 1) {

                        primeNumsFinder(arrayForNumsSize, arrayForNums);
                    } else if (chosenOption == 2) {

                        mostPopularNumber = mostPopularNum(arrayForNumsSize, arrayForNums);
                        System.out.printf("Най-често срещан елемент в масива: [%d]", mostPopularNumber);

                    } else if (chosenOption == 3) {

                        lineOfIncreasingNums(arrayForNumsSize, arrayForNums);

                    } else if (chosenOption == 4) {

                        lineOfDecreasingNums(arrayForNumsSize, arrayForNums);

                    } else if (chosenOption == 5) {

                        lineOfSameNums(arrayForNumsSize, arrayForNums);

                    } else if (chosenOption == 6) {

                        whichNumsMakeSum(arrayForNumsSize, arrayForNums, scanner);

                    } else {

                        System.out.println("Грешка, опитайте отново");
                    }

                    optionsForNumsDisplayer();

                    chosenOption = chosenOpetion(scanner);

                }

            } else if (chosenOption == 2) {

                workWith = "думите";

                arrayForWordsSize = arraySizeValidator(scanner, workWith);

                arrayForWords = new String[arrayForWordsSize];

                System.out.println("Броят на символите в една дума НЕ трябва да е по-голям от [20]");

                for (int i = 0; i < arrayForWordsSize; ) {

                    System.out.printf("Въведете дума No: [%d]\n", counterForEnters);

                    arrayForWords[i] = scanner.nextLine();

                    currentWordsLenght = arrayForWords[i].length();

                    if (currentWordsLenght <= 20 && currentWordsLenght > 0) {

                        i++;
                        counterForEnters++;

                    } else {

                        System.out.printf("Броят на символите в една дума НЕ може да бъде: [%d]\n", currentWordsLenght);
                        System.out.println("Опитайте отново");
                    }
                }

                optionsForWordsDisplayer();

                chosenOption = chosenOpetion(scanner);

                while (chosenOption != 5) {

                    if (chosenOption == 1) {

                       reverseWords(arrayForWordsSize, arrayForWords);

                    } else if (chosenOption == 2) {

                        mostCommonLetters(arrayForWordsSize, arrayForWords);

                    } else if (chosenOption == 3) {

                        wordsLength(arrayForWordsSize, arrayForWords);

                    } else if (chosenOption == 4) {

                        System.out.println("Не работи");
                    }

                    optionsForWordsDisplayer();

                    chosenOption = chosenOpetion(scanner);
                }
            }

            System.out.println("*Върнахте се назад към основното меню*");

            mainMenuOptionsDisplayer();

            chosenOption = chosenOpetion(scanner);
        }

        System.out.println("Чао и до нови срещи :)");

    }

    /**
     * Method that reverses every word from an array
     * @param arraySize Size of the arrayForWords
     * @param arrayForWords The array that contains all of the words
     */
    public static void reverseWords(int arraySize, String[] arrayForWords) {

        String reversedString;

        String[] unchangedArray = new String[arraySize];

        for (int wordNo = 0; wordNo < arraySize; wordNo++) {

            reversedString = "";
            unchangedArray[wordNo] = arrayForWords[wordNo];

            for(int charNo = arrayForWords[wordNo].length(); charNo > 0; charNo--) {

                reversedString += arrayForWords[wordNo].charAt(charNo - 1);

            }
            arrayForWords[wordNo] = reversedString;
        }

        for (int i = 0; i < arraySize; i++) {
            System.out.printf("Преди - %s || Сега - %s\n", unchangedArray[i], arrayForWords[i]);
        }
    }

    /**
     * Method that finds the most common char in a word
     *
     * @param arraySize     Size of the mainArray
     * @param arrayForWords Array that contains all of the words
     */
    public static void mostCommonLetters(int arraySize, String[] arrayForWords) {

        int count;
        String mostUsedChar;
        String[] array;

        for (int wordNo = 0; wordNo < arraySize; wordNo++) {

            mostUsedChar = "";
            count = 0;

            array = arrayForWords[wordNo].split("");

            for (int i = 0; i < array.length; i++) {

                int tempCount = 0;

                for (String s : array) {

                    if (array[i].equals(s)) {

                        tempCount++;
                    }
                    if (tempCount > count) {

                        count = tempCount;

                        mostUsedChar = array[i];
                    }
                }
            }
            System.out.printf("%s - брой повтарящи се символи: [%d] – буква [%s]\n", arrayForWords[wordNo], count, mostUsedChar);
        }
    }

    /**
     * Method that gets the length of the words
     *
     * @param arraySize     Size of the mainArray
     * @param arrayForWords Array that contains all of the words
     */
    public static void wordsLength(int arraySize, String[] arrayForWords) {

        int currentWordSize = 0;

        for (int wordNo = 0; wordNo < arraySize; wordNo++) {

            currentWordSize = arrayForWords[wordNo].length();

            System.out.printf("%s - брой символи: [%d]\n", arrayForWords[wordNo], currentWordSize);
        }

    }

    /**
     * Method that Displays a specific line of numbers
     *
     * @param count  Counter of how long he line is
     * @param number Displays the most common num
     */
    public static void lineDisplayer2(int count, int number) {

        for (int i = 0; i < count; i++) {

            System.out.printf("[%d] ", number);
        }
    }

    /**
     * Method that finds numbers back to back which's sum is equal to the searched number
     *
     * @param arraySize Size of the mainArray
     * @param mainArray The array that contains all the numbers
     * @param scanner   for input
     */
    public static void whichNumsMakeSum(int arraySize, int[] mainArray, Scanner scanner) {

        int searchedNumbersSum;

        int sum = 0;
        int start = 0;
        int end = 0;

        boolean sumFound = false;

        System.out.println("Сумата на кое число искате да намерите ?");

        searchedNumbersSum = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < arraySize - 1; i++) {

            sum = mainArray[i];

            for (int j = i + 1; j < arraySize; j++) {

                sum += mainArray[j];

                if (sum == searchedNumbersSum) {

                    start = i;
                    end = j;

                    sumFound = true;
                    break;
                }
            }
            if (sumFound) {

                break;
            }
        }

        if (sumFound) {

            for (int i = start; i <= end; i++) {

                System.out.printf("[%d] ", mainArray[i]);
            }
        } else {

            System.out.println("Няма сума на числа, коята е равна на търсеното число");
        }
    }

    /**
     * Method that displays the increasing and decreasing line of numbers
     *
     * @param bestStart The starter num
     * @param bestSames The
     * @param mainArray The array that contains all of the numbers
     */
    public static void increaseDecreaseLineDisplayer(int bestStart, int bestSames, int[] mainArray) {

        for (int i = bestStart; i < bestSames + bestStart; i++) {

            System.out.printf("[%d] ", mainArray[i]);
        }
    }

    /**
     * Method that finds the line of numbers that are back to back
     *
     * @param arraySize Size of the mainArray
     * @param mainArray The array that contains all of the numbers
     */
    public static void lineOfSameNums(int arraySize, int[] mainArray) {

        int tempCount = 1;
        int count = 1;
        int number = 0;

        for (int i = 0; i < arraySize - 1; i++) {
            if (mainArray[i] == mainArray[i + 1]) {

                tempCount++;
            } else {

                tempCount = 1;
            }
            if (tempCount > count) {

                count = tempCount;

                number = mainArray[i];
            }
        }

        lineDisplayer2(count, number);
    }

    /**
     * Method that finds a line of decreasing numbers that are back to back
     *
     * @param arraySize Size of the mainArray
     * @param mainArray The array that contains all of the numbers
     */
    public static void lineOfDecreasingNums(int arraySize, int[] mainArray) {

        int lastElement;

        int sames = 1;
        int bestSames = 1;
        int bestStart = 0;

        for (int i = 0; i < arraySize - 1; i++) {

            if (mainArray[i] - 1 == mainArray[i + 1]) {

                sames++;

                if (sames > bestSames) {

                    bestSames = sames;

                    lastElement = i + 1;

                    bestStart = lastElement - bestSames + 1;
                }
            } else {

                sames = 1;
            }
        }

        increaseDecreaseLineDisplayer(bestStart, bestSames, mainArray);
    }

    /**
     * Method that finds a line of increasing numbers that are back to back
     *
     * @param arraySize Size of the mainArray
     * @param mainArray The array that contains all of the numbers
     */
    public static void lineOfIncreasingNums(int arraySize, int[] mainArray) {

        int lastElement;

        int sames = 1;
        int bestSames = 1;
        int bestStart = 0;

        for (int i = 0; i < arraySize - 1; i++) {

            if (mainArray[i] + 1 == mainArray[i + 1]) {

                sames++;

                if (sames > bestSames) {

                    bestSames = sames;

                    lastElement = i + 1;

                    bestStart = lastElement - bestSames + 1;
                }
            } else {

                sames = 1;
            }
        }

        increaseDecreaseLineDisplayer(bestStart, bestSames, mainArray);
    }

    /**
     * Method that finds the most common number from an array
     *
     * @param arraySize Size of the mainArray
     * @param mainArray The array that contains all of the numbers
     * @return The most common number from an array
     */
    public static int mostPopularNum(int arraySize, int[] mainArray) {

        int temporary;

        int popularNum = 0;
        int count = 0;
        int tempCount = 0;

        for (int i = 0; i < arraySize; i++) {

            temporary = mainArray[i];

            tempCount = 0;

            for (int j = 1; j < arraySize; j++) {

                if (temporary == mainArray[j]) {

                    tempCount++;
                }
            }
            if (tempCount > count) {

                popularNum = temporary;

                count = tempCount;
            }
        }
        return popularNum;
    }

    /**
     * Method that finds the prime numbers
     *
     * @param arraySize The size of an mainArray
     * @param mainArray The array that contains all of the numbers
     */
    public static void primeNumsFinder(int arraySize, int[] mainArray) {

        for (int i = 0; i < arraySize; i++) {

            boolean isPrime = true;

            if (mainArray[i] == 1) {

                isPrime = false;

            } else {

                for (int j = 2; j <= mainArray[i] / 2; j++) {

                    if (mainArray[i] % j == 0) {

                        isPrime = false;
                        break;
                    }
                }
            }

            if (isPrime) {

                System.out.printf("[%d] ", mainArray[i]);
            }
        }
    }

    /**
     * Method that checks if the size of the array is negative or 0
     *
     * @param scanner  For input
     * @param workWith What we are working with numbers or words
     * @return Size of the array that will be used
     */
    public static int arraySizeValidator(Scanner scanner, String workWith) {

        int arraySize = 0;

        System.out.printf("Въведете броят на %s! \n", workWith);

        for (byte checker = 0; checker <= 0; ) {

            arraySize = Integer.parseInt(scanner.nextLine());

            if (arraySize <= 0) {

                System.out.printf("Броят на %s не може да бъде [%d]\n", workWith, arraySize);
                System.out.printf("Въведе броят на %s отново!\n", workWith);

            } else {

                checker++;
            }
        }

        return arraySize;
    }

    /**
     * Method that gets the No of the chosen option
     *
     * @param scanner For inout
     * @return The chosen option that the user will use
     */
    public static int chosenOpetion(Scanner scanner) {

        System.out.println("\nКоя опция искате да използвате ?");

        return Integer.parseInt(scanner.nextLine());
    }

    /**
     * Method that displays the Main Menu
     */
    public static void mainMenuOptionsDisplayer() {

        System.out.println("\nГлавно меню с опции:");
        System.out.println("1. Работа с числа");
        System.out.println("2. Работа с думи");
        System.out.println("3. Изход от програмата\n");

    }

    /**
     * Method that displays the options Menu for numbers
     */
    public static void optionsForNumsDisplayer() {

        System.out.println("\nМеню с опции:");
        System.out.println("1. Извеждане само на простите числа от масива");
        System.out.println("2. Извеждане на най-често срещан елемент в масива");
        System.out.println("3. Извеждане на максимална редица от нарастващи елементи в масива");
        System.out.println("4. Извеждане на максимална редица от намаляващи елементи в масива");
        System.out.println("5. Извеждане на максимална редица от еднакви елементи в масива");
        System.out.println("6. Извеждане на последователност от числа от масива, които имат сума\n" +
                           "равна на число, генерирано на случаен принцип");
        System.out.println("7. Връщане назад към основното меню");
    }

    /**
     * Method that displays the options Menu for Words
     */
    public static void optionsForWordsDisplayer() {
        System.out.println("\nМеню с опции:");
        System.out.println("1. Обърнете буквите на думите от масива наобратно и ги\n" +
                           "визуализирайте в конзолата");
        System.out.println("2. Изведете броя на повтарящите се символи за всяка една от думите в\n" +
                           "масива");
        System.out.println("3. Изведете броя на символите за всяка една от думите в масива");
        System.out.println("4. Изведете броя на повтарящите се думи от масива");
        System.out.println("5. Връщане назад към основното меню");
    }
}