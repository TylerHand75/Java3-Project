<%@ page import="se.michaelthelin.spotify.model_objects.specification.Album" %>
<%@ page import="java.util.List" %>
<%@ page import="FunStuff.Spotify.MySpotify" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Album[] albums = (Album[]) request.getAttribute("albums");
    if (albums == null) {
        MySpotify spotify = new MySpotify();
        albums = spotify.getAlbums("artist");
        request.setAttribute("albums", albums);
    }
%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Albums</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<h1>Albums</h1>
<table>
    <%
        Album[] albums1 = (Album[]) request.getAttribute("album");
        if (albums1 != null && albums1.length > 0) {
            for (Album album : albums1) { %>
    <tr>
        <td><img src="<%= album.getImages() %>"></td>
        <td><a href="tracks?albumId=<%= album.getId() %>"><%= album.getName() %></a></td>

    </tr>
<%
            }
        } else { %> <tr>
        <td>No albums found</td>
    </tr>

    <% } %>

    In this updated code block, we first check if the albums attribute is not null and has at least one album using the if statement. If the condition is true, we then iterate over the Album array and output each album's properties using JSP expressions. If the condition is false, we output a message stating that no albums were found using an else statement.

</table>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>