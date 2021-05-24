package de.nikos410.feedibus.util;

import de.nikos410.feedibus.model.RssFeed;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class RssFeedParser {

    private final JAXBContext jaxbContext;

    public RssFeedParser() {

        try {
            this.jaxbContext = JAXBContext.newInstance(RssFeed.class);
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    public RssFeed parse(String xml) {

        try {
            return tryParse(xml);
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    private RssFeed tryParse(String xml) throws JAXBException {

        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (RssFeed) unmarshaller.unmarshal(new InputSource(new StringReader(xml)));
    }
}
