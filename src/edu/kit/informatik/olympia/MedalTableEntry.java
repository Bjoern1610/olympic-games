package edu.kit.informatik.olympia;

/**
 * This class represents an entry for the overall medal table.
 *
 * @author Bjoern Holtvogt
 */
public class MedalTableEntry implements Comparable<MedalTableEntry> {

    private final IOC country;

    private int totalGold;
    private int totalSilver;
    private int totalBronze;
    private int totalMedals;

    /**
     * Creates a new medal table entry for each country participating at the Olympic
     * Games.
     *
     * @param country     Reference to a country.
     * @param totalGold   Total amount of gold medals for a country.
     * @param totalSilver Total amount of silver medals for a country.
     * @param totalBronze Total amount of bronze medals for a country.
     */
    public MedalTableEntry(final IOC country, final int totalGold, final int totalSilver, final int totalBronze) {
        this.country = country;
        this.totalGold = totalGold;
        this.totalSilver = totalSilver;
        this.totalBronze = totalBronze;
        this.totalMedals = (totalGold + totalSilver + totalBronze);

    }

    /**
     * Returns the IOC ID.
     *
     * @return IOC ID.
     */
    public String getIocID() {
        return country.getIocID();
    }

    /**
     * Returns the IOC code.
     *
     * @return IOC code.
     */
    public String getIocCode() {
        return country.getIocCode();
    }

    /**
     * Returns the country name.
     *
     * @return Country name.
     */
    public String getCountryName() {
        return country.getCountryName();
    }

    /**
     * Returns total amount of gold medals.
     *
     * @return Total amount of gold medals.
     */
    public int getTotalGold() {
        return totalGold;
    }

    /**
     * Sets the total amount of gold medals.
     *
     * @param totalGold Total amount of gold medals.
     */
    public void setTotalGold(final int totalGold) {
        this.totalGold = totalGold;
    }

    /**
     * Returns total amount of silver medals.
     *
     * @return Total amount of silver medals.
     */
    public int getTotalSilver() {
        return totalSilver;
    }

    /**
     * Sets the total amount of silver medals.
     *
     * @param totalSilver Total amount of silver medals.
     */
    public void setTotalSilver(final int totalSilver) {
        this.totalSilver = totalSilver;
    }

    /**
     * Returns total amount of bronze medals.
     *
     * @return Total amount of bronze medals.
     */
    public int getTotalBronze() {
        return totalBronze;
    }

    /**
     * Sets the total amount of bronze medals.
     *
     * @param totalBronze Total amount of bronze medals.
     */
    public void setTotalBronze(final int totalBronze) {
        this.totalBronze = totalBronze;
    }

    /**
     * Returns total amount of all received medals.
     *
     * @return Total amount of all received medals.
     */
    public int getTotalMedals() {
        return totalMedals;
    }

    /**
     * Sets the total amount of all received medals.
     *
     * @param totalMedals Total amount of all received medals.
     */
    public void setTotalMedals(final int totalMedals) {
        this.totalMedals = totalMedals;
    }

    @Override
    public int compareTo(final MedalTableEntry medalTableEntry) {
        if (this.getTotalGold() < medalTableEntry.getTotalGold()) {
            return 1;
        } else if (this.getTotalGold() > medalTableEntry.getTotalGold()) {
            return -1;
        } else {
            if (this.getTotalSilver() < medalTableEntry.getTotalSilver()) {
                return 1;
            } else if (this.getTotalSilver() > medalTableEntry.getTotalSilver()) {
                return -1;
            } else {
                if (this.getTotalBronze() < medalTableEntry.getTotalBronze()) {
                    return 1;
                } else if (this.getTotalBronze() > medalTableEntry.getTotalBronze()) {
                    return -1;
                } else {
                    if (Integer.valueOf(this.getIocID()) < Integer.valueOf(medalTableEntry.getIocID())) {
                        return -1;
                    } else if (Integer.valueOf(this.getIocID()) > Integer.valueOf(medalTableEntry.getIocID())) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((country == null) ? 0 : country.hashCode());
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
        MedalTableEntry other = (MedalTableEntry) obj;
        if (this.getIocID().equals(other.getIocID()) || this.getIocCode().equals(other.getIocCode())
                || this.getCountryName().equals(other.getCountryName()))
            return true;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        return true;
    }
}
