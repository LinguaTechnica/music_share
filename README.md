# Music Share

Music Share is a playlist service. Users can create music lists and share them with others.

## Best Practices

- Version your api

## Stories

As a user, I want to create music playlists from a selection of songs so that I can share them with my friends.

```gherkin
Given I'm a user
When I create a new playlist with a name
Then receive confirmation
And the playlist is empty.

Given I'm a user
When I create a playlist without a name
Then I receive a message that a name is required.
  
Given I have an empty playlist
When I add a song to it
Then my playlist has 1 song

Given I have a playlist with 2 songs
When I remove a song
Then my playlist has 1 song.
```
