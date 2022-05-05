package com.enspy.malaika.social.entities.actor;



public enum ArtistType {

    DJ("DJ"), MUSICIAN("MUSICIAN"), BITMAKER("BITMAKER"), PIANIST("PIANIST"),
    VIOLINIST("VIOLINIST"), GUITAR("GUITAR"), RAPPEUR("RAPPEUR"), SLAM("SLAM");

    private final String value;

    ArtistType(String val) {

        this.value = val;
    }
}
