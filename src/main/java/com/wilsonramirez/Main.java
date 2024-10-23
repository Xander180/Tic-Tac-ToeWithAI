package com.wilsonramirez;

import java.util.Random;
import java.util.Scanner;

import static com.wilsonramirez.Field.*;

public class Main {
    public static void main(String[] args) {
        Field field = new Field();

        play(field);
    }

    public static void play(Field field) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String input;
        String player1;
        String player2;
        String[] commands;
        int currentPlayer = 1;
        boolean isValid;

        do {
            System.out.print("Input command: ");
            input = scanner.nextLine();
            commands = input.split(" ");
        } while (!commands[0].equals("exit") && !checkParameters(commands));

        if (commands[0].equals("exit")) {
            System.exit(0);
        }

        player1 = commands[1];
        player2 = commands[2];

        printField();

        while (true) {
            isValid = false;
            while (!isValid) {
                if (currentPlayer == 1) {
                    System.out.print(player1.equals("user") ? "Enter the coordinates: "
                            : "Making move level " + '"' + "easy" + '"' + "\n");
                    input = player1.equals("user") ? scanner.nextLine() : (random.nextInt(3) + 1) + " "
                            + (random.nextInt(3) + 1);
                } else {
                    System.out.print(player2.equals("user") ? "Enter the coordinates: "
                            : "Making move level " + '"' + "easy" + '"' + "\n");
                    input = player2.equals("user") ? scanner.nextLine() : (random.nextInt(3) + 1) + " "
                            + (random.nextInt(3) + 1);
                }
                if (field.isValid(input, currentPlayer)) {
                    isValid = true;
                }
            }
            printField();
            if (checkMatchingRow(currentPlayer)) {
                System.out.println(currentPlayer == 1 ? "X wins" : "O wins");
                break;
            } else if (checkFilledField()) {
                System.out.println("Draw");
                break;
            } else {
                currentPlayer = currentPlayer == 1 ? 2 : 1;
            }
        }

    }


    public static boolean checkParameters(String[] parameters) {
        if (parameters.length == 3) {
            if (parameters[0].equals("start")) {
                return (parameters[1].equals("user") || parameters[1].equals("easy"))
                        && (parameters[2].equals("user") || parameters[2].equals("easy"));
            }
        }
        System.out.println("Bad parameters!");
        return false;
    }
}
