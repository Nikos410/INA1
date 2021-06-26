package de.nikos410.feedibus.util;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

public final class WebsiteParser {

    private static final Pattern RSS_LINE_PATTERN = Pattern.compile("^<link.*type=\"application/rss\\+xml\".*>$");
    private static final Pattern HREF_PATTERN = Pattern.compile("^<link.*href=\"(\\S+)\".*>$");

    private WebsiteParser() {
    }

    public static List<URI> findRssUris(String html, URI websiteUri) {

        final String[] lines = html.split("[\r\n]");
        return Arrays.stream(lines)
                .filter(WebsiteParser::isRssLine)
                .map(rssLine -> WebsiteParser.getUriFromRssLine(rssLine, websiteUri))
                .collect(toList());
    }

    private static boolean isRssLine(String htmlLine) {

        final Matcher matcher = RSS_LINE_PATTERN.matcher(htmlLine);
        return matcher.matches();
    }

    private static URI getUriFromRssLine(String rssLine, URI websiteUri) {

        final Matcher matcher = HREF_PATTERN.matcher(rssLine);
        if (matcher.matches()) {
            final String rssUrl = matcher.group(1);
            return ensureAbsoluteUri(websiteUri, rssUrl);
        } else {
            throw new IllegalArgumentException("RSS line contains no href tag: " + rssLine);
        }
    }

    private static URI ensureAbsoluteUri(URI baseUri, String possibleRelativeUrl) {

        final URI uri = URI.create(possibleRelativeUrl);
        if (uri.isAbsolute()) {
            return uri;
        } else {
            return baseUri.resolve(possibleRelativeUrl);
        }
    }
}
