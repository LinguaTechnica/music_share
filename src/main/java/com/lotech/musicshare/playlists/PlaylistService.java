package com.lotech.musicshare.playlists;

import com.lotech.musicshare.lib.MusicShareServiceException;
import com.lotech.musicshare.lib.PlaylistInvalidError;
import com.lotech.musicshare.lib.PlaylistNotFoundError;
import com.lotech.musicshare.songs.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaylistService {
    private final PlaylistRepository repository;

    @Autowired
    public PlaylistService (PlaylistRepository repository) {
        this.repository = repository;
    }

    public Playlist save(Playlist playlist) throws PlaylistInvalidError {
        if (playlist.getTitle() == null) {
            throw new PlaylistInvalidError("A title is required.");
        }
        return repository.save(playlist);
    }

    public Playlist getById(Long playlistId) throws PlaylistNotFoundError {
        try {
            return repository.findById(playlistId).get();
        } catch (Exception exc) {
            throw new PlaylistNotFoundError("Playlist does not exist.");
        }
    }

    public Playlist addSong(Long playlistId, Song song) throws MusicShareServiceException {
        Playlist playlist = getById(playlistId);
        playlist.addSong(song);
        return save(playlist);
    }

    public Playlist removeSong(Long playlistId, Song song) throws MusicShareServiceException {
        Playlist playlist = getById(playlistId);
        playlist.removeSong(song);
        return save(playlist);
    }
}
