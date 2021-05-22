package de.nikos410.feedibus;

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

    public static List<String> findRssUrls(String html) {

        final String[] lines = html.split("[\r\n]");
        return Arrays.stream(lines)
                .filter(WebsiteParser::isRssLine)
                .map(WebsiteParser::getUrlFromRssLine)
                .collect(toList());
    }

    private static boolean isRssLine(String htmlLine) {

        final Matcher matcher = RSS_LINE_PATTERN.matcher(htmlLine);
        return matcher.matches();
    }

    private static String getUrlFromRssLine(String rssLine) {

        final Matcher matcher = HREF_PATTERN.matcher(rssLine);
        if (matcher.matches()) {
            return matcher.group(1);
        } else {
            throw new IllegalArgumentException("RSS line contains no href tag: " + rssLine);
        }
    }
}
