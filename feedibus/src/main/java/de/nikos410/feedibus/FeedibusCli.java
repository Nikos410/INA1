package de.nikos410.feedibus;

import de.nikos410.feedibus.util.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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
        final URI firstRssUri = rssUris.get(0);
        System.out.println("Downloading first RSS feed from " + firstRssUri);

        final RssFeedParser rssFeedParser = new RssFeedParser();
        websiteDownloader.downloadWebsite(firstRssUri)
                .thenApply(rssFeedParser::parse)
                .thenAccept(System.out::println).join();
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

}
