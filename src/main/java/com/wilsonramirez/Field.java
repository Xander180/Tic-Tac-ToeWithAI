package com.wilsonramirez;

public class Field {
    private static final int[][] field = new int[3][3];

    public static void printField() {
        System.out.println("---------");
        for (int i = 0; i < field.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < field[i].length; j++) {
                switch (field[i][j]) {
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

    public void markField(String[] cells) {
        int currentCell = 0;

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                switch (cells[currentCell]) {
                    case "_":
                        break;
                    case "X":
                        setCell(j, i, 1);
                        break;
                    case "O":
                        setCell(j, i, 2);
                        break;
                }
                currentCell++;
            }
        }
    }

    public void setCell(int col, int row, int mark) {
        field[row][col] = mark;
    }

    public static boolean checkMatchingRow(int player) {
        return (field[0][0] == player && field[0][1] == player && field[0][2] == player)
                || (field[1][0] == player && field[1][1] == player && field[1][2] == player)
                || (field[2][0] == player && field[2][1] == player && field[2][2] == player)
                || (field[0][0] == player && field[1][0] == player && field[2][0] == player)
                || (field[0][0] == player && field[1][1] == player && field[2][2] == player)
                || (field[0][2] == player && field[1][1] == player && field[2][0] == player);
    }

    public static boolean checkFilledField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid(String input, int player) {
        String[] splitInput =input.split(" ");
        int[] coordinate;
        if (splitInput.length != 2) {
            System.out.println("You should enter numbers!");
            return false;
        }
        try {
            coordinate = new int[]{Integer.parseInt(splitInput[0]), Integer.parseInt(splitInput[1])};
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("You should enter numbers!");
            return false;
        }
        if (coordinate[0] < 1 || coordinate[0] > 3 || coordinate[1] < 1 || coordinate[1] > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }

        if (field[coordinate[0] - 1][coordinate[1] - 1] == 1 || field[coordinate[0] - 1][coordinate[1] - 1] == 2) {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }

        setCell(coordinate[1] - 1, coordinate[0] - 1, player);
        return true;
    }
}
