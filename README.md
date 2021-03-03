# Music Share
[![Gradle Build and Tests](https://github.com/LinguaTechnica/music_share/actions/workflows/gradle.yml/badge.svg)](https://linguatechnica.github.io/music_share/index.html) | [![Heroku CICD Pipeline](https://github.com/LinguaTechnica/music_share/actions/workflows/heroku.yml/badge.svg)](https://musicshare-app.herokuapp.com/swager-ui.html)

Music Share is a playlist service where users can create music lists and share them with others.

### Interactive Demo

- View test coverage report: https://linguatechnica.github.io/music_share/index.html
- Live application: https://musicshare-app.herokuapp.com/swager-ui.html

## Instructions

Use the stories and acceptance criteria to develop the Playlist Service.

1. Fork [this repository]().
1. Share your new repository with your group. One repo per group!
1. Submit the link to your repository below.
1. Submit the URL to: your Docker Hub image and/or your live app.

### Requirements

The following features are required.

- Version your api
- Must have integration tests.
- `Dockerfile`
- Deploy your application.

## Acceptance Tests

- Playlists are empty on creation.
- Playlists have many songs.
- Songs can be added to and removed from a playlist.
- Playlists are named.
- Playlists are unique.

```gherkin
When a playlist is created with a name
Then a confirmation is returned that it was successful.
And the playlist is empty.

When a playlist is created without a name
Then a message is returned that a name is required.
  
Given an empty playlist
When an song is added
Then the playlist has 1 song

Given a playlist with 2 songs
When a song is removed
Then the playlist has 1 song.

Given a song doesn't exist
  And a playlist with 1 song
When the song is added to the playlist
Then the playlist still has 1 song
And a message is returned that the song doesn't exist.
```
