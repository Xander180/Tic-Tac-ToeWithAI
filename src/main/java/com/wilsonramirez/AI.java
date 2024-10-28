package com.wilsonramirez;

import java.util.Random;

/**
 * All logic for how AI/CPU/Bot makes its move
 */
public class AI {
    static Random random = new Random();

    /**
     * If AI is set to easy, always use this method
     * If AI is set to higher difficulty, use this method as a last resort
     * @return Random set of coordinates within the confines of the game field
     */
    public static String easyDifficulty() {
        return (random.nextInt(3) + 1) + " " + (random.nextInt(3) + 1);
    }

    /**
     * If AI has 2 in a row and can win with 1 further move, it will do so
     * Else, if the opponent can win with 1 move, it will attempt to block opponent
     * Else, revert to easy difficulty (pick a random spot)
     * @param field Game field
     * @param player Current player
     * @param attempt If attempt == 1, AI will attempt to win. If attempt == 2, AI will attempt to block
     * @return Appropriate set of coordinates based on current conditions
     */
    public static String mediumDifficulty(int[][] field, int player, int attempt) {
        int playerCellCount = 0;
        int nonPlayerCellCount = 0;
        int[] coordinate = new int[2];

        // Check rows
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == player) {
                    playerCellCount++;
                } else {
                    nonPlayerCellCount++;
                    coordinate[0] = i;
                    coordinate[1] = j;
                }
            }
            if (playerCellCount == 2 && nonPlayerCellCount == 1) {
                if (field[coordinate[0]][coordinate[1]] == 0) {
                    return (coordinate[0] + 1) + " " + (coordinate[1] + 1);
                }
            }
            playerCellCount = 0;
            nonPlayerCellCount = 0;
        }

        // Check columns
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[j][i] == player) {
                    playerCellCount++;
                } else {
                    nonPlayerCellCount++;
                    coordinate[0] = j;
                    coordinate[1] = i;
                }
            }
            if (playerCellCount == 2 && nonPlayerCellCount == 1) {
                if (field[coordinate[0]][coordinate[1]] == 0) {
                    return (coordinate[0] + 1) + " " + (coordinate[1] + 1);
                }
            }
            playerCellCount = 0;
            nonPlayerCellCount = 0;
        }

        // Check diagonals left -> right
        for (int i = 0; i < field.length; i++) {
            if (field[i][i] == player) {
                playerCellCount++;
            } else {
                nonPlayerCellCount++;
                coordinate[0] = i;
                coordinate[1] = i;
            }
        }
        if (playerCellCount == 2 && nonPlayerCellCount == 1) {
            if (field[coordinate[0]][coordinate[1]] == 0) {
                return (coordinate[0] + 1) + " " + (coordinate[1] + 1);
            }
        }
        playerCellCount = 0;
        nonPlayerCellCount = 0;

        // Check diagonals right -> left
        int num = field.length - 1;
        for (int i = 0; i < field.length; i++) {
            if (field[i][num] == player) {
                playerCellCount++;
            } else {
                nonPlayerCellCount++;
                coordinate[0] = i;
                coordinate[1] = num;
            }
            num--;
        }
        if (playerCellCount == 2 && nonPlayerCellCount == 1) {
            if (field[coordinate[0]][coordinate[1]] == 0) {
                return (coordinate[0] + 1) + " " + (coordinate[1] + 1);
            }
        }

        // Recurses method, this time to try to block opponent
        if (attempt == 1) {
            return mediumDifficulty(field, player == 1 ? 2 : 1, 2);
        }

        // Reverts to picking a random location if win/block attempts fail
        return easyDifficulty();
    }
}
