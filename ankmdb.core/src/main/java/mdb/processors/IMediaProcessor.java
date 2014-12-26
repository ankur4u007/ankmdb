package mdb.processors;

import java.io.IOException;
import java.util.List;

import mdb.bo.EntryBO;
import mdb.bo.MediaBO;

public interface IMediaProcessor {

	List<MediaBO> findMedia() throws IOException;

	List<EntryBO> updateMediaDetails(List<MediaBO> mediaList);

	void addMedia();
}
