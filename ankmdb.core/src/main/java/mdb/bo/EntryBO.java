package mdb.bo;

import java.io.Serializable;

public class EntryBO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	protected MediaBO mediaBo;
	protected String sourceMachine;
	protected String referenceName;
	protected Double rating;
	protected String referenceUrl;
	protected String imageUrl;

	/**
	 * @return the mediaBo
	 */
	public MediaBO getMediaBo() {
		return mediaBo;
	}

	/**
	 * @param mediaBo
	 *            the mediaBo to set
	 */
	public void setMediaBo(final MediaBO mediaBo) {
		this.mediaBo = mediaBo;
	}

	/**
	 * @return the sourceMachine
	 */
	public String getSourceMachine() {
		return sourceMachine;
	}

	/**
	 * @param sourceMachine
	 *            the sourceMachine to set
	 */
	public void setSourceMachine(final String sourceMachine) {
		this.sourceMachine = sourceMachine;
	}

	/**
	 * @return the rating
	 */
	public Double getRating() {
		return rating;
	}

	/**
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(final Double rating) {
		this.rating = rating;
	}

	/**
	 * @return the referenceUrl
	 */
	public String getReferenceUrl() {
		return referenceUrl;
	}

	/**
	 * @param referenceUrl
	 *            the referenceUrl to set
	 */
	public void setReferenceUrl(final String referenceUrl) {
		this.referenceUrl = referenceUrl;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl
	 *            the imageUrl to set
	 */
	public void setImageUrl(final String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getReferenceName() {
		return referenceName;
	}

	public void setReferenceName(final String referenceName) {
		this.referenceName = referenceName;
	}

	@Override
	public String toString() {
		return "EntryBO [" + (mediaBo != null ? "mediaBo=" + mediaBo + ", " : "")
				+ (sourceMachine != null ? "sourceMachine=" + sourceMachine + ", " : "")
				+ (referenceName != null ? "referenceName=" + referenceName + ", " : "") + (rating != null ? "rating=" + rating + ", " : "")
				+ (referenceUrl != null ? "referenceUrl=" + referenceUrl + ", " : "") + (imageUrl != null ? "imageUrl=" + imageUrl : "") + "]";
	}

}
