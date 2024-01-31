package com.networking.meetingclient.util;

public class ModelUtil {

    public enum MeetingType {
        ONE_ON_ONE("One On One"),
        GROUP("Group");

        private final String type;

        MeetingType(String type) {
            this.type = type;
        }

        public static MeetingType fromString(String type) {
            for (MeetingType config : MeetingType.values()) {
                if (config.type.equalsIgnoreCase(type)) {
                    return config;
                }
            }
            throw new IllegalArgumentException("No enum constant " + MeetingType.class.getCanonicalName() + "." + type);
        }



        public String getType() {
            return type;
        }
    }
}
