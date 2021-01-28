package com.lotech.musicshare.playlists;

import com.lotech.musicshare.lib.PlaylistInvalidError;
import com.lotech.musicshare.lib.PlaylistNotFoundError;
import com.lotech.musicshare.lib.SongNotFoundException;
import com.lotech.musicshare.songs.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/playlists")
public class PlaylistController {
    private final PlaylistService service;

    @Autowired
    public PlaylistController (PlaylistService service) {
        this.service = service;
    }

    @GetMapping("{playlistId}")
    public Playlist getPlaylistById(@PathVariable Long playlistId) throws PlaylistNotFoundError {
        try {
            return service.getById(playlistId);
        } catch (NoSuchElementException exc) {
            throw new PlaylistNotFoundError("Playlist was not found.");
        }
    }

    @PostMapping
    public Playlist createNewPlaylist(@RequestBody Playlist playlist) throws PlaylistInvalidError {
        try {
            return service.save(playlist);
        } catch (Exception exc) {
            throw new PlaylistInvalidError(exc.getMessage());
        }
    }

    @DeleteMapping("{playlistId}/songs")
    public Playlist removeSongFromPlaylist(@PathVariable Long playlistId, @RequestBody Song song) throws SongNotFoundException {
        try {
            return service.removeSong(playlistId, song);
        } catch(Exception exc) {
            throw new SongNotFoundException(exc.getMessage());
        }
    }

    @PutMapping("{playlistId}/songs")
    public Playlist addSongToPlaylist(@PathVariable Long playlistId, @RequestBody Song song) throws SongNotFoundException {
        try {
            return service.addSong(playlistId, song);
        } catch (Exception exc) {
            throw new SongNotFoundException((exc.getMessage()));
        }
    }
}
