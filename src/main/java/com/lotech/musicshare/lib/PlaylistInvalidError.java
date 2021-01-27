package com.lotech.musicshare.lib;

public class PlaylistInvalidError extends Exception {
    public PlaylistInvalidError(String errMessage) {
        super(errMessage);
    }
}
