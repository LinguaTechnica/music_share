package com.lotech.musicshare;

import com.lotech.musicshare.lib.PlaylistInvalidError;
import com.lotech.musicshare.playlists.Playlist;
import com.lotech.musicshare.playlists.PlaylistService;
import com.lotech.musicshare.songs.Song;
import com.lotech.musicshare.songs.SongRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PlaylistControllerTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    PlaylistService service;
    @Autowired
    SongRepository songRepository;

    @BeforeEach
    void setup() {
        Song song = new Song("The Song");
        song.setId(1L);
        songRepository.save(song);
    }

    @Test
    void getPlaylistById_validId() throws Exception {
        Playlist playlist = new Playlist("Good Songs");
        service.save(playlist);
        mvc.perform(get("/api/v1/playlists/{id}", playlist.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Good Songs"));
    }

    @Test
    void getPlaylistById_invalidId() throws Exception {
        mvc.perform(get("/api/v1/playlists/30"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createNewPlaylist_validTitle() throws Exception {
        String playlistJson = "{\"title\": \"My List\"}";
        mvc.perform(post("/api/v1/playlists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(playlistJson)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void createNewPlaylist_invalidTitle() throws Exception {
        String playlistJson = "{}";
        mvc.perform(post("/api/v1/playlists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(playlistJson)
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    void addSongToPlaylist_validSong() throws Exception {
        Playlist playlist = new Playlist("Love to Test You");
        String songJson = "{\"id\": \"1\"}";

        service.save(playlist);
        mvc.perform(put("/api/v1/playlists/{id}/songs", playlist.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(songJson)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Love to Test You"))
                .andExpect(jsonPath("$.songs", hasSize(1)));
    }

    @Test
    void addSongToPlaylist_invalidSong() throws Exception {
        Playlist playlist = new Playlist("Love to Test You");
        String songJson = "{\"id\": \"100\"}";

        service.save(playlist);
        mvc.perform(put("/api/v1/playlists/{id}/songs", playlist.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(songJson)
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    void removeSongFromPlaylist_validSong() throws Exception {
        Playlist playlist = createPlaylistWithSongs(2);
        Long songId = playlist.getSongs().get(0).getId();
        String songJson = String.format("{\"id\": \"%s\"}", songId);

        mvc.perform(delete("/api/v1/playlists/{id}/songs", playlist.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(songJson)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.songs", hasSize(1)));
    }

    @Test
    void removeSongFromPlaylist_invalidSong() throws Exception {
        Playlist playlist = createPlaylistWithSongs(2);
        String songJson = "{\"id\": \"100\"}";

        mvc.perform(delete("/api/v1/playlists/{id}/songs", playlist.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(songJson)
        )
                .andExpect(status().isBadRequest());
    }

    // Test Utils =========================================================
    public Playlist createPlaylistWithSongs(int songCount) throws PlaylistInvalidError {
        Playlist playlist = new Playlist("My Playlist");
        for (int i=0; i < songCount; i++) {
            Song song = songRepository.save(new Song());
            playlist.addSong(song);
        }
        return service.save(playlist);
    }
}
