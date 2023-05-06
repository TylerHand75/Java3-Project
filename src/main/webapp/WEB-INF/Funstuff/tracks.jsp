<%@ page import="se.michaelthelin.spotify.model_objects.specification.Track" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String albumName = (String) request.getAttribute("albumName");
    Track[] tracks = (Track[]) request.getAttribute("tracks");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tracks</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" href="styles/spotify.css">
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
    <div class="container-fluid">
        <a class="navbar-brand" href="artist"><strong>Spotify Music App</strong></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
            </ul>

        </div>
        <form><!-- https://stackoverflow.com/questions/4629606/input-type-back-button -->
            <input class="btn btn-dark" type="button" value="Back" onclick="history.back()">
        </form>
    </div>
</nav>

<section class="py-5 text-center container">
    <div class="row py-lg-2">
        <div class="col-lg-6 col-md-8 mx-auto">
            <h1 class="fw-light"><%=albumName%>'s Tracks</h1>
            <p class="lead text-muted">Click the track's name below to listen to them</p>
        </div>
    </div>
</section>

<main class="container">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Song Name</th>
            <th class="text-center" scope="col">Popularity</th>
            <th class="text-end" scope="col">Duration</th>
            <th class="text-end" scope="col">Album</th>
            <th class="text-end" scope="col">Release Date</th>
            <th class="text-end" scope="col"> Album type</th>
            <th class="text-end" scope="col">Track Number</th>
        </tr>
        </thead>
        <tbody class="table-group-divider">
        <% for (Track track : tracks) { %>
        <tr>
            <td>
                <a class="cool-text" href="song?trackId=<%= track.getId()%>&trackName=<%=track.getName()%>&albumName=<%=albumName%>"/><%=track.getName()%>
            </td>
            <td class="text-center"><%=track.getPopularity()%>%</td>
            <td class="text-end"><%=track.getDurationMs() / 60000 %>  Minutes</td>
            <td class="text-end"><%=track.getAlbum().getName()%></td>
            <td class="text-end"><%=track.getAlbum().getReleaseDate()%></td>
            <td class="text-end"><%=track.getAlbum().getAlbumType()%></td>
            <td class="text-end"><%=track.getTrackNumber()%></td>
        </tr>
        <% } %>
        </tbody>
    </table>
</main>
<div id="track-player"></div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>