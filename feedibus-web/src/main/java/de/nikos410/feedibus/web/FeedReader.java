package de.nikos410.feedibus.web;

import de.nikos410.feedibus.web.model.feed.RssChannel;
import de.nikos410.feedibus.web.model.feed.RssFeed;
import de.nikos410.feedibus.web.model.feed.RssItem;
import de.nikos410.feedibus.web.util.RssFeedParser;
import de.nikos410.feedibus.web.util.UriUtils;
import de.nikos410.feedibus.web.util.WebsiteDownloader;
import de.nikos410.feedibus.web.util.WebsiteParser;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FeedReader {

    private final WebsiteDownloader websiteDownloader= new WebsiteDownloader();

    public List<RssFeed> findFeeds(String websiteUrl) {

        final List<URI> rssUris = findRssUris(buildUriFromUserInput(websiteUrl));
        return rssUris.stream()
                .map(this::readRssFeed)
                .map(CompletableFuture::join)
                .toList();
    }

    private URI buildUriFromUserInput(String userInput) {

        try {
            return tryBuildUriFromUserInput(userInput);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid URI entered.", e);
        }
    }

    private URI tryBuildUriFromUserInput(String userInput) throws URISyntaxException {

        return URI.create(UriUtils.addProtocolIfNecessary(userInput, "https"));
    }


    private List<URI> findRssUris(URI websiteUri) {

        return websiteDownloader.downloadWebsite(websiteUri)
                .thenApply(html -> WebsiteParser.findRssUris(html, websiteUri))
                .join();
    }

    private CompletableFuture<RssFeed> readRssFeed(URI rssFeedUri) {

        return websiteDownloader.downloadWebsite(rssFeedUri)
                .thenApply(RssFeedParser::parse);
    }
}
