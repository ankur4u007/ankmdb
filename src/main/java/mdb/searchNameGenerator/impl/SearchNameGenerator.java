package mdb.searchNameGenerator.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import mdb.searchNameGenerator.ISerachNameGenerator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("sp-searchNameGenerator")
public class SearchNameGenerator implements ISerachNameGenerator {

	@Value("#{'${media.formats}'.split(';')}")
	private List<String> mediaFormatsList;

	public List<String> getSearchableNames(final String mediaName, final String format) {
		Set<String> setOfString = null;
		if (mediaName != null) {
			setOfString = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
			if (mediaFormatsList.contains(format)) {
				for (final String separator : separators) {
					final String[] names = mediaName.split(separator);
					final StringBuilder sb = new StringBuilder();
					for (int index = 0; index < names.length; index++) {
						sb.append(names[index]);
						sb.append(" ");
					}
					final String searchableString = sb.toString().trim();
					String[] refinedString = searchableString.split(filterRegexp);
					refinedString = refinedString[0].split(filterRegexp);
					if (refinedString[0] != null || !"".equals(refinedString[0])) {
						setOfString.add(refinedString[0]);
					}
				}
			}
		}
		return new ArrayList<String>(setOfString);
	}
}
