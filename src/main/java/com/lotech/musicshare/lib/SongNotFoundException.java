package com.lotech.musicshare.lib;

public class SongNotFoundException extends MusicShareServiceException {
    public SongNotFoundException(String errMessage) {
        super(errMessage);
    }
}
