package mdb.updater.imdb;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mdb.updater.IMediaDetailsUpdater;
import mdb.updater.impl.MediaDetailsUpdater;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("sp-imdbUpdater")
public class ImdbUpdater extends MediaDetailsUpdater {

	private final String baseUrl = "http://www.imdb.com/";
	private static Logger log = LoggerFactory.getLogger(ImdbUpdater.class);
	private final String browser = "Mozilla";
	private final int timeout = 10000;

	@Override
	protected Document fetchResponse(final String mediaName) {
		Document webpageDoc = null;
		try {
			final String querry = "find?s=all&q=" + URLEncoder.encode(mediaName.trim(), "UTF-8");
			webpageDoc = Jsoup.connect(baseUrl + querry).userAgent(browser).timeout(timeout).get();
		} catch (final UnsupportedEncodingException e1) {
			log.info(e1.getMessage() + " for " + mediaName);
		} catch (final IOException e1) {
			log.info(e1.getMessage() + " for " + mediaName);
		}
		return webpageDoc;
	}

	@Override
	protected Map<String, Object> parseResponse(final Document responseDoc) {
		Map<String, Object> responseMap = null;
		if (responseDoc != null) {
			final Element elemPagecontent = responseDoc.getElementById("pagecontent");
			if (elemPagecontent != null) {
				final Element header = elemPagecontent.select("span").first();
				if (header != null) {
					final File file = new File("H:\\work\\tmp\\" + header.text().replace("\"", "") + ".txt");
					try {
						final FileWriter fw = new FileWriter(file.getAbsoluteFile());
						final BufferedWriter bw = new BufferedWriter(fw);
						final StringBuilder sb = new StringBuilder();
						final Element elemMain = responseDoc.getElementById("main");
						if (elemMain != null) {
							final Element elemTr = elemMain.select("tr").first();
							if (elemTr != null) {
								final Elements elementsTds = elemTr.select("td");
								if (elementsTds.size() >= 2) {
									final Element elementTd = elementsTds.get(1);
									if (elementTd != null) {
										responseMap = new HashMap<String, Object>();
										final String referenceUrl = elementTd.select("a").first().attr("href");
										updateMapWithDetails(baseUrl + referenceUrl, responseMap);
										bw.write("imgurl : " + responseMap.get(IMediaDetailsUpdater.imageUrl) + "\r\n");
										bw.write("refernceUrl : " + responseMap.get(IMediaDetailsUpdater.referenceUrl) + "\r\n");
										bw.write("releaseDate : " + responseMap.get(IMediaDetailsUpdater.releaseDate) + "\r\n");
										bw.write("rating : " + responseMap.get(IMediaDetailsUpdater.rating) + "\r\n");
										bw.write("casts : " + responseMap.get(IMediaDetailsUpdater.cast) + "\r\n");
										bw.close();
									}
								}
							}
						}
					} catch (final FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (final IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return responseMap;
	}

	private void updateMapWithDetails(final String url, final Map<String, Object> responseMap) {
		try {
			final Document webpageDoc = Jsoup.connect(url).userAgent(browser).timeout(timeout).get();
			if (webpageDoc != null) {
				final Element mainElem = webpageDoc.getElementById("title-overview-widget");
				final Element titleElem = webpageDoc.getElementById("titleCast");
				if (mainElem != null) {
					Element nextElem = fetchElementBasedOnTagsAndAttributes(mainElem, "img", "src", null, null, true);
					String imgUrl = null;
					if (nextElem != null) {
						imgUrl = nextElem.attr("src");
					}
					nextElem = fetchElementBasedOnTagsAndAttributes(mainElem, "meta", "content", null, null, true);
					String releaseDate = null;
					if (nextElem != null) {
						releaseDate = nextElem.attr("content");
					}
					nextElem = fetchElementBasedOnTagsAndAttributes(mainElem, "span", "itemprop", "ratingValue", null, true);
					String rating = null;
					if (nextElem != null) {
						rating = nextElem.text();
					}
					final List<String> castList = new ArrayList<String>();
					fetchElementBasedOnTagsAndAttributes(titleElem, "span", "itemprop", "name", castList, false);

					responseMap.put(IMediaDetailsUpdater.imageUrl, imgUrl);
					responseMap.put(IMediaDetailsUpdater.referenceUrl, url);
					responseMap.put(IMediaDetailsUpdater.releaseDate, releaseDate);
					responseMap.put(IMediaDetailsUpdater.rating, rating);
					responseMap.put(IMediaDetailsUpdater.cast, castList);
				}
			}
		} catch (final UnsupportedEncodingException e1) {
			log.info(e1.getMessage());
		} catch (final IOException e1) {
			log.info(e1.getMessage());
		}
	}

	private Element fetchElementBasedOnTagsAndAttributes(final Element mainElem, final String tag, final String attribute, final String attrvalue,
			final List<String> resultsList, final boolean limitOccurences) {
		Element foundElement = null;
		if (mainElem != null) {
			// base condition
			final String currentTag = mainElem.tagName();
			if (currentTag.equalsIgnoreCase(tag)) {
				final String attributeValue = mainElem.attr(attribute);
				if (attrvalue == null) {
					if (attributeValue != null && !"".equals(attributeValue)) {
						foundElement = mainElem;
					}
				} else {
					if (attributeValue != null && attributeValue.equalsIgnoreCase(attrvalue)) {
						foundElement = mainElem;
						if (resultsList != null) {
							resultsList.add(mainElem.text());
						}
					}
				}
			}
			// loop
			for (final Element child : mainElem.children()) {
				foundElement = fetchElementBasedOnTagsAndAttributes(child, tag, attribute, attrvalue, resultsList, limitOccurences);
				if (limitOccurences) {
					if (foundElement != null && foundElement.childNodeSize() <= 1) {
						break;
					}
				}

			}
		}
		// return
		return foundElement;
	}
}
