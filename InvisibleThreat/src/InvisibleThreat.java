import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Erkan Kamber
 */
public class InvisibleThreat {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(System.in);

        int lastRow;
        int lastCol;
        int probes;
        int disposal;
        int currentPow;
        int currentCol;
        int stationsPlacementPow;
        int stationsPlacementCol;
        int stationPositionPow;
        int stationsPositionCol;
        int stationsPowPosition;
        int stationsColPosition;
        int placementPow;
        int placementCol;
        int chosenOption;

        byte OFFSET_FOR_ARRAY = 1;
        int boardWidth = 0;
        int boardHeight = 0;
        int numOfMines = 0;
        int startRow = 0;
        int startCol = 0;
        int finishRow = 0;
        int finishCol = 0;

        String[][] boardToDisplay;
        String[][] boardContainsMines;

        int[] randomNumsForCol = {0, 1, 2, 3, 4, 5};

        boolean isStationMoveable;
        boolean isStationGoSideway;
        boolean isGameOver = false;

        File boardSize = new File("resources/enemy_territory.txt");
        File mines = new File("resources/configurations.txt");

        FileReader boardSizeReader = new FileReader(boardSize);
        FileReader minesInformation = new FileReader(mines);

        BufferedReader bufferedReader = new BufferedReader(boardSizeReader);
        BufferedReader bufferedReaderMines = new BufferedReader(minesInformation);


        boardWidth = boardWidth(boardSizeReader, bufferedReader, boardSize);

        boardHeight = boardHeight(boardSizeReader, bufferedReader, boardSize);

        numOfMines = numOfMines(boardSizeReader, bufferedReader, boardSize);

        probes = numberOfProbes(mines, minesInformation, bufferedReaderMines);

        disposal = disposalsForMines(mines, minesInformation, bufferedReaderMines);

        boardToDisplay = new String[boardWidth][boardHeight];

        boardContainsMines = new String[boardWidth][boardHeight];

        boardContainsMines = createBoardForMines(boardContainsMines, boardHeight, boardWidth);

        boardContainsMines = placeMinesInArray(boardContainsMines, boardHeight, boardWidth, numOfMines);

        //boardForMinesDisplayer(boardContainsMines);

        boardToDisplay = createBoardInGame(boardToDisplay, boardHeight, boardWidth);

        lastRow = boardWidth - 1;
        lastCol = boardHeight - 1;

       /* for (int i = 0; i < boardWidth; i++) {

            randomNumsForCol[i] = i;
            }

            Used for loop to put numbers to be randomly generated
            In my case the array has 6 numbers because the width of the board is 6
        */

        for (int checker = 0; checker <= 0; ) {

            Random randomNum = new Random();

            int randomGeneratedNum = randomNum.nextInt(4) + 1;

            if (randomGeneratedNum == 1) {

                startRow = 0;

                startCol = new Random().nextInt(randomNumsForCol.length);

                finishRow = lastRow;

                finishCol = new Random().nextInt(randomNumsForCol.length);

            } else if (randomGeneratedNum == 2) {

                startRow = lastRow;

                startCol = new Random().nextInt(randomNumsForCol.length);

                finishRow = 0;

                finishCol = new Random().nextInt(randomNumsForCol.length);

            } else if (randomGeneratedNum == 3) {

                startCol = lastCol;

                startRow = new Random().nextInt(randomNumsForCol.length);

                finishCol = 0;

                finishRow = new Random().nextInt(randomNumsForCol.length);

            } else if (randomGeneratedNum == 4) {

                startCol = 0;

                startRow = new Random().nextInt(randomNumsForCol.length);

                finishCol = lastCol;

                finishRow = new Random().nextInt(randomNumsForCol.length);
            }
            if (startRow == 0 || startRow == lastRow && finishRow == 0 || finishRow == lastRow) {

                System.out.println("Corner case");

            } else if (startRow == finishRow && startCol != finishCol) {

                checker++;

            } else if (startRow != finishRow && startCol != finishCol) {

                checker++;
            }
        }

        boardToDisplay[startRow][startCol] = "S";
        boardToDisplay[finishRow][finishCol] = "F";

        while (!isGameOver) {

            displayBoardInGame(boardToDisplay);

            System.out.println("От кой ред искате да преместите станцията ?");
            stationsPowPosition = Integer.parseInt(scanner.nextLine());

            System.out.println("От коя колона искате да преместите станцията ?");
            stationsColPosition = Integer.parseInt(scanner.nextLine());

            stationPositionPow = stationsPowPosition - OFFSET_FOR_ARRAY; // A variable for figure's col position
            stationsPositionCol = stationsColPosition - OFFSET_FOR_ARRAY; // A variable for figure's pow position

            System.out.println("В кой ред искате да преместите станцията ?");
            placementPow = Integer.parseInt(scanner.nextLine());

            System.out.println("В коя колона искате да преместите станцията ?");
            placementCol = Integer.parseInt(scanner.nextLine());

            stationsPlacementPow = placementPow - OFFSET_FOR_ARRAY; // For where to put the figure
            stationsPlacementCol = placementCol - OFFSET_FOR_ARRAY; // For where to put the figure

            isStationGoSideway = (Math.abs(stationPositionPow - stationsPlacementPow) == 0) &&
                    (Math.abs(stationsPositionCol - stationsPlacementCol) == 1);

            isStationMoveable = (Math.abs(stationPositionPow - stationsPlacementPow) == 1) &&
                    (Math.abs(stationsPositionCol - stationsPlacementCol) == 0);

            if (boardToDisplay[stationPositionPow][stationsPositionCol].equalsIgnoreCase("s") ||
                    boardToDisplay[stationPositionPow][stationsPositionCol].equalsIgnoreCase("*")) {

                if (isStationMoveable || isStationGoSideway) {

                    chosenOption = possibilytOfAction(scanner);

                    currentPow = stationPositionPow;
                    currentCol = stationsPositionCol;

                    if (chosenOption == 1) {

                        if (probes > 0) {

                            if (stationsPlacementCol < currentCol || stationsPlacementPow < currentPow) {

                                boardToDisplay[stationsPlacementPow][stationsPlacementCol] =
                                        boardContainsMines[stationsPlacementPow][stationsPlacementCol];

                                boardToDisplay[--stationsPlacementPow][stationsPlacementCol] =
                                        boardContainsMines[--stationsPlacementPow][stationsPlacementCol];

                            } else if (stationsPlacementCol > currentCol || stationsPlacementPow > currentPow) {

                                boardToDisplay[stationsPlacementPow][stationsPlacementCol] =
                                        boardContainsMines[stationsPlacementPow][stationsPlacementCol];

                                boardToDisplay[++stationsPlacementPow][stationsPlacementCol] =
                                        boardContainsMines[++stationsPlacementPow][stationsPlacementCol];
                            }

                            probes--;

                        } else if (probes == 0) {

                            System.out.println("Нямата право на проби");
                        }
                    } else if (chosenOption == 2) {

                        if (boardContainsMines[stationsPlacementPow][stationsPlacementCol].equals("Y")) {

                            if (disposal != 0) {

                                boardToDisplay[stationsPlacementPow][stationsPlacementCol] = "*";

                                boardToDisplay[stationPositionPow][stationsPositionCol] = "V";

                                disposal--;

                                System.out.println("Бомбата е обезвредена");

                            } else if (boardContainsMines[stationsPlacementPow][stationsPlacementCol].equals("N")) {

                                System.out.println("Там няма мина");

                            } else if (probes == 0) {

                                System.out.println("Нямате право на обезвреждания");
                            }
                        }
                    } else if (chosenOption == 3) {

                        if (boardToDisplay[stationsPlacementPow][stationsPlacementCol].equals("F")) {

                            isGameOver = true;

                        }
                        boardToDisplay[stationsPlacementPow][stationsPlacementCol] = "*";

                        boardToDisplay[stationPositionPow][stationsPositionCol] = "V";
                    }
                }
            }
        }
        System.out.println("Чао и до нови срещи");
    }

    /**
     * Method that displays the board
     *
     * @param boardToDisplay the board as an 2D array
     */
    public static void displayBoard(String[][] boardToDisplay) {

        System.out.println(Arrays.deepToString(boardToDisplay)
                .replace("],", "\n").replace(",", "\t|")
                .replaceAll("[\\[\\]]", " "));

    }

    /**
     * Method that checks if the chosen option is usable
     *
     * @param scanner scanner for inputs
     * @return the chosen option
     */
    public static int possibilytOfAction(Scanner scanner) {

        int chosenOption = 0;

        for (int checker = 0; checker <= 0; ) {

            System.out.println("Възможности за действия");
            System.out.println("1.Анализ");
            System.out.println("2.Обезвреждане");
            System.out.println("3.придвижване, по бойното поле");

            chosenOption = Integer.parseInt(scanner.nextLine());

            if (chosenOption < 1 || chosenOption > 3) {

                System.out.println("Опитайте отново");
            } else {

                checker++;
            }
        }
        return chosenOption;
    }

    /**
     * Method that gets the numbers of probes
     *
     * @param mines               the number of mines
     * @param minesReader         reader for File mines
     * @param minesBufferedReader buffered reader for File mines
     * @return the numbers of probes
     */
    public static int numberOfProbes(File mines, FileReader minesReader, BufferedReader minesBufferedReader) {

        int probes = 0;
        String lineReferences;
        String fileInformation;
        String information;

        mines = new File("resources/configurations.txt");
        try {
            minesReader = new FileReader(mines);

            minesBufferedReader = new BufferedReader(minesReader);

            while ((lineReferences = minesBufferedReader.readLine()) != null) {

                String[] boardSizeInformation = lineReferences.split("=");

                fileInformation = boardSizeInformation[0];

                information = boardSizeInformation[1];

                if (boardSizeInformation[0].equalsIgnoreCase("probes")) {

                    probes = Integer.parseInt(information);
                }
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

        return probes;
    }

    /**
     * Method that gets the numbers of disposals for mines
     *
     * @param mines               the numbers of mines
     * @param minesReader         reader for File mines
     * @param minesBufferedReader buffered reader for File mines
     * @return the numbers of disposals for mines
     */
    public static int disposalsForMines(File mines, FileReader minesReader, BufferedReader minesBufferedReader) {

        int disposals = 0;
        String lineReferences;
        String fileInformation;
        String information;

        mines = new File("resources/configurations.txt");
        try {
            minesReader = new FileReader(mines);

            minesBufferedReader = new BufferedReader(minesReader);

            while ((lineReferences = minesBufferedReader.readLine()) != null) {

                String[] boardSizeInformation = lineReferences.split("=");

                fileInformation = boardSizeInformation[0];

                information = boardSizeInformation[1];

                if (boardSizeInformation[0].equalsIgnoreCase("disposal")) {

                    disposals = Integer.parseInt(information);
                }
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

        return disposals;
    }

    /**
     * Method that gets the width of the board
     *
     * @param boardSizeReader reader for the File boardSize
     * @param bufferedReader  buffered reader for the File boardSize
     * @param boardSize       The text File
     * @return returns the width of the board
     */
    public static int boardWidth(FileReader boardSizeReader, BufferedReader bufferedReader, File boardSize) {

        int boardWidth = 0;
        String lineReferences;
        String boardInformation;
        String boardWidthAndHeight;

        boardSize = new File("resources/enemy_territory.txt");
        try {
            boardSizeReader = new FileReader(boardSize);

            bufferedReader = new BufferedReader(boardSizeReader);

            while ((lineReferences = bufferedReader.readLine()) != null) {

                String[] boardSizeInformation = lineReferences.split("=");

                boardInformation = boardSizeInformation[0];

                boardWidthAndHeight = boardSizeInformation[1];

                if (boardSizeInformation[0].equalsIgnoreCase("Width")) {

                    boardWidth = Integer.parseInt(boardWidthAndHeight);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return boardWidth;
    }

    /**
     * Method that gets the height of the board
     *
     * @param boardSizeReader reader for the File boardSize
     * @param bufferedReader  buffered reader for the File boardSize
     * @param boardSize       The text File
     * @return returns the height of the board
     */
    public static int boardHeight(FileReader boardSizeReader, BufferedReader bufferedReader, File boardSize) {

        int boardHeight = 0;
        String lineReferences;
        String boardInformation;
        String boardWidthAndHeight;

        boardSize = new File("resources/enemy_territory.txt");
        try {
            boardSizeReader = new FileReader(boardSize);

            bufferedReader = new BufferedReader(boardSizeReader);

            while ((lineReferences = bufferedReader.readLine()) != null) {

                String[] boardSizeInformation = lineReferences.split("=");

                boardInformation = boardSizeInformation[0];

                boardWidthAndHeight = boardSizeInformation[1];

                if (boardSizeInformation[0].equalsIgnoreCase("Height")) {
                    boardHeight = Integer.parseInt(boardWidthAndHeight);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return boardHeight;
    }

    /**
     * Method that gets the number of mines
     *
     * @param boardSizeReader File reader for File bufferedReader
     * @param bufferedReader  buffered reader for File boardSize
     * @param boardSize       The text File
     * @return the number of the mines
     */
    public static int numOfMines(FileReader boardSizeReader, BufferedReader bufferedReader, File boardSize) {

        int numOfmines = 0;
        String lineReferences;
        String boardInformation;
        String boardWidthAndHeight;

        boardSize = new File("resources/enemy_territory.txt");
        try {
            boardSizeReader = new FileReader(boardSize);

            bufferedReader = new BufferedReader(boardSizeReader);

            while ((lineReferences = bufferedReader.readLine()) != null) {

                String[] boardSizeInformation = lineReferences.split("=");

                boardInformation = boardSizeInformation[0];

                boardWidthAndHeight = boardSizeInformation[1];

                if (boardSizeInformation[0].equalsIgnoreCase("mines")) {

                    numOfmines = Integer.parseInt(boardWidthAndHeight);
                }
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
        return numOfmines;
    }

    /**
     * Method that creates the in game board
     *
     * @param boardToDisplay board you see on screen
     * @param boardHeight    the height of the board
     * @param boardWidth     the width of the board
     * @return returns the board as an array
     */
    public static String[][] createBoardInGame(String[][] boardToDisplay, int boardHeight, int boardWidth) {

        for (int rowNo = 0; rowNo < boardHeight; rowNo++) {

            for (int colNo = 0; colNo < boardWidth; colNo++) {

                boardToDisplay[rowNo][colNo] = "X";
            }
        }
        return boardToDisplay;
    }

    /**
     * Method that creaates the board that contains all of the mines
     *
     * @param boardForMines Special array for mines
     * @param boardHeight   boards height
     * @param boardWidth    boards width
     * @return returns 2D array for mines
     */
    public static String[][] createBoardForMines(String[][] boardForMines, int boardHeight, int boardWidth) {

        for (int rowNo = 0; rowNo < boardHeight; rowNo++) {

            for (int colNo = 0; colNo < boardWidth; colNo++) {

                boardForMines[rowNo][colNo] = "N";
            }
        }
        return boardForMines;
    }

    /**
     * Method that places the mines in the array for mines
     *
     * @param boardForMines array that contains all of the mines
     * @param boardHeight   the height of an array
     * @param boardWidth    the width of an array
     * @param numOfMines    the numbers of mines
     * @return the array with all mines placed in it
     */
    public static String[][] placeMinesInArray(String[][] boardForMines, int boardHeight, int boardWidth, int numOfMines) {
        Random randomNum = new Random();

        int randomCol;
        int randomRow;

        for (int i = 1; i <= numOfMines; i++) {

            randomRow = randomNum.nextInt(boardHeight);
            randomCol = randomNum.nextInt(boardWidth);

            boardForMines[randomRow][randomCol] = "Y";
        }

        return boardForMines;
    }

    /**
     * Method that checks if checks if you can move the station
     *
     * @param figuresPlacementCol the col of station you want to place with offset
     * @param figuresPlacementPow the pow of station you want to place with offset
     * @param figuresPositionCol  the col of station
     * @param figuresPositionPow  the pow of station
     * @return bolean to check if its moveable
     */
    public static boolean isStationMoveable(int figuresPlacementCol, int figuresPlacementPow, int figuresPositionCol, int figuresPositionPow) {

        boolean isStationMoveable = false;

        isStationMoveable = (Math.abs(figuresPlacementPow - figuresPositionPow) == 0) &&

                (Math.abs(figuresPositionCol - figuresPlacementCol) == 1) ||

                (Math.abs(figuresPlacementPow - figuresPositionPow) == 1) &&

                        (Math.abs(figuresPositionCol - figuresPlacementCol) == 0);

        return isStationMoveable;
    }

    /**
     * Method to display the array for mines
     *
     * @param array the array that has all the mines in it
     */
    public static void boardForMinesDisplayer(String[][] array) {

        System.out.println(Arrays.deepToString(array)
                .replace("],", "\n").replace(",", "\t|")
                .replaceAll("[\\[\\]]", " "));

    }

    /**
     * Method that displays the board during game
     *
     * @param array the array for displaying
     */
    public static void displayBoardInGame(String[][] array) {

        System.out.println(Arrays.deepToString(array)
                .replace("],", "\n").replace(",", "\t|")
                .replaceAll("[\\[\\]]", " "));
    }
}