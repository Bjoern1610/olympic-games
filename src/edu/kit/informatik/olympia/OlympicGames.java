package edu.kit.informatik.olympia;

import edu.kit.informatik.Main;
import edu.kit.informatik.InvalidInputException;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author Björn Holtvogt
 *
 */
public class OlympicGames {

    private final short olympicBegin = 1926;
    private int currentOlympicYear;
    private Map<String, Administrator> adminMap;
    private Map<String, IOC> iocMap;
    private Map<String, Venue> venueMap;
    private Map<Sports, Sports> sportMap;
    private Map<String, Athlete> athleteMap;
    private Set<MedalTableEntry> medalTable;

    /**
     * Creates an Olympic Games management and archiving system.
     */
    public OlympicGames() {

        this.currentOlympicYear = 2018;
        this.adminMap = new HashMap<>();
        this.iocMap = new TreeMap<>();
        this.venueMap = new HashMap<>();
        this.sportMap = new TreeMap<>();
        this.athleteMap = new HashMap<>();
        this.medalTable = new TreeSet<>();
    }

    /**
     * Processed outputs of the program will be send to the main class, the only user interface.
     * 
     * @param text
     *            Current answer of a command method.
     */
    public void prepareTextOutput(final String text) {

        Main.textOutput(text);
    }

    /**
     * Checks for an logged in admin.
     * 
     * @return True, if an admin is logged in. False, if not.
     */
    public boolean isLoggedIn() {

        for (Administrator administrator: adminMap.values()) {
            if (administrator.isOnline()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an admin to the system who can manage and archive records.
     * 
     * @param forName
     *            Forname of an admin.
     * @param surName
     *            Surname of an admin.
     * @param userName
     *            User name for an admin.
     * @param passWord
     *            Password for an admin account.
     * @return OK, if the given user name doesn't exist yet.
     * @throws InvalidInputException
     *             if the user name already exists.
     */
    public String addAdmin(String forName, String surName, String userName, String passWord)
            throws InvalidInputException {

        Administrator newAdmin = new Administrator(forName, surName, userName, passWord);
        return addElement(adminMap, userName, newAdmin, "user name already exists.");
    }

    /**
     * Authenticates an admin of the management and archiving system and gives him the rights to call certain commands
     * according to the rights.
     * 
     * @param userName
     *            User name of an admin.
     * @param passWord
     *            Password of an admin account.
     * @return OK, if user name and password are valid.
     * @throws InvalidInputException
     *             if any of the given parameters are invalid.
     */
    public String loginAdmin(String userName, String passWord) throws InvalidInputException {

        if (adminMap.get(userName) != null && adminMap.get(userName).getPassWord().equals(passWord)) {
            adminMap.get(userName).setOnline(true);
            return "OK";
        }
        throw new InvalidInputException("user name or password is incorrect.");
    }

    /**
     * Logs out an previously authenticated admin of the management and archiving system of the login command and
     * deprives him of the ability to invoke appropriate commands until he authenticates again.
     * 
     * @return OK, if an admin was successfully logged out.
     * @throws InvalidInputException
     *             if no admin has been online before the command was executed.
     */
    public String logoutAdmin() throws InvalidInputException {

        for (Administrator administrator: adminMap.values()) {
            if (administrator.isOnline()) {
                administrator.setOnline(false);
                return "OK";
            }
        }
        throw new InvalidInputException("no admin is being online.");
    }

    /**
     * Adds a new sports venue to the management and archiving system, which can be used as a venue for the
     * competitions, as well as the venue for the opening and closing ceremonies of the Olympic Games.
     * 
     * @param venueID
     *            Unique venue ID.
     * @param countryName
     *            Country of a venue.
     * @param location
     *            City/village in which the venue is located.
     * @param venueName
     *            Name of a venue.
     * @param yearOfOpening
     *            Opening year of the sports venue.
     * @param amountOfSeats
     *            Amount of seats of a venue.
     * @return OK, if the venue was successfully added.
     * @throws InvalidInputException
     *             if the given country parameter has no associated IOC code.
     */
    public String addSportsVenue(String venueID, String countryName, String location, String venueName,
            int yearOfOpening, int amountOfSeats) throws InvalidInputException {

        if (iocMap.containsKey(countryName)) {
            Venue newVenue = new Venue(venueID, iocMap.get(countryName), location, venueName, yearOfOpening,
                    amountOfSeats);
            return addElement(venueMap, venueID, newVenue, "venue already exists.");
        }
        throw new InvalidInputException("country has no associated IOC code.");
    }

    /**
     * Lists the sports facilities of a certain country in ascending order of number of seats. In the case of equality,
     * the unique venue ID is crucial for the listing and will thereby listed in ascending order.
     * 
     * @param countryName
     *            Country in which the venues are located.
     * @throws InvalidInputException
     *             if the given country parameter has no associated IOC code.
     */
    public void listSportsVenues(String countryName) throws InvalidInputException {

        boolean matchingIOC = iocMap.containsKey(countryName);
        if (!matchingIOC) {
            throw new InvalidInputException("country has no associated IOC code.");
        } else if (matchingIOC && !venueMap.isEmpty()) {
            int placement = 1;
            Set<Venue> sortedVenues = new TreeSet<Venue>();
            sortedVenues.addAll(venueMap.values());
            for (Venue venue: sortedVenues) {
                if (venue.getVenueCountry().equals(countryName)) {
                    prepareTextOutput("(" + placement + " " + venue.getVenueID() + " " + venue.getLocation() + " "
                            + venue.getAmountOfSeats() + ")");
                    placement++;
                }
            }
        }
    }

    /**
     * A sport and sports discipline in which the athletes can participate in competitions will thereby be created.
     * 
     * @param sport
     *            Name of the sport.
     * @param discipline
     *            Discipline of the sport.
     * @return OK, if a sport tuple was successfully added.
     * @throws InvalidInputException
     *             if the given sport tuple already exists.
     */
    public String addOlympicSport(String sport, String discipline) throws InvalidInputException {

        Sports newSport = new Sports(sport, discipline);
        return addElement(sportMap, newSport, newSport, "sport and discipline already exists.");
    }

    /**
     * Lists the sports and sports disciplines in alphabetic order.
     */
    public void listOlympicSports() {

        if (!sportMap.isEmpty()) {
            for (Sports sport: sportMap.values()) {
                prepareTextOutput(sport.getSport() + " " + sport.getDiscipline());
            }
        }
    }

    /**
     * Adds an IOC country code to the management and archiving system.
     * 
     * @param iocID
     *            Unique IOC ID.
     * @param iocCode
     *            Unique IOC code.
     * @param countryName
     *            Unique country name.
     * @param yearOfDetermination
     *            Year in which the International Olympic Committee determined the IOC country code.
     * @return OK, if the IOC code was successfully added.
     * @throws InvalidInputException
     *             if the IOC already exists.
     */
    public String addIocCode(String iocID, String iocCode, String countryName, String yearOfDetermination)
            throws InvalidInputException {

        IOC newIOC = new IOC(iocID, iocCode, countryName, yearOfDetermination);
        // Check if element exists, so that the subsequent addition can no longer trigger an error
        for (IOC ioc: iocMap.values()) {
            if (ioc.equals(newIOC)) {
                throw new InvalidInputException("IOC already exists.");
            }
        }
        // Country name as key because it is the most queried attribute and should also be unique
        return addElement(iocMap, countryName, new IOC(iocID, iocCode, countryName, yearOfDetermination), "");
    }

    /**
     * Lists the codes in ascending order after the year of determination. In case of equality, the IOC ID is crucial
     * for listing and will thereby listed in ascending order.
     */
    public void listIocCodes() {

        if (!iocMap.isEmpty()) {
            Set<IOC> sortedIOC = new TreeSet<IOC>();
            sortedIOC.addAll(iocMap.values());
            for (IOC ioc: sortedIOC) {
                prepareTextOutput(ioc.getYearOfDetermination() + " " + ioc.getIocID() + " " + ioc.getIocCode() + " "
                        + ioc.getCountryName());
            }
        }
    }

    /**
     * Adds an athlete profile to the management and archiving system.
     * 
     * @param athleteID
     *            Unique athlete ID.
     * @param foreName
     *            Forename of an athlete.
     * @param surName
     *            Surname of an athlete.
     * @param countryOfOrigin
     *            Athlete's country of origin.
     * @param sport
     *            Name of the sport in which the athlete wants to participate.
     * @param discipline
     *            Discipline of the sport in which the athlete wants to participate.
     * @return OK, if an athlete was successfully added.
     * @throws InvalidInputException
     *             if
     *             <p>
     *             Athlete has already participated to the given sports tuple. The athlete's forename, surname or
     *             country of origin is invalid.
     *             <p>
     *             Given IOC or sport doesn't exist.
     */
    public String addAthlete(String athleteID, String foreName, String surName, String countryOfOrigin, String sport,
            String discipline) throws InvalidInputException {

        Sports sportReference = new Sports(sport, discipline);
        boolean matchingIOC = iocMap.containsKey(countryOfOrigin);
        boolean matchingSport = sportMap.containsKey(sportReference);
        if (matchingIOC && matchingSport) {
            if (!athleteMap.isEmpty()) {
                // Athlete's characteristics such as ID, forename, surname, country of origin already exist
                if (athleteMap.containsKey(athleteID) && athleteMap.get(athleteID).getForeName().equals(foreName)
                        && athleteMap.get(athleteID).getSurName().equals(surName)
                        && athleteMap.get(athleteID).getCountryOfOrigin().equals(countryOfOrigin)) {

                    if (!athleteMap.get(athleteID).isParticipating(sportReference)) {
                        athleteMap.get(athleteID).participates(sportReference);
                        athleteMap.get(athleteID).setSportsMedal(sportReference, 0);
                        return "OK";
                    } else {
                        throw new InvalidInputException("athlete can't participate twice.");
                    }
                }
            }
            Athlete newAthlete = new Athlete(athleteID, foreName, surName, iocMap.get(countryOfOrigin), sportReference);
            // Doesn't trigger an error, if no athlete has been added yet
            return addElement(athleteMap, athleteID, newAthlete,
                    "athlete forename, surname or country of origin is invalid.");
        }
        throw new InvalidInputException("not existing IOC or sport.");
    }

    /**
     * Lists the participants of the Olympic Winter Games on the basis of their sporting achievements in the respective
     * sport and sports discipline. It is sorted by the number of medals won in descending order. If equal, the ID is
     * sorted in ascending order.
     * 
     * @param sport
     *            Name of the sport which wants to be summarised.
     * @param discipline
     *            Discipline of the sport which wants to be summarised.
     * @throws InvalidInputException
     *             if the given sport tuple doesn't exist.
     */
    public void summaryAthlete(String sport, String discipline) throws InvalidInputException {

        Sports wantedSport = new Sports(sport, discipline);
        if (sportMap.containsKey(wantedSport)) {
            if (!athleteMap.isEmpty()) {
                // Receiving possible candidates and assigning them with a current medal table
                for (Athlete athlete: athleteMap.values()) {
                    if (athlete.isParticipating(wantedSport)) {
                        athlete.setCurrentMedals(athlete.getSportsMedals(wantedSport));
                    }
                }
                Set<Athlete> competingAthletes = new TreeSet<Athlete>();
                competingAthletes.addAll(athleteMap.values());
                for (Athlete athlete: competingAthletes) {
                    if (athlete.isParticipating(wantedSport)) {
                        prepareTextOutput(athlete.getAthleteID() + " " + athlete.getForeName() + " "
                                + athlete.getSurName() + " " + athlete.getCurrentMedals());
                        athlete.setCurrentMedals(-1);
                    }
                }
            }
        } else {
            throw new InvalidInputException("not existing sport.");
        }
    }

    /**
     * Adds the result of a competition by a participating athlete to the management and archiving system.
     * 
     * @param athleteID
     *            Unique athlete ID.
     * @param participationYear
     *            Year in which the athlete has participated.
     * @param countryOfOrigin
     *            Athlete's country of origin.
     * @param sport
     *            Name of the sport in which the athlete has competed.
     * @param discipline
     *            Discipline of the sport in which the athlete has competed.
     * @param gold
     *            Amount of won gold medals.
     * @param silver
     *            Amount of won silver medals.
     * @param bronze
     *            Amount of won bronze medals.
     * @return OK, if the result of a competition was successfully added.
     * @throws InvalidInputException
     *             if
     *             <p>
     *             Input shows that the athlete wants to compete twice.
     *             <p>
     *             Year, athlete or sport characteristics doesn't match.
     *             <p>
     *             Athlete, sport or country hasn't been added yet.
     *             <p>
     *             The athlete's medal input is more than one medal.
     */
    public String addCompetition(String athleteID, int participationYear, String countryOfOrigin, String sport,
            String discipline, int gold, int silver, int bronze) throws InvalidInputException {

        Sports sportReference = new Sports(sport, discipline);
        // Athlete can't win more than one medal in a competition
        if (!((gold + silver + bronze) > 1)) {
            if (athleteMap.containsKey(athleteID) && sportMap.containsKey(sportReference)
                    && iocMap.containsKey(countryOfOrigin)) {

                boolean validAthlet = athleteMap.get(athleteID).getCountryOfOrigin().equals(countryOfOrigin);
                boolean validSport = athleteMap.get(athleteID).isParticipating(sportReference);
                boolean validYear = olympicTurnus(iocMap.get(countryOfOrigin), participationYear);
                if (validAthlet && validSport && validYear) {
                    // Same sport and year isn't allowed
                    if (!athleteMap.get(athleteID).hasCompeted(sportReference, participationYear)) {
                        athleteMap.get(athleteID).competes(sportReference, participationYear);
                        refreshMedals(athleteMap.get(athleteID), sportReference, gold, silver, bronze);
                        return "OK";
                    }
                    throw new InvalidInputException("athlete can't compete twice in a year.");
                }
                throw new InvalidInputException("no matching year, athlete or sport characteristics.");
            }
            throw new InvalidInputException("athlete, sport or country hasn't been addet yet.");
        }
        throw new InvalidInputException("athlete can only earn no or one medal per competition.");
    }

    /**
     * Creates a ranking in form of a medal table of the participating countries. Starting with place 1, the output will
     * be sorted in descending order by the number of gold medals, then the silver medals and lastly by the number of
     * bronze medals. In case of equality, the order is sorted in ascending order according to the IOC ID.
     */
    public void olympicMedalTable() {

        if (!iocMap.isEmpty()) {                       
            for (IOC ioc: iocMap.values()) {
                int totalGold = 0;
                int totalSilver = 0;
                int totalBronze = 0;
                // Every country receives a total result of their athletes medal amount
                for (Athlete athlete: athleteMap.values()) {
                    if (athlete.getCountryOfOrigin().equals(ioc.getCountryName())) {
                        totalGold += athlete.getGoldMedal();
                        totalSilver += athlete.getSilverMedal();
                        totalBronze += athlete.getBronzeMedal();
                    }
                }
                MedalTableEntry newEntry = new MedalTableEntry(ioc, totalGold, totalSilver, totalBronze);
                medalTable.add(newEntry);
            }
            int placement = 1;
            for (MedalTableEntry medalTableEntry: medalTable) {
                prepareTextOutput("(" + (placement) + "," + medalTableEntry.getIocID() + ","
                        + medalTableEntry.getIocCode() + "," + medalTableEntry.getCountryName() + ","
                        + medalTableEntry.getTotalGold() + "," + medalTableEntry.getTotalSilver() + ","
                        + medalTableEntry.getTotalBronze() + "," + medalTableEntry.getTotalMedals() + ")");
                placement++;
            }
            // Prepare for next update
            medalTable.clear();
        }
    }

    /**
     * Every entry and table apart from the admins will be reseted.
     * 
     * @return OK, if the reset was successfully executed.
     */
    public String reset() {

        iocMap.clear();
        venueMap.clear();
        sportMap.clear();
        athleteMap.clear();
        medalTable.clear();
        return "OK";
    }

    private <K, V> String addElement(Map<K, V> map, K key, V value, String errorMessage) throws InvalidInputException {

        // Doesn't allow duplicates
        if (map.containsKey(key)) {
            throw new InvalidInputException(errorMessage);
        } else {
            map.put(key, value);
            return "OK";
        }
    }

    private boolean olympicTurnus(IOC country, int year) {

        // No competition can take place before an IOC has been determined
        boolean afterIOC = Integer.valueOf(country.getYearOfDetermination()) <= year;
        // Every 4th year after 1926
        boolean turnus = (((year - olympicBegin) % 4) == 0);
        if (year >= olympicBegin && year <= currentOlympicYear && turnus && afterIOC) {
            return true;
        }
        return false;
    }

    private void refreshMedals(Athlete refreshedAthlete, Sports sport, int gold, int silver, int bronze) {

        refreshedAthlete.setGoldMedal(refreshedAthlete.getGoldMedal() + gold);
        refreshedAthlete.setSilverMedal(refreshedAthlete.getSilverMedal() + silver);
        refreshedAthlete.setBronzeMedal(refreshedAthlete.getBronzeMedal() + bronze);
        refreshedAthlete.setSportsMedal(sport, (refreshedAthlete.getSportsMedals(sport) + (gold + silver + bronze)));
    }
}
