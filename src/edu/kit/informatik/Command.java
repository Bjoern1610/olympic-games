package edu.kit.informatik;

import edu.kit.informatik.exceptions.InvalidInputException;
import edu.kit.informatik.olympia.OlympicGames;
import java.util.regex.Pattern;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;

/**
 * This class implements all necessary commands for the olympic games
 * administration.
 * 
 * @author Björn Holtvogt
 *
 */
public enum Command {

	/**
	 * Implementation of the command "add-admin" as mentioned in the task.
	 */
	// add-admin <forename>;<surname>;<username>;<password>
	CMD_ADD_ADMIN("add-admin ([^;]+);([^;]+);([^;]{4,8});([^;]{8,12})") {

		@Override
		public void commandMethod(final MatchResult matcher, final OlympicGames olympicGames)
				throws InvalidInputException {

			if (!olympicGames.isLoggedIn()) {
				String foreName = matcher.group(1);
				String surName = matcher.group(2);
				String userName = matcher.group(3);
				String passWord = matcher.group(4);
				olympicGames.output(olympicGames.addAdmin(foreName, surName, userName, passWord));
			} else {
				throw new InvalidInputException("unexecutable, if an admin is logged in.");
			}
		}
	},

	/**
	 * Implementation of the command "login-admin" as mentioned in the task.
	 */
	// login-admin <username>;<password>
	CMD_LOGIN_ADMIN("login-admin ([^;]{4,8});([^;]{8,12})") {

		@Override
		public void commandMethod(final MatchResult matcher, final OlympicGames olympicGames)
				throws InvalidInputException {

			if (!olympicGames.isLoggedIn()) {
				String userName = matcher.group(1);
				String passWord = matcher.group(2);
				olympicGames.output(olympicGames.loginAdmin(userName, passWord));
			} else {
				throw new InvalidInputException("unexecutable, if an admin is logged in.");
			}
		}
	},

	/**
	 * Implementation of the command "logout-admin" as mentioned in the task.
	 */
	// logout-admin
	CMD_LOGOUT_ADMIN("logout-admin") {

		@Override
		public void commandMethod(final MatchResult matcher, final OlympicGames olympicGames)
				throws InvalidInputException {

			olympicGames.output(olympicGames.logoutAdmin());
		}
	},

	/**
	 * Implementation of the command "add-sports-venue" as mentioned in the task.
	 */
	// add-sports-venue <id>;<country name>;<location>;<name>;<year of
	// opening>;<amount of seats>
	CMD_ADD_SPORTS_VENUE("add-sports-venue (00[1-9]|0[1-9]\\d|[1-9]\\d\\d);" + "([^;]+);([^;]+);([^;]+);"
			+ "(\\d\\d\\d\\d);(\\d+)") {

		@Override
		public void commandMethod(final MatchResult matcher, final OlympicGames olympicGames)
				throws InvalidInputException {

			if (olympicGames.isLoggedIn()) {
				String venueID = matcher.group(1);
				String countryName = matcher.group(2);
				String location = matcher.group(3);
				String venueName = matcher.group(4);
				int yearOfOpening = Integer.parseInt(matcher.group(5));
				int amountOfSeats = Integer.parseInt(matcher.group(6));
				olympicGames.output(olympicGames.addSportsVenue(venueID, countryName, location, venueName,
						yearOfOpening, amountOfSeats));
			} else {
				throw new InvalidInputException("unexecutable, if no admin is logged in.");
			}
		}
	},

	/**
	 * Implementation of the command "list-sports-venues" as mentioned in the task.
	 */
	// list-sports-venues <country name>
	CMD_LIST_SPORTS_VENUES("list-sports-venues ([^;]+)") {

		@Override
		public void commandMethod(final MatchResult matcher, final OlympicGames olympicGames)
				throws InvalidInputException {

			if (olympicGames.isLoggedIn()) {
				String countryName = matcher.group(1);
				olympicGames.listSportsVenues(countryName);
			} else {
				throw new InvalidInputException("unexecutable, if no admin is logged in.");
			}
		}
	},

	/**
	 * Implementation of the command "add-olympic-sport" as mentioned in the task.
	 */
	// add-olympic-sport <kind of sport>;<sport discipline>
	CMD_ADD_OLYMPIC_SPORT("add-olympic-sport ([^;]+);([^;]+)") {

		@Override
		public void commandMethod(final MatchResult matcher, final OlympicGames olympicGames)
				throws InvalidInputException {

			if (olympicGames.isLoggedIn()) {
				String sport = matcher.group(1);
				String discipline = matcher.group(2);
				olympicGames.output(olympicGames.addOlympicSport(sport, discipline));
			} else {
				throw new InvalidInputException("unexecutable, if no admin is logged in.");
			}
		}
	},

	/**
	 * Implementation of the command "list-olympic-sports" as mentioned in the task.
	 */
	// list-olympic-sports
	CMD_LIST_OLYMPIC_SPORTS("list-olympic-sports") {

		@Override
		public void commandMethod(final MatchResult matcher, final OlympicGames olympicGames)
				throws InvalidInputException {

			if (olympicGames.isLoggedIn()) {
				olympicGames.listOlympicSports();
			} else {
				throw new InvalidInputException("unexecutable, if no admin is logged in.");
			}
		}
	},

	/**
	 * Implementation of the command "add-ioc-code" as mentioned in the task.
	 */
	// add-ioc-code <ioc-id>;<ioc-code>;<country name>;<year of determination>
	CMD_ADD_IOC_CODE("add-ioc-code (00[1-9]|0[1-9]\\d|[1-9]\\d\\d);([a-z]{3});([^;]+);"
			+ "(000[1-9]|00[1-9]\\d|0[1-9]\\d\\d|[1-9]\\d\\d\\d)") {

		@Override
		public void commandMethod(final MatchResult matcher, final OlympicGames olympicGames)
				throws InvalidInputException {

			if (olympicGames.isLoggedIn()) {
				String iocID = matcher.group(1);
				String iocCode = matcher.group(2);
				String countryName = matcher.group(3);
				String yearOfDetermination = matcher.group(4);
				olympicGames.output(olympicGames.addIocCode(iocID, iocCode, countryName, yearOfDetermination));
			} else {
				throw new InvalidInputException("unexecutable, if no admin is logged in.");
			}
		}
	},

	/**
	 * Implementation of the command "list-ioc-codes" as mentioned in the task.
	 */
	// list-ioc-codes
	CMD_LIST_IOC_CODES("list-ioc-codes") {

		@Override
		public void commandMethod(final MatchResult matcher, final OlympicGames olympicGames)
				throws InvalidInputException {

			if (olympicGames.isLoggedIn()) {
				olympicGames.listIocCodes();
			} else {
				throw new InvalidInputException("unexecutable, if no admin is logged in.");
			}
		}
	},

	/**
	 * Implementation of the command "add-athlete" as mentioned in the task.
	 */
	// add-athlete <id>;<forename>;<surname>;<country of origin>;<kind of
	// sport>;<sport discipline>
	CMD_ADD_ATHLETE("add-athlete (000[1-9]|00[1-9]\\d|0[1-9]\\d\\d|[1-9]\\d\\d\\d);"
			+ "([^;]+);([^;]+);([^;]+);([^;]+);([^;]+)") {

		@Override
		public void commandMethod(final MatchResult matcher, final OlympicGames olympicGames)
				throws InvalidInputException {

			if (olympicGames.isLoggedIn()) {
				String athleteID = matcher.group(1);
				String foreName = matcher.group(2);
				String surName = matcher.group(3);
				String countryOfOrigin = matcher.group(4);
				String sport = matcher.group(5);
				String discipline = matcher.group(6);
				olympicGames.output(
						olympicGames.addAthlete(athleteID, foreName, surName, countryOfOrigin, sport, discipline));
			} else {
				throw new InvalidInputException("unexecutable, if no admin is logged in.");
			}
		}
	},

	/**
	 * Implementation of the command "summary-athletes" as mentioned in the task.
	 */
	// summary-athletes <kind of sport>;<sport discipline>
	CMD_SUMMARY_ATHLETES("summary-athletes ([^;]+);([^;]+)") {

		@Override
		public void commandMethod(final MatchResult matcher, final OlympicGames olympicGames)
				throws InvalidInputException {

			if (olympicGames.isLoggedIn()) {
				String sport = matcher.group(1);
				String discipline = matcher.group(2);
				olympicGames.summaryAthlete(sport, discipline);
			} else {
				throw new InvalidInputException("unexecutable, if no admin is logged in.");
			}
		}
	},

	/**
	 * Implementation of the command "add-competition" as mentioned in the task.
	 */
	// add-competition <id>;<year>;<country name>;<kind of sport>;<sport
	// discipline>;<gold>;<silver>;<bronze>
	CMD_ADD_COMPETITION("add-competition (000[1-9]|00[1-9]\\d|0[1-9]\\d\\d|[1-9]\\d\\d\\d);"
			+ "([1-9]\\d\\d\\d);([^;]+);([^;]+);([^;]+);(0|1);(0|1);(0|1)") {

		@Override
		public void commandMethod(final MatchResult matcher, final OlympicGames olympicGames)
				throws InvalidInputException {

			if (olympicGames.isLoggedIn()) {
				String athleteID = matcher.group(1);
				int participationYear = Integer.parseInt(matcher.group(2));
				String countryName = matcher.group(3);
				String sport = matcher.group(4);
				String discipline = matcher.group(5);
				int gold = Integer.parseInt(matcher.group(6));
				int silver = Integer.parseInt(matcher.group(7));
				int bronze = Integer.parseInt(matcher.group(8));
				olympicGames.output(olympicGames.addCompetition(athleteID, participationYear, countryName, sport,
						discipline, gold, silver, bronze));
			} else {
				throw new InvalidInputException("unexecutable, if no admin is logged in.");
			}
		}
	},

	/**
	 * Implementation of the command "olympic-medal-table" as mentioned in the task.
	 */
	// olympic-medal-table
	CMD_OLYMPIC_MEDAL_TABLE("olympic-medal-table") {

		@Override
		public void commandMethod(final MatchResult matcher, final OlympicGames olympicGames)
				throws InvalidInputException {

			if (olympicGames.isLoggedIn()) {
				olympicGames.olympicMedalTable();
			} else {
				throw new InvalidInputException("unexecutable, if no admin is logged in.");
			}
		}
	},

	/**
	 * Implementation of the command "reset" as mentioned in the task.
	 */
	// reset
	CMD_RESET("reset") {

		@Override
		public void commandMethod(final MatchResult matcher, final OlympicGames olympicGames)
				throws InvalidInputException {

			if (olympicGames.isLoggedIn()) {
				olympicGames.output(olympicGames.reset());
			} else {
				throw new InvalidInputException("unexecutable, if no admin is logged in.");
			}
		}
	},

	/**
	 * Implementation of the command "quit" as mentioned in the task.
	 */
	// quit
	CMD_QUIT("quit") {

		@Override
		public void commandMethod(final MatchResult matcher, final OlympicGames olympicGames)
				throws InvalidInputException {

			running = false;
		}
	};

	private static boolean running = true;
	private final Pattern pattern;

	/**
	 * Creates a command.
	 * 
	 * @param regex Regular expression for the command.
	 */
	Command(final String regex) {

		this.pattern = Pattern.compile(regex);
	}

	/**
	 * Returns the command which matches to the user input.
	 * 
	 * @param userInput    Command and/or arguments for it, based on the command
	 *                     itself.
	 * @param olympicGames Reference to Olympic Games control.
	 * @return The matching command.
	 * @throws InvalidInputException if any user input doesn't match with the
	 *                               command pattern.
	 */
	public static Command matchingCommand(final String userInput, final OlympicGames olympicGames)
			throws InvalidInputException {

		for (Command commands : Command.values()) {
			Matcher matcher = commands.pattern.matcher(userInput);
			if (matcher.matches()) {
				commands.commandMethod(matcher, olympicGames);
				return commands;
			}
		}
		throw new InvalidInputException("invalid command.");
	}

	/**
	 * Command dependent execution method.
	 * 
	 * @param matcher      Successfully compiled command pattern which contains the
	 *                     user input for the command dependent methods.
	 * @param olympicGames Reference to Olympic Games control.
	 * @throws InvalidInputException if any user input was invalid.
	 */
	protected abstract void commandMethod(MatchResult matcher, OlympicGames olympicGames) throws InvalidInputException;

	/**
	 * Returns the current program state.
	 * 
	 * @return True, if the game is still on and hasn't been quit yet. False, if the
	 *         user wants to quit the game by the command quit.
	 */
	public boolean isRunning() {

		return running;
	}
}
