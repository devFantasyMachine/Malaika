package com.enspy.malaika.social.entities.actor;

public enum UserType {

    AUDITOR("AUDITOR"), ARTIST("ARTIST"), MODERATOR("MODERATOR");

    private final String value;

    UserType(String val) {

        this.value = val;
    }
}
