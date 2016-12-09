package com.company;

import java.io.*;
import java.util.Scanner;

public class TicTacToe{
    private char[][] board;
    private char player; // 'X' or 'O'

    /*
     * Instantiate board to be a 3 by 3 char array of spaces.
     * Set player to be 'X'.
     */
    public TicTacToe() {
		/*
		* Step 1: create an empty board, with an initial value
		* of a space (' ')
		*/
		board = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }

    }

    /*
     * If s represents a valid move, add the current player's symbol to the board and return true.
     * Otherwise return false.
     */
    public boolean play(String s) {
		/* Step 2: Fill in here with your own
		* play logic, and replace the return with you
		* own.
		*/
        int row;
        int col;
        char firstCoor;
        char secondCoor;

        if (s.length() != 2) {
            // Only valid when the length of the string is 2
            System.out.println("Please enter an valid coordinate, such as A1");
            return false;
        } else {
            firstCoor = s.charAt(0);
            secondCoor = s.charAt(1);
        }
        // check the first coordinate
        switch (firstCoor) {
            case 'A':
                row = 0;
                break;
            case 'B':
                row = 1;
                break;
            case 'C':
                row = 2;
                break;
            default:
                System.out.println("Please enter A, B or C for the first coordinate");
                return false;
        }
        // check the second coordinate
        switch (secondCoor) {
            case '1':
                col = 0;
                break;
            case '2':
                col = 1;
                break;
            case '3':
                col = 2;
                break;
            default:
                System.out.println("Please enter 1, 2 or 3 for the second coordinate");
                return false;
        }
        // check if the spot has already been filled
        if (board[row][col] == ' '){
            // fill the board with the player's
            board[row][col] = player;
            // Only by then, return true
            return true;
        } else {
            System.out.println("Please fill only the empty spots");
        }
        return false;
    }

    /*
     * Switches the current player from X to O, or O to X.
     */
    public void switchTurn() {
        // Step 3: Fill in with your code to toggle between
        // 'X' and 'O'
        // for the first turn, 'O' goes first
        player = player =='X' ? 'O' : 'X';
    }

    /*
     * Returns true if the current player has won the game.
     * Three in a row, column or either diagonal.
     * Otherwise, return false.
     */
    public boolean won() {
		/* Step 5: Fill in the code for the won method. This method
        * should return true if the current player has 3 in-a-row
		* in any row, column or diagonal. Otherwise, return false.
		*/
        for (int i = 0; i < 3; i++) {
            if (checkInRow(board[i][0], board[i][1], board[i][2])
                    ||checkInRow(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }

        return ((checkInRow(board[0][0], board[1][1], board[2][2]))
                || (checkInRow(board[0][2], board[1][1], board[2][0])));
        //return false; // TODO: replace with your own return statement.
    }

    private boolean checkInRow(char c1, char c2, char c3) {
        return ((c1 != ' ') && (c1 == c2) && (c2 == c3));
    }
    /*
     * Returns true if there are no places left to move
     */
    public boolean stalemate() {
	    /*
		 * Step 4: Fill in the code for the stalemate method. It
         * should return true if there are no more places to move
		 * on the board. Otherwise, return false return false;
		 */
        //return true;   // replace with your own return
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
    public char getPlayer() {
        return player;
    }

    public void print() {
        System.out.println();
        System.out.println("\t  1 2 3");
        System.out.println();
        System.out.println("\tA "+board[0][0]+"|"+board[0][1]+"|"+board[0][2]);
        System.out.println("\t  -----");
        System.out.println("\tB "+board[1][0]+"|"+board[1][1]+"|"+board[1][2]);
        System.out.println("\t  "+"-----");
        System.out.println("\tC "+board[2][0]+"|"+board[2][1]+"|"+board[2][2]);
        System.out.println();
    }

	/*
	 * Step 6: Main Method for Final Step - Delete your main method
	 * and uncomment this one.
	 * Runs the game by getting input from the user, making the
	 * appropriate moves, and prints who won or if it was a stalemate.
	*/

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        TicTacToe game = new TicTacToe();
        System.out.println("Welcome to tic-tac-toe");
        System.out.println("Enter coordinates for your move following the X and O prompts");
        // Starts with 'X'
        game.player = 'X';
        String input = new String();

        while(!game.stalemate()) {
            //Print the game
            game.print();
            //Prompt player for their move
            //Loop while the method play does not return true when given their move.
            //Body of loop should ask for a different move
            boolean coorValid = false;

            while (!coorValid){
                input =  in.next();
                coorValid = game.play(input);
                // Print the input string
                System.out.println(game.getPlayer()+":"+ input);

                // Ask to re-enter the coordinates if they are invalid
                if (!coorValid) {
                    System.out.println("Enter coordinates for your move following the X and O prompts");
                    in = new Scanner(System.in);
                }
            }
            //If the game is won, call break;
            if(game.won()) break;

            //Switch the turn
            game.switchTurn();
        }
        game.print();
        if(game.won()){
            System.out.println("Player "+game.getPlayer()+" Wins!!!!");
        } else {
            System.out.println("Stalemate");
        }
    }
}