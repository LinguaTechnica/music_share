# Music Share

Music Share is a playlist service where users can create music lists and share them with others.

## Instructions

Use the stories and acceptance criteria to develop the Playlist Service.

1. Fork [this repository]().
1. Share your new repository with your group. One repo per group!
1. Submit the link to your repository below.
1. Submit the URL to your live app.

### Requirements

The following features are required.

- Version your api
- Must have integration tests.
- Deploy your application.

## Acceptance Tests

- Playlists are empty on creation.
- Songs can be added to and removed from a playlist.
- Playlists are named.
- Playlists are unique.
- Playlists can have an unlimited number of songs.

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
