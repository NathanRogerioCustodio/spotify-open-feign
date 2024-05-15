package br.com.nathan.spotify.controllers;

import br.com.nathan.spotify.client.Album;
import br.com.nathan.spotify.client.AlbumSpotifyClient;
import br.com.nathan.spotify.client.AuthSpotifyClient;
import br.com.nathan.spotify.client.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spotify/api")
public class albumController {

    private final AuthSpotifyClient authSpotifyClient;

    private final AlbumSpotifyClient albumSpotifyClient;

    public albumController(AuthSpotifyClient authSpotifyClient, AlbumSpotifyClient albumSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
        this.albumSpotifyClient = albumSpotifyClient;
    }

    @GetMapping("/albums")
    public ResponseEntity<List<Album>>helloWorld(){

        var request = new LoginRequest(
                "client_credentials",
                "036238fe15c5422b8daa4259659a1d06",
                "197c7b58fbbe498085a63fb0d28f0bdb"
        );

        var token = authSpotifyClient.login(request).getAccessToken();

        var response = albumSpotifyClient.getReleases("Bearer " + token);

        return ResponseEntity.ok(response.getAlbums().getItems());
    }
}
