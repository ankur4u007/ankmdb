import java.io.IOException;
import java.util.List;

import mdb.bo.EntryBO;
import mdb.bo.MediaBO;
import mdb.bo.MovieBO;
import mdb.processors.IMediaProcessor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(final String[] args) throws IOException {
		// TODO Auto-generated method stub

		final ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		final IMediaProcessor mp = (IMediaProcessor) ctx.getBean("sp-mediaProcessor");
		final List<MediaBO> mediaBOList = mp.findMedia();
		final List<EntryBO> entryBOList = mp.updateMediaDetails(mediaBOList);
		if (entryBOList != null) {
			for (final EntryBO bo : entryBOList) {
				if (bo.getMediaBo() instanceof MovieBO) {
					final MovieBO movieBO = (MovieBO) bo.getMediaBo();
					System.out.println(movieBO);
					System.out.println("ReferenceName :" + bo.getReferenceName());
					System.out.println("rating :" + bo.getRating());
					System.out.println("ImageUrl :" + bo.getImageUrl());
					System.out.println("ReferenceURL :" + bo.getReferenceUrl());
					System.out.println();
				}
			}
		}
	}

}
