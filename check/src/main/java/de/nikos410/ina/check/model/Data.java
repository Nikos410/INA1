package de.nikos410.ina.check.model;

public class Data {

    private static final String DEFAULT_VALUE = "Keine Angabe";

    private String name;
    private String redirectUrl;
    private String description;

    public String getName() {
        return getDefaultValueIfBlank(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRedirectUrl() {
        return getDefaultValueIfBlank(redirectUrl);
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getDescription() {
        return getDefaultValueIfBlank(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String getDefaultValueIfBlank(String value) {

        if (value == null || value.isBlank()) {
            return DEFAULT_VALUE;
        } else {
            return value;
        }
    }
}
