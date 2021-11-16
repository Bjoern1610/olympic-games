package edu.kit.informatik.olympia;

/**
 * This class represents a sports venue.
 *
 * @author Bjoern Holtvogt
 */
public class Venue implements Comparable<Venue> {

    private String venueID;
    private IOC country;
    private String location;
    private String venueName;
    private int yearOfOpening;
    private int amountOfSeats;

    /**
     * Creates a new sports venue to the system, which can be used as a venue for
     * the competitions, as well as the venue for the opening and closing ceremonies
     * of the Olympic Games.
     *
     * @param venueID       Unique venue ID.
     * @param country       Country of a venue.
     * @param location      City/village in which the venue is located.
     * @param venueName     Name of a venue.
     * @param yearOfOpening Opening year of the sports venue.
     * @param amountOfSeats Amount of seats of a venue.
     */
    public Venue(final String venueID, final IOC country, final String location, final String venueName,
                 final int yearOfOpening, final int amountOfSeats) {

        this.venueID = venueID;
        this.country = country;
        this.location = location;
        this.venueName = venueName;
        this.yearOfOpening = yearOfOpening;
        this.amountOfSeats = amountOfSeats;
    }

    /**
     * Returns venue ID.
     *
     * @return Venue ID.
     */
    public String getVenueID() {
        return venueID;
    }

    /**
     * Sets venue ID.
     *
     * @param venueID Unique venue ID.
     */
    public void setVenueID(final String venueID) {
        this.venueID = venueID;
    }

    /**
     * Returns country in which the venue is located,
     *
     * @return Country in which the venue is located.
     */
    public String getVenueCountry() {
        return country.getCountryName();
    }

    /**
     * Returns city/village in which the venue is located.
     *
     * @return City/village in which the venue is located.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets city/village in which the venue is located.
     *
     * @param location City/village in which the venue is located.
     */
    public void setLocation(final String location) {
        this.location = location;
    }

    /**
     * Returns venue name.
     *
     * @return Venue name.
     */
    public String getVenueName() {
        return venueName;
    }

    /**
     * Sets venue name.
     *
     * @param venueName Name of a venue.
     */
    public void setVenueName(final String venueName) {
        this.venueName = venueName;
    }

    /**
     * Returns opening year of the sports venue.
     *
     * @return Opening year of the sports venue.
     */
    public int getYearOfOpening() {
        return yearOfOpening;
    }

    /**
     * Sets the opening year of the sports venue.
     *
     * @param yearOfOpening Opening year of the sports venue.
     */
    public void setYearOfOpening(final int yearOfOpening) {
        this.yearOfOpening = yearOfOpening;
    }

    /**
     * Returns amount of seats of a venue.
     *
     * @return Amount of seats of a venue.
     */
    public int getAmountOfSeats() {
        return amountOfSeats;
    }

    /**
     * Sets amount of seats of a venue.
     *
     * @param amountOfSeats Amount of seats of a venue.
     */
    public void setAmountOfSeats(final int amountOfSeats) {
        this.amountOfSeats = amountOfSeats;
    }

    @Override
    public int compareTo(final Venue venue) {
        if (this.getAmountOfSeats() > venue.getAmountOfSeats()) {
            return 1;
        } else if (this.getAmountOfSeats() < venue.getAmountOfSeats()) {
            return -1;
        } else {
            if (Integer.parseInt(this.getVenueID()) > Integer.parseInt(venue.getVenueID())) {
                return 1;
            } else if (Integer.parseInt(this.getVenueID()) < Integer.parseInt(venue.getVenueID())) {
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
        result = prime * result + ((venueID == null) ? 0 : venueID.hashCode());
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
        Venue other = (Venue) obj;
        if (venueID == null) {
            if (other.venueID != null)
                return false;
        } else if (!venueID.equals(other.venueID))
            return false;
        return true;
    }

}
