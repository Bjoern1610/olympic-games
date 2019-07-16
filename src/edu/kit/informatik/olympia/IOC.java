package edu.kit.informatik.olympia;

/**
 * This class represents a unique country code determined by the International
 * Olympic Committee (IOC).
 * 
 * @author Björn Holtvogt
 *
 */
public class IOC implements Comparable<IOC> {

	private String iocID;
	private String iocCode;
	private String countryName;
	private String yearOfDetermination;

	/**
	 * Creates an IOC country code.
	 * 
	 * @param iocID               Unique IOC ID.
	 * @param iocCode             Unique IOC code.
	 * @param countryName         Unique country name.
	 * @param yearOfDetermination Year in which the International Olympic Committee
	 *                            determined the IOC country code.
	 */
	public IOC(final String iocID, final String iocCode, final String countryName, final String yearOfDetermination) {

		this.iocID = iocID;
		this.iocCode = iocCode;
		this.countryName = countryName;
		this.yearOfDetermination = yearOfDetermination;
	}

	/**
	 * Returns the IOC ID.
	 * 
	 * @return IOC ID.
	 */
	public String getIocID() {

		return iocID;
	}

	/**
	 * Sets the IOC ID.
	 * 
	 * @param iocID Unique IOC ID.
	 */
	public void setIocID(final String iocID) {

		this.iocID = iocID;
	}

	/**
	 * Returns the IOC code.
	 * 
	 * @return IOC code.
	 */
	public String getIocCode() {

		return iocCode;
	}

	/**
	 * Sets the IOC code.
	 * 
	 * @param iocCode Unique IOC code.
	 */
	public void setIocCode(final String iocCode) {

		this.iocCode = iocCode;
	}

	/**
	 * Returns the country name.
	 * 
	 * @return Country name.
	 */
	public String getCountryName() {

		return countryName;
	}

	/**
	 * Sets the country name.
	 * 
	 * @param countryName Unique country name.
	 */
	public void setCountryName(final String countryName) {

		this.countryName = countryName;
	}

	/**
	 * Returns the year of determination.
	 * 
	 * @return Year of determination.
	 */
	public String getYearOfDetermination() {

		return yearOfDetermination;
	}

	/**
	 * Sets the year of determination.
	 * 
	 * @param yearOfDetermination Year in which the International Olympic Committee
	 *                            determined the IOC country code.
	 */
	public void setYearOfDetermination(final String yearOfDetermination) {

		this.yearOfDetermination = yearOfDetermination;
	}

	@Override
	public int compareTo(final IOC ioc) {

		if (Integer.valueOf(this.getYearOfDetermination()) > Integer.valueOf(ioc.getYearOfDetermination())) {
			return 1;
		} else if (Integer.valueOf(this.getYearOfDetermination()) < Integer.valueOf(ioc.getYearOfDetermination())) {
			return -1;
		} else {
			if (Integer.valueOf(this.getIocID()) > Integer.valueOf(ioc.getIocID())) {
				return 1;
			} else if (Integer.valueOf(this.getIocID()) < Integer.valueOf(ioc.getIocID())) {
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
		result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
		result = prime * result + ((iocCode == null) ? 0 : iocCode.hashCode());
		result = prime * result + ((iocID == null) ? 0 : iocID.hashCode());
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
		IOC other = (IOC) obj;
		if (iocID.equals(other.iocID) || iocCode.equals(other.iocCode) || countryName.equals(other.countryName))
			return true;
		if (countryName == null) {
			if (other.countryName != null)
				return false;
		} else if (!countryName.equals(other.countryName))
			return false;
		if (iocCode == null) {
			if (other.iocCode != null)
				return false;
		} else if (!iocCode.equals(other.iocCode))
			return false;
		if (iocID == null) {
			if (other.iocID != null)
				return false;
		} else if (!iocID.equals(other.iocID))
			return false;
		return true;
	}
}
