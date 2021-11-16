package edu.kit.informatik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import edu.kit.informatik.olympia.OlympicGames;

/**
 * The main class is the entry point of the olympic games administration and
 * archiving system.
 * 
 * @author Bjoern Holtvogt
 *
 */
public final class Main {

	/**
	 * Reads text from the "standard" input stream, buffering characters so as to
	 * provide for the efficient reading of characters, arrays, and lines. This
	 * stream is already open and ready to supply input data and corresponds to
	 * keyboard input.
	 */
	private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Private constructor to avoid object generation.
	 * 
	 * @deprecated Utility-class constructor.
	 */
	@Deprecated
	private Main() {

		throw new AssertionError("Utility class constructor.");
	}

	/**
	 * This is the program entry method main.
	 * 
	 * @param args Array of strings of the given command line arguments.
	 */
	public static void main(final String[] args) {

		OlympicGames olympicGames = new OlympicGames();
		Command command = null;
		do {
			try {
				command = Command.matchingCommand(IN.readLine(), olympicGames);
			} catch (InvalidInputException invalidInputException) {
				olympicGames.output("Error, " + invalidInputException.getMessage());
			} catch (NumberFormatException numberFormatException) {
				olympicGames.output("Error, input isn't equal to an integer.");
			} catch (IOException ioException) {
				throw new RuntimeException(ioException);
			}
		} while (command == null || command.isRunning());
	}
}
