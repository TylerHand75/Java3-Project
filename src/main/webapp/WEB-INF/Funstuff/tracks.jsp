<%@ page import="se.michaelthelin.spotify.model_objects.specification.Track" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="FunStuff.Spotify.MySpotify" %>
<%@ page import="se.michaelthelin.spotify.model_objects.specification.AlbumSimplified" %>
<%@ page import="se.michaelthelin.spotify.model_objects.specification.Album" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MySpotify spotify = new MySpotify();
    String albumId = request.getParameter("albumId");
    String artistName = request.getParameter("artistName");
    Track[] tracks = spotify.getTracks(albumId);
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Album Tracks</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
    <div class="container-fluid">
        <a class="navbar-brand" href="artist"><strong>Spotify Music App</strong></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
            </ul>

        </div>
        <form> <!-- https://stackoverflow.com/questions/4629606/input-type-back-button -->
            <input class="btn btn-dark" type="button" value="Back" onclick="history.back()">
        </form>
    </div>
</nav>
<section class="py-5 text-center container">
    <div class="row py-lg-2">
        <div class="col-lg-6 col-md-8 mx-auto">
            <h1 class="fw-light"><%=request.getParameter("albumName")%> Tracks</h1>
        </div>
    </div>
</section>
<main class="container">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Track Number</th>
            <th scope="col">Track Name</th>
            <th scope="col">Duration (ms)</th>
            <th scope="col">Play</th>
        </tr>
        </thead>
        <tbody>
        <%
            for(int i=0; i<tracks.length; i++) {
                Track track = tracks[i];
                int trackNumber = i + 1;
        %>
        <tr>
            <td><%=trackNumber%></td>
            <td><%=track.getName()%></td>
            <td><%=track.getDurationMs()%></td>
            <td>
                <button class="btn btn-primary" onclick="playTrack('<%=track.getPreviewUrl()%>')">Play</button>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <audio id="audioPlayer"></audio>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>