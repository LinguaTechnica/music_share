package com.lotech.musicshare.lib;

public class PlaylistNotFoundError extends MusicShareServiceException {
    public PlaylistNotFoundError(String errorMsg) {
        super(errorMsg);
    }
}
