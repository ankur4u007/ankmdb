/**
 * 
 */
package db.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import db.IMediaFinder;

/**
 * @author CHANDRAYAN
 *
 */
@Component("sp-mediafinder")
public class MediaFinder implements IMediaFinder{
	@Value("#{'${media.location}'.split(';')}") 
	private List<String> mediaLocation;
	@Value("${database.location}")
	private String databaseLocation;
	
	public void addMedia() {
		// TODO Auto-generated method stub
		System.out.println(mediaLocation + "\n"+databaseLocation );
	}
	
	
	
}
