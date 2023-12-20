package com.networking.meetingclient.config;

public enum FxmlConfig {
    LOGIN("login"),
    MAIN("main"),
    REGISTER("register");

    private final String dir;


    FxmlConfig(String dir) {
        this.dir = dir;
    }

    public static FxmlConfig fromString(String dir) {
        for (FxmlConfig config : FxmlConfig.values()) {
            if (config.dir.equalsIgnoreCase(dir)) {
                return config;
            }
        }
        throw new IllegalArgumentException("No enum constant " + FxmlConfig.class.getCanonicalName() + "." + dir);
    }

    public String getFxmlPath() {
        return String.format("%s/index.fxml", dir);
    }

    public String getCssPath() {
        return String.format("%s/styles.css", dir);
    }

    public String getTitle() {
        return dir;
    }
}
