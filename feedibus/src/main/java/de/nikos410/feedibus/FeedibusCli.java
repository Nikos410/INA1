package de.nikos410.feedibus;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

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

        final String websiteUrl = getWebsiteUrl();
        final List<String> rssUrls = getRssUrls(websiteUrl);
        System.out.println(rssUrls);
    }

    private String getWebsiteUrl() {

        final String userEnteredUrl = commandLineReader.readLine("Please enter a website URL.");
        return ensureHttps(userEnteredUrl);
    }

    private String ensureHttps(String url) {

        if (url.startsWith("https://")) {
            return url;
        } if (url.startsWith("http://")) {
            return url.replace("http://", "https://");
        } else {
            return "https://" + url;
        }
    }

    private List<String> getRssUrls(String websiteUrl) {

        return websiteDownloader.downloadWebsite(websiteUrl)
                .thenApply(html -> WebsiteParser.findRssUrls(html, websiteUrl))
                .join();
    }

}
