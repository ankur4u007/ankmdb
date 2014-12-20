import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import db.IMediaFinder;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		IMediaFinder mf = (IMediaFinder) ctx.getBean("sp-mediafinder");
		mf.addMedia();
	}

}
