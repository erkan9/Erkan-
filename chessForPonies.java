import java.util.Arrays;
import java.util.Scanner;

public class chessForPonies {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean isThereYourColorFigures;
        boolean isMortarMoveable;
        boolean isQueenMoveable;
        boolean isDwarfMoveable;
        boolean isDwarfMovingForward;
        boolean isKingMoveable;
        boolean isDonkeyMoveable;

        //Variables created to check if the Figure Dwarf[Dw] reached end of the board
        //isGameOver is exception
        boolean isFigureDwPow6Col1Finished = false;
        boolean isFigureDwPow6Col6Finished = false;
        boolean isFigureDwPow1Col1Finished = false;
        boolean isFigureDwPow1Col6Finished = false;
        boolean isGameOver                 = false;

        // Variables used to check if you are trying to place figure on your figure
        String donkey       = "";
        String king         = "";
        String queen        = "";
        String dwarf        = "";
        String mortar       = "";
        String kingToKill   = "";

        // Variables to get coordinates of the figure that we want to move
        int figuresPowPosition; //Figure's Pow coordinate that we get as an input
        int figuresColPosition; //Figure's Column coordinate that we get as an input

        int figuresPositionCol; //Figure's Pow coordinate with offset added [-1] -calculations done with this variable
        int figuresPositionPow; //Figure's Column coordinate that offset added [-1] -calculations done with this variable

        // Variables to get coordinates of the place that we want to put our figure
        int placementPow;//Figure's Pow coordinate that we want to put it gotten as an input
        int placementCol;//Figure's Column coordinate that we want to put it gotten as an input

        int figuresPlacementPow; //Figure's Pow coordinate with offset added [-1] -calculations done with this variable
        int figuresPlacementCol; //Figure's Column coordinate that offset added [-1] -calculations done with this variable

        final byte OFFSET_FOR_2D_ARRAY = 1;
        int maxTurns = Integer.MAX_VALUE;

        int whitePlayerTurnsCounter = 0;
        int blackPlayerTurnsCounter = 0;
        int playerIdAndTurnGiver    = 1;

        String currentPlayer = "";

        String[][] poniesChessBoard = {
                {"wDw", "wD",  "wQ",  "wK",  "wM",  "wDw"},       //line 0
                {"XXX", "XXX", "XXX", "XXX", "XXX", "XXX"},       //line 1
                {"XXX", "XXX", "XXX", "XXX", "XXX", "XXX"},       //line 2
                {"XXX", "XXX", "XXX", "XXX", "XXX", "XXX"},       //line 3
                {"XXX", "XXX", "XXX", "XXX", "XXX", "XXX"},       //line 4
                {"bDw", "bM",  "bK",  "bQ",  "bD",  "bDw"}        //line 5
        };//column 1      2     3       4      5      6

        System.out.println("NOTE: You have to enter your figures' positions with numbers");
        
        while (!isGameOver) {

            System.out.println(Arrays.deepToString(poniesChessBoard)
                    .replace("],", "\n").replace(",", "\t| ")
                    .replaceAll("[\\[\\]]", " "));

            if (isFigureDwPow6Col1Finished) {
                poniesChessBoard[5][0] = "XXX";
                poniesChessBoard[0][0] = "wDw";
            }
            if (isFigureDwPow6Col6Finished) {
                poniesChessBoard[5][5] = "XXX";
                poniesChessBoard[0][0] = "wDw";
            }
            if(isFigureDwPow1Col1Finished) {
                poniesChessBoard[0][0] = "XXX";
                poniesChessBoard[5][0] = "bWd";
            }
            if(isFigureDwPow1Col6Finished) {
                poniesChessBoard[0][5] = "XXX";
                poniesChessBoard[5][5] = "bWd";
            }

            while (playerIdAndTurnGiver <= maxTurns) {

                if (playerIdAndTurnGiver % 2 == 0) {
                    System.out.println("White Player's Turn");

                    donkey        = "wD";
                    king          = "wK";
                    queen         = "wQ";
                    dwarf         = "wDw";
                    mortar        = "wM";
                    currentPlayer = "White";
                    kingToKill    = "bK";

                    whitePlayerTurnsCounter++;
                }
                if (playerIdAndTurnGiver % 2 != 0) {
                    System.out.println("Black Player's Turn");

                    dwarf         = "bDw";
                    donkey        = "bD";
                    king          = "bK";
                    queen         = "bQ";
                    mortar        = "bM";
                    currentPlayer = "Black";
                    kingToKill    = "wK";

                    blackPlayerTurnsCounter++;
                }

                System.out.println("From which POW you want to MOVE your figure");
                figuresPowPosition = Integer.parseInt(scanner.nextLine());

                System.out.println("From which COLUMN you want to MOVE your figure");
                figuresColPosition = Integer.parseInt(scanner.nextLine());

                figuresPositionPow = figuresPowPosition - OFFSET_FOR_2D_ARRAY; // A variable for figure's col position
                figuresPositionCol = figuresColPosition - OFFSET_FOR_2D_ARRAY; // A variable for figure's pow position

                System.out.println("To which POW you want to PLACE your figure ");
                placementPow = Integer.parseInt(scanner.nextLine());

                System.out.println("To which COLUMN you want to PLACE your figure ");
                placementCol = Integer.parseInt(scanner.nextLine());

                figuresPlacementPow = placementPow - OFFSET_FOR_2D_ARRAY; // For where to put the figure
                figuresPlacementCol = placementCol - OFFSET_FOR_2D_ARRAY; // For where to put the figure

                    isThereYourColorFigures =
                            poniesChessBoard[figuresPlacementPow][figuresPlacementCol].equals("wDw")||
                            poniesChessBoard[figuresPlacementPow][figuresPlacementCol].equals("wD") ||
                            poniesChessBoard[figuresPlacementPow][figuresPlacementCol].equals("wQ") ||
                            poniesChessBoard[figuresPlacementPow][figuresPlacementCol].equals("wK") ||
                            poniesChessBoard[figuresPlacementPow][figuresPlacementCol].equals("wM") ||
                            poniesChessBoard[figuresPlacementPow][figuresPlacementCol].equals("wK");
                
                isDwarfMovingForward = figuresPositionCol + 1 == figuresPlacementPow;

                if (playerIdAndTurnGiver % 2 != 0) {
                    
                    isThereYourColorFigures =
                            poniesChessBoard[figuresPlacementPow][figuresPlacementCol].equals("bDw")||
                            poniesChessBoard[figuresPlacementPow][figuresPlacementCol].equals("bD") ||
                            poniesChessBoard[figuresPlacementPow][figuresPlacementCol].equals("bQ") ||
                            poniesChessBoard[figuresPlacementPow][figuresPlacementCol].equals("bK") ||
                            poniesChessBoard[figuresPlacementPow][figuresPlacementCol].equals("bM") ||
                            poniesChessBoard[figuresPlacementPow][figuresPlacementCol].equals("bK");
                    
                    isDwarfMovingForward = figuresPositionPow - 1 == figuresPlacementPow;
             }

                // Calculations if one figure is moveable
                isMortarMoveable = (Math.abs(figuresPlacementPow - figuresPositionPow)    == 0) &&
                                   (Math.abs(figuresPositionCol  - figuresPlacementCol)   == 1) ||
                                   (Math.abs(figuresPlacementPow - figuresPositionPow)    == 1) &&
                                   (Math.abs(figuresPositionCol  - figuresPlacementCol)   == 0) ;
                isQueenMoveable  = (Math.abs(figuresPositionPow  - figuresPlacementPow)   == 1) &&
                                   (Math.abs(figuresPositionCol  - figuresPlacementCol)   == 1);
                isDwarfMoveable  = Math.abs(figuresPlacementPow  - figuresPositionPow)    == 1  &&
                                   isDwarfMovingForward;
                isDonkeyMoveable =(Math.abs(figuresPlacementPow  - figuresPositionPow)    == 2) ||
                                  (Math.abs(figuresPlacementPow  - figuresPlacementCol)   == 2);
                isKingMoveable   = isMortarMoveable || isQueenMoveable;

                if (poniesChessBoard[figuresPlacementPow][figuresPlacementCol].equals(kingToKill)) {

                    isGameOver = true;
                    break;

                } else if (poniesChessBoard[figuresPositionPow][figuresPositionCol].equals(dwarf)) {
                    if (isDwarfMoveable) {
                        if(isThereYourColorFigures) {
                            System.out.println("\nSorry, your figure is there, try again");
                            break;
                        }
                        if (figuresPlacementPow == 5 && figuresPlacementCol == 0) {
                            isFigureDwPow6Col1Finished = true;
                        }
                        if (figuresPlacementPow == 5 && figuresPlacementCol == 5) {
                            isFigureDwPow6Col6Finished = true;
                        }
                        if(figuresPlacementPow  == 0 && figuresPlacementCol == 0) {
                            isFigureDwPow1Col1Finished = true;
                        }
                        if(figuresPlacementPow  == 0 && figuresPlacementCol == 5){
                            isFigureDwPow1Col6Finished = true;
                        }
                        poniesChessBoard[figuresPlacementPow][figuresPlacementCol] =
                                poniesChessBoard[figuresPositionPow][figuresPositionCol];

                        poniesChessBoard[figuresPositionPow][figuresPositionCol] = "XXX";
                        playerIdAndTurnGiver++;
                        break;
                    }
                } else if (poniesChessBoard[figuresPositionPow][figuresPositionCol].equals(mortar)) {

                    if (isMortarMoveable) {
                        if(isThereYourColorFigures) {
                            System.out.println("\nSorry, your figure is there, try again");
                            break;
                        }

                        poniesChessBoard[figuresPlacementPow][figuresPlacementCol] =
                                poniesChessBoard[figuresPositionPow][figuresPositionCol];

                        poniesChessBoard[figuresPositionPow][figuresPositionCol] = "XXX";
                        playerIdAndTurnGiver++;
                        break;
                    }
                } else if (poniesChessBoard[figuresPositionPow][figuresPositionCol].equals(queen)) {

                    if (isQueenMoveable) {
                        if(isThereYourColorFigures) {
                            System.out.println("\nSorry, your figure is there, try again");
                            break;
                        }

                        poniesChessBoard[figuresPlacementPow][figuresPlacementCol] =
                                poniesChessBoard[figuresPositionPow][figuresPositionCol];

                        poniesChessBoard[figuresPositionPow][figuresPositionCol] = "XXX";
                        playerIdAndTurnGiver++;
                        break;
                    }
                } else if (poniesChessBoard[figuresPositionPow][figuresPositionCol].equals(king)) {

                    if (isKingMoveable) {
                        if(isThereYourColorFigures) {
                            System.out.println("\nSorry, your figure is there, try again");
                            break;
                        }

                        poniesChessBoard[figuresPlacementPow][figuresPlacementCol] =
                                poniesChessBoard[figuresPositionPow][figuresPositionCol];

                        poniesChessBoard[figuresPositionPow][figuresPositionCol] = "XXX";
                        playerIdAndTurnGiver++;
                        break;
                    }
                } else if (poniesChessBoard[figuresPositionPow][figuresPositionCol].equals(donkey)) {

                    if (whitePlayerTurnsCounter % 3 == 0 || blackPlayerTurnsCounter % 3 == 0) {
                        if (isDonkeyMoveable) {
                            if(isThereYourColorFigures) {
                                System.out.println("\nSorry, your figure is there, try again");
                                break;
                            }
                            poniesChessBoard[figuresPlacementPow][figuresPlacementCol] =
                                    poniesChessBoard[figuresPositionPow][figuresPositionCol];

                            poniesChessBoard[figuresPositionPow][figuresPositionCol] = "XXX";
                            playerIdAndTurnGiver++;
                            break;
                        }
                    }
                }
                System.out.println("You can't do that");
            }
        }
        System.out.printf("Game Over!\n%s Player is the WINNER!!!", currentPlayer);
    }
}
