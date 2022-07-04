package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        String fieldSring = "         ";
        boolean haveWinner = false;
        boolean xMove = true;

        //drawing the first grid
        drawGrid(fieldSring);

        boolean haveCoordinates;
        int firstCoordinate = 0;
        int secondCoordinate = 0;

        char[][] fieldArray = new char[3][3];
        updateFieldArray(fieldSring, fieldArray);

        do {
            do {
                System.out.print("Enter the coordinates: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("You should enter numbers!");
                    haveCoordinates = false;
                    scanner.nextLine();
                } else {
                    firstCoordinate = scanner.nextInt();
                    if (!scanner.hasNextInt()) {
                        System.out.println("You should enter numbers!");
                        haveCoordinates = false;
                        scanner.nextLine();
                        continue;
                    } else {
                        secondCoordinate = scanner.nextInt();
                    }

                    if (firstCoordinate < 1 || firstCoordinate > 3 || secondCoordinate < 1 || secondCoordinate > 3) {
                        System.out.println("Coordinates should be from 1 to 3!");
                        haveCoordinates = false;
                        scanner.nextLine();
                    } else if (fieldArray[firstCoordinate - 1][secondCoordinate - 1] != ' ') {
                        System.out.println("This cell is occupied! Choose another one!");
                        haveCoordinates = false;
                        scanner.nextLine();
                    } else {
                        haveCoordinates = true;
                    }
                }
            } while (!haveCoordinates);

            if (firstCoordinate == 1) {
                if (xMove) {
                    fieldSring = fieldSring.substring(0, secondCoordinate - 1) + 'X' + fieldSring.substring(secondCoordinate);
                    updateFieldArray(fieldSring, fieldArray);
                    xMove = false;
                } else {
                    fieldSring = fieldSring.substring(0, secondCoordinate - 1) + 'O' + fieldSring.substring(secondCoordinate);
                    updateFieldArray(fieldSring, fieldArray);
                    xMove = true;
                }
                drawGrid(fieldSring);
            }

            if (firstCoordinate == 2) {
                if (xMove) {
                    fieldSring = fieldSring.substring(0, 2 + secondCoordinate) + 'X' + fieldSring.substring(3 + secondCoordinate);
                    updateFieldArray(fieldSring, fieldArray);
                    xMove = false;
                } else {
                    fieldSring = fieldSring.substring(0, 2 + secondCoordinate) + 'O' + fieldSring.substring(3 + secondCoordinate);
                    updateFieldArray(fieldSring, fieldArray);
                    xMove = true;
                }
                drawGrid(fieldSring);
            }

            if (firstCoordinate == 3) {
                if (xMove) {
                    fieldSring = fieldSring.substring(0, 5 + secondCoordinate) + 'X' + fieldSring.substring(6 + secondCoordinate);
                    updateFieldArray(fieldSring, fieldArray);
                    xMove = false;
                } else {
                    fieldSring = fieldSring.substring(0, 5 + secondCoordinate) + 'O' + fieldSring.substring(6 + secondCoordinate);
                    updateFieldArray(fieldSring, fieldArray);
                    xMove = true;
                }
                drawGrid(fieldSring);
            }



            //checking for possibility
            int xCounter = 0;
            int oCounter = 0;
            for (int i = 0; i < fieldSring.length(); i++) {
                if (fieldSring.charAt(i) == 'X') {
                    xCounter++;
                    continue;
                }
                if (fieldSring.charAt(i) == 'O') {
                    oCounter++;
                }
            }
            int diff = xCounter - oCounter;
            if (diff < 0) {
                diff = diff * -1;
            }
            if (diff >= 2) {
                System.out.println("Impossible");
                haveWinner = true;
            }

            //finding the winner
            if (!haveWinner) {
                int[][] winCombination = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
                char winner = ' ';
                int winnerCounter = 0;
                for (int[] ints : winCombination) {
                    char currentValue = ' ';
                    int counter = 0;
                    for (int anInt : ints) {
                        if (fieldSring.charAt(ints[0]) == 'X' || fieldSring.charAt(ints[0]) == 'O') {
                            currentValue = fieldSring.charAt(ints[0]);
                        } else {
                            break;
                        }
                        if (fieldSring.charAt(anInt) == currentValue) {
                            counter++;
                        }
                    }
                    if (counter == 3) {
                        winnerCounter++;
                        winner = currentValue;
                        haveWinner = true;
                    }
                }
                if (winnerCounter == 1) {
                    System.out.println(winner + " wins");
                } else if (winnerCounter > 1) {
                    System.out.println("Impossible");
                }
            }

            //checking if game is finished
            if (!haveWinner) {
                boolean isFinished = true;
                for (int i = 0; i < fieldSring.length() - 1; i++) {
                    if (fieldSring.charAt(i) == ' ') {
                        isFinished = false;
                        break;
                    }
                }
                if (isFinished) {
                    haveWinner = true;
                    System.out.println("Draw");
                }
            }
        } while (!haveWinner);
    }

    private static void updateFieldArray(String fieldSring, char[][] fieldArray) {
        int index = 0;
        for (int i = 0; i < fieldArray.length; i++) {
            for (int j = 0; j < fieldArray[i].length; j++) {
                fieldArray[i][j] = fieldSring.charAt(index);
                index++;
            }
        }
    }

    private static void drawGrid(String input) {
        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == 0 || i == 4) {
                    if (j == 8) {
                        System.out.println("-");
                    } else {
                        System.out.print("-");
                    }
                    continue;
                }
                if (j == 0) {
                    System.out.print("|");
                    continue;
                }
                if (j == 8) {
                    System.out.println("|");
                    continue;
                }
                if (j == 2 || j == 4 || j == 6) {
                    System.out.print(input.charAt(index));
                    index++;
                    continue;
                }
                System.out.print(" ");
            }
        }
    }
}
