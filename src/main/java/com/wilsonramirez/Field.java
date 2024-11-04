package com.wilsonramirez;

/**
 * All logic for printing/checking/marking field
 * Checks for valid coordinates
 */
public class Field {
    /**
     * Prints the current game board
     * @param field Game field
     */
    public static void printField(int[][] field) {
        System.out.println("---------");
        for (int[] ints : field) {
            System.out.print("| ");
            for (int anInt : ints) {
                switch (anInt) {
                    case 0:
                        System.out.print("  ");
                        break;
                    case 1:
                        System.out.print("X ");
                        break;
                    case 2:
                        System.out.print("O ");
                        break;
                }
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    /**
     * Mark current coordinate (X or O)
     * @param field Game field
     * @param col Column
     * @param row Row
     * @param mark Current player's mark (X or O)
     */
    public static void setCell(int[][] field, int col, int row, int mark) {
        field[row][col] = mark;
    }

    /**
     * Checks if there is a row of 3 matching sets of X's or O's
     * @param field Game field
     * @param player Current player to check
     * @return True if there is a matching set, else false
     */
    public static boolean checkMatchingRow(int[][] field, int player) {
        return (field[0][0] == player && field[0][1] == player && field[0][2] == player)
                || (field[1][0] == player && field[1][1] == player && field[1][2] == player)
                || (field[2][0] == player && field[2][1] == player && field[2][2] == player)
                || (field[0][0] == player && field[1][0] == player && field[2][0] == player)
                || (field[0][1] == player && field[1][1] == player && field[2][1] == player)
                || (field[0][2] == player && field[1][2] == player && field[2][2] == player)
                || (field[0][0] == player && field[1][1] == player && field[2][2] == player)
                || (field[0][2] == player && field[1][1] == player && field[2][0] == player);
    }

    /**
     * Checks if all cells in field have been filled
     * @param field Game field
     * @return True if all cells are filled, else false
     */
    public static boolean checkFilledField(int[][] field) {
        for (int[] ints : field) {
            for (int anInt : ints) {
                if (anInt == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if the current player's input is valid1
     * @param field Game field
     * @param input Current player's input
     * @param player Current player
     * @return True if input is valid, else false
     */
    public static boolean isValid(int[][] field, String input, int player) {
        String[] splitInput =input.split(" ");
        int[] coordinate;
        if (splitInput.length != 2) {
            Alert.Error(2);
            return false;
        }
        try {
            coordinate = new int[]{Integer.parseInt(splitInput[0]), Integer.parseInt(splitInput[1])};
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            Alert.Error(2);
            return false;
        }
        if ((coordinate[0] < 1 || coordinate[0] > 3) || (coordinate[1] < 1 || coordinate[1] > 3)) {
            Alert.Error(3);
            return false;
        }

        if (field[coordinate[0] - 1][coordinate[1] - 1] == 1 || field[coordinate[0] - 1][coordinate[1] - 1] == 2) {
            Alert.Error(4);
            return false;
        }

        setCell(field, coordinate[1] - 1, coordinate[0] - 1, player);
        return true;
    }
}
