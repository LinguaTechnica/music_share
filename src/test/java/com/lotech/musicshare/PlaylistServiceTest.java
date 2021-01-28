package com.lotech.musicshare;

import com.lotech.musicshare.lib.MusicShareServiceException;
import com.lotech.musicshare.lib.PlaylistInvalidError;
import com.lotech.musicshare.lib.PlaylistNotFoundError;
import com.lotech.musicshare.playlists.Playlist;
import com.lotech.musicshare.playlists.PlaylistRepository;
import com.lotech.musicshare.playlists.PlaylistService;
import com.lotech.musicshare.songs.Song;
import com.lotech.musicshare.songs.SongRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

/* Note that some tests may overlap with integration tests in other folders.
    It's provided as an example of additional tests.
**/
@DataJpaTest
public class PlaylistServiceTest {
    Playlist playlist;
    PlaylistService service;

    @Autowired
    PlaylistRepository repository;
    @MockBean
    SongRepository songRepository;

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
    void getById_exists() throws MusicShareServiceException {
        service.save(playlist);
        Playlist response = service.getById(playlist.getId());
        assertThat(response.getTitle()).isEqualTo(playlist.getTitle());
    }

    @Test
    void getById_notExists() {
        assertThatThrownBy(() -> service.getById(31L))
                .isInstanceOf(PlaylistNotFoundError.class);
    }

    @Test
    void addSong() throws MusicShareServiceException {
        service.save(playlist);
        Song song = mock(Song.class);
        Playlist response = service.addSong(playlist.getId(), song);
        assertThat(response.getSongs()).hasSize(1);
    }

    @Test
    void removeSong() throws MusicShareServiceException {
        Song song = mock(Song.class);
        playlist.addSong(song);
        service.save(playlist);
        assertThat(playlist.getSongs()).hasSize(1);

        service.removeSong(playlist.getId(), song);
        assertThat(playlist.getSongs()).hasSize(0);
    }
}
