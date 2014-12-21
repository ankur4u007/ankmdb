/**
 *
 */
package mdb.bo;

/**
 * @author CHANDRAYAN
 *
 */
public class EntryBOBuilder {

	private EntryBOBuilder() {
		// avoid initialisation
	}

	public static EntryBO buildBoFromList(final MediaBO mediaBO, final String sourceMachine, final Double rating, final String referenceUrl,
			final String imageUrl) {
		final EntryBO bo = new EntryBO();
		bo.setMediaBo(mediaBO);
		bo.setRating(rating);
		bo.setSourceMachine(sourceMachine);
		bo.setReferenceUrl(referenceUrl);
		bo.setImageUrl(imageUrl);
		return bo;
	}
}
