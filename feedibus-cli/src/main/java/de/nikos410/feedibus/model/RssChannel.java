package de.nikos410.feedibus.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class RssChannel {

    @XmlElement
    private String title;
    @XmlElement
    private String description;
    @XmlElement(name = "item")
    private List<RssItem> items;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RssItem> getItems() {
        return items;
    }

    public void setItems(List<RssItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "RssChannel{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", items=" + items +
                '}';
    }
}
