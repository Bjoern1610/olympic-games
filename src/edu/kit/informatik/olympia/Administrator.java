package edu.kit.informatik.olympia;

/**
 * This class represents an administrator who can manage and archive records.
 * 
 * @author Bjoern Holtvogt
 *
 */
public class Administrator {

	private String foreName;
	private String surName;
	private String userName;
	private String passWord;
	private boolean online;

	/**
	 * Creates an administrator who can manage and archive records.
	 * 
	 * @param foreName Forename of an admin.
	 * @param surName  Surname of an admin.
	 * @param userName Unique user name for an admin.
	 * @param passWord Password for the admin's account.
	 */
	public Administrator(final String foreName, final String surName, final String userName, final String passWord) {

		this.foreName = foreName;
		this.surName = surName;
		this.userName = userName;
		this.passWord = passWord;
	}

	/**
	 * Returns forename of an admin.
	 * 
	 * @return Forename of an admin.
	 */
	public String getForeName() {

		return foreName;
	}

	/**
	 * Sets forename of an admin.
	 * 
	 * @param forName Forename of an admin.
	 */
	public void setForeName(final String forName) {

		this.foreName = forName;
	}

	/**
	 * Returns surname of an admin.
	 * 
	 * @return Surname of an admin.
	 */
	public String getSurName() {

		return surName;
	}

	/**
	 * Sets surname of an admin.
	 * 
	 * @param surName Surname of an admin.
	 */
	public void setSurName(final String surName) {

		this.surName = surName;
	}

	/**
	 * Returns user name of an admin.
	 * 
	 * @return User name of an admin.
	 */
	public String getUserName() {

		return userName;
	}

	/**
	 * Sets user name for an admin.
	 * 
	 * @param userName User name of an admin.
	 */
	public void setUserName(final String userName) {

		this.userName = userName;
	}

	/**
	 * Returns password of an admin account.
	 * 
	 * @return Password of an admin account.
	 */
	public String getPassWord() {

		return passWord;
	}

	/**
	 * Sets password for an admin.
	 * 
	 * @param passWord Password of an admin.
	 */
	public void setPassWord(final String passWord) {

		this.passWord = passWord;
	}

	/**
	 * Returns online state of an admin account.
	 * 
	 * @return True, if the admin is online. False, if not.
	 */
	public boolean isOnline() {

		return online;
	}

	/**
	 * Sets online state of an admin account.
	 * 
	 * @param online Online state of an admin account.
	 */
	public void setOnline(final boolean online) {

		this.online = online;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		Administrator other = (Administrator) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
}
