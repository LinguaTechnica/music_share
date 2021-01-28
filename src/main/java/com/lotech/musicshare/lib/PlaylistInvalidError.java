package com.lotech.musicshare.lib;

public class PlaylistInvalidError extends MusicShareServiceException {
    public PlaylistInvalidError(String errMessage) {
        super(errMessage);
    }
}
