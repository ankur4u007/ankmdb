import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import db.IMediaProcessor;

public class Main {

	public static void main(final String[] args) {
		// TODO Auto-generated method stub

		final ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		final IMediaProcessor mp = (IMediaProcessor) ctx.getBean("sp-mediafinder");
		mp.addMedia();
	}

}
