package de.nikos410.feedibus;

import de.nikos410.feedibus.model.RssChannel;
import de.nikos410.feedibus.model.RssFeed;
import de.nikos410.feedibus.model.RssItem;
import de.nikos410.feedibus.util.CommandLineReader;
import de.nikos410.feedibus.util.RssFeedParser;
import de.nikos410.feedibus.util.UriUtils;
import de.nikos410.feedibus.util.WebsiteDownloader;
import de.nikos410.feedibus.util.WebsiteParser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static java.text.MessageFormat.format;

public class FeedibusCli {

    private final CommandLineReader commandLineReader;
    private final WebsiteDownloader websiteDownloader;

    public FeedibusCli(CommandLineReader commandLineReader) {
        this.commandLineReader = commandLineReader;
        this.websiteDownloader = new WebsiteDownloader();
    }

    public static void main(String[] args) throws IOException {
        try (final var commandLineReader = new CommandLineReader()) {
            final var feedibusCli = new FeedibusCli(commandLineReader);
            feedibusCli.run();
        }
    }

    public void run() {

        final URI websiteUri = getWebsiteUri();
        final List<URI> rssUris = findRssUris(websiteUri);

        if (rssUris.isEmpty()) {
            System.out.println("No RSS feed found.");
            return;
        }

        final int numberOfRssFeeds = rssUris.size();
        System.out.println(format("Found {0} RSS feed(s)", numberOfRssFeeds));
        if (numberOfRssFeeds > 1) {
            System.out.println("Showing first feed only.");
        }

        downloadAndPrintRssFeed(rssUris.get(0));
    }

    private URI getWebsiteUri() {

        final String userInput = commandLineReader.readLine("Please enter a website URI.");
        return buildUriFromUserInput(userInput);
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

    private void downloadAndPrintRssFeed(URI rssFeedUri) {

        websiteDownloader.downloadWebsite(rssFeedUri)
                .thenApply(RssFeedParser::parse)
                .thenAccept(this::printRssFeed)
                .join();
    }

    private void printRssFeed(RssFeed rssFeed) {

        final List<RssChannel> channels = rssFeed.getChannels();
        if (channels.isEmpty()) {
            System.out.println("No channel found in feed.");
            return;
        }

        final int numberOfChannels = channels.size();
        System.out.println(format("Found {0} channel(s).", numberOfChannels));

        if (numberOfChannels > 1) {
            System.out.println(format("Showing first channel only."));
        }

        printRssChannel(channels.get(0));
    }

    private void printRssChannel(RssChannel rssChannel) {

        System.out.println("========");
        System.out.println("Title: " + rssChannel.getTitle());
        System.out.println("Description: " + rssChannel.getDescription());

        final List<RssItem> items = rssChannel.getItems();
        System.out.println(items.size() + " items:");
        System.out.println(items.stream()
                .map(RssItem::getTitle)
                .map(title -> "- Title: " + title)
                .collect(Collectors.joining(String.format("%n"))));
    }
}
