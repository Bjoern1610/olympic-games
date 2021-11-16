package edu.kit.informatik.olympia;

/**
 * This class represents a given sport with its discipline as a tuple.
 * 
 * @author Bjoern Holtvogt
 *
 */
public class Sports implements Comparable<Sports> {

	private String sport;
	private String discipline;

	/**
	 * Creates a sport tuple with a sport and its discipline in which the athletes
	 * can participate in competitions.
	 * 
	 * @param sport      Name of the sport.
	 * @param discipline Discipline of the sport.
	 */
	public Sports(final String sport, final String discipline) {
		this.sport = sport;
		this.discipline = discipline;
	}

	/**
	 * Returns name of the sport.
	 * 
	 * @return Name of the sport.
	 */
	public String getSport() {
		return sport;
	}

	/**
	 * Sets name of the sport.
	 * 
	 * @param sport Name of the sport.
	 */
	public void setSport(final String sport) {
		this.sport = sport;
	}

	/**
	 * Returns discipline of the sport.
	 * 
	 * @return Discipline of the sport.
	 */
	public String getDiscipline() {
		return discipline;
	}

	/**
	 * Sets discipline of the sport.
	 * 
	 * @param discipline Discipline of the sport.
	 */
	public void setDiscipline(final String discipline) {
		this.discipline = discipline;
	}

	@Override
	public int compareTo(final Sports sport) {
		if (this.getSport().compareTo(sport.getSport()) < 0) {
			return -1;
		} else if (this.getSport().compareTo(sport.getSport()) > 0) {
			return 1;
		} else {
			if (this.getDiscipline().compareTo(sport.getDiscipline()) < 0) {
				return -1;
			} else if (this.getDiscipline().compareTo(sport.getDiscipline()) > 0) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sport == null) ? 0 : sport.hashCode());
		result = prime * result + ((discipline == null) ? 0 : discipline.hashCode());
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
		Sports other = (Sports) obj;
		if (sport.equals(other.sport) && discipline.equals(other.discipline))
			return true;
		if (sport == null) {
			if (other.sport != null)
				return false;
		} else if (!sport.equals(other.sport))
			return false;
		if (discipline == null) {
			if (other.discipline != null)
				return false;
		} else if (!discipline.equals(other.discipline))
			return false;
		return true;
	}
}
