/**
 *
 */
package mdb.processors.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import mdb.bo.EntryBO;
import mdb.bo.EntryBOBuilder;
import mdb.bo.MediaBO;
import mdb.bo.MediaBOBuilder;
import mdb.processors.IMediaProcessor;
import mdb.searchNameGenerator.ISerachNameGenerator;
import mdb.updater.IMediaDetailsUpdater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

/**
 * @author CHANDRAYAN
 *
 */
@Component("sp-mediaProcessor")
public class MediaProcessor implements IMediaProcessor {
	@Value("#{'${media.location}'.split(';')}")
	private List<String> mediaLocation;
	@Value("${database.location}")
	private String databaseLocation;
	@Value("${media.source}")
	private String mediaSource;

	@Autowired
	@Qualifier(value = "sp-imdbUpdater")
	private IMediaDetailsUpdater mediaDetailsUpdater;

	@Autowired
	@Qualifier(value = "sp-searchNameGenerator")
	private ISerachNameGenerator serachNameGenerator;

	private static Logger log = LoggerFactory.getLogger(MediaProcessor.class);

	public void addMedia() {
		// TODO Auto-generated method stub
		System.out.println(mediaLocation + "\n" + databaseLocation);
	}

	public List<MediaBO> findMedia() throws IOException {
		Preconditions.checkNotNull(mediaLocation, "Media Location cant be null");
		Preconditions.checkNotNull(databaseLocation, "databaseLocation cant be null");
		Preconditions.checkArgument(!mediaLocation.isEmpty(), "Media Location should not be empty");
		Preconditions.checkArgument(!"".equals(databaseLocation.trim()), "Database Location should not be empty");
		final List<MediaBO> mediaBOList = new ArrayList<MediaBO>();
		for (final String stringpath : mediaLocation) {
			final File file = new File(stringpath);
			Preconditions.checkNotNull(!file.isDirectory(), "Location:" + stringpath + " should be a directory");
			final Path path = Paths.get(stringpath);
			Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
					final String fileName = file.getFileName().toString();
					String format = null;
					String mediaName = null;
					final String[] namesArray = fileName.split("\\.");
					if (namesArray != null) {
						format = namesArray[namesArray.length - 1];
						format = format != null ? format.trim().toLowerCase() : null;
						final StringBuilder sb = new StringBuilder();
						for (int i = 0; i < namesArray.length - 1; i++) {
							sb.append(namesArray[i]).append(" ");
						}
						mediaName = sb.toString().trim();
					}
					mediaBOList.add(MediaBOBuilder.buildBoFromDetails(mediaName, new Date(attrs.lastModifiedTime().toMillis()), attrs.size(), file
							.getParent().toAbsolutePath().toString(), serachNameGenerator.getSearchableNames(mediaName, format), format));
					return super.visitFile(file, attrs);
				}
			});

		}
		return mediaBOList;
	}

	public List<EntryBO> updateMediaDetails(final List<MediaBO> mediaList) {
		final List<EntryBO> entryBOList = new ArrayList<EntryBO>();
		for (final MediaBO mediaBO : mediaList) {
			final List<Map<String, Object>> responseMapList = new ArrayList<Map<String, Object>>();
			for (final String searchableNames : mediaBO.getSearchableNames()) {
				if (searchableNames != null) {
					responseMapList.add(mediaDetailsUpdater.updateMedia(searchableNames));
				}
			}
			entryBOList.add(EntryBOBuilder.buildBoFromList(mediaBO, mediaSource, null, null, null));
		}
		return entryBOList;
	}
}
