package FunStuff.Spotify;

import com.neovisionaries.i18n.CountryCode;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsAlbumsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchArtistsRequest;

import javax.sound.midi.Track;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class MySpotify {
    private static String accessToken;
    public static String getAccessToken() {
        Dotenv dotenv = Dotenv.load();
        String clientId = dotenv.get("SPOTIFY_CLIENT_ID");
        String clientSecret = dotenv.get("SPOTIFY_CLIENT_SECRET");
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();
        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
                .build();
        ClientCredentials clientCredentials = null;
        try {
            clientCredentials = clientCredentialsRequest.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SpotifyWebApiException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        accessToken = clientCredentials.getAccessToken();
        return accessToken;
    }

    public static Artist[] searchArtists(String q) {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(getAccessToken())
                .build();
        SearchArtistsRequest searchArtistsRequest = spotifyApi.searchArtists(q)
                .market(CountryCode.US)
//          .limit(10)
//          .offset(0)
//          .includeExternal("audio")
                .build();
        Artist[] artists = null;
        try {
            final CompletableFuture<Paging<Artist>> pagingFuture = searchArtistsRequest.executeAsync();

            // Thread free to do other tasks...

            // Example Only. Never block in production code.
            final Paging<Artist> artistPaging = pagingFuture.join();
            artists = artistPaging.getItems();
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
        return artists;
    }

    public static Album[] getAlbums(String artistID){
        SpotifyApi spotifyApi = getSpotifyApi();
        Album[] albums = null;

        GetArtistsAlbumsRequest getArtistsAlbumsRequest = spotifyApi.getArtistsAlbums(artistID)
                .album_type("album")
                .limit(50)
                .offset(0)
                .build();

        try {
            Paging<AlbumSimplified> albumPaging = getArtistsAlbumsRequest.execute();
            ArrayList<Album> albumList = new ArrayList<>();

            for (AlbumSimplified albumSimplified : albumPaging.getItems()) {
                String albumID = albumSimplified.getId();
                Album album = spotifyApi.getAlbum(albumID).build().execute();
                albumList.add(album);
            }

            albums = albumList.toArray(new Album[0]);

        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return albums;
    }

    private static SpotifyApi getSpotifyApi() {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(getAccessToken())
                .build();
        return spotifyApi;
    }


    public static TrackSimplified[] getTracks(String albumID){
        SpotifyApi spotifyApi = getSpotifyApi();
        TrackSimplified[] tracks = null;

        try {
            Paging<TrackSimplified> trackPaging = spotifyApi.getAlbumsTracks(albumID)
                    //.market(CountryCode.US)
                    .limit(50)
                    .offset(0)
                    .build().execute();

            tracks = trackPaging.getItems();

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return tracks;
    }


}

