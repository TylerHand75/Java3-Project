<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="se.michaelthelin.spotify.model_objects.specification.Track" %>
<%
  String trackId = request.getParameter("trackId");
  String trackName = request.getParameter("trackName");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Spotify Music App</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  <link rel="stylesheet" href="styles/spotify.css">
  <script src="https://open.spotify.com/embed-podcast/iframe-api/v1" async></script>
  <script src="https://sdk.scdn.co/spotify-player.js"></script>
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
<div class="container">
  <div class="row">
    <div class="col-sm-8 offset-sm-2">
      <h1><%=trackName%></h1>
      <iframe id="spotify-player" src="https://open.spotify.com/embed/track/<%=trackId%>" width="100%" height="150px" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>
