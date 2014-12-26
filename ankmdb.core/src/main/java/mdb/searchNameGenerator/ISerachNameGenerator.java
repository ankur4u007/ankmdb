package mdb.searchNameGenerator;

import java.util.List;

public interface ISerachNameGenerator {

	public final String[] separators = { " ", ",", "\\." };

	public final String filterRegexp = "(?i)\\[[0-9]{4}\\]|\\([0-9]{4}\\)|[0-9]{4}|\\[(dvdrip)\\]|\\[(dvdscr)\\]|(dvdrip)|(720p)|(dvdscr)|[-]|\\(\\w+\\s+\\w+\\)";

	List<String> getSearchableNames(String mediaName, String format);
}
