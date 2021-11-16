package edu.kit.informatik.olympia;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/**
 * This class represents an athlete who participates in the Olympic Games.
 * 
 * @author Bjoern Holtvogt
 *
 */
public class Athlete implements Comparable<Athlete> {

	private String athleteID;
	private String foreName;
	private String surName;
	private int goldMedal;
	private int silverMedal;
	private int bronzeMedal;
	private int currentMedals;
	private IOC countryOfOrigin;
	private Set<Integer> participatedYears;
	private Map<Sports, Set<Integer>> participatedSports;
	private Map<Sports, Integer> medalTable;

	/**
	 * Creates an athlete profile who can participate and compete at various sports.
	 * 
	 * @param athleteID       Unique athlete ID.
	 * @param foreName        Forename of an athlete.
	 * @param surName         Surname of an athlete.
	 * @param countryOfOrigin Athlete's country of origin.
	 * @param sport           Participated sport.
	 */
	public Athlete(final String athleteID, final String foreName, final String surName, final IOC countryOfOrigin,
			final Sports sport) {

		this.athleteID = athleteID;
		this.foreName = foreName;
		this.surName = surName;
		this.currentMedals = -1;
		this.countryOfOrigin = countryOfOrigin;
		this.participatedYears = new HashSet<>();
		this.participatedSports = new HashMap<>();
		this.participatedSports.put(sport, participatedYears);
		this.medalTable = new HashMap<>();
		this.medalTable.put(sport, 0);
	}

	/**
	 * Returns the athlete ID.
	 * 
	 * @return Athlete ID.
	 */
	public String getAthleteID() {
		return athleteID;
	}

	/**
	 * Sets the athlete ID.
	 * 
	 * @param athleteID Unique athlete ID.
	 */
	public void setAthleteID(final String athleteID) {
		this.athleteID = athleteID;
	}

	/**
	 * Returns the forename of an athlete.
	 * 
	 * @return Forename of an athlete.
	 */
	public String getForeName() {
		return foreName;
	}

	/**
	 * Sets the forename of an athlete.
	 * 
	 * @param foreName Forename of an athlete.
	 */
	public void setForeName(final String foreName) {
		this.foreName = foreName;
	}

	/**
	 * Returns the surname of an athlete.
	 * 
	 * @return Surname of an athlete.
	 */
	public String getSurName() {
		return surName;
	}

	/**
	 * Sets the surname of an athlete.
	 * 
	 * @param surName Surname of an athlete.
	 */
	public void setSurName(final String surName) {
		this.surName = surName;
	}

	/**
	 * Returns the athlete's received gold medals.
	 * 
	 * @return Athlete's received gold medals.
	 */
	public int getGoldMedal() {
		return goldMedal;
	}

	/**
	 * Sets the athlete's received gold medals
	 * 
	 * @param goldMedal Amount of received gold medals.
	 */
	public void setGoldMedal(final int goldMedal) {
		this.goldMedal = goldMedal;
	}

	/**
	 * Returns the athlete's received silver medals.
	 * 
	 * @return Athlete's received silver medals.
	 */
	public int getSilverMedal() {
		return silverMedal;
	}

	/**
	 * Sets the athlete's received gold medals.
	 * 
	 * @param silverMedal Amount of received silver medals.
	 */
	public void setSilverMedal(final int silverMedal) {

		this.silverMedal = silverMedal;
	}

	/**
	 * Returns the athlete's received bronze medals.
	 * 
	 * @return Athlete's received bronze medals.
	 */
	public int getBronzeMedal() {
		return bronzeMedal;
	}

	/**
	 * Sets the athlete's received bronze medals.
	 * 
	 * @param bronzeMedal Amount of received bronze medals.
	 */
	public void setBronzeMedal(final int bronzeMedal) {
		this.bronzeMedal = bronzeMedal;
	}

	/**
	 * Returns the athlete's country of origin.
	 * 
	 * @return Athlete's country of origin.
	 */
	public String getCountryOfOrigin() {
		return countryOfOrigin.getCountryName();
	}

	/**
	 * Returns the current amount of received medals of a certain sport.
	 * 
	 * @return Current amount of received medals of a certain sport.
	 */
	public int getCurrentMedals() {
		return currentMedals;
	}

	/**
	 * Sets the current amount of received medals of a certain sport.
	 * 
	 * @param currentMedals Current amount of received medals of a certain sport.
	 */
	public void setCurrentMedals(final int currentMedals) {
		this.currentMedals = currentMedals;
	}

	/**
	 * Returns the amount of received medals dependent on the sport.
	 * 
	 * @param sport Sport from which one wants the medal table.
	 * @return Amount of received medals dependent on the sport.
	 */
	public int getSportsMedals(final Sports sport) {
		return medalTable.get(sport);
	}

	/**
	 * Sets the amount of received medals dependent on the sport.
	 * 
	 * @param sport        Sport from which the medal table is going to be
	 *                     refreshed.
	 * @param sportsMedals New amount of received medals.
	 */
	public void setSportsMedal(final Sports sport, final int sportsMedals) {
		medalTable.put(sport, sportsMedals);
	}

	/**
	 * Allows an athlete to participate in a sport.
	 * 
	 * @param otherSport Sport from which the athlete wants to participate.
	 */
	public void participates(final Sports otherSport) {
		Set<Integer> newYearSet = new HashSet<>();
		this.participatedSports.put(otherSport, newYearSet);
	}

	/**
	 * Returns the participating state of a sport.
	 * 
	 * @param otherSport Sport which wants to be checked, if the athlete is
	 *                   participating or not.
	 * @return True, if the athlete is already participating in this sport. False,
	 *         if not.
	 */
	public boolean isParticipating(final Sports otherSport) {
		return participatedSports.containsKey(otherSport);
	}

	/**
	 * Inserts the year in which the athlete has competed in an Olympic Games
	 * competition.
	 * 
	 * @param sport   Sport which the athlete has competed.
	 * @param newYear Year in which the athlete has competed.
	 */
	public void competes(final Sports sport, final int newYear) {
		Set<Integer> addedYearSet = new HashSet<>();
		addedYearSet.addAll(participatedYears);
		addedYearSet.add(newYear);
		this.participatedSports.replace(sport, participatedYears, addedYearSet);
	}

	/**
	 * Checks if an athlete has already competed in a year of an Olympic Games
	 * competition.
	 * 
	 * @param sport Sport which is checked if it has already been contested.
	 * @param year  Year in which the athlete might have already contested.
	 * @return True, if the athlete has already competed in the given sport and
	 *         year. False, if not.
	 */
	public boolean hasCompeted(final Sports sport, final int year) {
		return participatedSports.get(sport).contains(year);
	}

	@Override
	public int compareTo(final Athlete athlete) {
		if (this.getCurrentMedals() > athlete.getCurrentMedals()) {
			return -1;
		} else if (this.getCurrentMedals() < athlete.getCurrentMedals()) {
			return 1;
		} else {
			if (Integer.valueOf(this.getAthleteID()) > Integer.valueOf(athlete.getAthleteID())) {
				return 1;
			} else if (Integer.valueOf(this.getAthleteID()) < Integer.valueOf(athlete.getAthleteID())) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((athleteID == null) ? 0 : athleteID.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Athlete other = (Athlete) obj;
		if (athleteID == null) {
			if (other.athleteID != null)
				return false;
		} else if (!athleteID.equals(other.athleteID))
			return false;
		return true;
	}
}
