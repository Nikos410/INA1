package de.nikos410.feedibus.web.util;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class UriUtils {

    private static final Pattern PROTOCOL_PATTERN = Pattern.compile("^(\\S+)://\\S+");

    private UriUtils() {
    }

    public static String addProtocolIfNecessary(String userInput, String protocol) {

        final Optional<String> existingProtocol = getProtocolFromUserInput(userInput);
        if (existingProtocol.isPresent()) {
            return userInput;
        } else {
            return "https://" + userInput;
        }
    }

    private static Optional<String> getProtocolFromUserInput(String userInput) {

        final Matcher matcher = PROTOCOL_PATTERN.matcher(userInput);
        if (matcher.matches()) {
            return Optional.of(matcher.group(1));
        } else {
            return Optional.empty();
        }
    }
}
