package mdb.bo;

import java.util.Date;
import java.util.List;

public class MediaBOBuilder {

	private MediaBOBuilder() {
		// Avoid init
	}

	public static final MediaBO buildBoFromDetails(final String name, final Date lastUpdated, final Long size, final String location,
			final List<String> searchableNames, final String format) {
		final MediaBO bo = new MediaBO();
		bo.setName(name);
		bo.setLastUpdated(lastUpdated);
		bo.setSize(size);
		bo.setLocation(location);
		bo.setFormat(format);
		bo.setSearchableNames(searchableNames);
		return bo;
	}
}
