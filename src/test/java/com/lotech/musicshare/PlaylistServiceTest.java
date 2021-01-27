package com.lotech.musicshare;

import com.lotech.musicshare.lib.PlaylistInvalidError;
import com.lotech.musicshare.playlists.Playlist;
import com.lotech.musicshare.playlists.PlaylistRepository;
import com.lotech.musicshare.playlists.PlaylistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PlaylistServiceTest {
    Playlist playlist;
    PlaylistService service;

    @Autowired
    PlaylistRepository repository;

    @BeforeEach
    void setUp() {
        playlist = new Playlist("Good Songs");
        service = new PlaylistService(repository);
    }

    @Test
    void savePlaylist() throws PlaylistInvalidError {
        assertThat(playlist.getId()).isNull();
        service.save(playlist);
        assertThat(playlist.getId()).isNotNull();
    }

    @Test
    void getById() throws PlaylistInvalidError {
        service.save(playlist);
        Playlist response = service.getById(playlist.getId());
        assertThat(response.getTitle()).isEqualTo(playlist.getTitle());
    }
}
