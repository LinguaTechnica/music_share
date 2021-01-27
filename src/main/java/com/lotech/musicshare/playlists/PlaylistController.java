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
            Playlist playlist = service.getById(playlistId);
            return playlist;
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

    @PutMapping("{playlistId}/songs")
    public Playlist addSongToPlaylist(@PathVariable Long playlistId, @RequestBody Song song) throws SongNotFoundException {
        try {
            Playlist playlist = service.getById(playlistId);
            playlist.addSong(song);
            return service.save(playlist);
        } catch(Exception exc) {
            throw new SongNotFoundException(exc.getMessage());
        }
    }

    @DeleteMapping("{playlistId}/songs")
    public Playlist removeSongFromPlaylist(@PathVariable Long playlistId, @RequestBody Song song) throws SongNotFoundException {
        try {
            Playlist playlist = service.getById(playlistId);
            playlist.removeSong(song);
            return service.save(playlist);
        } catch (Exception exc) {
            throw new SongNotFoundException((exc.getMessage()));
        }
    }
}
