package com.lotech.musicshare.lib;

public class SongNotFoundException extends Exception {
    public SongNotFoundException(String errMessage) {
        super(errMessage);
    }
}
