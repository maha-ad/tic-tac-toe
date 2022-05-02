package com.company;

import java.util.*;

public class Main {

    public static List<Integer> playerPlacements = new ArrayList<>();
    public static List<Integer> cpuPlacements = new ArrayList<>();

    public static void main(String[] args) {

        char[][] gameBoard = {{' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'}, {' ', '|', ' ', '|', ' '}};

        // display game board before playing to see how it looks like
        printGameBoard(gameBoard);

        while (true) {
            System.out.println("Enter a number (1-9)");
            Scanner scanner = new Scanner(System.in);
            // player's turn to play
            int playerPos = scanner.nextInt();
            // if the number is already taken before, choose another one
            while (playerPlacements.contains(playerPos) || cpuPlacements.contains(playerPos)) {
                System.out.println("number is taken, try another one");
                playerPos = scanner.nextInt();
            }

            // player's placements
            placePieces(gameBoard, playerPos, "USER");

            Random random = new Random();
            int cpuPos = random.nextInt(9) + 1;

            while (playerPlacements.contains(cpuPos) || cpuPlacements.contains(cpuPos)) {
                cpuPos = random.nextInt(9) + 1;
            }

            // cpu placements
            placePieces(gameBoard, cpuPos, "CPU");

            // show game board how it looks like after playing every shot of the game
            printGameBoard(gameBoard);

            // to announce who is the winner
            String result = checkTheWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }
        }
    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePieces(char[][] gameBoard, int pos, String player) {
        char symbol = ' ';

        if (player.equals("USER")) {
            symbol = 'X';
            playerPlacements.add(pos);
        } else if (player.equals("CPU")) {
            symbol = 'O';
            cpuPlacements.add(pos);
        }

        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;

        }

    }

    public static String checkTheWinner() {
        List<Integer> topRow    = Arrays.asList(1, 2, 3);
        List<Integer> midRow    = Arrays.asList(4, 5, 6);
        List<Integer> bottomRow = Arrays.asList(7, 8, 9);
        List<Integer> leftCol   = Arrays.asList(1, 4, 7);
        List<Integer> midCol    = Arrays.asList(2, 5, 8);
        List<Integer> rightCol  = Arrays.asList(3, 6, 9);
        List<Integer> crossX    = Arrays.asList(1, 5, 9);
        List<Integer> crossY    = Arrays.asList(7, 5, 3);

        List<List> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(bottomRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(crossX);
        winning.add(crossY);

        for (List list : winning) {
            if (playerPlacements.containsAll(list)) {
                return "Congratulations you WON ;)";
            } else if (cpuPlacements.containsAll(list)) {
                return "Sorry ;( you lose";
            } else if (cpuPlacements.size() + playerPlacements.size() == 9) {
                return "Sub-zero game ;)";
            }
        }

        return "";
    }
}
