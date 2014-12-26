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
	@Value("#{'${text.to.filter}'.split(';')}")
	private List<String> filterTextList;

	public List<String> getSearchableNames(final String mediaName, final String format) {
		Set<String> setOfString = null;
		if (mediaName != null) {
			setOfString = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
			final String filterExp = getFilterRegExp();
			if (mediaFormatsList.contains(format)) {
				for (final String separator : separators) {
					final String[] names = mediaName.split(separator);
					final StringBuilder sb = new StringBuilder();
					for (int index = 0; index < names.length; index++) {
						sb.append(names[index]);
						sb.append(" ");
					}
					final String searchableString = sb.toString().trim();
					String[] refinedString = searchableString.split(filterExp);
					if (refinedString != null && refinedString.length > 0) {
						refinedString = refinedString[0].split(filterExp);
						if (refinedString[0] != null || !"".equals(refinedString[0])) {
							setOfString.add(refinedString[0]);
						}
					}
				}
			}
		}
		return new ArrayList<String>(setOfString);
	}

	private String getFilterRegExp() {
		final StringBuilder sb = new StringBuilder();
		sb.append(filterRegexp);
		if (filterTextList != null) {
			for (final String s : filterTextList) {
				sb.append("|").append("\\[(").append(s).append(")\\]").append("|").append("\\((").append(s).append(")\\)").append("|").append("(")
				.append(s).append(")");
			}
		}
		return sb.toString();
	}
}
