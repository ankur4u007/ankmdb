/**
 *
 */
package db.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import db.IMediaProcessor;

/**
 * @author CHANDRAYAN
 *
 */
@Component("sp-mediaprocessor")
public class MediaProcessor implements IMediaProcessor {
	@Value("#{'${media.location}'.split(';')}")
	private List<String> mediaLocation;
	@Value("${database.location}")
	private String databaseLocation;

	public void addMedia() {
		// TODO Auto-generated method stub
		System.out.println(mediaLocation + "\n" + databaseLocation);
	}

}
