package com.wilsonramirez;

import java.util.Scanner;

/**
 * Main method of program
 * @author Wilson Ramirez
 */
public class Main {
    public static void main(String[] args) {
        final int[][] field = new int[3][3];

        play(field);
    }

    /**
     * Main game logic. Determines what game mode to play (Player vs Player. Player vs. CPU, CPU vs. CPU)
     * Determines which player is currently making a move
     * @param field 3x3 game field
     */
    public static void play(int[][] field) {

        Scanner scanner = new Scanner(System.in);
        String input;
        String player1;
        String player2;
        String[] commands;
        int currentPlayer = 1;
        boolean isValid;

        // Initial commands for beginning game
        // Parameters:
        // start [user or bot difficulty] [user or bot difficulty] - begins the game based on player types
        // exit - exits the program
        do {
            Alert.Information(1);
            input = scanner.nextLine();
            commands = input.split(" ");
        } while (!commands[0].equals("exit") && !checkParameters(commands));

        if (commands[0].equals("exit")) {
            System.exit(0);
        }

        player1 = commands[1];
        player2 = commands[2];

        Field.printField(field);

        // Continues to ask current player for input if that input is invalid
        // After valid input, switch current player
        while (true) {
            isValid = false;
            while (!isValid) {
                    input = switch (currentPlayer == 1 ? player1 : player2) {
                        case "user" -> {
                            Alert.Information(2);
                            yield scanner.nextLine();
                        }
                        case "easy" -> {
                            Alert.Information(3, "easy");
                            yield AI.easyDifficulty();
                        }
                        case "medium" -> {
                            Alert.Information(3, "medium");
                            yield AI.mediumDifficulty(field, currentPlayer, 1);
                        }
                        case "hard" -> {
                            Alert.Information(3, "hard");
                            yield AI.hardDifficulty(field, currentPlayer);
                        }
                        default -> input;
                    };
                if (Field.isValid(field, input, currentPlayer)) {
                    isValid = true;
                }
            }
            Field.printField(field);

            // Check if there is a matching row, or if all rows are filled, else switch to next player
            if (Field.checkMatchingRow(field, currentPlayer)) {
                Alert.Information(currentPlayer == 1 ? 4 : 5);
                break;
            } else if (Field.checkFilledField(field)) {
                Alert.Information(6);
                break;
            } else {
                currentPlayer = currentPlayer == 1 ? 2 : 1;
            }
        }

    }

    /**
     * Checks if initial command to start game is valid
     * @param parameters Input parameters
     * @return true if input is valid, else false
     */
    public static boolean checkParameters(String[] parameters) {
        if (parameters.length == 3) {
            if (parameters[0].equals("start")) {
                if ((parameters[1].equals("user") || parameters[1].equals("easy") || parameters[1].equals("medium") || parameters[1].equals("hard"))
                        && (parameters[2].equals("user") || parameters[2].equals("easy") || parameters[2].equals("medium") || parameters[2].equals("hard"))) {
                    return true;
                }
            }
        }
        Alert.Error(1);
        return false;
    }
}
