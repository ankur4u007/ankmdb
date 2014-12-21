package mdb.bo;

import java.util.Date;
import java.util.List;

public class MediaBO {

	protected String name;
	protected Long size;
	protected Date lastUpdated;
	protected List<String> searchableNames;
	protected String location;
	protected String format;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the size
	 */
	public Long getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(final Long size) {
		this.size = size;
	}

	/**
	 * @return the lastUpdated
	 */
	public Date getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * @param lastUpdated
	 *            the lastUpdated to set
	 */
	public void setLastUpdated(final Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MediaBO [name=" + name + "]";
	}

	/**
	 * @return the searchableNames
	 */
	public List<String> getSearchableNames() {
		return searchableNames;
	}

	/**
	 * @param searchableNames
	 *            the searchableNames to set
	 */
	public void setSearchableNames(final List<String> searchableNames) {
		this.searchableNames = searchableNames;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(final String location) {
		this.location = location;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format
	 *            the format to set
	 */
	public void setFormat(final String format) {
		this.format = format;
	}

}
