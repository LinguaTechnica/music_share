package com.lotech.musicshare.playlists;

import com.lotech.musicshare.songs.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/playlists")
public class PlaylistController {
    private PlaylistService service;

    @Autowired
    public PlaylistController (PlaylistService service) {
        this.service = service;
    }

    @GetMapping("{playlistId}")
    public Playlist getPlaylistById(@PathVariable Long playlistId) {
        return service.getById(playlistId);
    }

    @PutMapping("{playlistId}")
    public Playlist addSongToPlaylist(@PathVariable Long playlistId, @RequestBody Song song) {
        Playlist playlist = service.getById(playlistId);
        playlist.addSong(song);
        return service.save(playlist);
    }

    @DeleteMapping("{playlistId}/songs")
    public Playlist removeSongFromPlaylist(@PathVariable Long playlistId, @RequestBody Song song) {
        Playlist playlist = service.getById(playlistId);
        playlist.removeSong(song);
        return service.save(playlist);
    }
}
