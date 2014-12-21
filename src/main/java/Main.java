import java.io.IOException;
import java.util.List;

import mdb.bo.MediaBO;
import mdb.processors.IMediaProcessor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(final String[] args) throws IOException {
		// TODO Auto-generated method stub

		final ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		final IMediaProcessor mp = (IMediaProcessor) ctx.getBean("sp-mediaProcessor");
		final List<MediaBO> mediaBOList = mp.findMedia();
		mp.updateMediaDetails(mediaBOList);

	}

}
