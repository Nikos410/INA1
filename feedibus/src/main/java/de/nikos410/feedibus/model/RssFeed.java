package de.nikos410.feedibus.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "rss")
@XmlAccessorType(XmlAccessType.FIELD)
public class RssFeed {

    @XmlElement(name = "channel")
    private List<RssChannel> channels;

    public List<RssChannel> getChannels() {
        return channels;
    }

    public void setChannels(List<RssChannel> channels) {
        this.channels = channels;
    }

    @Override
    public String toString() {
        return "RssFeed{" +
                "channels=" + channels +
                '}';
    }
}
