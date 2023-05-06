package FunStuff.Spotify;

import com.neovisionaries.i18n.CountryCode;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchAlbumsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchArtistsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;

import java.io.IOException;
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


    public static AlbumSimplified[] getAlbum(String artistName){
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(getAccessToken()).build();

        SearchAlbumsRequest artistsAlbumsRequest = spotifyApi.searchAlbums(artistName)
                /*
                .album_type("album")
                .limit(10)
                .offset(0)
                .market(CountryCode.SE)
                 */
                .build();
        AlbumSimplified[] albums = null;
        try{
            final CompletableFuture<Paging<AlbumSimplified>> pagingFuture = artistsAlbumsRequest.executeAsync();

            //Other tasks

            final  Paging<AlbumSimplified> albumSimplifiedPaging =pagingFuture.join();

            albums = albumSimplifiedPaging.getItems();

            System.out.println("Total: " + albumSimplifiedPaging.getTotal());

        }catch (CompletionException e){
            System.out.println("Error: " + e.getCause().getMessage());

        }
        catch (CancellationException e){
            System.out.println("Async operation cancelled.");
        }




        return albums;
    }

    public static Track[] getTracks(String albumId){
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(getAccessToken()).build();

        SearchTracksRequest getSearchTracksRequest = spotifyApi.searchTracks(albumId)
                //.limit(10)
                //.offset(0)
                //.market(CountryCode.SE)
                .build();
        Track[] tracks = null;

        try {
            final CompletableFuture<Paging<Track>> pagingFuture = getSearchTracksRequest.executeAsync();



            final Paging<Track> trackPaging = pagingFuture.join();
            System.out.println("Total: " + trackPaging.getTotal());
            tracks = trackPaging.getItems();
        }
        catch (CompletionException e){
            System.out.println("Error: " + e.getCause().getMessage());

        }
        catch (CancellationException e){
            System.out.println("Async operation cancelled.");
        }
        return tracks;

    }

}

