package FunStuff.Spotify;

import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AlbumServlet", value = "/albums")
public class AlbumServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String artistId = request.getParameter("artist");
            String artists = request.getParameter("artists");
            Album[] albums = MySpotify.getAlbums(artistId);
            request.setAttribute("albums", albums);
            request.setAttribute("artists", artists);
            request.getRequestDispatcher("WEB-INF/Funstuff/album.jsp").forward(request, response);
        }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
