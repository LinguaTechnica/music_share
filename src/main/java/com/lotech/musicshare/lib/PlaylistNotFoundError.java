package com.lotech.musicshare.lib;

public class PlaylistNotFoundError extends Exception {
    public PlaylistNotFoundError(String errorMsg) {
        super(errorMsg);
    }
}
