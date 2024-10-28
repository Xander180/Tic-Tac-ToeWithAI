package com.wilsonramirez;

/**
 * Repository for all alert messages
 * @author Wilson Ramirez
 */
public class Alert {

    /**
     * All informational messages
     * @param type Alert code
     * @param difficulty Difficulty of AI if current player is AI
     */
    public static void Information(int type, String... difficulty) {
        switch (type) {
            case 1:
                System.out.print("Input command: ");
                break;
            case 2:
                System.out.print("Enter the coordinates: ");
                break;
            case 3:
                System.out.print("Making move level " + '"' + difficulty[0] + '"' + "\n");
                break;
            case 4:
                System.out.println("X wins");
                break;
            case 5:
                System.out.println("O wins");
                break;
            case 6:
                System.out.println("Draw");

        }
    }

    /**
     * All error messages
     * @param type Alert code
     */
    public static void Error(int type) {
        switch (type) {
            case 1:
                System.out.println("Bad parameters!");
            case 2:
                System.out.println("You should enter numbers!");
            case 3:
                System.out.println("Coordinates should be from 1 to 3!");
            case 4:
                System.out.println("This cell is occupied! Choose another one!");
        }
    }
}
