package com.project.TicTacToeSolution;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class TictacToe {

	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	static String playerTurn;

	static String[] boardBoxes = new String[9];

	static String winner = null;

	static Consumer<String> print = m -> System.out.println(m);

	public static void main(String[] args) {

		playTicTacToe();

	}

	private static void playTicTacToe() {

		boardBoxes = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" };

		playerTurn = "X";

		placeTheNumber(boardBoxes);

		print.accept("Player X will play first. Please select any number that you would like to place in::");

		Scanner scanner = new Scanner(System.in);

		while (winner == null) {

			validatePlayerInput(scanner);

			placeTheNumber(boardBoxes);

			nextPlayerTurn();

			anyRowOrColumnDiagonalLineFilled(boardBoxes);

			successMessageOrNextPlay();
		}

	}

	protected static void validatePlayerInput(Scanner scanner) {
		int inputNumber = scanner.nextInt();

		if (!(inputNumber > 0 && inputNumber < 10)) {
			print.accept("Invalid Input..!!Please select any number from 1 to 9.");
		} else {
			try {
				if (boardBoxes[inputNumber - 1].equals(String.valueOf(inputNumber))) {
					boardBoxes[inputNumber - 1] = playerTurn;
				}
			} catch (ArrayIndexOutOfBoundsException exception) {
				print.accept("Invalid Input..!!Please enter a valid nubmer.");
			}
		}
	}

	protected static void successMessageOrNextPlay() {
		if (winner == "X" || winner == "O") {
			print.accept("Congrats..!! You-" + winner + " have won the game..Thanks for playing");
		} else if (winner == "draw") {
			print.accept("It is a draw...Thanks for playing.");
		}

		if (winner == null) {
			if (playerTurn.equals("O")) {
				print.accept("Player O's turn. Please enter the number that you would like to place in::");
			} else {
				print.accept("Player X's turn. Please enter the number that you would like to place in::");
			}
		}
	}

	protected static void nextPlayerTurn() {
		if (null != playerTurn && playerTurn.equals("X")) {
			playerTurn = "O";
		} else if (null != playerTurn && playerTurn.equals("O")) {
			playerTurn = "X";
		}
	}

	protected static void anyRowOrColumnDiagonalLineFilled(String[] boardBoxes) {
		IntStream.range(0, 8).forEach(i -> {
			String line = null;
			switch (i) {
			case 0:
				line = boardBoxes[0] + boardBoxes[1] + boardBoxes[2];
				break;
			case 1:
				line = boardBoxes[3] + boardBoxes[4] + boardBoxes[5];
				break;
			case 2:
				line = boardBoxes[6] + boardBoxes[7] + boardBoxes[8];
				break;
			case 3:
				line = boardBoxes[0] + boardBoxes[3] + boardBoxes[6];
				break;
			case 4:
				line = boardBoxes[1] + boardBoxes[4] + boardBoxes[7];
				break;
			case 5:
				line = boardBoxes[2] + boardBoxes[5] + boardBoxes[8];
				break;
			case 6:
				line = boardBoxes[0] + boardBoxes[4] + boardBoxes[8];
				break;
			case 7:
				line = boardBoxes[2] + boardBoxes[4] + boardBoxes[6];
				break;
			}

			findWinner(boardBoxes, line);

		});
	}

	protected static void findWinner(String[] boardBoxes, String line) {
		if (line.equals("XXX")) {
			winner = "X";
		} else if (line.equals("OOO")) {
			winner = "O";
		}

		for (int a = 0; a < 9; a++) {
			if (Arrays.asList(boardBoxes).contains(String.valueOf(a + 1))) {
				break;
			} else if (a == 8) {
				winner = "draw";
			}
		}
	}

	protected static void placeTheNumber(String[] boardBoxes) {
		print.accept(boardBoxes[0] + " | " + boardBoxes[1] + " | " + boardBoxes[2]);

		print.accept("---------");

		print.accept(boardBoxes[3] + " | " + boardBoxes[4] + " | " + boardBoxes[5]);

		print.accept("---------");

		print.accept(boardBoxes[6] + " | " + boardBoxes[7] + " | " + boardBoxes[8]);

	}
}
